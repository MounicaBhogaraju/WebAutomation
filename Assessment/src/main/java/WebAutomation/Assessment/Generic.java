package WebAutomation.Assessment;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Generic extends Utils {
	protected void LaunchApplication() {
		//launch application
    	driver.get(dprop.getProperty("url"));
	}
	
	protected void Login() {
		//click the hello 
    	Click("id", oprop.getProperty("hello"));
    	
    	//entering the login credentials
    	SendKeys("id", oprop.getProperty("emailaddress"), dprop.getProperty("emailaddress"));
    	Click("xpath", oprop.getProperty("submitbutton"));
    	SendKeys("id", oprop.getProperty("password"), dprop.getProperty("password"));
    	Click("id", oprop.getProperty("signinButton"));
	}
	
	protected void ClearCart() {
		//deletig the items in the cart
    	Click("id",oprop.getProperty("carticon"));
    	try {
	    	List<WebElement> itemsincart= FindElements("xpath", oprop.getProperty("itemsincart"));
	    	for(int i=0;i<itemsincart.size();i++) {
	    		itemsincart.get(i).click();
	    	}
    	}catch(Exception e) {
    		System.out.println("there are no items in cart");
    	}
	}
	
	protected void SearchItem() {
		WaitUntil("id", oprop.getProperty("searchbox"), 10);
    	//entering the camera in search box
    	SendKeys("id", oprop.getProperty("searchbox"), dprop.getProperty("searchdata"));
    	Click("xpath", oprop.getProperty("searchicon"));
	}
	
	protected void SelectItem() {
		//listing the cameras in the amazon
    	List<WebElement> listofCameras=FindElements("xpath", oprop.getProperty("listofCameras"));
    	WebElement elementToAdd = listofCameras.get(Integer.parseInt(dprop.getProperty("randomElementToAdd")));
    	ScrollToElement(elementToAdd);
    	elementToAdd.click();
	}
	
	protected ItemInfo ExtractItemInfo() {
		//Exctracting the product title and price
		WaitUntil("id", oprop.getProperty("price"), 10);
		ItemInfo s = new ItemInfo(GetText("id", oprop.getProperty("producttilte")), GetText("id",oprop.getProperty("price")));
    	System.out.println("the product details " +s.title +"-"+ s.price);
    	return s;
	}
	
	protected void AddItemToCart() {
		//clicking on add to cart
		WaitUntil("xpath",oprop.getProperty("addtocart"), 3);
		ScrollToElement(FindElement("xpath",oprop.getProperty("addtocart")));
    	Click("xpath",oprop.getProperty("addtocart"));
	}
	
	protected void ProceedToCheckout() {
		//click on proceed to checkout
		WaitUntil("xpath",oprop.getProperty("proceedtocheckout"), 3);
    	Click("xpath", oprop.getProperty("proceedtocheckout"));
	}
	
	protected void Signout() {
		driver.navigate().to(dprop.getProperty("url"));
		//sign out
		WaitUntil("id", oprop.getProperty("hamburger"), 10);
		Click("id", oprop.getProperty("hamburger"));
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		ScrollToElement(FindElement("xpath", oprop.getProperty("signout")));
		Click("xpath", oprop.getProperty("signout"));
	}
	
	protected ItemInfo ExtractCheckoutItemInfo() {
		//click on deliverytoaddress
    	Click("xpath", oprop.getProperty("Deliverytoaddress"));
    	//Extracting the product information from checkout
    	
    	ItemInfo s = new ItemInfo(GetText("xpath", oprop.getProperty("producttitlefromcheckout")), GetText("xpath", oprop.getProperty("pricefromcheckout")));
    	return s;
	}
}
