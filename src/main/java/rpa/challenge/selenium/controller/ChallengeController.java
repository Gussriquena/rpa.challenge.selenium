package rpa.challenge.selenium.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.analysis.function.Log;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import rpa.challenge.selenium.model.Person;

public class ChallengeController {
	
	private Logger log = Logger.getLogger(ChallengeController.class);
	private WebDriver driver;
	private List<Person> personList = new ArrayList<>();
	
	public void initFlow() {
		ExcelController excelController = new ExcelController();
		personList = excelController.readRowsExcel();
		
		driver = new ChromeDriver();
		openRpaChallenge();
		inputData();
	}
	
	private void openRpaChallenge() {
		driver.get("http://www.rpachallenge.com/");
	}
	
	private void inputData() {
		for (Person person : personList) {
			try {
				driver.findElement(By.xpath("//div//label[contains(text(), 'First Name')]//following-sibling::input")).sendKeys(person.getFirstName());
				driver.findElement(By.xpath("//div//label[contains(text(), 'Last Name')]//following-sibling::input")).sendKeys(person.getLastName());
				driver.findElement(By.xpath("//div//label[contains(text(), 'Role')]//following-sibling::input")).sendKeys(person.getRoleInCompany());
				driver.findElement(By.xpath("//div//label[contains(text(), 'Company Name')]//following-sibling::input")).sendKeys(person.getCompanyName());
				driver.findElement(By.xpath("//div//label[contains(text(), 'Address')]//following-sibling::input")).sendKeys(person.getAddress());
				driver.findElement(By.xpath("//div//label[contains(text(), 'Email')]//following-sibling::input")).sendKeys(person.getEmail());
				driver.findElement(By.xpath("//div//label[contains(text(), 'Phone')]//following-sibling::input")).sendKeys(person.getPhoneNumber());
				driver.findElement(By.xpath("//form//input[@Type='submit' or contains(text(), 'submit') or starts-with(@class, 'btn')]")).click();
			} catch (Exception e) {
				driver.findElement(By.xpath("//div//label[contains(text(), 'First Name')]//following-sibling::input")).clear();
				driver.findElement(By.xpath("//div//label[contains(text(), 'Last Name')]//following-sibling::input")).clear();
				driver.findElement(By.xpath("//div//label[contains(text(), 'Role')]//following-sibling::input")).clear();
				driver.findElement(By.xpath("//div//label[contains(text(), 'Company Name')]//following-sibling::input")).clear();
				driver.findElement(By.xpath("//div//label[contains(text(), 'Address')]//following-sibling::input")).clear();
				driver.findElement(By.xpath("//div//label[contains(text(), 'Email')]//following-sibling::input")).clear();
				driver.findElement(By.xpath("//div//label[contains(text(), 'Phone')]//following-sibling::input")).clear();
				log.error(e.getMessage());
			}
		}
	}
}
