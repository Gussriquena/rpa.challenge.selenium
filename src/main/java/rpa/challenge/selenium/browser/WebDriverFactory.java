package rpa.challenge.selenium.browser;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


public class WebDriverFactory {
	private static Logger log = Logger.getLogger(WebDriverFactory.class);
	private static WebDriver driver = null;
	
	public static WebDriver getInstance() {
		try {
			driver = null;
			WebDriverManager.chromedriver().browserVersion("91.0.4472.124").setup();
			driver = new ChromeDriver(BrowserConfiguration.getChromeOptios());
			driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} catch(Exception e) {
			log.error(e.getMessage());
		}
		
		return driver;
	}
	
	public static void closeWebDriver() {
		// Close the browser window that the driver has focus of
		driver.close();
		
		// Closes all browser windows and safely ends the session (kills chromedriver)
		driver.quit();
	}
	
}
