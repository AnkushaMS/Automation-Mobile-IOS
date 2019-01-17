package screens.eBay;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import base.ScreenBase;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CheckOutScreen extends ScreenBase {
 //Identify the elements related to Checkout screen
	@AndroidFindBy(id = "com.ebay.mobile:id/item_title")
	public WebElement itemNameAtCheckOut;
	
	@AndroidFindBy(id = "com.ebay.mobile:id/item_price")
	public WebElement itemPriceAtCheckOut;
	


	public CheckOutScreen(AndroidDriver<MobileElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver, 15, TimeUnit.SECONDS), this);

	}


	public void validateItemDetails() throws Exception {
		waitForElementPresent(3000, "com.ebay.mobile:id/item_title");
		
		Double price = (Double) ScreenBase.getProperty("ItemPrice");
		Double screenPrice = Double.parseDouble(itemPriceAtCheckOut.getText().toString().replaceAll("[^\\d.]", ""));
		
		Assert.assertEquals(ScreenBase.getProperty("ItemName"), itemNameAtCheckOut.getText().toString());
		Assert.assertEquals(price, screenPrice);
	
	}
	
	
	
}
