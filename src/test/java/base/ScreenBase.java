package base;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class ScreenBase {

	public static AndroidDriver<MobileElement> driver;
	public WebDriverWait wait;
	static Hashtable<Object, Object> htab = new Hashtable<Object, Object>();

	public void waitForElementPresent(long duration, String locator) {

		wait = new WebDriverWait(driver, duration);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locator)));
	}

	public ScreenBase(AndroidDriver<MobileElement> driver) {

		ScreenBase.driver = driver;

	}

	public void hideKeyboard() {

		driver.hideKeyboard();

	}

	public static boolean swipeVertically(WebElement element) {
		int x = element.getLocation().getX();
		int y = element.getLocation().getY();
		int width = element.getSize().getWidth();
		int height = element.getSize().getHeight();
		int startX = (width / 2) + x;
		int startY = (height / 4) + y;
		int endX = (width / 2) + x;
		int endY = (int) ((height * 0.75) + y);

		TouchAction action = new TouchAction(driver);
		action.press(startX, startY).moveTo(endX, endY).release().perform();

		return true;

	}

	public static void setProperty(Object key, Object value) {

		htab.put(key, value);

	}

	public static Object getProperty(Object key) {

		return htab.get(key);
	}

}
