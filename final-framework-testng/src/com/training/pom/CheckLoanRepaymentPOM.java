package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckLoanRepaymentPOM {
	private WebDriver driver;
	//constructor to initialize the driver
	public CheckLoanRepaymentPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="cyclosUsername") //capture user name on the login page
	private WebElement userName; 
	
	@FindBy(id="cyclosPassword") //capture password on the login page
	private WebElement password;
	
	@FindBy(xpath= "//*[@id='cyclosLogin']/table/tbody/tr[3]/td/input") //capture submit button on the login page
	private WebElement loginBtn;
	
	@FindBy(xpath="//li[@id='menu2']/span[2]")
	private WebElement accountlink;
	
	@FindBy(xpath = "//li[@id='submenu2.3']/span[2]")
	private WebElement loanlink;
	
	@FindBy(xpath = "//div[@id='tdContents']/table/tbody/tr[2]/td/table/tbody/tr[2]/td[4]/img")
	private WebElement viewbtn;
	
	@FindBy(xpath = "//form[@id='repayForm']/table/tbody/tr[3]/td/input")
	private WebElement loanrepaybtn;
	
	@FindBy(xpath = "//li[@id='submenu2.0']/span[2]")
	private WebElement accountinfolink;
	
	@FindBy(xpath="//div[@id='tdContents']/table/tbody/tr[2]/td/table/tbody/tr[4]/td[2]")
	public WebElement loanamount;
	
	@FindBy(xpath = "//div[@id='tdContents']/table/tbody/tr[2]/td/table/tbody/tr[4]/td[4]")
	public WebElement accountbal;
	
	@FindBy(id = "amounttext")
	public WebElement amountrepaid;
		
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
	public void clickLoanLink() {
		this.loanlink.click();
	}
	public void clickViewLoanButton(){
		this.viewbtn.click();
	}
	public void clickLoanRepaymentBtn(){
		this.loanrepaybtn.click();
	}
	public void clickAccountInfoLink(){
		this.accountinfolink.click();
	}
	
}
