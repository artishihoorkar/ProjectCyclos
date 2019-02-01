package com.training.sanity.tests;

import org.testng.annotations.Test;

import com.training.dataproviders.LoginDataProviders;
import com.training.generics.ScreenShot;

import com.training.pom.AddMultipleAdvertCheckCYTC_068POM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import org.testng.annotations.BeforeClass;


import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.testng.annotations.AfterTest;

public class AddMultipleAvertisementCheckCYTC_068 {
	private WebDriver driver;
	private String baseUrl;
	private static Properties properties;
	private ScreenShot screenShot;
	private AddMultipleAdvertCheckCYTC_068POM addmultipleadvertPOM;
	String adverttitle, actual, expected;

	@Test(priority = 1)
	public void checkAdminLogin() {
		 System.out.println("--------Executing CYTC_068---------");
		addmultipleadvertPOM.sendUserName("admin");
		addmultipleadvertPOM.sendPassword("12345");
		addmultipleadvertPOM.clickLoginBtn();
	}

	@Test(priority = 2, dataProvider = "advert-list", dataProviderClass = LoginDataProviders.class) //adding data from excel sheet - CYCTD_005
	public void addMultipleAvertisment(String uname, String title, String text, String price, String desc)
			throws InterruptedException {
		System.out.println("--------Adding data from CYCTD005---------");
		try {
			String expected = "Advertisement is in the list";
			addmultipleadvertPOM.sendMemberProfile(uname);
			addmultipleadvertPOM.clickManageAdvertisementLink();
			Thread.sleep(3000);
			addmultipleadvertPOM.addNewAdvert();
			addmultipleadvertPOM.sendAdTitle(title);
			Thread.sleep(3000);
			addmultipleadvertPOM.selectCategory(text);
			Thread.sleep(3000);
			addmultipleadvertPOM.sendPrice(price);
			Thread.sleep(3000);
			addmultipleadvertPOM.selectNotExpirableCheckBox();
			Thread.sleep(3000);
			addmultipleadvertPOM.enterDescription(desc);
			Thread.sleep(3000);
			addmultipleadvertPOM.clickSubmitAdvertBtn();
			Thread.sleep(3000);
			Alert addadverttext = driver.switchTo().alert();
			addadverttext.accept();
			addmultipleadvertPOM.goBackToAdvertList();
			List<WebElement> advertlist = driver.findElements(By.xpath("//div[@id='tdContents']/table/tbody/tr[2]/td/table/tbody/tr/td[2]/div[1]"));
			ArrayList<String> searchAdvertTitle = new ArrayList<String>(); // initializing array for entering all the advertisement titles on the page
			if (advertlist.size() != 0) {
				for (int j = 0; j < advertlist.size(); j++) { //getting all advert list titles in a string array
					try {
						WebElement membergetadverttitle = advertlist.get(j);
						adverttitle = membergetadverttitle.getText();  
						searchAdvertTitle.add(adverttitle); // saving in string array
					} catch (Exception e) {
						System.out.println("Exception Message : " + e.getMessage());
					}
				}
				if (searchAdvertTitle != null) { // checking if advertisement title is visible or not
													
					if (searchAdvertTitle.contains(title)) {
						actual = "Advertisement is in the list";
					} else {
						actual = "Advertisement (" + adverttitle + ") is not in the list";
						screenShot.captureScreenShot("advertinlist.png");
					}
				}
			} else {
				actual = "No advertisement in the list";
				screenShot.captureScreenShot("noadvertvisible.png");
			}
			assertEquals(actual, expected);
			addmultipleadvertPOM.clickHome();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@BeforeClass
	public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		addmultipleadvertPOM = new AddMultipleAdvertCheckCYTC_068POM(driver);
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

}
