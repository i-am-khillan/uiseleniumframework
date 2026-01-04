package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class AccountPage {
	// Page class /Page Library pPage Object

	// Page class will give behaviour of human
	// https://martinfowler.com/bliki/PageObject.html
	private WebDriver driver;
	private ElementUtils eleUtil;

	// 1.private By locators
	private By logoutLink = By.linkText("Logout");
	private By myAccountLink = By.linkText("My Account");
	private By headers = By.cssSelector("div#content >h2");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");

	// 2. public page class const....

	public AccountPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtils(driver);
	}

	public String getAccountPageTitle() {

		String title = eleUtil.waitForTitleIs(AppConstants.ACCOUNTS_PAGE_TITLE, 15);
		System.out.println("Account page title :" + title);
		return title;
	}

	public String getAccountPageUrl() {

		String url = eleUtil.waitForURLContains(AppConstants.ACCOUNTS_PAGE_URL_FRACTION, 10);

		System.out.println("Account page title :" + url);
		return url;
	}

	public boolean isLogOutLinkExist() {
		return eleUtil.waitForElementVisible(logoutLink, 10).isDisplayed();
	}

	public boolean myAccountLinkExist() {
		return eleUtil.waitForElementVisible(myAccountLink, 10).isDisplayed();
	}
	public List<String> getAccountPageHeaders() {
	List<WebElement> headersEleList=	eleUtil.getElements(headers);
	List<String> headersList=new ArrayList<>();
	for(WebElement e:headersEleList) {
		String header=e.getText();
		headersList.add(header);
	}
	return headersList;
	}

	public SearchResultsPage doSearch(String searchKey) {
		System.out.println("Searching for :" + searchKey);
		eleUtil.doSendKeys(search, searchKey);
		eleUtil.doClick(searchIcon);
		return new SearchResultsPage(driver);
	}
}
