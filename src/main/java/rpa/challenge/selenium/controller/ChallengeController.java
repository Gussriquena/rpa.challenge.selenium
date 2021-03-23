package rpa.challenge.selenium.controller;

import java.util.ArrayList;
import java.util.List;

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
		
		if (personList.size() != 0) {
			driver = new ChromeDriver();
			
			for (Person person : personList) {
				try {
					driver.get("http://www.rpachallenge.com/");
					driver.findElement(By.xpath("//div//label[contains(text(), 'First Name')]//following-sibling::input")).sendKeys(person.getFirstName());
					driver.findElement(By.xpath("//div//label[contains(text(), 'Last Name')]//following-sibling::input")).sendKeys(person.getLastName());
					driver.findElement(By.xpath("//div//label[contains(text(), 'Role')]//following-sibling::input")).sendKeys(person.getRoleInCompany());
					driver.findElement(By.xpath("//div//label[contains(text(), 'Company Name')]//following-sibling::input")).sendKeys(person.getCompanyName());
					driver.findElement(By.xpath("//div//label[contains(text(), 'Address')]//following-sibling::input")).sendKeys(person.getAddress());
					driver.findElement(By.xpath("//div//label[contains(text(), 'Email')]//following-sibling::input")).sendKeys(person.getEmail());
					driver.findElement(By.xpath("//div//label[contains(text(), 'Phone')]//following-sibling::input")).sendKeys(person.getPhoneNumber().toString());
					driver.findElement(By.xpath("//form//input[@Type='submit' or contains(text(), 'submit') or starts-with(@class, 'btn')]")).click();
					
					log.info("Data inserted with success");
				} catch (Exception e) {
					log.error("Was not possible insert data to person: " + person.getFirstName() + " - " + e.getMessage());
				}
			}
			
			driver.close();
			driver.quit();
		} else {
			log.error("No registers found to input");
		}
	}
}
