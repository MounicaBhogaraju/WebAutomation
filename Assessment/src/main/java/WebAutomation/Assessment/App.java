package WebAutomation.Assessment;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class App extends Generic
{
	@Test
    public void test() throws InterruptedException {
    	LaunchApplication();
    	Login();
    	ClearCart();
    	SearchItem();
    	SelectItem();
    	ItemInfo itemInfo = ExtractItemInfo();
    	AddItemToCart();
    	ProceedToCheckout();
    	ItemInfo itemCheckoutInfo = ExtractCheckoutItemInfo();
    	Assert.assertEquals(itemInfo.title, itemCheckoutInfo.title, "titles are equal");
    	Assert.assertEquals(itemInfo.price, itemCheckoutInfo.price, "prices are equal");
    	Signout();
    }
}
