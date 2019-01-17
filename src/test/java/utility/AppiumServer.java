package utility;

import java.io.File;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumServer {
	
	public static AppiumDriverLocalService service;
	
	public static void start(){
		
		// starting the Appium server code

		service = AppiumDriverLocalService.buildService(
				new AppiumServiceBuilder().usingDriverExecutable(new File("/usr/local/bin/node"))
						.withAppiumJS(new File("/Applications/Appium.app/Contents/Resources/app/node_modules/appium/build/lib/main.js"))
						.withLogFile(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\logs\\Appium.log")));

		service.start();
		
	}
	
	
	public static void stop(){
		
		service.stop();
		
	}

}
