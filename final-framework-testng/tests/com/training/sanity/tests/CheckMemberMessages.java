package com.training.sanity.tests;


import org.testng.annotations.Test;
import java.util.List;

import com.training.generics.ScreenShot;

import com.training.pom.CheckMemberMessagesPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class CheckMemberMessages {
	private WebDriver driver;
	private String baseUrl;
	private static Properties properties;
	private ScreenShot screenShot;
	private CheckMemberMessagesPOM messagesPOM;
	private String expected;
	
	@Test(priority =1) //checking admin login
	public void checkAdminLogin() throws InterruptedException{
		messagesPOM.sendUserName("admin");
		messagesPOM.sendPassword("12345");
		messagesPOM.clickLoginBtn();
		messagesPOM.clickMessageLink();
		messagesPOM.clickMessageSublink();
	}
	@Test(priority =2, dataProvider = "dp") //composing message from admin login using dataprovider
	public void composeMessageCheck(String musername, String text, String sub, String body) throws InterruptedException{
		messagesPOM.clickComposeMessage();
		messagesPOM.sendMemberUserName(musername); //sending username
		Thread.sleep(3000);
		//Cannot identify the category dropdown
		/*WebElement categoryselect = driver.findElement(By.id("categorySelect"));
		Actions action = new Actions(driver);
		action.moveToElement(categoryselect);
		action.build().perform();
		Select catsel = new Select(categoryselect);
		catsel.selectByVisibleText(text);
		Thread.sleep(3000);*/
		messagesPOM.enterSubject(sub); //entering subject
		Thread.sleep(2000);
		messagesPOM.enterText(body); //entering message body
		Thread.sleep(2000);
		expected = driver.findElement(By.id("subjectText")).getAttribute("value"); //saving the subject of message in string
		messagesPOM.clickSendMessage(); //sending the message to the member
		Alert confirmmsgsent = driver.switchTo().alert();
		confirmmsgsent.accept(); //confirm message sent
	}
	@Test(priority =3) //checking admin logout
	public void checkAdminLogout() throws InterruptedException{
		messagesPOM.clickLogout();	
		Alert logoutalert = driver.switchTo().alert();
		logoutalert.accept();
	}
	@Test(priority =4) //check member login and message from member profile
	public void checkMemberLogin() throws InterruptedException{
		messagesPOM.sendUserName("artish");
		messagesPOM.sendPassword("12345");
		messagesPOM.clickLoginBtn();
		messagesPOM.clickMemberPersonalLink();
		Thread.sleep(3000);
		messagesPOM.clickMemberMessagesLink();
		Thread.sleep(3000);
		WebElement message = driver.findElement(By.xpath("//div[@id='tdContents']/form[2]/table/tbody/tr[2]/td/table/tbody/tr[2]/td[3]/table/tbody/tr/td[2]/a"));//first link of on the message list
		message.click();
		List <WebElement> messagecontents = driver.findElements(By.xpath("//div[@id='tdContents']/table/tbody/tr[2]/td/table/tbody/tr/td[2]"));//message details page
		screenShot.captureScreenShot("checkmembermessages.png");
		String actual  = messagecontents.get(4).getText(); //getting the message subject from message details
		Assert.assertEquals(actual,expected);
	}

  @BeforeClass //initial setup
  public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		messagesPOM = new CheckMemberMessagesPOM(driver); 
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}

  @AfterTest //quit browser
  public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
  @DataProvider //data provider for composing the messages
  public Object[][] dp() {
    return new Object[][] {
      new Object[] {"artish","Administrator", "test sub1","samplemessge1"},
      {"artish", "Administrator", "test sub1", "samplemessage2"
      }
    };
  }

}
