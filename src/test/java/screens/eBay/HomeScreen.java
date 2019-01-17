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

public class HomeScreen extends ScreenBase {

	//Identify all the elements related to Home screen
	
	@AndroidFindBy(id = "com.ebay.mobile:id/button_sign_in")
	public WebElement homeScreenSignIn;

	@AndroidFindBy(id = "com.ebay.mobile:id/home")
	public WebElement menuButton;

	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='Settings']")
	public WebElement settings;
	
	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='Home']")
	public WebElement Home;

	@AndroidFindBy(xpath = "//android.widget.RelativeLayout/android.widget.TextView[@text='Country / region']")
	public WebElement countryTab;

	@AndroidFindBy(xpath = "//android.widget.Switch[@text='ON']")
	public WebElement onButton;

	@AndroidFindBy(xpath = "//android.widget.Switch[@text='OFF']")
	public WebElement offButton;

	@AndroidFindBy(xpath = "//android.widget.RelativeLayout/android.widget.TextView[@text='Country / region']")
	public WebElement countryRegionTab;

	@AndroidFindBy(id = "com.ebay.mobile:id/filter")
	public WebElement searchCountyField;

	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='Australia']")
	public WebElement countryName;

	@AndroidFindBy(className = "android.widget.ImageButton")
	public WebElement backArrow;
	
	@AndroidFindBy(id = "com.ebay.mobile:id/textview_sign_in_status")
	public WebElement signInUser;
	
	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='Sign out']")
	public WebElement signOut;
	
	@AndroidFindBy(id ="com.ebay.mobile:id/search_box")
	public WebElement searchField;
	
	@AndroidFindBy(id ="com.ebay.mobile:id/search_src_text")
	public WebElement searchBox;

	public HomeScreen(AndroidDriver<MobileElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver, 15, TimeUnit.SECONDS), this);

	}
	//Method to set country(Need to set as App is not available for India)
	
	public void setCounty() throws Exception {
		try {
			clickOnView(menuButton, "menu");
			clickOnView(settings, "Settings");
			clickOnView(countryTab, "Country Tab");
			if(onButton.isDisplayed()) {
				onButton.click();
			}
			clickOnView(countryRegionTab, "Country/Region Tab");
			if (searchCountyField.isDisplayed()) {
				searchCountyField.clear();
				searchCountyField.sendKeys("Australia");
			}

			clickOnView(countryName, "Chosen Country");
			clickOnView(backArrow, "Bcak Arrow button");

		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	private void clickOnView(WebElement element, String name) throws Exception {

		try {

			if (element.isDisplayed()) {
				element.click();
			}

		} catch (Exception e) {
			throw new Exception(name + " is not displayed");
		}

	}
	
	//Method to Navigate to login screen
	
	public void tapOnSignIn() throws Exception {
     waitForElementPresent(1000, "com.ebay.mobile:id/button_sign_in");
		if (homeScreenSignIn.isDisplayed()) {
			homeScreenSignIn.click();
		} else {
			Assert.fail("Sign In button is not displayed");
		}

	}
	
	//Method to validate if login was successful
	
	public void validateSignIn() throws Exception {
		
		try {
		clickOnView(menuButton, "Menu");
		clickOnView(signInUser, "LoggedIn user status");
	    Assert.assertTrue(signOut.isDisplayed(), "User is not logged in");
	    clickOnView(signInUser, "LoggedIn user status");
	    clickOnView(Home, "Home Screen buttton");
		}catch(Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	//Method to search for the Product
	
	public void searchForProduct(String key) throws Exception {
		try {
		clickOnView(searchField, "Search field");
		searchBox.clear();
		searchBox.click();
		searchBox.sendKeys(key);
		driver.pressKeyCode(66);
		}catch(Exception e) {
			Assert.fail(e.getMessage());
			
		}
		
	}
	
	

}
