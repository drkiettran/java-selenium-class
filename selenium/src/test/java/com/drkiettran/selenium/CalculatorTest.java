
package com.drkiettran.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

public class CalculatorTest {
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

	@Test
	void testGoogle() {
		driver.get("https://google.com");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    @Test
    void testAddition() {
        Calculator calculator = new Calculator();
        assertEquals(5, calculator.add(2, 3), "2 + 3 should equal 5");
    }

    @Test
    void testSubtraction() {
        Calculator calculator = new Calculator();
        assertEquals(1, calculator.subtract(3, 2), "3 - 2 should equal 1");
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
