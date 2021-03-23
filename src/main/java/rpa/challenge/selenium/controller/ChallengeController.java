package rpa.challenge.selenium.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import rpa.challenge.selenium.model.Person;

public class ChallengeController {
	
	private Logger log = Logger.getLogger(ChallengeController.class);
	private WebDriver driver;
	private List<Person> personList = new ArrayList<>();
	
	public void initFlow() {
		ExcelController excelController = new ExcelController();
		personList = excelController.readRowsExcel();
		
		if (personList.size() != 0) {
			
			// ChromePreferences
			// 0 = default
			// 1 = allow
			// 2 = block
			HashMap<String, Object> chromePreferences = new HashMap<String, Object>();
			ChromeOptions options = new ChromeOptions();
			
			chromePreferences.put("profile.managed_default_content_settings.popups", 0);
			chromePreferences.put("profile.managed_default_content_settings.notifications", 2);
			chromePreferences.put("profile.managed_default_content_settings.cookies", 2);
			chromePreferences.put("profile.managed_default_content_settings.images", 2);
			chromePreferences.put("profile.managed_default_content_settings.stylesheets", 2);
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
			
			options.setExperimentalOption("prefs", chromePreferences);
			options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			
			driver = new ChromeDriver(options);
			
			for (Person person : personList) {
				try {
					driver.get("http://www.rpachallenge.com/");
					sendTextByXpath("//div//label[contains(text(), 'First Name')]//following-sibling::input", person.getFirstName());
					sendTextByXpath("//div//label[contains(text(), 'Last Name')]//following-sibling::input", person.getLastName());
					sendTextByXpath("//div//label[contains(text(), 'Role')]//following-sibling::input", person.getRoleInCompany());
					sendTextByXpath("//div//label[contains(text(), 'Company Name')]//following-sibling::input", person.getCompanyName());
					sendTextByXpath("//div//label[contains(text(), 'Address')]//following-sibling::input", person.getAddress());
					sendTextByXpath("//div//label[contains(text(), 'Email')]//following-sibling::input", person.getEmail());
					sendTextByXpath("//div//label[contains(text(), 'Phone')]//following-sibling::input", person.getPhoneNumber().toString());

					driver.findElement(By.xpath("//form//input[@Type='submit' or contains(text(), 'submit') or starts-with(@class, 'btn')]")).click();
					
					log.info("Data inserted with success");
				} catch (Exception e) {
					log.error("Was not possible insert data to person: " + person.getFirstName() + " - " + e.getMessage());
				}
			}
			
			driver.close();
			driver.quit();
		} else {
			log.error("No registers found to input");
		}
	}
	
	private void sendTextByXpath(String xpath, String typeData) {
		driver.findElement(By.xpath(xpath)).sendKeys(typeData);
	}
}
