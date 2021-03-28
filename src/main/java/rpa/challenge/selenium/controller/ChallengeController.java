package rpa.challenge.selenium.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import rpa.challenge.selenium.browser.WebDriverFactory;
import rpa.challenge.selenium.constants.PageEnum;
import rpa.challenge.selenium.model.Person;
import rpa.challenge.selenium.util.PageUtils;

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
			PageUtils pageUtils = new PageUtils(driver);

			pageUtils.sendTextByXpath("First Name", person.getFirstName());
			pageUtils.sendTextByXpath("Last Name", person.getLastName());
			pageUtils.sendTextByXpath("Role", person.getRoleInCompany());
			pageUtils.sendTextByXpath("Company Name", person.getCompanyName());
			pageUtils.sendTextByXpath("Address", person.getAddress());
			pageUtils.sendTextByXpath("Email", person.getEmail());
			pageUtils.sendTextByXpath("Phone", person.getPhoneNumber().toString());

			pageUtils.clickByXpath(PageEnum.XPATH_BUTTON_SUBMIT.getValue());

			log.info("Data inserted with success");
		} catch (Exception e) {
			log.error("Was not possible insert data to person: " + person.getFirstName() + " - " + e.getMessage());
			driver.get(PageEnum.URL_CHALLENGE.getValue());
		}
	}
}
