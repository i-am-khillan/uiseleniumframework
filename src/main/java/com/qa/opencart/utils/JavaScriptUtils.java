package com.qa.opencart.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtils {

	private WebDriver driver;

	public JavaScriptUtils(WebDriver driver) {

		this.driver = driver;
	}

	public void flash(WebElement element) {
		String bgColor = element.getCssValue("backgroundColor");
		for (int i = 0; i < 10; i++) {
			changeColor("rgb(0,200,0)", element);// 1
			changeColor(bgColor, element); // 2

		}
	}

	private void changeColor(String color, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.backgroundColor='" + color + " '", element);
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {

		}
	}

	public String getTitleByJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("return document.title;").toString();
	}

	public void goBackByJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("history.go(-1);");
	}

	public void goForwardByJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("history.go(1);");
	}

	public void refreshBrowserByJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("history.go(0);");
	}

	public void GenerateAlert(String message) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("alert('" + message + "')");
	}

	public void GenerateConfirmPopUp(String message) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("confirm('" + message + "')");
	}

	public String getPageInnerText(String message) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("return document.documentElement.innerText;").toString();
	}// debug

	public void clickElementByJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();");
	}

	public void sendKeyswithIdJS(String id, String value) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('" + id + "').value'" + value + "'");
	}

	public void scrollPageDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight;");

	}

	public void scrollPageDown(String height) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,'" + height + "')");

	}

	public void scrollPageUp() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(document.body.scrollHeight,0");

	}

	public void scrollPageMiddlePage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight/2");

	}

	public void scrollIntoView(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("argument[0].scrollIntoView(true);", element);

	}

	/*
	 * example : "document.body.style.zoom='400.0%'"
	 * 
	 * @param zoomPercentage
	 */
	public void zoomChromeEdgeSafari(String zoomPercentage) {
		String zoom = "document.body.style.zoom='" + zoomPercentage + "%'";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(zoom);
	}

	/*
	 * example : "document.body.style.MozTransform='scale(.05)';";
	 * 
	 * @param zoomPercentage
	 */
	public void zoomFirefox(String zoomPercentage) {
		String zoom = "document.body.style.'MozTransform='scale(" + zoomPercentage + ")'";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(zoom);
	}

	public void drawBorder(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("argument[0].style.border=3px solid red'", element);
	}
}
