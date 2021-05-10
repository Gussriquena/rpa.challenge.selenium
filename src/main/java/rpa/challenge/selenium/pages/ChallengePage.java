package rpa.challenge.selenium.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rpa.challenge.selenium.constants.PageEnum;

public class ChallengePage {
	private WebDriver driver;
	private Logger log = Logger.getLogger(ChallengePage.class);
	
	private By inputDefaultBy;
	private By buttonSubmitBy = By.xpath(PageEnum.XPATH_BUTTON_SUBMIT.getValue());
	private By buttonStartBy = By.xpath(PageEnum.XPATH_BUTTON_START.getValue());
	private By resultMessageBy = By.xpath(PageEnum.XPATH_RESULT_MESSAGE.getValue());
	
	public ChallengePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void fillInputText(String inputName, String dataInput) throws Exception {
		this.inputDefaultBy = By.xpath(String.format(PageEnum.XPATH_INPUT_DEFAULT.getValue(), inputName));
		driver.findElement(inputDefaultBy).sendKeys(dataInput);
	}
	
	public void clickSubmitButton() throws Exception {
		driver.findElement(buttonSubmitBy).click();
	}
	
	public void clickStartButton(){
		try {
			driver.findElement(buttonStartBy).click();
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
	
	public void teste() {
		
		By element = By.id("txtElemento");
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		
	}
}
