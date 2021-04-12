package rpa.challenge.selenium.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ChallengePageJs {
	private JavascriptExecutor js;
	
	public ChallengePageJs(WebDriver driver) {
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
		 return (String) js.executeScript("return document.querySelector('div.message2').innerText");
	}
}
