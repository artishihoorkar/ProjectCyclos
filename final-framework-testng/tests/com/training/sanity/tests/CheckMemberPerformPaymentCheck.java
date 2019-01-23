package com.training.sanity.tests;

import org.testng.annotations.Test;


import com.training.generics.ScreenShot;
import com.training.pom.CheckMemberPerformPaymentPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class CheckMemberPerformPaymentCheck {
	private WebDriver driver;
	private String baseUrl;
	private static Properties properties;
	private ScreenShot screenShot;
	private CheckMemberPerformPaymentPOM performpaymentPOM;
	String expected;

		
	@Test(priority =1) //check user login
	public void checklogin() throws InterruptedException{
		  performpaymentPOM.sendUserName("artish");
		  performpaymentPOM.sendPassword("12345");
		  performpaymentPOM.clickLoginBtn();
		  performpaymentPOM.clickAccountLink();
		  Thread.sleep(2000);
		  performpaymentPOM.clickMemberPaymentLink();
	}
  @Test(priority =2,dataProvider = "dp") //enter loan details for other member
  public void checkPerformPayment(String lgn, String amt, String desc) throws InterruptedException {
	  performpaymentPOM.enterLoginName(lgn);
	  Thread.sleep(2000);
	  performpaymentPOM.enterAmount(amt);
	  performpaymentPOM.enterDescription(desc);
	  expected = driver.findElement(By.id("description")).getAttribute("value"); //capturing loan description
	  performpaymentPOM.clickSubmitBtn();
	  performpaymentPOM.clickConfirmTransactionSubmitBtn();
	  performpaymentPOM.clickAccountInformationLink();
	  screenShot.captureScreenShot("confirmpayment.png");
  }
  @Test(priority =3) //check loan details on account information page.
  public void checkMemberPayments(){
	  	performpaymentPOM.clickAccountInformationLink();
		List <WebElement> maccinfosearchresults = driver.findElements(By.xpath("//div[@id='tdContents']/table/tbody/tr/td/table/tbody/tr/td"));
		String actual = maccinfosearchresults.get(4).getText();
		screenShot.captureScreenShot("paymentresult.png");
		Assert.assertEquals(actual, expected); //comparing loan description
	}
    @BeforeClass
  public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		performpaymentPOM = new CheckMemberPerformPaymentPOM(driver); 
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}
  
  @DataProvider //providing data for loan payment to member
  public Object[][] dp() {
    return new Object[][] {
      new Object[] {"Soumita123","1.0","Test1"},
     //{"Soumita123","2.0","Test2"},
     //{"Soumita123","3.0","Test3"},
    };
  }
  @AfterTest
  public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}

}
