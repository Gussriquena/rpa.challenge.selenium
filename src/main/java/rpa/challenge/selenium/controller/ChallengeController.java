package rpa.challenge.selenium.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import rpa.challenge.selenium.browser.BrowserConfiguration;
import rpa.challenge.selenium.constants.PageEnum;
import rpa.challenge.selenium.model.Person;

public class ChallengeController {

	private Logger log = Logger.getLogger(ChallengeController.class);
	private WebDriver driver;
	private List<Person> personList = new ArrayList<>();

	public void initFlow() {
		ExcelController excelController = new ExcelController();

		personList = excelController.readRowsExcel();

		if (!personList.isEmpty()) {
			processData(personList);
		} else {
			log.info("No registers found to input");
		}
	}

	private void processData(List<Person> personList) {
		driver = new ChromeDriver(BrowserConfiguration.getChromeOptios());

		for (Person person : personList) {
			insertPersonData(person);
		}

		closeWebDriver();
	}

	private void insertPersonData(Person person) {
		try {
			driver.get("http://www.rpachallenge.com/");

			sendTextByXpath("First Name", person.getFirstName());
			sendTextByXpath("Last Name", person.getLastName());
			sendTextByXpath("Role", person.getRoleInCompany());
			sendTextByXpath("Company Name", person.getCompanyName());
			sendTextByXpath("Address", person.getAddress());
			sendTextByXpath("Email", person.getEmail());
			sendTextByXpath("Phone", person.getPhoneNumber().toString());

			driver.findElement(By.xpath(PageEnum.XPATH_BUTTON_SUBMIT.getValue())).click();

			log.info("Data inserted with success");
		} catch (Exception e) {
			log.error("Was not possible insert data to person: " + person.getFirstName() + " - " + e.getMessage());
		}
	}

	private void sendTextByXpath(String xpathInputParam, String typeData) {
		driver.findElement(By.xpath(String.format(PageEnum.XPATH_INPUT_DEFAULT.getValue(), xpathInputParam)))
				.sendKeys(typeData);
	}

	private void closeWebDriver() {
		// Close the browser window that the driver has focus of
		driver.close();
		// Closes all browser windows and safely ends the session (kills chromedriver)
		driver.quit();
	}
}
