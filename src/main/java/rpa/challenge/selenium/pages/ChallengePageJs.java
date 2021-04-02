package rpa.challenge.selenium.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import rpa.challenge.selenium.constants.PageEnum;

public class ChallengePageJs {
	private Logger log = Logger.getLogger(ChallengePageJs.class);
	
	private WebDriver driver;
	private JavascriptExecutor js;
	
	private By inputDefaultBy;
	private By buttonSubmitBy = By.xpath(PageEnum.XPATH_BUTTON_SUBMIT.getValue());
	private By buttonStartBy = By.xpath(PageEnum.XPATH_BUTTON_START.getValue());
	private By resultMessageBy = By.xpath(PageEnum.XPATH_RESULT_MESSAGE.getValue());
	
	WebElement input;
	WebElement button;
	
	public ChallengePageJs(WebDriver driver) {
		this.driver = driver;
		this.js = (JavascriptExecutor) driver;
	}
	
	public void fillInputText(String inputName, String dataInput) throws Exception {
		this.inputDefaultBy = By.xpath(String.format(PageEnum.XPATH_INPUT_DEFAULT.getValue(), inputName));
		input = driver.findElement(inputDefaultBy);
		
		String command = "arguments[0].value='"+dataInput+"'";
		js.executeScript(command, input);
	}
	
	public void clickSubmitButton() throws Exception {
		button = driver.findElement(buttonSubmitBy);
		js.executeScript("arguments[0].click()", button);
	}
	
	public void clickStartButton(){
		try {
			button = driver.findElement(buttonStartBy);
			js.executeScript("arguments[0].click()", button);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
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
