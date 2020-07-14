package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeTests {
	
	@Test
	public void NegativeUserNameTest() {
		// create diver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		// Open test url
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);
		
		// Maximize browser
		driver.manage().window().fullscreen();
		
		// Enter incorrect username
		WebElement userName = driver.findElement(By.id("username"));
		userName.sendKeys("Deepakk");
		// Enter correct password
		WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
		password.sendKeys("SuperSecretPassword!");
		
		// click login page
		WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit' and @class='radius']"));
		loginButton.click();		
		
		// Versfication
		// no navigation to the new page
		Assert.assertEquals(driver.getCurrentUrl(), url, "Expected and Actual url are not same");
		
		// error message is displayed
		WebElement errorMessage = driver.findElement(By.id("flash"));
		String expectedMessage = "Your username is invalid!";
		String actualMessage = errorMessage.getText();
		Assert.assertTrue(actualMessage.contains(expectedMessage),
				String.format("Actual message doesn't contains expected message \n ActualMessage: %s \n ExpectedMessage: %s", actualMessage, expectedMessage));
		
		// close browser
		driver.quit();
	}

}
