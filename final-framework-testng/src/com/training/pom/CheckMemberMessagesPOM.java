package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CheckMemberMessagesPOM {
	private WebDriver driver;
	//constructor to initialize the driver
	public CheckMemberMessagesPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="cyclosUsername") //capture user name on the login page
	private WebElement userName; 
	
	@FindBy(id="cyclosPassword") //capture password on the login page
	private WebElement password;
	
	@FindBy(xpath= "//*[@id='cyclosLogin']/table/tbody/tr[3]/td/input") //capture submit button on the login page
	private WebElement loginBtn;
	
	@FindBy(xpath="//li[@id='menu8']/span[2]")
	private WebElement adminmessageslink;
	
	@FindBy(xpath = "//li[@id='submenu8.0']/span[2]")
	private WebElement adminmessagessublink;
	
	@FindBy(id = "newButton")
	private WebElement composemessagebtn;
	
	@FindBy(xpath = "//div[@id='tdContents']/form/table/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/input[2]")
	private WebElement memberusername;
	
	@FindBy(id = "subjectText")
	private WebElement messagesubject;
	
	@FindBy(id="categorySelect")
	public Select categoryselect;
	
	@FindBy(id ="cke_contents_bodyText") 
	private WebElement messagebody;
	
	@FindBy(xpath = "//div[@id='tdContents']/form/table/tbody/tr[2]/td/table/tbody/tr[8]/td/input")
	public WebElement sendmessagebtn;
	
	@FindBy(xpath = "//li[@id='menu15']/span[2]")
	public WebElement logoutlink;
	
	@FindBy(xpath= "//li[@id='menu1']/span[2]")
	private WebElement memberpersonallink;
	
	@FindBy(xpath="//li[@id='submenu1.1']")
	private WebElement membermessageslink;
	
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
	public void clickMessageLink(){
		this.adminmessageslink.click();
	}
	public void clickMessageSublink(){
		this.adminmessagessublink.click();
	}
	public void clickComposeMessage(){
		this.composemessagebtn.click();
	}
	public void sendMemberUserName(String musername){
		this.memberusername.sendKeys(musername);
	}
	/*public void enterCategory(String text){
		Actions actiondd = new Actions(driver);
		this.categoryselect.selectByVisibleText(text);
	}*/
	public void enterSubject(String sub){
		this.messagesubject.sendKeys(sub);
	}
	public void enterText(String txt){
		Actions actions = new Actions(driver);
		actions.moveToElement(messagebody);
		actions.click();
		actions.sendKeys(txt);
		actions.build().perform();
	}
	public void clickSendMessage(){
		this.sendmessagebtn.click();
	}
	public void clickLogout(){
		this.logoutlink.click();
	}
	public void clickMemberPersonalLink(){
		this.memberpersonallink.click();
	}
	public void clickMemberMessagesLink(){
		this.membermessageslink.click();
	}
}

