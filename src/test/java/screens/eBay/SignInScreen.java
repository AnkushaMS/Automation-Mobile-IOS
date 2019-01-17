package screens.eBay;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.ScreenBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SignInScreen extends ScreenBase {

	//Identify the elements related to Login screen
	
	@AndroidFindBy(id = "com.ebay.mobile:id/sign_in_title")
	public WebElement signInTitle;

	@AndroidFindBy(id = "com.ebay.mobile:id/edit_text_username")
	public WebElement usernameTextField;

	@AndroidFindBy(id = "com.ebay.mobile:id/edit_text_password")
	public WebElement passwordTextField;

	@AndroidFindBy(id = "com.ebay.mobile:id/button_sign_in")
	public WebElement signInButton;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Link your eBay and Google accounts to sign in with one tap instead of a password']")
	public WebElement accountLinking;
	
	@AndroidFindBy(id = "com.ebay.mobile:id/button_google_deny")
	public WebElement noThanksButton;
	
	
	

	public SignInScreen(AndroidDriver<MobileElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver, 20, TimeUnit.SECONDS), this);

	}

	public void validateSignInScreen() {

	}

	public void enterUserName(String username) throws Exception {

		if (usernameTextField.isDisplayed()) {
			usernameTextField.clear();
			usernameTextField.click();
			usernameTextField.sendKeys(username);
		} else {
			Assert.fail("Username field is not diplayed");
		}
	}

	public void enterPassword(String password) throws Exception {

		if (passwordTextField.isDisplayed()) {
			passwordTextField.clear();
			passwordTextField.click();
			passwordTextField.sendKeys(password);
		} else {
			Assert.fail("Passowrd field is not diplayed");
		}
	}
	
	public void tapOnLogin() throws Exception {

		if (signInButton.isDisplayed()) {
			
			signInButton.click();
			
			System.out.println(driver.getPageSource());
			
			if(accountLinking.isDisplayed()) {
				noThanksButton.click();
			}
			
		} else {
			Assert.fail("Signin button is not diplayed");
		}
	}

}
