package com.drkiettran.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.logging.*;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Run: mvn -Dtest=com.drkiettran.selenium.FirstScript test
 */

public class TestActiveMQLogin {
	static final String ACTIVEMQ_LOGIN_PAGE = "http://%s:%s@localhost:8161/admin";
	static final String LOGIN_USERNAME = "admin";
	static final String LOGIN_PASSWORD = "admin";
	private WebDriver driver = null;
	private static Logger logger = Logger.getLogger(TestJenkinsLogin.class.getName());

	@BeforeEach
	void startUp() {
		logger.info("Start up ...");
		driver = new ChromeDriver();
		logger.info("Chrome driver opened ...");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));

	}

	void sleep(int secs) {
		try {
			TimeUnit.SECONDS.sleep(secs);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testActiveMQLoginPrompt() {
		logger.info("Testing with ActiveMQ login prompt.");
		driver.get(String.format(ACTIVEMQ_LOGIN_PAGE, LOGIN_USERNAME, LOGIN_PASSWORD));
		String title = driver.getTitle();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		// Wait for the last element on page to show up.
		WebElement lastTextItem = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(),'Temp percent used')]")));
		WebElement message = driver.findElement(By.tagName("h2"));
		String welcomeMsg = message.getText();
		logger.info("title: " + title);
		logger.info("welcomeMsg: " + welcomeMsg);
		assertEquals("Welcome!", welcomeMsg);
		sleep(5);
	}

	@AfterEach
	void cleanUp() {
		logger.info("Clean up ...");
		if (driver != null) {
			driver.quit();
			logger.info("Driver closed ...");
		}

	}
}
