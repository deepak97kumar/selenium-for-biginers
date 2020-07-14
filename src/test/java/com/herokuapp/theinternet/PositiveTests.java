package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveTests {

	@Test
	public void loginTest() {
		// Create Driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// Open Test Page
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);

		// maximize browser
		driver.manage().window().fullscreen();

		// enter username
		WebElement userName = driver.findElement(By.id("username"));
		userName.sendKeys("tomsmith");

		// enter password
		WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
		password.sendKeys("SuperSecretPassword!");

		// click lgin button
		WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit' and @class='radius']"));
		loginButton.click();

		// verification
		// new url
		String expectedurl = "http://the-internet.herokuapp.com/secure";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedurl, "Actual url is not as expected url");

		// logout button is visible
		WebElement logoutButton = driver
				.findElement(By.xpath("//a[@href='/logout' and @class='button secondary radius']"));
		Assert.assertTrue(logoutButton.isDisplayed(), "Logout button is not visible");

		// successful login message
		WebElement successMessage = driver.findElement(By.xpath("//div[@id='flash']"));
		String expectedMessage = "You logged into a secure area!";
		String actualMessage = successMessage.getText();
		Assert.assertTrue(actualMessage.contains(expectedMessage), 
				String.format("Actual message doesn't contains expected message \n ActualMessage: %s \n ExpectedMessage: %s", actualMessage, expectedMessage));

		// close browser
		driver.quit();

	}

}
