package rpa.challenge.selenium.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
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
			
			String fullCommand = createFullCommand();
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(fullCommand);
			
			resultMessage = challengePageJs.getResultMessage();
			log.info(resultMessage);
			WebDriverFactory.closeWebDriver();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return resultMessage;
	}
	
	private String createFullCommand() {
		ChallengePageJs challengePageJs = new ChallengePageJs(driver);
		StringBuilder fullCommand = new StringBuilder();
		
		fullCommand.append(challengePageJs.clickStartButton());
		for (Person person : personList) {
			fullCommand.append(challengePageJs.fillPageCommand(person));
		}
	
		return fullCommand.toString();
	}
}
