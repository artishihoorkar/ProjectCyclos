package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddNewAdvertCategoryCYTC_069POM {
	private WebDriver driver;
	
	//constructor to initialize the driver
	public AddNewAdvertCategoryCYTC_069POM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="cyclosUsername") //capture user name on the login page
	private WebElement userName; 
	
	@FindBy(id="cyclosPassword") //capture password on the login page
	private WebElement password;
	
	@FindBy(xpath= "//*[@id='cyclosLogin']/table/tbody/tr[3]/td/input") //capture submit button on the login page
	private WebElement loginBtn;
	
	@FindBy(xpath = "//li[@id='menu6']/span[2]")
	private WebElement advertlink;
	
	@FindBy(xpath="//li[@id='submenu6.1']/span[2]")
	private WebElement managecategorylink;
	
	@FindBy(id="newButton")
	private WebElement addnewcategorybtn;
	
	@FindBy(xpath = "//tbody/tr[2]/td[2]/textarea")
	private WebElement newcategoryname;
	
	@FindBy(id ="saveButton")
	private WebElement savenewcategorybtn;
	
	@FindBy(xpath = "//li[@id='menu0']/span[2]")
	private WebElement homelink;
	
	@FindBy(id ="memberUsername")
	private WebElement memberusername;
	
	@FindBy(xpath = "//div[@id='tdContents']/table/tbody/tr[2]/td/table/tbody/tr[4]/td/fieldset/table/tbody/tr/td[2]/input")
	private WebElement manageadvertsubmitbtn;
	
	@FindBy(id = "newButton")
	private WebElement insertnewadsubmitbtn;
	
	@FindBy(xpath = "//*[@id='tdContents']/form/table[1]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/input")
	public WebElement adverttitle;
	
	@FindBy(xpath = "//*[@id='tdContents']/form/table[1]/tbody/tr[2]/td/table/tbody/tr[3]/td[2]/select")
	private WebElement selectcategory;
	
	@FindBy(xpath = "//*[@id='tdContents']/form/table[1]/tbody/tr[2]/td/table/tbody/tr[4]/td[2]/input[1]")
	private WebElement price;
	
	@FindBy(id = "notExpirableCheck")
	private WebElement notexpirablecheckbox;
	
	@FindBy(id ="cke_contents_descriptionText") 
	private WebElement addescription;
	
	@FindBy(id = "saveButton")
	private WebElement saveadvertbtn;
	
	@FindBy(id="backButton")
	private WebElement backbtn;
	
	@FindBy(xpath = "//*[contains(text(),'Personal')]")
	private WebElement personal;
	
	@FindBy(xpath="//li[@id='submenu1.2']/span[2]")
	private WebElement advertisement;
	
	@FindBy(xpath="//li[@id='menu15']/span[2]")
	private WebElement logoutlink;
	
	@FindBy(xpath = "//td[2]/input[@name='category(name)']") //capturing new category name from member login which was created by admin
	public WebElement membernewcategory;
	
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
	
	public void clickAdvertLink(){
		this.advertlink.click();
	}
	public void clickManageCategoryLink(){
		this.managecategorylink.click();
	}
	public void addNewCategoryBtn(){
		this.addnewcategorybtn.click();
	}
	public void setCategoryName(String cname){
		this.newcategoryname.clear();
		this.newcategoryname.sendKeys(cname);
	}
	public void clickSubmitCategoryBtn(){
		this.savenewcategorybtn.click();
	}
	public void clickHomeLink(){
		this.homelink.click();
	}
	public void sendMemberLogin(String  mname){
		this.memberusername.sendKeys(mname);
	}
	public void manageAdvertBtn(){
		this.manageadvertsubmitbtn.click();
	}
	public void addNewAdvert(){
		this.insertnewadsubmitbtn.click();
	}
	public void addTitle(String title){
		this.adverttitle.sendKeys(title);
	}
	public void addCategory(String text){
		Select options = new Select(selectcategory);
		options.selectByVisibleText(text);
	}
	public void addPrice(String price){
		this.price.sendKeys(price);
	}
	public void selectCheckbox(){
		this.notexpirablecheckbox.click();
	}
	public void description(String desc){
		Actions actions = new Actions(driver);
		actions.moveToElement(addescription);
		actions.click();
		actions.sendKeys(desc);
		actions.build().perform();
	}
	public void submitnewAdvert(){
		this.saveadvertbtn.click();
	}
	public void goBackToAdvertList(){
		this.backbtn.click();
	}
	public void clickpersonal() {
		this.personal.click();
	}
	public void clickMemberAdvertisementLink(){
		this.advertisement.click();
	}
	public void adminLogout(){
		this.logoutlink.click();
	}
	public  String checkMemberCategory(){
		String category = this.membernewcategory.getAttribute("value");
		return category;
	}
}
