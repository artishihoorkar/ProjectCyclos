package com.training.sanity.tests;

import org.testng.annotations.Test;


import com.training.generics.ScreenShot;
import com.training.pom.CheckMemberLoanDetailsPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

public class CheckMemberLoanDetails {
	private WebDriver driver;
	private String baseUrl;
	private static Properties properties;
	private ScreenShot screenShot;
	private CheckMemberLoanDetailsPOM loandetailsPOM;
	String expected;
	
@Test(priority =1) //check admin login
		public void checkAdminLogin() throws InterruptedException{
			loandetailsPOM.sendUserName("admin");
			loandetailsPOM.sendPassword("12345");
			loandetailsPOM.clickLoginBtn();
			loandetailsPOM.enterLoginName("artish");
		}
  @Test(priority =2, dataProvider = "dp") //grant loan from admin page
  public void checkGrantLoan(String amt, String desc) throws InterruptedException {
	  
	  loandetailsPOM.clickGrantLoanSubmitBtn();
	  loandetailsPOM.enterLoanAmount(amt);
	  loandetailsPOM.enterDescription(desc);
	  loandetailsPOM.clickLoanSubmitBtn();	
	  loandetailsPOM.clickConfirmLoanSubmitBtn();
	  Alert confirmloanalert = driver.switchTo().alert();
	  confirmloanalert.accept();
	  Thread.sleep(5000);
}
  @Test(priority =3) //check loan page on admin page
  public void checkViewLoans(){
	  loandetailsPOM.viewLoanSubmitBtn();
	  List <WebElement> adminloansearchresults = driver.findElements(By.xpath("//div[@id='tdContents']/table/tbody/tr[2]/td/table/tbody/tr/td[1]"));
		expected = adminloansearchresults.get(0).getText();
  }
  @Test(priority =4) // check admin login
  public void checkAdminLogout(){
	  loandetailsPOM.adminLogout();
  }
  @Test(priority =5) //check memberlogin
	public void checkMemberLogin() throws InterruptedException{
		loandetailsPOM.sendUserName("artish");
		loandetailsPOM.sendPassword("12345");
		loandetailsPOM.clickLoginBtn();
	}
  @Test(priority =6) //check member loans page
  public void checkMemberLoans(){
	  loandetailsPOM.clickAccountLink();
	  loandetailsPOM.clickLoanLink();
		List <WebElement> loansearchresults = driver.findElements(By.xpath("//div[@id='tdContents']/table/tbody/tr[2]/td/table/tbody/tr[2]/td"));
					String actual = loansearchresults.get(0).getText();
					screenShot.captureScreenShot("loandetails.png");
					Assert.assertEquals(actual, expected);//checking titles of loan granted from admin and loan details on member page
		}
    @BeforeClass //initial setup
  public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		loandetailsPOM = new CheckMemberLoanDetailsPOM(driver); 
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}
  
  @DataProvider //data provider for grant loan
  public Object[][] dp() {
    return new Object[][] {
      new Object[] {"1.0","Test1"},
      //{"2.0","Test2"},
     //{"3.0","Test3"},
    };
  }
  @AfterTest //close driver
  public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}

}
