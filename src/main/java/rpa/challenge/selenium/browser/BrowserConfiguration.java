package rpa.challenge.selenium.browser;

import java.util.HashMap;

import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserConfiguration {

	public static ChromeOptions getChromeOptios() throws Exception {
		HashMap<String, Object> chromePreferences = new HashMap<String, Object>();
		ChromeOptions options = new ChromeOptions();
		// ChromePreferences 0 = default, 1 = allow, 2 = block
		chromePreferences.put("profile.managed_default_content_settings.popups", 0);
		chromePreferences.put("profile.managed_default_content_settings.notifications", 2);
		chromePreferences.put("profile.managed_default_content_settings.cookies", 2);
		chromePreferences.put("profile.managed_default_content_settings.images", 2);
		chromePreferences.put("profile.managed_default_content_settings.stylesheets", 0);
		chromePreferences.put("profile.managed_default_content_settings.javascript", 0);
		chromePreferences.put("profile.managed_default_content_settings.plugins", 2);
		chromePreferences.put("profile.managed_default_content_settings.geolocation", 2);
		chromePreferences.put("profile.managed_default_content_settings.media_stream", 2);
		chromePreferences.put("download.default_directory", 0);
		options.addArguments("--enable-automation");
		options.addArguments("--start-maximized");
		options.addArguments("--no-sandbox");
		options.addArguments("--dns-prefetch-disable");
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--incognito");
		options.addArguments("--enable-precise-memory-info");
		options.addArguments("--disable-default-apps");
		options.addArguments("--disable-extensions");
		options.addArguments("--disable-gpu");
		options.addArguments("--disable-infobars");
		//options.addArguments("--headless");

		options.setExperimentalOption("prefs", chromePreferences);
		//options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		
		return options;
	}

}
