package rpa.challenge.selenium.controller;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import rpa.challenge.selenium.model.Person;

public class ChallengeController {
	
	private WebDriver driver;
	private List<Person> personList = new ArrayList<>();
	
	public void initFlow() {
		driver = new ChromeDriver();
		
		personList.add(new Person("John", "Smith", "IT Solutions", "Analyst", "98 North Road", "jsmith@itsolutions.co.uk", "40716543298"));
		
		openRpaChallenge();
		inputData();
	}
	
	private void openRpaChallenge() {
		driver.get("http://www.rpachallenge.com/");
	}
	
	private void inputData() {
		for (Person person : personList) {
			driver.findElement(By.xpath(" //div//label[contains(text(), 'First Name')]//following-sibling::input")).sendKeys(person.getFirstName());
			driver.findElement(By.xpath(" //div//label[contains(text(), 'Last Name')]//following-sibling::input")).sendKeys(person.getLastName());
			driver.findElement(By.xpath(" //div//label[contains(text(), 'Role')]//following-sibling::input")).sendKeys(person.getRoleInCompany());
			driver.findElement(By.xpath(" //div//label[contains(text(), 'Company Name')]//following-sibling::input")).sendKeys(person.getCompanyName());
			driver.findElement(By.xpath(" //div//label[contains(text(), 'Address')]//following-sibling::input")).sendKeys(person.getAddress());
			driver.findElement(By.xpath(" //div//label[contains(text(), 'Email')]//following-sibling::input")).sendKeys(person.getEmail());
			driver.findElement(By.xpath(" //div//label[contains(text(), 'Phone')]//following-sibling::input")).sendKeys(person.getPhoneNumber());
		}
	}
}
