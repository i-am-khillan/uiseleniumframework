package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;
import com.qa.opercart.listeners.ExtentListenerReports;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

//@Listeners(ExtentListenerReports.class)
@Epic("Epic 100: Design open cart login page")
@Story("Us 101: design login page features for open cart application")
@Feature("Feature 20: adding login features")
public class LoginPageTest extends BaseTest {


	@Description("Checking login page title...")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String acttitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(acttitle,AppConstants.LOGIN_PAGE_TITLE,AppError.TITLE_MISSING);

	}

	@Test(priority = 2)
	public void loginPageURLTest() {
		String acURL = loginPage.getLoginPageUrl();
		Assert.assertTrue(acURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION),AppError.URL_NOT_FOUND);

	}

	@Test(priority = 3)
	public void forgotPwdLinkedExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}

	@Test(priority = 4)
	public void loginTest() {
	//	String username = "vishwa@gmail.com";
	//	String password = "Selenium@123";
		accountPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		
		Assert.assertEquals(accountPage.getAccountPageTitle(), AppConstants.ACCOUNTS_PAGE_TITLE);

	}
	/**
	 * Your Account Has Been Created! Congratulations! Your new account has been
	 * successfully created!
	 * 
	 * Account Logout You have been logged off your account. It is now safe to leave
	 * the computer.
	 * 
	 * Your shopping cart has been saved, the items inside it will be restored
	 * whenever you log back into your account.
	 * 
	 * 
	 * 
	 */
}
