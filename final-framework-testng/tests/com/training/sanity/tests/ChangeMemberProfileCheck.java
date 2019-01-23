package com.training.sanity.tests;

import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.ChangeMemberProfileCheckPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

public class ChangeMemberProfileCheck {
	
	private WebDriver driver;
	private String baseUrl;
	private ChangeMemberProfileCheckPOM memberProfilePOM;
	private static Properties properties;
	private ScreenShot screenShot;
	
  @BeforeClass
  public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		memberProfilePOM = new ChangeMemberProfileCheckPOM(driver); 
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
		
	}

  @AfterTest
  public void tearDown() throws Exception {
		Thread.sleep(1000);
		//close the driver
		driver.quit();
	}
  @Test (priority =1) //verify member login
	 public void validLoginTest() throws InterruptedException{
		memberProfilePOM.sendUserName("artish");
		  memberProfilePOM.sendPassword("12345");
		  memberProfilePOM.clickLoginBtn();
		  memberProfilePOM.clickpersonal();
		  memberProfilePOM.clickProfile();
	}
	@Test (priority =2) //modify full name of the user
public void checkFullName() throws InterruptedException {
	  memberProfilePOM.changeFullName("arti testnew");
	  Thread.sleep(3000);
	  memberProfilePOM.submitProfileChange();
	  memberProfilePOM.confirmProfileChange();
	  screenShot.captureScreenShot("profilechangecheck"); 
	  }

}
