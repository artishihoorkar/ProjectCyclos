package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;

import com.training.dataproviders.LoginDataProviders;

import com.training.pom.CheckMemberAccessCYTC_066POM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;


public class CheckMemberAccessCYTC_066 {
	private WebDriver driver;
	private String baseUrl;
	private static Properties properties;
	private CheckMemberAccessCYTC_066POM checkmemberaccessPOM;
	
	
  @Test(priority =1) //check adminLogin
  public void checkAdminLogin() {
	  System.out.println("--------Executing CYTC_066---------");
	  checkmemberaccessPOM.sendUserName("admin");
	  checkmemberaccessPOM.sendPassword("12345");
	  checkmemberaccessPOM.clickLoginBtn();
  }
  
  @Test(priority =2 ,dataProvider = "member-input", dataProviderClass = LoginDataProviders.class) //adding data from excel sheet - CYCTD_004
  public void changePermission(String uname, String value, String Comments) throws InterruptedException{
	  System.out.println("--------Adding data from CYCTD004---------");
	  try{
	  System.out.println("testing with " + uname);
	  checkmemberaccessPOM.sendMemberLogin(uname);
	  checkmemberaccessPOM.clickChangePermissionBtn();
	  Thread.sleep(3000);
	  checkmemberaccessPOM.selectGroup(value);
	  String expected = value;
	  checkmemberaccessPOM.sendComments(Comments);
	  checkmemberaccessPOM.clickChangeGroupBtn();
	  Alert changegrptext = driver.switchTo().alert();
	  changegrptext.accept();
	  String actual = driver.findElement(By.xpath("//tbody/tr[1]/td[2]/input")).getAttribute("value");
	  checkmemberaccessPOM.clickHome();
	  Assert.assertEquals(actual, expected);
	  }catch(Exception e){
		  System.out.println(e.getMessage());
	  }
  }
  @BeforeClass
  public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		checkmemberaccessPOM = new CheckMemberAccessCYTC_066POM(driver); 
		baseUrl = properties.getProperty("baseURL");
		// open the browser 
		driver.get(baseUrl);
	}

  @AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
 
}
