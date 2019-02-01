package com.training.sanity.tests;

import org.testng.annotations.Test;


import com.training.dataproviders.LoginDataProviders;
import com.training.pom.CheckAdvertInvalidValuesCYTC_070POM;
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

public class CheckAdvertInvalidValuesCYTC_070 {
	private WebDriver driver;
	private String baseUrl;
	private static Properties properties;
	private CheckAdvertInvalidValuesCYTC_070POM advertinvalidvaluesPOM;
	String adverttitle, actual, expected;
	

	@Test(priority = 1)
	public void checkAdminLogin() {
		 System.out.println("--------Executing CYTC_070---------");
		advertinvalidvaluesPOM.sendUserName("admin");
		advertinvalidvaluesPOM.sendPassword("12345");
		advertinvalidvaluesPOM.clickLoginBtn();
	}
	@Test(priority = 2, dataProvider = "advert-invalid-test", dataProviderClass = LoginDataProviders.class) //adding data from excel sheet - CYCTD_006
	public void addMultipleAvertisment(String uname, String title, String text, String price, String desc)
			throws InterruptedException {
		System.out.println("--------Adding data from CYCTD006---------");
		try {
			advertinvalidvaluesPOM.testassert(uname, title, text, price, desc);
			actual = advertinvalidvaluesPOM.actual;
			advertinvalidvaluesPOM.sendMemberProfile(uname);
			advertinvalidvaluesPOM.clickManageAdvertisementLink();
			Thread.sleep(3000);
			advertinvalidvaluesPOM.addNewAdvert();
			if(title!=null){
			advertinvalidvaluesPOM.sendAdTitle(title);
			Thread.sleep(3000);
			}
			if(text!=null){
			advertinvalidvaluesPOM.selectCategory(text);
			}
			if(price!=null){
			advertinvalidvaluesPOM.sendPrice(price);
			}
			advertinvalidvaluesPOM.selectNotExpirableCheckBox();
			Thread.sleep(3000);
			if(desc!=null){
			advertinvalidvaluesPOM.enterDescription(desc);
			}else{
			Thread.sleep(3000);
			advertinvalidvaluesPOM.clickSubmitAdvertBtn();
			Thread.sleep(3000);
			Alert addadverttext = driver.switchTo().alert();
			expected = addadverttext.getText();
			Thread.sleep(3000);
			System.out.println(expected);
			addadverttext.accept();
			Thread.sleep(3000);
			advertinvalidvaluesPOM.goBackToAdvertList();
			advertinvalidvaluesPOM.clickHome();
			}
		} catch (Exception e) {
			System.out.println(actual);
		}
	Assert.assertEquals(actual, expected);
	}

	@BeforeClass
	public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		advertinvalidvaluesPOM = new CheckAdvertInvalidValuesCYTC_070POM(driver);
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
