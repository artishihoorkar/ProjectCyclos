package com.training.pom;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckMemberLoanDetailsPOM {
	
private WebDriver driver;
	//constructor to initialize the driver
	public CheckMemberLoanDetailsPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="cyclosUsername") //capture user name on the login page
	private WebElement userName; 
	
	@FindBy(id="cyclosPassword") //capture password on the login page
	private WebElement password;
	
	@FindBy(xpath= "//*[@id='cyclosLogin']/table/tbody/tr[3]/td/input") //capture submit button on the login page
	private WebElement loginBtn;
	
	@FindBy(id = "memberUsername")
	private WebElement paytomembermemberusername;
	
	@FindBy(xpath = "//div[@id='tdContents']/table/tbody/tr[2]/td/table/tbody/tr[8]/td/fieldset/table/tbody/tr/td[4]/input")
	private WebElement grantloansubmitbuttononpage;
	
	@FindBy(id = "amount")
	private WebElement loanamount;
	
	@FindBy(id = "description")
	private WebElement loandescription;
	
	@FindBy(xpath = "//div[@id='tdContents']/form/table/tbody/tr[2]/td/table/tbody/tr[17]/td/input")
	private WebElement loansubmitbutton;

	@FindBy(xpath = "//input[@value='Submit']")
	private WebElement confirmloansubmitbutton;
	
	@FindBy(xpath = "//div[@id='tdContents']/table/tbody/tr[2]/td/table/tbody/tr[8]/td/fieldset/table/tbody/tr/td[2]/input")
	private WebElement viewloansubmitbtnonpage;
	
	@FindBy(xpath="//li[@id='menu15']/span[2]")
	private WebElement logoutlink;
	
	@FindBy(xpath="//li[@id='menu2']/span[2]")
	private WebElement accountlink;
	
	@FindBy(xpath = "//li[@id='submenu2.3']/span[2]")
	private WebElement loanlink;
	
	
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
	//Enter other member id on the member payment page
	public void enterLoginName(String loginname)
	{
		this.paytomembermemberusername.clear();
		this.paytomembermemberusername.sendKeys(loginname);
	}
	//Enter amount on the admin loan payment page
	public void enterLoanAmount(String loanamount){
		this.loanamount.clear();
		this.loanamount.sendKeys(loanamount);
	}
	//Enter description of the admin loan payment page
	public void enterDescription(String loandescription){
		this.loandescription.clear();
		this.loandescription.sendKeys(loandescription);
	}
	//click on submit button of member payment page
	public void clickGrantLoanSubmitBtn(){
		this.grantloansubmitbuttononpage.click();
	}
	public void clickLoanSubmitBtn(){
		this.loansubmitbutton.click();
	}
	//click on the submit button on the transaction confirmation page
	public void clickConfirmLoanSubmitBtn(){
		this.confirmloansubmitbutton.click();
	}
	public void viewLoanSubmitBtn(){
		this.viewloansubmitbtnonpage.click();
	}
	public void clickLoanLink(){
		this.loanlink.click();
	}
	public void adminLogout(){
		this.logoutlink.click();
		Alert logoutalert = driver.switchTo().alert();
		logoutalert.accept();
	}
}
