package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class SearchResultsPage {
	// Page class /Page Library pPage Object

		// Page class will give behaviour of human
		// https://martinfowler.com/bliki/PageObject.html
		private WebDriver driver;
		private ElementUtils eleUtil;

		// 1.private By locators
		private By searchProducts = By.cssSelector("div.product-thumb");

		// 2. public page class const....

		public SearchResultsPage(WebDriver driver) {
			this.driver = driver;
			eleUtil = new ElementUtils(driver);
		}

		public int getSearchProductAcount() {

			return eleUtil.waitforElementsVisible(searchProducts, 15).size();
			
		}
		public ProductInfoPage selectProduct(String productName) {
			
			System.out.println("Search for product"+productName);
			eleUtil.waitForElementVisible(By.linkText(productName),20).click();;
			//eleUtil.doClick(By.linkText(productName),10);
		return new ProductInfoPage(driver);
		}

		public String getAccountPageUrl() {

			String url = eleUtil.waitForURLContains(AppConstants.ACCOUNTS_PAGE_URL_FRACTION, 10);

			System.out.println("Account page title :" + url);
			return url;
		}
}
