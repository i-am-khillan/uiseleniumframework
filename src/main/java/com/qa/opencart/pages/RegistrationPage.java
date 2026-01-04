package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class RegistrationPage {
	
	private WebDriver driver;
	private ElementUtils eleUtil;
	
	private	By fn=By.id("input-firstname");
	private By ln=By.id("input-lastname");
	private By email=By.id("input-email");
	private By tel=By.id("input-telephone");
	private By pwd=By.id("input-password");
	private By confirmPwd=By.id("input-confirm");
	private By subcribeYes=By.xpath("//input[@name='newsletter' and @value='1']");
	private By subcribeNo=By.xpath("//input[@name='newsletter' and @value='0']");
	private By checkBoxPolicy= By.xpath("//input[@name='agree' and @value='1']");
	private By continueButton=By.xpath("//input[@value='Continue' and @type='submit']");
	
	private By successMessage=By.cssSelector("div#content h1");
	
	private By logoutLink=By.linkText("Logout");
	private By registerLink=By.linkText("Register");
	
	public RegistrationPage(WebDriver driver){
		this.driver=driver;
		eleUtil=new ElementUtils(driver);
	}

	//Your Account Has Been Created!
	//Same page 
	public boolean userRegistrationPage(String firstName,String lastName,String email,String telephone,String password,String subcribe) {
		
		eleUtil.waitForElementVisible(this.fn, 10).sendKeys(firstName);
		eleUtil.doSendKeys(this.ln,lastName);
		eleUtil.doSendKeys(this.email,email);
		eleUtil.doSendKeys(this.tel,telephone);
		eleUtil.doSendKeys(this.pwd,password);
		eleUtil.doSendKeys(this.confirmPwd,password);
		
		if(subcribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subcribeYes);
		}else {
			eleUtil.doClick(subcribeNo);
		}
			
		eleUtil.doClick(checkBoxPolicy);
		eleUtil.doClick(continueButton);
		String regSuccessMessage=eleUtil.waitForElementVisible(successMessage, 5).getText();
		System.out.println(regSuccessMessage);
		
		if(regSuccessMessage.equalsIgnoreCase(AppConstants.USER_REG_SUCCESS_MESSAGE)) {
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		}
		return false;
		
	}
}
