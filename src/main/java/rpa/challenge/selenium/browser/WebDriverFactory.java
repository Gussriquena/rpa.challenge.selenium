package rpa.challenge.selenium.browser;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverFactory {

	private static WebDriver driver = null;
	
	public static WebDriver getInstance() {
		driver = new ChromeDriver(BrowserConfiguration.getChromeOptios());
		driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	public static void closeWebDriver() {
		// Close the browser window that the driver has focus of
		driver.close();
		
		// Closes all browser windows and safely ends the session (kills chromedriver)
		driver.quit();
	}
	
}
