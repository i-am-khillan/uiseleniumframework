package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtils;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtils eleUtil;
	
	
	/**
	 * if you don't want to maintain  the Key's order  than you should go for HashMap
	 * if you want to maintain the Key's order of the product details page than  you can use the LinkedHashMap
	 * if you want to maintain the Key's order in alphabatic order than you can use the TreeMap
	 */
	private Map<String,String> productMap=new TreeMap<>();

	// 1.private By locators
	private By productHeader = By.xpath("//h1");
	private By images = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");

	// 2. public page class const....

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtils(driver);
	}
	// 3. public Page action /methods

	public String getProductHeader() {

		String header = eleUtil.doGetElementText(productHeader);
		System.out.println("header of  page  :" + header);
		return header;
	}

	public int getProductImagesCount() {
		int tototalImage = eleUtil.waitforElementsVisible(images, 10).size();
		System.out.println("Image Count for " + getProductHeader() + ":" + tototalImage);
		return tototalImage;
	}
	private void getProductMetaData() {
		List<WebElement> metaList=eleUtil.getElements(productMetaData);
		for(WebElement e: metaList) {
			String text=e.getText();
		String metaKey=	text.split(":")[0].trim();
		String metaValue=	text.split(":")[1].trim();
		productMap.put(metaKey, metaValue);
		
		}
		
	}
	private void getProductPriceData() {
		List<WebElement> priceList=eleUtil.getElements(productPriceData);
		String price=	priceList.get(0).getText();
		String exTaxPrice=priceList.get(1).getText().split(":")[1].trim();
			productMap.put("productPrice", price);
			productMap.put("exTaxPrice", exTaxPrice);
		
		}
	/**
	 * Brand: Apple
	Product Code: Product 18
	Reward Points: 800
	Availability: Out Of Stock	
	 * @return
	 */
	public Map<String, String> getProductDetailsMap() {
		productMap.put("header", getProductHeader());
		productMap.put("productImages",String.valueOf(getProductImagesCount()));
		getProductMetaData();
		getProductPriceData();
		System.out.println("Product Details : \n"+productMap);
		return productMap;
	}

}
