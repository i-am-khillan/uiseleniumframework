package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.exceptions.ElementException;
import com.qa.opencart.factory.DriverFactory;

import io.qameta.allure.Step;

// SRP : single resposible principle;
public class ElementUtils {

	private WebDriver driver = null;
	private JavaScriptUtils jsUtil;

	// encapsulation Oop concepts
	// to solve the null pointer exception
	public ElementUtils(WebDriver driver) {
		this.driver = driver;
		jsUtil=new JavaScriptUtils(driver);
	}

	private final String DEFAULT_ELEMENT_TIME_OUT_MESSAGE = "Timeout... Element is not found...";
	private final String DEFAULT_ALERT_TIME_OUT_MESSAGE = "Timeout... Alert is not found...";

	private void checkHighLight(WebElement element) {
		if(Boolean.parseBoolean(DriverFactory.highlight));
		{
			jsUtil.flash(element);
		}
	}
	private void nullBlackCheck(String value) {
		if (value == null || value.length() == 0) {
			throw new ElementException(value + "value text can not be null or black");
		}
	}

	@Step("getting web element sing locator {0} ...")
	public WebElement getElement(By locator) {
		WebElement element = null;
		try {
			
			element = driver.findElement(locator);
			checkHighLight(element);//highlight element
		} catch (NoSuchElementException e) {
			System.out.println("Element is not present on the page ");
			e.printStackTrace();
			return null;

		}
		return element;

	}

	// ==============================
	public void doClear(By locator) {
		getElement(locator).clear();
	}

	@Step("checking element {0} is displayed ...")
	public boolean isElementDisplayed(By locator) {
		return getElement(locator).isDisplayed();

	}

	public boolean isElementsExist(By locator) {
		if (getElements(locator).size() == 1) {
			return true;
		}
		return false;
	}

	public boolean multipleElementsExist(By locator) {
		if (getElements(locator).size() > 0) {
			return true;
		}
		return false;
	}

	public boolean multipleElementsExist(By locator, int elementCount) {
		if (getElements(locator).size() == elementCount) {
			return true;
		}
		return false;
	}

	// Overlaoding Methods
	public WebElement getElement(String locatorType, String locatorValue) {

		WebElement element= driver.findElement(getBy(locatorType, locatorValue));
		checkHighLight(element);//highlight element
		return element;
	}
	@Step("entering value :{1} in  element using locator {0}...")	
	public void doSendKeys(By locator, String value) {
		nullBlackCheck(value);
		getElement(locator).clear();
		getElement(locator).sendKeys(value);
	}

	public void doSendKeys(By locator, String value, int timeout) {
		nullBlackCheck(value);
		waitForElementVisible(locator, timeout).sendKeys(value);
	}

	// overloaded methods
	public void doSendKeys(String locatorType, String locatorValue, String value) {
		nullBlackCheck(locatorType);
		nullBlackCheck(locatorValue);
		nullBlackCheck(value);

		getElement(locatorType, locatorValue).sendKeys(value);
	}
	@Step("clicking on element using locator {0}...")
	public void doClick(By locator) {
		getElement(locator).click();
	}

	public void doClick(By locator, int timeout) {
		waitForElementVisible(locator, timeout).click();
	}

	public String doGetElementText(By locator) {
		return getElement(locator).getText();
	}

	public String doGetElementGetAttribute(By locator, String attrName) {
		return getElement(locator).getAttribute(attrName);
	}

	// Elements Utilities :
	public List<WebElement> getElements(By locator) {

		return driver.findElements(locator);
	}

	public int getElementsCount(By locator) {

		return driver.findElements(locator).size();
	}

	public ArrayList<String> getElementsTextList(By locator) {

		List<WebElement> eleList = getElements(locator);

		ArrayList<String> eleTextList = new ArrayList<String>();
		for (WebElement e : eleList) {
			String text = e.getText();
			if (text.length() != 0) {
				eleTextList.add(text);
			}
		}
		return eleTextList;

	}

