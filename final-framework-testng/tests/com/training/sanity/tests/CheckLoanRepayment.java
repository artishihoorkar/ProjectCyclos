package com.training.sanity.tests;

import org.testng.annotations.Test;
import com.training.generics.ScreenShot;
import com.training.pom.CheckLoanRepaymentPOM;
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

public class CheckLoanRepayment {
	private WebDriver driver;
	private String baseUrl;
	private CheckLoanRepaymentPOM loanrepaymentPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	private String expected;

	
@Test (priority=1) //check member login
	  public void checkLogin() throws Exception {
		  loanrepaymentPOM.sendUserName("artish");
		  loanrepaymentPOM.sendPassword("12345");
		  loanrepaymentPOM.clickLoginBtn();  	  
	  }
	
  @Test (priority=2) //perform loan repayment
  public void loanRemainingLoanBalance() throws NumberFormatException, InterruptedException {
	  loanrepaymentPOM.clickAccountLink();
	  Thread.sleep(1000);
	  loanrepaymentPOM.clickLoanLink();
	  loanrepaymentPOM.clickViewLoanButton();
	  //couldn't convert string amount to integer
	  /*String amountprepaid_str = driver.findElement(By.id("amountText")).getAttribute("value");
	  amountprepaid_str.replaceAll(",", ".");
	  float amountrepaid = Float.valueOf(amountprepaid_str);
	  System.out.println(amountprepaid_str);
	  double loanamount = Integer.parseInt(driver.findElement(By.xpath("//div[@id='tdContents']/table/tbody/tr[2]/td/table/tbody/tr[4]/td[2]")).getText());*/
	  loanrepaymentPOM.clickLoanRepaymentBtn();
	  Thread.sleep(2000);
	  Alert repaymentalert = driver.switchTo().alert();
	  repaymentalert.accept();
	  Alert repaymentconf = driver.switchTo().alert();
	  repaymentconf.accept();
	  List <WebElement> loanrepaymentdetails = driver.findElements(By.xpath("//div[@id='tdContents']/table/tbody/tr/td/table/tbody/tr/td")); //capture date of loan repayment
	  expected = loanrepaymentdetails.get(15).getText();
	  //double expected = Integer.parseInt(driver.findElement(By.xpath("//div[@id='tdContents']/table/tbody/tr[2]/td/table/tbody/tr/td[2]")).getText());
	 // double accountbal = loanamount - amountrepaid;
	  //double actual =  accountbal;
	 }
  
  @Test (priority=3) //check loan repayments from account information page
  public void checkLoanRepayment() throws InterruptedException {
	  loanrepaymentPOM.clickAccountInfoLink();
	  WebElement accountbalance = driver.findElement(By.xpath("//div[@id='tdContents']/table/tbody/tr[2]/td/table/tbody/tr/td[2]")); //capturing account balance
	  accountbalance.getText();
	  WebElement viewloanimage = driver.findElement(By.xpath("//div[@id='tdContents']/table/tbody/tr[3]/td/table/tbody/tr[2]/td[5]")); //
	  Thread.sleep(1000);
	  viewloanimage.click();
	  List <WebElement> loandetails = driver.findElements(By.xpath("//div[@id='tdContents']/table/tbody/tr[2]/td/table/tbody/tr/td"));//capturing loan details
	  screenShot.captureScreenShot("checkloanrepayment.png");
	   String actual = loandetails.get(1).getText(); //getting the date of loan repayment from loan details
	   Assert.assertEquals(actual,expected); //comparing dates of loan repayment from loan repayment loan and account information page.
  }
    
  @BeforeClass
  public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		loanrepaymentPOM = new CheckLoanRepaymentPOM(driver);
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

}
