package rpa.challenge.selenium.constants;

import rpa.challenge.selenium.util.ConfigReader;

public enum PageEnum {
	
	URL_CHALLENGE(ConfigReader.read("url.challenge.main")),
	
	PATH_CHROME_DRIVER(ConfigReader.read("chrome.driver.path")),
	CHROME_IS_HEADLESS(ConfigReader.read("chrome.driver.headless")),
	
	PATH_EXCEL_INPUT(ConfigReader.read("path.excel.input")),
	PATH_EXCEL_OUTPUT(ConfigReader.read("path.excel.output")),
	PATH_EXCEL_PROCESSING(ConfigReader.read("path.excel.processing")),
	NAME_EXCEL_FILE(ConfigReader.read("name.excel.processing")),
	
	CONFIG_LOG4J_PATH(ConfigReader.read("config.log4j.path")),
	
	XPATH_INPUT_DEFAULT("//div//label[contains(text(), '%s')]//following-sibling::input"),
	XPATH_BUTTON_SUBMIT("//form//input[@Type='submit' or contains(text(), 'submit') or starts-with(@class, 'btn')]"),
	XPATH_BUTTON_START("//div/button[contains(text(), 'Start')]"),
	XPATH_RESULT_MESSAGE("//div/div[@class='message2']"),
	
	JS_PATH_INPUT_DEFAULT("div > rpa1-field[ng-reflect-label='%s'] > div > input");

	private String value;
	
	private PageEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
}
