package com.training.sanity.tests;

import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.ChangeAdminPasswordPOM;
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class ChangeAdminPasswordCheck {
	private WebDriver driver;
	private String baseUrl;
	private static Properties properties;
	private ScreenShot screenShot;
	private ChangeAdminPasswordPOM adminpasswordPOM;
  
 @BeforeClass
  public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		adminpasswordPOM = new ChangeAdminPasswordPOM(driver); 
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
		adminpasswordPOM.sendUserName("admin");
		adminpasswordPOM.sendPassword("12345");
		adminpasswordPOM.clickLoginBtn(); 
		Thread.sleep(1000);
		adminpasswordPOM.clickpersonal();
		Thread.sleep(1000);
		adminpasswordPOM.clickchangepassword();
		Thread.sleep(10000);
		screenShot.captureScreenShot("ChangePassword");
	}
	@Test(priority =2) //checking for admin password confirmation
	public void adminpasswordCheck() throws InterruptedException{
		adminpasswordPOM.oldpassword("12345");
		adminpasswordPOM.newpassword("12345");
		adminpasswordPOM.confirmpassword("12345");
		adminpasswordPOM.clickSubmitBtn();
		String expected = "The password was modified";
		Alert alert = driver.switchTo().alert();
		String actual = alert.getText();
		Assert.assertEquals(actual, expected);
		alert.accept();
		Thread.sleep(5000);
		screenShot.captureScreenShot("CheckChangePassword");
	}
	
}