package com.drkiettran.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTest {
static WebDriver driver = null;
	
	@BeforeAll
	static void startUp() {
		System.out.println("starting up ...");
		 // Set the path to the ChromeDriver executable
//        System.setProperty("webdriver.chrome.driver", "c:\\dev\\chromedriver.exe");

        // Initialize WebDriver instance
        driver = new ChromeDriver();
//		driver = new EdgeDriver();
//		driver = new FirefoxDriver();
        System.out.println("opened firefox driver ...");
	}

	void sleep(int secs) {
		try {
			TimeUnit.SECONDS.sleep(secs);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	void testGoogle() {
		driver.get("https://google.com");
		sleep(1);
		String className = "gLFyf";
		List<WebElement> wes = driver.findElements(By.className(className));
		if (wes.isEmpty()) {
			System.out.println("text area not found for " + className);
		}
		System.out.println("found text area ... entering search text.");
		wes.get(0).clear();
		wes.get(0).sendKeys("Generative AI" + Keys.RETURN);
		sleep(2);
	}
	    
    @AfterAll
    static void cleanUp() {
    	System.out.println("cleaing up ...");
    	if (driver != null) {
    		driver.quit();
    		System.out.println("closed driver ...");
    	}
    	
    }
}