	public ArrayList<String> getElementsAttributeList(By locator, String attriName) {

		List<WebElement> eleList = getElements(locator);

		ArrayList<String> eleAttributeList = new ArrayList<String>();
		for (WebElement e : eleList) {
			String Attribute = e.getAttribute(attriName);
			if (Attribute.length() != 0) {
				eleAttributeList.add(Attribute);
			}
		}
		return eleAttributeList;

	}
	// ------------------------Select based Dorp down Utils-------

	public void doSelectByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));

		select.selectByIndex(index);
	}

	public void doSelectByVisibleText(By locator, String visiableText) {
		nullBlackCheck(visiableText);

		Select select = new Select(getElement(locator));

		select.selectByVisibleText(visiableText);
	}

	public void doSelectByValue(By locator, String value) {

		nullBlackCheck(value);
		Select select = new Select(getElement(locator));

		select.selectByValue(value);
	}

	public List<String> getDropDownOptionsTextList(By locator) {
		List<WebElement> optionList = getDropDownOptionList(locator);
		List<String> optionsTextList = new ArrayList<String>();

		for (WebElement e : optionList) {
			String optionText = e.getText();
			optionsTextList.add(optionText);
		}
		return optionsTextList;
	}

	public List<WebElement> getDropDownOptionList(By locator) {
		Select select = new Select(getElement(locator));

		return select.getOptions();

	}

	public int getDropDownValuesCount(By locator) {

		return getDropDownOptionList(locator).size();
	}

	public void doSelectDropDownValue(By locator, String value) {
		nullBlackCheck(value);
		List<WebElement> optionList = getDropDownOptionList(locator);
		for (WebElement e : optionList) {
			String text = e.getText();
			System.out.println(text);
			if (text.equals(value)) {
				e.click();
				break;
			}
		}

	}

	public void printSelectDropDownValue(By locator) {
		List<WebElement> optionList = getDropDownOptionList(locator);
		for (WebElement e : optionList) {
			String text = e.getText();
			System.out.println(text);

		}

	}

	// without select class , select the drop down values
	public void doSelectValueFromDropDown(By locator, String value) {

		nullBlackCheck(value);
		List<WebElement> optionList = getElements(locator);

		System.out.println(optionList.size());
		for (WebElement e : optionList) {
			String text = e.getText();
			if (text.equals(value)) {
				e.click();
				break;
			}

		}
	}

	// do Search methods
	public void doSearch(By searchLocator, String searchKey, By SearchSuggestion, String value)
			throws InterruptedException {

		doSendKeys(searchLocator, searchKey);
		Thread.sleep(3000);

		List<WebElement> autoSuggestion = getElements(SearchSuggestion);
		System.out.println(autoSuggestion.size());
		for (WebElement e : autoSuggestion) {
			String test = e.getText();
			System.out.println(test);
			if (test.contains(value)) {
				e.click();
				break;
			}
		}
	}

	// Multi Choice selection
	/**
	 * this method is used to handle single ,multiple selection , all selection
	 * .please pass "all" in case of all selections;
	 * 
	 * 
	 * @param dropDownLocator
	 * @param choice
	 * @param value
	 * @throws InterruptedException
	 * 
	 *                              String... varArgs -- spread parameter(JS) ---
	 *                              array you can pass the multiple choice
	 */

	public void selectChoice(By dropDownLocator, By choice, String... value) throws InterruptedException {
		getElement(dropDownLocator).click();
		Thread.sleep(3000);

		List<WebElement> choiceList = getElements(choice);

		// do not select All if you pass the same
		if (!value[0].equals("all")) {
			Thread.sleep(3000);
			System.out.println(choiceList.size());

			for (WebElement e : choiceList) {

				String text = e.getText();
				System.out.println(text);
				for (String val : value) {

					if (text.equals(val)) {
						e.click();
						// break;
					}
				}
			}

		} else {
			// All selection logic:
			for (WebElement eL : choiceList) {
				try {
					eL.click();
				} catch (ElementClickInterceptedException e) {
					break;
				}
			}
		}
	}

	// -----------------Actions Utils----------------
	public void handleMenuSubMenu(By parentLocator, By subMenuLocator) throws InterruptedException {

		Actions act = new Actions(driver);// font[normalize-space()='Articles']
		act.moveToElement(getElement(parentLocator)).perform();
		Thread.sleep(5000);
		doClick(subMenuLocator);

	}

	public void handleMultiSubMenus4(By levelMenu1, By levelMenu2, By levelMenu3, By levelMenu4)
			throws InterruptedException {
		Actions act = new Actions(driver);
		doClick(levelMenu1);
		Thread.sleep(2000);
		act.moveToElement(getElement(levelMenu2)).perform();
		;
		Thread.sleep(2000);
		act.moveToElement(getElement(levelMenu3)).perform();
		Thread.sleep(2000);
		doClick(levelMenu4);
		Thread.sleep(2000);

	}

	public void doActionsClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).perform();
	}

	public void doActionsSendKeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();
	}
	// ***********Wait Utils***********************///

