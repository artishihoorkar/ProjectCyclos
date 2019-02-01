package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import com.mysql.jdbc.StringUtils;

public class CheckAdvertInvalidValuesCYTC_070POM {
	private WebDriver driver;
	public String actual;
	//constructor to initialize the driver
	public CheckAdvertInvalidValuesCYTC_070POM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="cyclosUsername") //capture user name on the login page
	private WebElement userName; 
	
	@FindBy(id="cyclosPassword") //capture password on the login page
	private WebElement password;
	
	@FindBy(xpath= "//*[@id='cyclosLogin']/table/tbody/tr[3]/td/input") //capture submit button on the login page
	private WebElement loginBtn;
	
	@FindBy(id ="memberUsername")
	private WebElement memberusername;
	
	@FindBy(xpath = "//div[@id='tdContents']/table/tbody/tr[2]/td/table/tbody/tr[4]/td/fieldset/table/tbody/tr/td[2]/input")
	private WebElement manageadvertsubmitbtn;
	
	@FindBy(id = "newButton")
	private WebElement insertnewadsubmitbtn;
	
	@FindBy(xpath = "//*[@id='tdContents']/form/table[1]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/input")
	private WebElement adverttitle;
	
	@FindBy(xpath = "//*[@id='tdContents']/form/table[1]/tbody/tr[2]/td/table/tbody/tr[3]/td[2]/select")
	private WebElement selectcategory;
	
	@FindBy(xpath = "//*[@id='tdContents']/form/table[1]/tbody/tr[2]/td/table/tbody/tr[4]/td[2]/input[1]")
	private WebElement price;
	
	@FindBy(id="notExpirableCheck")
	private WebElement notexpirablecheckbox;
	
	@FindBy(id ="cke_contents_descriptionText") 
	private WebElement addescription;
	
	@FindBy(id = "saveButton")
	private WebElement saveadvertbtn;
	
	@FindBy(id="backButton")
	private WebElement backbtn;
	
	@FindBy(xpath = "//li[@id='menu0']/span[2]")
	private WebElement homelink;
			
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
	public void sendMemberProfile(String memberusername){
		this.memberusername.clear();
		this.memberusername.sendKeys(memberusername);
	}
public void clickManageAdvertisementLink(){
	this.manageadvertsubmitbtn.click();
}
public void sendAdTitle(String title){
	this.adverttitle.clear();
	this.adverttitle.sendKeys(title);
}
public void selectCategory(String text){
	Select category = new Select(selectcategory);
	category.selectByVisibleText(text);
}
public void sendPrice(String price){
	this.price.clear();
	this.price.sendKeys(price);
}
public void selectNotExpirableCheckBox(){
	this.notexpirablecheckbox.click();
}
	public void enterDescription(String txt){
		Actions actions = new Actions(driver);
		actions.moveToElement(addescription);
		actions.click();
		actions.sendKeys(txt);
		actions.build().perform();
	}
	public void clickSubmitAdvertBtn(){
		this.saveadvertbtn.click();
	}
	public void goBackToAdvertList(){
		this.backbtn.click();
	}
	public void addNewAdvert(){
		this.insertnewadsubmitbtn.click();
	}
	public void clickHome() {
		this.homelink.click();
	}
	
	public void testassert(String uname, String title, String text, String price, String desc){
		if(StringUtils.isNullOrEmpty(title)){
				actual = "Title is required"+"\n";
			}
			if(StringUtils.isNullOrEmpty(text)){
				actual = "Category is required"+"\n";
			}
			if(StringUtils.isNullOrEmpty(desc)){
				actual = "Description is required"+"\n";
			}
			if(StringUtils.isNullOrEmpty(title)&&StringUtils.isNullOrEmpty(text)){
				actual = "Title is required"
						+ "\n"
						+ "Category is required"+"\n";
			}
			if(StringUtils.isNullOrEmpty(title)&&StringUtils.isNullOrEmpty(desc)){
				actual = "Title is required"
						+ "\n"
						+ "Description is required"+"\n";
			}
			if(StringUtils.isNullOrEmpty(text)&&StringUtils.isNullOrEmpty(desc)){
				actual = "Description is required"
						+ "\n"
						+ "Category is required"+"\n";
			}
			if(StringUtils.isNullOrEmpty(title)&&StringUtils.isNullOrEmpty(text)&&StringUtils.isNullOrEmpty(desc)){
				actual = "Title is required "
						+ "\n"
						+ "Category is required"
						+"/n"
						+ "Description is required"+"\n";
			}
		
	}
}
