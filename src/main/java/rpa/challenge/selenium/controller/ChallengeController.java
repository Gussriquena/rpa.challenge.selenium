package rpa.challenge.selenium.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import rpa.challenge.selenium.browser.BrowserConfiguration;
import rpa.challenge.selenium.model.Person;

public class ChallengeController {
	
	private Logger log = Logger.getLogger(ChallengeController.class);
	private WebDriver driver;
	private List<Person> personList = new ArrayList<>();
	
	public void initFlow() {
		ExcelController excelController = new ExcelController();
		personList = excelController.readRowsExcel();
		
		if (personList.size() != 0) {
			
			driver = new ChromeDriver(BrowserConfiguration.getChromeOptios());
			
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
