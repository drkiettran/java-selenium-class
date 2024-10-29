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
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Run: mvn -Dtest=com.drkiettran.selenium.FirstScript test
 */

public class TestJenkinsLogin {
	static final String JENKINS_LOGIN_PAGE = "http://localhost:8080";
	static final String LOGIN_USERNAME = "selenium";
	static final String LOGIN_PASSWORD = "selenium";
	static final String SELENIUM_FORM_PAGE = "https://www.selenium.dev/selenium/web/web-form.html";
	private WebDriver driver = null;
	private static Logger logger = Logger.getLogger(TestJenkinsLogin.class.getName());

	@BeforeEach
	void startUp() {
		logger.info("Start up ...");
		ChromeDriverService service = new ChromeDriverService.Builder().withLogOutput(System.out).build();
		driver = new ChromeDriver(service);
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
	void testForm() {
		logger.info("Testing with selenium form.");

		driver.get(SELENIUM_FORM_PAGE);

		driver.getTitle();

		WebElement textBox = driver.findElement(By.name("my-text"));
		WebElement submitButton = driver.findElement(By.cssSelector("button"));

		textBox.sendKeys("Selenium");
		submitButton.click();

		WebElement message = driver.findElement(By.id("message"));
		logger.info("message: " + message.getText());
	}

	@Test
	void testJenkins() {
		logger.info("Testing with Jenkins login form.");
		driver.get(JENKINS_LOGIN_PAGE);
		String title = driver.getTitle();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement signinButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("Submit")));
		WebElement usernameBox = driver.findElement(By.name("j_username"));
		WebElement passwordBox = driver.findElement(By.id("j_password"));

		usernameBox.sendKeys(LOGIN_USERNAME);
		passwordBox.sendKeys(LOGIN_PASSWORD);
		signinButton.click();

		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		// Wait for the last element on page to show up.
		WebElement learnMore = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@target='_blank']")));
		WebElement message = driver.findElement(By.tagName("h1"));
		String welcomeMsg = message.getText();
		logger.info("title: " + title);
		logger.info("welcomeMsg: " + welcomeMsg);
		assertEquals("Welcome to Jenkins!", welcomeMsg);
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
