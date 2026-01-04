package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.logger.Log;
import com.qa.opencart.utils.ElementUtils;
import com.qa.opencart.utils.TimeUtil;

import io.qameta.allure.Step;

public class LoginPage {
//Page class /Page Library pPage Object 
	
//Page class will give behaviour of human 	
//https://martinfowler.com/bliki/PageObject.html	
	private WebDriver driver;
	private ElementUtils eleUtil;

	// 1.private By locators
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");

	private By registerLink = By.linkText("Register");
	
	// 2. public page class const....

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil=new ElementUtils(driver);
	}
	// 3. public Page action /methods

	@Step("Getting login page title...")
	public String getLoginPageTitle() {

	String title=eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, TimeUtil.DEFAULT_MEDIUM_TIME);
	Log.info("Login page title :"+title);	
	//System.out.println("login page title :" + title);
		return title;
	}

	@Step("waiting login page url...")
	public String getLoginPageUrl() {
		
	String url=	eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, TimeUtil.DEFAULT_LONG_TIME);//10
	
	Log.info("login page URL :" + url);
		System.out.println("login page URL :" + url);
		return url;
	}
	@Step("getting forget password link...")
	public boolean isForgotPwdLinkExist() {
	return	eleUtil.isElementDisplayed(forgotPwdLink);
		
		//return driver.findElement(forgotPwdLink).isDisplayed();
		
	}
	@Step("login with user: {0}  and password: {1}...")
	public AccountPage doLogin(String username,String pwd) {
		System.out.println("user credentails :"+username+":"+pwd);
		//eleUtil.doClear(emailId);
		//eleUtil.waitForElementVisible(emailId, 10)//.sendKeys(username);
		eleUtil.waitForElementVisible(emailId, TimeUtil.DEFAULT_LONG_TIME);
		eleUtil.doSendKeys(emailId, username);
		eleUtil.doSendKeys(password,pwd);
		eleUtil.doClick(loginButton);
	//	return 	eleUtil.waitForTitleIs("My Account", 15);
	return new AccountPage(driver);	
		
//		
//		driver.findElement(emailId).sendKeys(username);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(loginButton).click();
//		return driver.getTitle();
	}
	@Step("navigate to the registration page ...")
	public RegistrationPage navigateToRegisterPage() {
		eleUtil.waitForElementVisible(registerLink,TimeUtil.DEFAULT_LONG_TIME).click(); // long time
		return new RegistrationPage(driver);
		
	}
}
