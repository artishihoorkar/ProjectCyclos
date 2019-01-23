package com.training.sanity.tests;

import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.RemoveAdvertPOM;
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

public class RemoveAdvert {
	private WebDriver driver;
	private String baseUrl;
	private RemoveAdvertPOM removeadvertPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	WebElement memberadverttitle;
	WebElement getadverttitle;
	List <WebElement> membersearchresults;
	String actual, expected;
	String adverttitle;

	@BeforeClass // driver initialization
	public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		removeadvertPOM = new RemoveAdvertPOM(driver);
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

	@Test (priority =1) //check admin login
	public void checkAdminLogin() {
		removeadvertPOM.sendUserName("admin");
		removeadvertPOM.sendPassword("12345");
		removeadvertPOM.clickLoginBtn();
		removeadvertPOM.sendMemberProfile("artish");
	}
	
	@Test (priority = 2) //checking advertisement for member from admin profile and deleting the title of the first advertisement
	public void clickManageAdvertBtn() throws InterruptedException{
		driver.findElement(By.xpath("//div[@id='tdContents']/table/tbody/tr[2]/td/table/tbody/tr[4]/td/fieldset/table/tbody/tr/td[2]/input")).click();//click on manage advert submit button
		Thread.sleep(3000);
		List <WebElement> advertsearchresults = driver.findElements(By.xpath("//div[@id='tdContents']/table/tbody/tr[2]/td/table/tbody/tr/td[2]/div[1]")); //capturing advertisement list
		if (advertsearchresults !=null && advertsearchresults.size() != 0){
			getadverttitle = driver.findElement(By.xpath("//div[@id='tdContents']/table/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/div/a"));
			adverttitle = getadverttitle.getText();
			screenShot.captureScreenShot("capturetitletoremove.png");
			removeadvertPOM.clickRemoveImage();
			System.out.println(adverttitle + "deleted");
			expected = "Deleted advertisement ("+adverttitle+") is not in the list";
			}
			else {
				expected = "No advertisement in the list";
				System.out.println(expected);
			}
	}		
	@Test (priority = 3) //admin logout
	public void checkAdminLogout() {
		removeadvertPOM.adminLogout();
		Alert logoutalert = driver.switchTo().alert();
		logoutalert.accept();
	}
	@Test (priority = 4) //member login
	public void checkMemberLogin() {
		removeadvertPOM.sendUserName("artish");
		removeadvertPOM.sendPassword("12345");
		removeadvertPOM.clickLoginBtn();
		removeadvertPOM.clickPersonalLink();
		removeadvertPOM.clickAdvertisementLink();
	}
	
	@Test(priority = 6) //checking if deleted advertisement is visible from member profile
	public void findadvert(){
		membersearchresults = driver.findElements(By.xpath("//div[@id='tdContents']/table/tbody/tr[2]/td/table/tbody/tr/td[2]/div[1]")); //capturing the list of advertisements on member profile
		ArrayList <String>searchAdvertTitle = new ArrayList<String>(); //initializing array for entering all the advertisement titles on the page
		if (membersearchresults.size() != 0){
		for (int j = 0; j < membersearchresults.size(); j++){
			try {
				WebElement membergetadverttitle = membersearchresults.get(j);
				String adverttitle = membergetadverttitle.getText(); //getting the title from the list of advertisement
				searchAdvertTitle.add(adverttitle); //saving in string array
			} catch (Exception e) {
				System.out.println("[findadvert] Exception Message : "+e.getMessage());
			}
		}
		if(searchAdvertTitle != null){ //checking if advertisement title is visible or not
			if(searchAdvertTitle.contains(adverttitle)){
				actual = "Deleted advertisement ("+adverttitle+") is in the list";
			}else{
				actual = "Deleted advertisement ("+adverttitle+") is not in the list";
				screenShot.captureScreenShot("advertisementremoved.png");
			}
		}
	}else {
		actual = "No advertisement in the list";
		screenShot.captureScreenShot("noadvertvisible.png");
	}
		assertEquals(actual,expected);
	}
	
}
