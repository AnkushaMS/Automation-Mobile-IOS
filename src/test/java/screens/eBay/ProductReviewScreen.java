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

public class ProductReviewScreen extends ScreenBase {

	//Identify all the elments related to Product review screen
	
	@AndroidFindBy(id = "com.ebay.mobile:id/item_title")
	public WebElement itemName;
	
	@AndroidFindBy(id = "com.ebay.mobile:id/take_action")
	public WebElement reviewButton;
	


	public ProductReviewScreen(AndroidDriver<MobileElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver, 15, TimeUnit.SECONDS), this);

	}

	//Method to review product and navigate to checkout screen
	
	public void tapOnReview() throws Exception {
		
		
		Assert.assertEquals(ScreenBase.getProperty("ItemName"), itemName.getText().toString());
			
		if(reviewButton.isDisplayed()) {
			reviewButton.click();
		}else {
			Assert.fail("Review button is not displayed");
		}
	}
	
	
	
}
