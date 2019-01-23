package com.training.pom;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MemberConfirmPasswordCheckPOM {
		private WebDriver driver; 
		//constructor to initialize the driver
		public MemberConfirmPasswordCheckPOM(WebDriver driver) {
			this.driver = driver; 
			PageFactory.initElements(driver, this);
		}
		
		@FindBy(id="cyclosUsername") //capture user name on the login page
		private WebElement userName; 
		
		@FindBy(id="cyclosPassword") //capture password on the login page
		private WebElement password;
		
		@FindBy(xpath= "//*[@id='cyclosLogin']/table/tbody/tr[3]/td/input") //capture submit button on the login page
				
		private WebElement loginBtn; 
		
		@FindBy(xpath = "//*[contains(text(),'Personal')]") //capture Personal link on the home page
		private WebElement personal;
		
		@FindBy(xpath = "//*[contains(text(),'Change password')]") //capture change password link under personal link
		private WebElement chgpwd;
		
		@FindBy(name = "oldPassword") //capturing old password
		private WebElement oldpwd;
		
		@FindBy(name = "newPassword") //capturing new password
		public WebElement newpwd;
		
		@FindBy(name = "newPasswordConfirmation") //capturing confirmation password
		public WebElement newpwdconf;
		
		@FindBy(xpath = ".//*[@id='changePasswordForm']/table/tbody/tr[2]/td/table/tbody/tr[5]/td/input")// capturing submit button on confirm password page
		private WebElement submit;
		
		@FindBy(xpath = "//*[contains(text(),'Logout')]")// click on logout link on the home page. to be used for LogoutTest.java
		private WebElement logout;
		
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
		//submit the login page
		public void clickLoginBtn() {
			this.loginBtn.click(); 
		}
		//click on personal link on the home page
		public void clickpersonal() {
			this.personal.click();
		}
		//click on change password link
		public void clickchangepassword() {
			this.chgpwd.click();
		}
		//enter old password
		public void oldpassword(String oldpwd) {
			this.oldpwd.clear();
			this.oldpwd.sendKeys(oldpwd);
		}
		//enter new password
		public void newpassword(String newpwd) {
			this.newpwd.clear(); 
			this.newpwd.sendKeys(newpwd);		
		}
		//confirm new password
		public void confirmpassword(String newpwdconf) {
			this.newpwdconf.clear(); 
			this.newpwdconf.sendKeys(newpwdconf);
		}
		//submit form on change password page
		public void clickSubmitBtn() {
			this.submit.click();
		}
		//logout check method used in Logout.java
		public void logout(){
			this.logout.click();
		}
}
		
