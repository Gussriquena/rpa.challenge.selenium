package rpa.challenge.selenium.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import rpa.challenge.selenium.constants.PageEnum;

public class ChallengePageJs {
	private Logger log = Logger.getLogger(ChallengePageJs.class);
	
	private WebDriver driver;
	private JavascriptExecutor js;
	
	private By resultMessageBy = By.xpath(PageEnum.XPATH_RESULT_MESSAGE.getValue());
	
	public ChallengePageJs(WebDriver driver) {
		this.driver = driver;
		this.js = (JavascriptExecutor) driver;
	}
	
	public void fillInputText(String inputName, String dataInput) throws Exception {
		String labelName = "label" + inputName.replaceAll(" ", "");
		js.executeScript("document.querySelector('div > rpa1-field[ng-reflect-label=\"" + labelName + "\"] > div > input').value='" + dataInput +"'");
	}
	
	public void clickSubmitButton() throws Exception {
		js.executeScript("document.querySelector(\"form > input[value='Submit']\").click()");
	}
	
	public void clickStartButton() {
		js.executeScript("document.querySelector(\"div > button\").click()");
	}
	
	public String getResultMessage() {
		String resultMessage = "Result message not found!";
		
		try {
			resultMessage = driver.findElement(resultMessageBy).getText();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return resultMessage;
	}
}
