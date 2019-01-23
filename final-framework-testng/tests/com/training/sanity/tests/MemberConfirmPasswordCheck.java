package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.training.generics.ScreenShot;
import com.training.pom.MemberConfirmPasswordCheckPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class MemberConfirmPasswordCheck {

	private WebDriver driver;
	private String baseUrl;
	private MemberConfirmPasswordCheckPOM passwordPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	String newpwdstr;
	String confnewpwdstr;

	@BeforeClass //driver initialization
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
		Thread.sleep(2000);
		driver.quit();
	}
	@Test (priority =1) //checking login
	public void checkLogin() throws InterruptedException {
		passwordPOM.sendUserName("artish");
		passwordPOM.sendPassword("12345");
		passwordPOM.clickLoginBtn();
		Thread.sleep(5000);
		passwordPOM.clickpersonal();
		Thread.sleep(1000);
		passwordPOM.clickchangepassword();
		Thread.sleep(5000);
	}
	@Test(priority=2) //Checking for member password confirmation
	public void checkpassword() throws Exception{
		passwordPOM.oldpassword("12345");
		passwordPOM.newpassword("1243");
		passwordPOM.confirmpassword("1234");
		passwordPOM.clickSubmitBtn();
		Thread.sleep(2000);	
		}
	//check when new password is not same as confirm password
	@Test (priority =3)
	public void checkconfpassword(){
		Alert alert = driver.switchTo().alert();
		String alerttext = alert.getText();
		alert.accept();
		String expectedfail = "Passwords are not Equal";			
		Assert.assertEquals(alerttext, expectedfail,"notmodified");
		screenShot.captureScreenShot("ChangeMemberConfirmPassword");
	}
}
