package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class CommonUtils {

	public static int IMPLICIT_WAIT_TIME;
	public static int EXPLICIT_WAIT_TIME;
	public static String BASE_PKG;
	public static String APP_PATH;
	public static String APP_ACTIVITY;
	public static String BROWSER_NAME;
	public static String PLATFORM_NAME;
	public static String PLATFORM_VERSION;
	public static String DEVICE_NAME;
	public static String APPIUM_PORT;
	public static URL serverUrl;
	public static String UDID;
	public static String BUNDLE_ID;
	public static String APP;
	public static String AUTOMATION_INSTRUMENTATION;

	private static Properties prop = new Properties();
	private static DesiredCapabilities capabilities = new DesiredCapabilities();

	private static AndroidDriver<MobileElement> driver;

	// Method to loadAndroidConfProp

	public static void loadAndroidConfProp(String propertyFileName) throws IOException {

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/test/resources/properties/" + propertyFileName);
		prop.load(fis);

		IMPLICIT_WAIT_TIME = Integer.parseInt(prop.getProperty("implicit.wait"));
		EXPLICIT_WAIT_TIME = Integer.parseInt(prop.getProperty("explicit.wait"));
		APP_PATH = prop.getProperty("application.path");
		BASE_PKG = prop.getProperty("base.pkg");
		APP_ACTIVITY = prop.getProperty("application.activity");
		BROWSER_NAME = prop.getProperty("browser.name");
		PLATFORM_NAME = prop.getProperty("platform.name");
		PLATFORM_VERSION = prop.getProperty("platform.version");
		DEVICE_NAME = prop.getProperty("device.name");
		APPIUM_PORT = prop.getProperty("appium.server.port");

	}

	// Method to setAndroidCap

	public static void setAndroidCapabilities() {

		// capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BROWSER_NAME);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM_NAME);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, PLATFORM_VERSION);
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);

		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, BASE_PKG);
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, APP_ACTIVITY);

	}

	// Method to getAndroidDriver

	public static AndroidDriver<MobileElement> getAndroidDriver() throws MalformedURLException {

		serverUrl = new URL("http://127.0.0.1:" + APPIUM_PORT + "/wd/hub");
		driver = new AndroidDriver<MobileElement>(serverUrl, capabilities);

		return driver;

	}
}
