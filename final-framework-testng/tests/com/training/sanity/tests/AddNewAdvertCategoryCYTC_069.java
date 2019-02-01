package com.training.sanity.tests;

import org.testng.annotations.Test;


import com.training.pom.AddNewAdvertCategoryCYTC_069POM;

import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class AddNewAdvertCategoryCYTC_069 {
	private WebDriver driver;
	private String baseUrl;
	private static Properties properties;
	private AddNewAdvertCategoryCYTC_069POM addnewcategoryPOM;
	String catname;
	String adminadverttitle, adverttitle, actual, expected;
	
	@Test(priority =1)
	public void checkAdminLogin() {
		System.out.println("--------Executing CYTC_069---------");
		addnewcategoryPOM.sendUserName("admin");
		addnewcategoryPOM.sendPassword("12345");
		addnewcategoryPOM.clickLoginBtn();
	  }
  @Test(priority = 2) //add new category for advertisement
  public void AddCategory() throws InterruptedException {
	  addnewcategoryPOM.clickAdvertLink();
	  addnewcategoryPOM.clickManageCategoryLink();
	  addnewcategoryPOM.addNewCategoryBtn();
	  addnewcategoryPOM.setCategoryName("Special Offer Test8");
	  addnewcategoryPOM.clickSubmitCategoryBtn();
	  Thread.sleep(2000);
	  Alert alert = driver.switchTo().alert();
	  alert.accept();
	  expected = addnewcategoryPOM.membernewcategory.getAttribute("value");
	  System.out.println("new category name is : " + expected);
  }
  @Test(priority = 3) //adding new advertisement with the new category using admin login
  public void addAdvertisement() throws InterruptedException{
	  addnewcategoryPOM.clickHomeLink();
	  addnewcategoryPOM.sendMemberLogin("artish");
	  addnewcategoryPOM.manageAdvertBtn();
	  addnewcategoryPOM.addNewAdvert();
	  addnewcategoryPOM.addTitle("new offer");
	  adminadverttitle = addnewcategoryPOM.adverttitle.getAttribute("value");
	  addnewcategoryPOM.addCategory(expected);
	  addnewcategoryPOM.addPrice("78");
	  Thread.sleep(1000);
	  addnewcategoryPOM.selectCheckbox();
	  addnewcategoryPOM.description("Test description for new offer");
	  addnewcategoryPOM.submitnewAdvert();
	  Alert alert = driver.switchTo().alert();
	  alert.accept();
	  addnewcategoryPOM.adminLogout();
	  Alert alertlogout = driver.switchTo().alert();
	  alertlogout.accept();
  }
  @Test(priority = 4) //login using member and check whether new advertisement is available in the list of adverts
  public void checkAdvertMember(){
	  addnewcategoryPOM.sendUserName("artish");
	  addnewcategoryPOM.sendPassword("12345");
	  addnewcategoryPOM.clickLoginBtn();
	  addnewcategoryPOM.clickpersonal();
	  addnewcategoryPOM.clickMemberAdvertisementLink();
	 List<WebElement>memberadvertlist = driver.findElements(By.xpath("//tbody/tr/td/div[@class = 'productTitle']"));
		ArrayList<String> searchAdvertTitle = new ArrayList<String>(); // initializing array for entering all the advertisement titles on the page
		if (memberadvertlist.size() != 0) { //adding the advertisement title on the webpage in a string array
			for (int j = 0; j < memberadvertlist.size(); j++) {
				try {
					WebElement membergetadverttitle = memberadvertlist.get(j);
					adverttitle = membergetadverttitle.getText();  
					searchAdvertTitle.add(adverttitle); // saving in string array
				} catch (Exception e) {
					System.out.println("Exception Message : " + e.getMessage());
				}
			}
			if (searchAdvertTitle != null) { // checking if advertisement title is visible or not
												
				if (searchAdvertTitle.contains(adminadverttitle)) {
					driver.findElement(By.linkText(adminadverttitle)).click();
					actual = driver.findElement(By.xpath("//tbody/tr[4]/td[2]")).getText();
					
					} else {
					actual = "Advertisement (" + adminadverttitle + ") is not in the list";
					//screenShot.captureScreenShot("advertinlist.png");
				}
			}
		} else {
			actual = "No advertisement in the list";
			//screenShot.captureScreenShot("noadvertvisible.png");
		}
		Assert.assertEquals(actual, expected);
	 }
  
  @BeforeClass
  public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		addnewcategoryPOM = new AddNewAdvertCategoryCYTC_069POM(driver); 
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
