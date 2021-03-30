package rpa.challenge.selenium.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import rpa.challenge.selenium.browser.WebDriverFactory;
import rpa.challenge.selenium.constants.PageEnum;
import rpa.challenge.selenium.model.Person;
import rpa.challenge.selenium.pages.ChallengePage;

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
		
		log.info("Process ended");
	}

	private void processData(List<Person> personList) {
		driver = WebDriverFactory.getInstance();
		driver.get(PageEnum.URL_CHALLENGE.getValue());
		
		for (Person person : personList) {
			insertPersonData(person);
		}

		WebDriverFactory.closeWebDriver();
	}

	private void insertPersonData(Person person) {
		try {
			ChallengePage challengePage = new ChallengePage(driver);

			challengePage.fillInputText("First Name", person.getFirstName());
			challengePage.fillInputText("Last Name", person.getLastName());
			challengePage.fillInputText("Role", person.getRoleInCompany());
			challengePage.fillInputText("Company Name", person.getCompanyName());
			challengePage.fillInputText("Address", person.getAddress());
			challengePage.fillInputText("Email", person.getEmail());
			challengePage.fillInputText("Phone", person.getPhoneNumber().toString());
			challengePage.clickSubmitButton();

			log.info("Data inserted with success");
		} catch (Exception e) {
			log.error("Was not possible insert data to person: " + person.getFirstName() + " - " + e.getMessage());
			driver.get(PageEnum.URL_CHALLENGE.getValue());
		}
	}
}
