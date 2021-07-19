package rpa.challenge.selenium.controller;

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
	
	public void initFlow() {
		ExcelController excelController = new ExcelController();
		excelController.readRowsExcel().ifPresentOrElse(personList -> {
			excelController.setResultMessage(processData(personList));
			excelController.writeOutputFile(personList);
		}, () -> log.info("No registers found to input"));
	}

	private String processData(List<Person> personList) {
		StringBuilder resultMessage = new StringBuilder();
		
		try {
			driver = WebDriverFactory.getInstance();
			driver.get(PageEnum.URL_CHALLENGE.getValue());
			
			ChallengePageJs challengePageJs = new ChallengePageJs(driver);
			StringBuilder fullCommand = new StringBuilder();
			
			personList.forEach(person -> fullCommand.append(challengePageJs.fillPageCommand(person)));
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(challengePageJs.clickStartButton() + fullCommand.toString());
			
			resultMessage.append(challengePageJs.getResultMessage());
			//WebDriverFactory.closeWebDriver();
			
			log.info(resultMessage);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return resultMessage.toString();
	}
}