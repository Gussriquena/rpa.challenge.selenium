package rpa.challenge.selenium.constants;

import rpa.challenge.selenium.util.ConfigReader;

public enum PageEnum {
	
	URL_CHALLENGE(ConfigReader.read("url.challenge.main")),
	
	PATH_EXCEL_INPUT(ConfigReader.read("path.excel.input")),
	PATH_EXCEL_OUTPUT(ConfigReader.read("path.excel.output")),
	PATH_EXCEL_PROCESSING(ConfigReader.read("path.excel.processing")),
	
	XPATH_INPUT_DEFAULT("//div//label[contains(text(), '%s')]//following-sibling::input"),
	XPATH_BUTTON_SUBMIT("//form//input[@Type='submit' or contains(text(), 'submit') or starts-with(@class, 'btn')]"),
	XPATH_BUTTON_START("//div/button[contains(text(), 'Start')]"),
	XPATH_RESULT_MESSAGE("//div/div[@class='message2']");

	private String value;
	
	private PageEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
}
