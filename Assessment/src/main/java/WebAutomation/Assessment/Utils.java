package WebAutomation.Assessment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class Utils {
	public static Properties oprop,dprop;
	
	WebDriver driver=null;
	
	@Parameters({"browser"})
	@BeforeTest
    public void openBrowser(String browser) {
	
		if(browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
	        driver = new ChromeDriver();
	        System.out.println("launched chrome browse");
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        loadObjects();
        loadTestData();
    }
	
	@AfterTest
    public void CloseBrowser() {
    	driver.close();
    }
	
	private void loadObjects() {
		oprop = new Properties();
		try {
			oprop.load(new FileInputStream(new File("./src/test/java/object.properties")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// testdata
	private void loadTestData() {
		dprop = new Properties();
		try {
			dprop.load(new FileInputStream(new File("./src/test/java/data.properties")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void SendKeys(String locatorType, String locator, String data) {
		WebElement element = FindElement(locatorType, locator);
		element.sendKeys(data);
	}
	
	public void Click(String locatorType, String locator) {
		WebElement element = FindElement(locatorType, locator);
		element.click();
	}
	
	public String GetText(String locatorType, String locator) {
		WebElement element = FindElement(locatorType, locator);
		return element.getText().trim();
	}
	
	protected WebElement FindElement(String locatorType, String locator) {
		switch(locatorType) {
			case "xpath":
				return driver.findElement(By.xpath(locator));
			case "class":
				return driver.findElement(By.className(locator));
			case "name":
				return driver.findElement(By.name(locator));
			case "id":
				return driver.findElement(By.id(locator));
		}
		return null;
	}
	
	private By ByElement(String locatorType, String locator) {
		switch(locatorType) {
			case "xpath":
				return By.xpath(locator);
			case "class":
				return By.className(locator);
			case "name":
				return By.name(locator);
			case "id":
				return By.id(locator);
		}
		return null;
	}
	
	protected List<WebElement> FindElements(String locatorType, String locator) {
		switch(locatorType) {
			case "xpath":
				return driver.findElements(By.xpath(locator));
			case "class":
				return driver.findElements(By.className(locator));
			case "name":
				return driver.findElements(By.name(locator));
			case "id":
				return driver.findElements(By.id(locator));
		}
		return null;
	}
	
	protected void ScrollToElement(WebElement e) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", e);
	}
	
	protected void WaitUntil(String locatorType, String locator, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
    	wait.until(ExpectedConditions.visibilityOfElementLocated(ByElement(locatorType, locator)));
	}
}