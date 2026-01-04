package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest {

	// AAA --->
	// TC--- only one hard assertion or can have multiple soft assertions
	// POM --- zigzag or chaining
	//
	@BeforeClass
	public void infoPageSetup() {
		accountPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}

	@DataProvider(name = "getProductSearchData")
	public Object[][] getProductSearchData() {
		return new Object[][] {
			{ "macbook", "MacBook" },
			{ "macbook", "MacBook Pro" },
			{ "imac", "iMac"},
			{ "macbook", "MacBook Air"},
			{ "samsung", "Samsung SyncMaster 941BW" },
			{ "samsung", "Samsung Galaxy Tab 10.1"} 
			
		};

	}

	//Always test with 2-3 test data not all the data like 
	//1900 employee ,you need to check with 3-5 if work with 3-5 than work with other as well.
	@Test(enabled = true ,dataProvider="getProductSearchData")
	public void productHeaderTest(String searchKey,String productName) {
		searchResultsPage = accountPage.doSearch(searchKey);//macbook
		productInfoPage = searchResultsPage.selectProduct(productName); // MacBook Pro

		Assert.assertEquals(productInfoPage.getProductHeader(),productName );//"MacBook Pro"

	}

	@DataProvider(name = "getProductImagesData")
	public Object[][] getProductImagesData() {
		return new Object[][] {
			{ "macbook", "MacBook" ,5},
			{ "macbook", "MacBook Pro" ,4},
			{ "imac", "iMac" ,3},
			{ "macbook", "MacBook Air" ,4},
			{ "samsung", "Samsung SyncMaster 941BW" ,1 },
			{ "samsung", "Samsung Galaxy Tab 10.1" ,7} 
			
		};
/**
 * Data provider data is good to have but data should not maintain in excel sheet or excel sheet
 problem : with external data file to maintain the data
 1. excel sheet should be manipulated
 2. depends on thirdParty apache poi
 3. excel sheet is liences base project 
 
 Delta testing or partial testing : meaning specific data or sample data test using.
 */
	}
	@Test(dataProvider="getProductImagesData")
	public void productImagesCountTest(String searchKey,String productName,int imagesCount) {
		searchResultsPage = accountPage.doSearch(searchKey);//"macbook"
		productInfoPage = searchResultsPage.selectProduct(productName); // MacBook Pro

		Assert.assertEquals(productInfoPage.getProductImagesCount(), imagesCount);

	}

	/**
	 * Brand: Apple Product Code: Product 18 Reward Points: 800 Availability: Out Of
	 * Stock
	 * 
	 * @return
	 * 
	 *         AAA Unit test Pattern
	 * 
	 *         Arrange ---> action ----> Assert
	 * 
	 *         Hard assertion in below
	 */
	@Test
	public void productInfoDetailsTest() {

		searchResultsPage = accountPage.doSearch("macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro"); // MacBook Pro
		Map<String, String> productActualDetailsMap = productInfoPage.getProductDetailsMap();
		softAssert.assertEquals(productActualDetailsMap.get("Brand"), "Apple");
		softAssert.assertEquals(productActualDetailsMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(productActualDetailsMap.get("Reward Points"), "800");
		softAssert.assertEquals(productActualDetailsMap.get("Availability"), "Out Of Stock");
		softAssert.assertEquals(productActualDetailsMap.get("productPrice"), "$2,000.00");
		softAssert.assertEquals(productActualDetailsMap.get("exTaxPrice"), "$2,000.00");

		softAssert.assertAll();// mandatory for software its give the results

		// {Brand=Apple, Availability=Out Of Stock, productImages=4, Product
		// Code=Product 18, header=MacBook Pro, Reward Points=800,
		// productPrice=$2,000.00, exTaxPrice=$2,000.00}

	}
}
