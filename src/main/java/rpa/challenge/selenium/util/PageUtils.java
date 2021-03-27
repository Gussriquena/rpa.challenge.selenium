package rpa.challenge.selenium.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import rpa.challenge.selenium.constants.PageEnum;

public class PageUtils {

	private WebDriver driver = null;
	
	public PageUtils(WebDriver driver){
		this.driver = driver;
	}
	
	public void sendTextByXpath(String xpathInputParam, String typeData) {
		driver.findElement(By.xpath(String.format(PageEnum.XPATH_INPUT_DEFAULT.getValue(), xpathInputParam))).sendKeys(typeData);
	}
	
	public void clickByXpath(String xpath) {
		driver.findElement(By.xpath(xpath)).click();
	}
	
}
