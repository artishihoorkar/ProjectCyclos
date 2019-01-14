package com.training.sanity.tests;

import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.ChangeMemberPasswordCheckPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class ChangeMemberPasswordCheck {
	private WebDriver driver;
	private String baseUrl;
	private static Properties properties;
	private ScreenShot screenShot;
	private ChangeMemberPasswordCheckPOM memberpasswordPOM;
  
 @BeforeClass
  public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		memberpasswordPOM = new ChangeMemberPasswordCheckPOM(driver); 
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}
	
	@AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	@Test(priority =1) //admin login check
	public void validLoginTest() throws InterruptedException {
		memberpasswordPOM.sendUserName("artish");
		memberpasswordPOM.sendPassword("12345");
		memberpasswordPOM.clickLoginBtn(); 
		Thread.sleep(1000);
		memberpasswordPOM.clickpersonal();
		Thread.sleep(1000);
		memberpasswordPOM.clickchangepassword();
		Thread.sleep(10000);
		screenShot.captureScreenShot("ChangePassword");
	}
	@Test(priority =2) //checking for admin password confirmation
	public void memberpasswordCheck() throws InterruptedException{
		memberpasswordPOM.oldpassword("12345");
		memberpasswordPOM.newpassword("12345");
		memberpasswordPOM.confirmpassword("12345");
		memberpasswordPOM.clickSubmitBtn();
		String expected = "The password was modified";
		Alert alert = driver.switchTo().alert();
		String actual = alert.getText();
		Assert.assertEquals(actual, expected);
		alert.accept();
		Thread.sleep(5000);
		screenShot.captureScreenShot("CheckChangePassword");
	}
}