//		public  WebElement waitForElementPresence(By locator, int timeout) {
	//
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
//			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	//
//		}
	public WebElement waitForElementPresence(By locator, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		/**
		 * An expectation for checking that an element is present on the DOM of a page.
		 * This does not necessarily mean that the element is visible.
		 * 
		 * 
		 * 
		 */
	}
	@Step("waitingfro element using locator {0} within timeout {1}...")
	// Recommended
	public WebElement waitForElementVisible(By locator, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		/*
		 * An expectation for checking that an element is present on the DOM of a page
		 * and visible. Visibility means that the element is not only displayed but also
		 * has a height and width that is greater than 0.
		 * 
		 */
	}

	public WebElement waitForElementVisible(By locator, int timeout, int intervalTime) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout), Duration.ofMillis(intervalTime));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		/*
		 * An expectation for checking that an element is present on the DOM of a page
		 * and visible. Visibility means that the element is not only displayed but also
		 * has a height and width that is greater than 0.
		 * 
		 */
	}

	public String waitForTitleContains(String titleFraction, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			if (wait.until(ExpectedConditions.titleContains(titleFraction)))
				;
			{
				return driver.getTitle();
			}
		} catch (Exception e) {
			System.out.println("Title is not found:" + timeout);
		}
		return null;
	}

	public String waitForTitleIs(String title, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			if (wait.until(ExpectedConditions.titleIs((title)))) {
				return driver.getTitle();
			}
		} catch (Exception e) {
			System.out.println("Title is not found:" + timeout);
		}
		return driver.getTitle();
	}

	public String waitForURLContains(String urlFraction, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			if (wait.until(ExpectedConditions.urlContains(urlFraction)))
				;
			{
				return driver.getCurrentUrl();
			}
		} catch (Exception e) {
			System.out.println("URL is not found:" + timeout);
		}
		 return driver.getCurrentUrl();
	}

	public String waitForURLIs(String url, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			if (wait.until(ExpectedConditions.urlToBe((url)))) {
				return driver.getCurrentUrl();
			}
		} catch (Exception e) {
			System.out.println("Title is not found:" + timeout);
		}
		return null;
	}

	// Alerts Utilities
	public Alert waitForJSAlert(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.alertIsPresent());

	}

	public Alert waitForJSAlertWithFluentWait(int timeout, int pollingTimeout) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(pollingTimeout)).ignoring(NoSuchElementException.class)
				.withMessage(DEFAULT_ALERT_TIME_OUT_MESSAGE);
		return wait.until(ExpectedConditions.alertIsPresent());

	}

	public String getAlertText(int timeout) {
		return waitForJSAlert(timeout).getText();
	}

	public void acceptAlert(int timeout) {
		waitForJSAlert(timeout).accept();
		;
	}

	public void dismissAlert(int timeout) {
		waitForJSAlert(timeout).dismiss();
		;
	}

	public void getAlertText(int timeout, String value) {
		waitForJSAlert(timeout).sendKeys(value);
	}

	// Click and ready for locator-- visiable & enabled
	/**
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it.
	 * 
	 * 
	 * @param locator
	 * @param timeout
	 */
	public void clickWhenReady(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	/**
	 * An expectation for checking that there is at least one element present on a
	 * web page.
	 * 
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public List<WebElement> waitforElementsPresence(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	public List<WebElement> waitforElementsPresenceWithFluentWait(By locator, int timeout, int pollingTimeout) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(pollingTimeout)).ignoring(NoSuchElementException.class)
				.withMessage(DEFAULT_ELEMENT_TIME_OUT_MESSAGE);
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	/**
	 * An expectation for checking that all elements present on the web page that
	 * match the locator are visible. Visibility means that the elements are not
	 * only displayed but also have a height and width that is greater than 0.
	 * 
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public List<WebElement> waitforElementsVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	// window s--- new Tab vs new Window
	public boolean waitForWindow(int totalNumberOfWindowsTobe, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.numberOfWindowsToBe(totalNumberOfWindowsTobe));

	}

	// Frame;
	public void waitForFrameAndSwitchToIt(By frameLocator, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));

	}

	public void waitForFrameAndSwitchToIt(int frameIndex, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));

	}

	public void waitForFrameAndSwitchToIt(WebElement element, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));

	}

	// -------------Custom wait -------------------------
	public WebElement retryingElement(By locator, int timeout) {

		WebElement element = null;
		int attempt = 0;
		while (attempt < timeout) {
			try {
				element = driver.findElement(locator);
				System.out.println("element is found..." + locator + "in attempt" + attempt);
				break;
			} catch (NoSuchElementException e) {
				System.out.println("element is not found...." + "in attempts" + attempt);
				TimeUtil.defaultTime();
				// Thread.sleep(500);
			}
			attempt++;
		}
		if (element == null) {
			System.out.println("element is not found .... tried for " + timeout + "times" + "with the interval of "
					+ 500 + "milliseconds");
			throw new ElementException("No such element");
		}
		return element;
	}

	public WebElement retryingElement(By locator, int timeout, int intervalTime) {

		WebElement element = null;
		int attempt = 0;
		while (attempt < timeout) {
			try {
				element = driver.findElement(locator);
				System.out.println("element is found..." + locator + "in attempt" + attempt);
				break;
			} catch (NoSuchElementException e) {
				System.out.println("element is not found...." + "in attempts" + attempt);
				TimeUtil.applyWait(intervalTime);
			}
			attempt++;
		}
		if (element == null) {
			System.out.println("element is not found .... tried for " + timeout + "times" + "with the interval of "
					+ 500 + "milliseconds");
			throw new ElementException("No such element");
		}
		return element;
	}

	// -------------------------------
	public By getBy(String locatorType, String locatorValue) {

		By locator = null;
		switch (locatorType.toLowerCase().trim()) {

		case "id":
			locator = By.id(locatorValue);
			break;
		case "name":
			locator = By.name(locatorValue);
			break;
		case "classname":
			locator = By.className(locatorValue);
			break;
		case "xpath":
			locator = By.xpath(locatorValue);
			break;
		case "linktext":
			locator = By.linkText(locatorValue);
			break;
		case "partiallinktext":
			locator = By.partialLinkText(locatorValue);
			break;
		case "css":
			locator = By.cssSelector(locatorValue);
			break;
		case "tagname":
			locator = By.tagName(locatorValue);
			break;
		default:
			// throw new IllegalArgumentException("Unexpected value: " +
			// locatorType.toLowerCase().trim());
			break;
		}
		return locator;
	}

}
