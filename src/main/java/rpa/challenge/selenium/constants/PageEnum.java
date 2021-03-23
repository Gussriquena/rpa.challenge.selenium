package rpa.challenge.selenium.constants;

public enum PageEnum {
	
	XPATH_INPUT_DEFAULT("//div//label[contains(text(), '%s')]//following-sibling::input"),
	XPATH_BUTTON_SUBMIT("//form//input[@Type='submit' or contains(text(), 'submit') or starts-with(@class, 'btn')]");

	private String value;
	
	private PageEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
}
