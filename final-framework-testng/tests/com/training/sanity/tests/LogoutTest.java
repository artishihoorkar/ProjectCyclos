package com.training.sanity.tests;

import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.MemberConfirmPasswordCheckPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

public class LogoutTest {

	private WebDriver driver;
	private String baseUrl;
	private MemberConfirmPasswordCheckPOM passwordPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	Alert logoutalert;

	@BeforeClass
	public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		passwordPOM = new MemberConfirmPasswordCheckPOM(driver); 
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
	
	@Test //check login and logout
	public void logoutcheck() throws Exception{
		passwordPOM.sendUserName("artish");
		passwordPOM.sendPassword("12345");
		passwordPOM.clickLoginBtn();
		passwordPOM.logout();
		logoutalert = driver.switchTo().alert();
		String actual = logoutalert.getText();
		logoutalert.accept();
		String expected = "Please confirm to logout";
		assertEquals(expected,actual);
		screenShot.captureScreenShot("CheckLogout");
	}
}
