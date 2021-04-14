package rpa.challenge.selenium.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import rpa.challenge.selenium.browser.WebDriverFactory;
import rpa.challenge.selenium.constants.PageEnum;
import rpa.challenge.selenium.model.Person;
import rpa.challenge.selenium.pages.ChallengePageJs;

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
			
			ChallengePageJs challengePageJs = new ChallengePageJs(driver);
			challengePageJs.clickStartButton();
		
			for (Person person : personList) {
				insertPersonData(person);
			}
			
			resultMessage = challengePageJs.getResultMessage();
			log.debug(resultMessage);
			WebDriverFactory.closeWebDriver();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return resultMessage;
	}

	private void insertPersonData(Person person) {
		try {
			ChallengePageJs challengePageJs = new ChallengePageJs(driver);
            challengePageJs.fillPage(person);
		} catch (Exception e) {
			log.error("Was not possible insert data to person: " + e.getMessage()  + " - " + person.toString());
			driver.get(PageEnum.URL_CHALLENGE.getValue());
		}
	}
}
