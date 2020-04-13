package WebAutomation.Assessment;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class App 
{
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
		} else if (browser.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", "./drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
	        System.out.println("launched ie browse");
		}
    	
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
    }
    
    @Test
    public void test() throws InterruptedException {
    	driver.get("https://www.amazon.com/");
    	
    	driver.findElement(By.xpath("//*[@id=\"input_1\"]")).sendKeys("BhogaraM");
    	driver.findElement(By.xpath("//*[@id=\"input_2\"]")).sendKeys("B0ral123");
    	driver.findElement(By.xpath("//*[@id=\"submit\"]")).click();
    	Thread.sleep(3000);
    	
    	Actions action = new Actions(driver);
    	WebElement we = driver.findElement(By.id("nav-link-accountList"));
    	action.moveToElement(we).build().perform();
    	driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    	driver.findElement(By.className("nav-action-inner")).click();
    	Thread.sleep(2000);
    	driver.findElement(By.id("ap_email")).sendKeys("mounicabhogaraju1995@gmail.com");
    	driver.findElement(By.id("continue-announce")).click();
    	driver.findElement(By.id("ap_password")).sendKeys("mounil007");
    	
    	
    }
    
    @AfterTest
    public void CloseBrowser() {
    	driver.close();
    }
}
