package rpa.challenge.selenium.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import rpa.challenge.selenium.model.Person;

public class ChallengePageJs {
	private JavascriptExecutor js;
	
	public ChallengePageJs(WebDriver driver) {
		this.js = (JavascriptExecutor) driver;
	}
	
	public String clickStartButton() {
		return "$('div > button').click();";
	}
	
	public String fillPageCommand(Person person) {
		StringBuilder command = new StringBuilder();
		
		command.append("$('input[ng-reflect-name=labelFirstName]').val('" + person.getFirstName() + "');");
		command.append("$('input[ng-reflect-name=labelLastName]').val('" + person.getLastName() + "');");
		command.append("$('input[ng-reflect-name=labelCompanyName]').val('" + person.getCompanyName() + "');");
		command.append("$('input[ng-reflect-name=labelRole]').val('" + person.getRoleInCompany() + "');");
		command.append("$('input[ng-reflect-name=labelAddress]').val('" + person.getAddress() + "');");
		command.append("$('input[ng-reflect-name=labelEmail]').val('" + person.getEmail() + "');");
		command.append("$('input[ng-reflect-name=labelPhone]').val('" + person.getPhoneNumber() + "');");
		command.append("$('.inputFields .uiColorButton').click();\n");
		
		return command.toString();
	}
	
	public String getResultMessage() {
		return (String) js.executeScript("return document.querySelector('div.message2').innerText");
	}
	
	public void runSingleJsCommand(String command) {
		js.executeScript(command);
	}
	
	public void jsNavigate(String url) {
		js.executeScript("window.location.href = '"+url+"';");
	}

}
