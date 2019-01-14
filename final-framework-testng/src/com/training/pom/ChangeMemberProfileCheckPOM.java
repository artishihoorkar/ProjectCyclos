package com.training.pom;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ChangeMemberProfileCheckPOM {
	private WebDriver driver; 
	
	public ChangeMemberProfileCheckPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="cyclosUsername")
	private WebElement userName; 
	
	@FindBy(id="cyclosPassword")
	private WebElement password;
	
	@FindBy(xpath= "//*[@id='cyclosLogin']/table/tbody/tr[3]/td/input")
	private WebElement loginBtn; 
	
	@FindBy(xpath = "//*[contains(text(),'Personal')]")
	private WebElement personal;
	
	@FindBy(xpath = "//*[contains(text(),'Profile')]")
	private WebElement profile;
	
	@FindBy(xpath = ".//*[@id='modifyButton']")
	private WebElement modify;
	
	@FindBy(id = "saveButton")
	private WebElement savebtn;
	
	@FindBy(xpath = "//input[@name='member(name)']")
	private WebElement fullname;
	
	public void sendUserName(String userName) {
		this.userName.clear();
		this.userName.sendKeys(userName);
	}
	
	public void sendPassword(String password) {
		this.password.clear(); 
		this.password.sendKeys(password); 
	}
	
	public void clickLoginBtn() {
		this.loginBtn.click(); 
	}
	
	public void clickpersonal() {
		this.personal.click();
	}
	public void clickProfile(){
		this.profile.click();
	}
	public void changeFullName(String fullname){
		this.modify.click();
		this.fullname.clear();
		this.fullname.sendKeys(fullname);
	}
	public void submitProfileChange() throws InterruptedException{
		this.savebtn.click();
		Thread.sleep(2000);
	}
	public void confirmProfileChange(){
		Alert alert = driver.switchTo().alert();
		String alerttext = alert.getText();
		String expected = "Profile modified";
		Assert.assertEquals(alerttext, expected);
		alert.accept();
	}
	
}
