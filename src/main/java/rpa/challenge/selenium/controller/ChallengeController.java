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
			excelController.setResultMessage(processData(personList));
			excelController.writeOutputFile(personList);
		} else {
			log.info("No registers found to input");
		}
	}

	private String processData(List<Person> personList) {
		String resultMessage = "";
		
		try {
			driver = WebDriverFactory.getInstance();
			driver.get(PageEnum.URL_CHALLENGE.getValue());
			
			ChallengePage challengePage = new ChallengePage(driver);
			challengePage.clickStartButton();
			
			for (Person person : personList) {
				insertPersonData(person);
			}

			resultMessage = challengePage.getResultMessage();
			WebDriverFactory.closeWebDriver();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return resultMessage;
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

			person.setSuccessProcessed(true);
			log.info("Data inserted with success");
		} catch (Exception e) {
			log.error("Was not possible insert data to person: " + person.getFirstName() + " - " + e.getMessage());
			driver.get(PageEnum.URL_CHALLENGE.getValue());
		}
	}
}
