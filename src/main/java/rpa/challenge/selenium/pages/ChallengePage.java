package rpa.challenge.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import rpa.challenge.selenium.constants.PageEnum;

public class ChallengePage {
	private WebDriver driver;
	
	private By inputDefaultBy;
	private By buttonSubmitBy = By.xpath(PageEnum.XPATH_BUTTON_SUBMIT.getValue());
	
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
}
