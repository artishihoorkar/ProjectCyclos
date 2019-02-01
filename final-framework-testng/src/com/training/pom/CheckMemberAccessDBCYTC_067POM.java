package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CheckMemberAccessDBCYTC_067POM {
private WebDriver driver;
	
	public CheckMemberAccessDBCYTC_067POM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="cyclosUsername") //capture user name on the login page
	private WebElement userName; 
	
	@FindBy(id="cyclosPassword") //capture password on the login page
	private WebElement password;
	
	@FindBy(xpath= "//form[@id='cyclosLogin']/table/tbody/tr[3]/td/input") //capture submit button on the login page
	private WebElement loginBtn;
	
	@FindBy(id = "memberUsername")
	private WebElement membermemberusername;
	
	@FindBy(xpath = "//*[contains(text(),'Change permission group')]//following-sibling::td")
	private WebElement changepermissionsubmitbtn;
	
	@FindBy(xpath = "//div[@id='tdContents']/form/table/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/select")
	private WebElement newgroup;
	
	
	@FindBy(xpath = "//div[@id='tdContents']/form/table/tbody/tr[2]/td/table/tbody/tr[4]/td/input")
	private WebElement changegrpsubmitbtn;
	
	@FindBy(id ="comments")
	private WebElement comments;
	
	@FindBy(xpath = "//li[@id='menu0']/span[2]")
	private WebElement homelink;
	
		//enter user name on login page
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
		public void sendMemberLogin(String uname){
			this.membermemberusername.clear();
			this.membermemberusername.sendKeys(uname);
		}
		public void selectGroup(String value){
			Select options = new Select (newgroup);
			options.selectByVisibleText(value);
		}
		public void clickChangePermissionBtn(){
			this.changepermissionsubmitbtn.click();
		}
		public void clickChangeGroupBtn(){
			this.changegrpsubmitbtn.click();
		}
		public void sendComments(String comments){
			this.comments.sendKeys(comments);
		}
		public void clickHome(){
			this.homelink.click();
		}

}
