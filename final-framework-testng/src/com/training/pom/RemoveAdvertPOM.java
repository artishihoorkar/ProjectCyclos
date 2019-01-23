package com.training.pom;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RemoveAdvertPOM {
private WebDriver driver;
String adverttitle;
String removeadverttitle;
WebElement memberadverttitle;
List <WebElement>advertsearchresults;
	//constructor to initialize the driver
	public RemoveAdvertPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="cyclosUsername") //capture user name on the login page
	private WebElement userName; 
	
	@FindBy(id="cyclosPassword") //capture password on the login page
	private WebElement password;
	
	@FindBy(xpath= "//*[@id='cyclosLogin']/table/tbody/tr[3]/td/input") //capture submit button on the login page
	private WebElement loginBtn;
	
	@FindBy(xpath= "//li[@id='menu1']/span[2]")
	private WebElement personal;
	
	@FindBy(xpath="//li[@id='submenu1.2']/span[2]")
	private WebElement advertisement;
	
	@FindBy(id ="memberUsername")
	private WebElement memberusername;
	
	@FindBy(xpath = "//div[@id='tdContents']/table/tbody/tr[2]/td/table/tbody/tr[4]/td/fieldset/table/tbody/tr/td[2]/input")
	private WebElement manageadvertsubmitbtn;
	
	@FindBy(xpath = "//img[@title='Remove']")
	public WebElement removeimage;
	
	@FindBy(xpath="//li[@id='menu15']/span[2]")
	private WebElement logoutlink;
	
	/*@FindBy(xpath = "//div[@id='tdContents']/table/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/div/a")
	public WebElement advertpresent;*/
	
	public void sendUserName(String userName) {
		this.userName.clear();
		this.userName.sendKeys(userName);
	}
	//enter password on login page
	public void sendPassword(String password) {
		this.password.clear(); 
		this.password.sendKeys(password); 
	}
	//submit button on login page
	public void clickLoginBtn() {
		this.loginBtn.click(); 
	}
	public void clickPersonalLink(){
		this.personal.click();
	}
	public void clickAdvertisementLink(){
		this.advertisement.click();
	}
	public void clickRemoveImage(){
		this.removeimage.click();
		Alert confirmremoveadalert = driver.switchTo().alert();
		confirmremoveadalert.accept();
		Alert removealertmsg = driver.switchTo().alert();
		removealertmsg.accept();
	}
	public void sendMemberProfile(String memberusername){
		this.memberusername.clear();
		this.memberusername.sendKeys(memberusername);
	}
	public void adminLogout(){
		this.logoutlink.click();
	}
}
