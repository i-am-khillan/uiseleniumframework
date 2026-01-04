package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchResultsPage;

import io.qameta.allure.Step;

public class BaseTest {
	DriverFactory df;
	WebDriver driver;
	
protected Properties prop;
protected LoginPage loginPage;
protected AccountPage accountPage;
protected SearchResultsPage searchResultsPage;
protected ProductInfoPage productInfoPage;
protected RegistrationPage registrationPage;
protected SoftAssert softAssert;

//Define the parameter so that will execute the test as parallel

	@Step("launching {0} browser & init the properties")
	@Parameters({"browser"})
	@BeforeTest
	public void setup(String browserName) {
		df=new DriverFactory();
		prop=df.initProp();
		
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
		}
		
		
		driver=df.initDriver(prop);//("chrome"
		loginPage=new LoginPage(driver);
		softAssert=new SoftAssert();
	}
	
	@Step("closing the browser")
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
	
}
