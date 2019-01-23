package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CheckMemberPerformPaymentPOM {
	private WebDriver driver;
	
	
	//constructor to initialize the driver
	public CheckMemberPerformPaymentPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="cyclosUsername") //capture user name on the login page
	private WebElement userName; 
	
	@FindBy(id="cyclosPassword") //capture password on the login page
	private WebElement password;
	
	@FindBy(xpath= "//*[@id='cyclosLogin']/table/tbody/tr[3]/td/input") //capture submit button on the login page
	private WebElement loginBtn;
	
	@FindBy(xpath = "//span[@class='menuText'][contains(text(),'Account')]")
	private WebElement accountlink;
	
	@FindBy(xpath = "//li[@id='submenu2.4']/span[2]")
	private WebElement memberpaymentlink;
	
	@FindBy(id = "memberUsername")
	private WebElement paytomembermemberusername;
	
	@FindBy(id = "amount")
	private WebElement paytomemberamount;
	
	@FindBy(id = "description")
	private WebElement paytomemberdescription;
	
	@FindBy(id = "submitButton")
	private WebElement paytomembersubmitbutton;
	
	@FindBy(xpath = "//div[@id='tdContents']/form/table/tbody/tr[2]/td/table/tbody/tr[6]/td/input")
	private WebElement confirmtransactionsubmitbutton;
	
	@FindBy(xpath="//span[contains(text(),'Account Information')]")
	private WebElement accountinfolink;
	
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
	//click on account link on the home page
	public void clickAccountLink() {
		this.accountlink.click();
	}
	//click on member payment link on the home page
	public void clickMemberPaymentLink(){
		this.memberpaymentlink.click();
	}
	//Enter other member id on the member payment page
	public void enterLoginName(String loginname)
	{
		this.paytomembermemberusername.clear();
		this.paytomembermemberusername.sendKeys(loginname);
	}
	//Enter amount on the member payment page
	public void enterAmount(String amount){
		this.paytomemberamount.clear();
		this.paytomemberamount.sendKeys(amount);
	}
	//Enter description of the payment
	public void enterDescription(String description){
		this.paytomemberdescription.clear();
		this.paytomemberdescription.sendKeys(description);
	}
	//click on submit button of member payment page
	public void clickSubmitBtn(){
		this.paytomembersubmitbutton.click();
	}
	//click on the submit button on the transaction confirmation page
	public void clickConfirmTransactionSubmitBtn(){
		this.confirmtransactionsubmitbutton.click();
	}
	public void clickAccountInformationLink(){
		this.accountinfolink.click();
	}
}

