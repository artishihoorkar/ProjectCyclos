package com.training.sanity.tests;

import org.testng.annotations.Test;

import com.training.dataproviders.LoginDataProviders;

import com.training.pom.CheckMemberAccessDBCYTC_067POM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class CheckMemberAccessDBCYTC_067 {
	private WebDriver driver;
	private String baseUrl;
	private static Properties properties;
	private CheckMemberAccessDBCYTC_067POM checkmemberaccessPOM;
	
	  @Test(priority =1)
	  public void checkAdminLogin() {
		  System.out.println("--------Executing CYTC_067---------");
		  checkmemberaccessPOM.sendUserName("admin");
		  checkmemberaccessPOM.sendPassword("12345");
		  checkmemberaccessPOM.clickLoginBtn();
	  }
	  
	  @Test(priority =2 ,dataProvider = "db-inputsforpermissiongrp", dataProviderClass = LoginDataProviders.class) //change permission group in database
	  public void changePermission(String uname, String value, String Comments) throws InterruptedException{
		  System.out.println("--------Adding data from table changepermissiongrp---------");
		  try{
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
			checkmemberaccessPOM = new CheckMemberAccessDBCYTC_067POM(driver); 
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
