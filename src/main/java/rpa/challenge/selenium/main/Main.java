package rpa.challenge.selenium.main;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import rpa.challenge.selenium.constants.PageEnum;
import rpa.challenge.selenium.controller.ChallengeController;

public class Main {
	private static Logger log = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		PropertyConfigurator.configure("resources\\log4j.properties");
		System.setProperty("webdriver.chrome.driver", PageEnum.PATH_CHROME_DRIVER.getValue());
		
		log.info("Starting RPA Challenge automation");
		
		ChallengeController challengeController = new ChallengeController();
		challengeController.initFlow();
		
		log.info("Process ended");
	}

}
