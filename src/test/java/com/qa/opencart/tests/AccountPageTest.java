package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountPageTest extends BaseTest{

	/**
	 * Page class chaining patterns
	 */
	
	@BeforeClass
	public void accSetUp() {
		accountPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void accountPageTitleTest() {
		String actTitle=accountPage.getAccountPageTitle();
		Assert.assertEquals(actTitle, AppConstants.ACCOUNTS_PAGE_TITLE);
	}
	@Test
	public void accountPageURLTest() {
		String actURl=accountPage.getAccountPageUrl();
		Assert.assertTrue(actURl.contains(AppConstants.ACCOUNTS_PAGE_URL_FRACTION));
	}
	
	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accountPage.isLogOutLinkExist());
	}
	@Test
	public void isMyAccountLinkExistTest() {
		Assert.assertTrue(accountPage.myAccountLinkExist());
	}
	
	@Test 
	public void accountPageHeadersTest() {
		List<String> accountHeaderList=accountPage.getAccountPageHeaders();
		System.out.println(accountHeaderList);
	
	}
	@Test
	public void searchTest() {
		accountPage.doSearch("macbook");
	}
}
