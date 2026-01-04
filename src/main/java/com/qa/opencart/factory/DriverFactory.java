package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameworkException;
import com.qa.opencart.logger.Log;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public static String highlight;

	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser");
		// String browserName=System.getProperty("browser");
		System.out.println("browser name is : " + browserName);

		// Logging line
		Log.info("Browser name is :" + browserName);
		highlight = prop.getProperty("highlight");

		optionsManager = new OptionsManager(prop);
		switch (browserName.toLowerCase().trim()) {

		case "chrome":

			driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(driver);
			break;
		case "firefox":
			driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(driver);
			break;
		case "safari":
			driver = new SafariDriver();
			tlDriver.set(driver);
			break;
		case "edge":

			driver = new EdgeDriver(optionsManager.getEdgeOptions());
			tlDriver.set(driver);
			break;
		default:
			System.out.println("Please pass the right browser" + browserName);
			Log.error("Please pass the right browser" + browserName);
			throw new BrowserException("browser not found");
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));// "https://naveenautomationlabs.com/opencart/index.php?route=account/login"
		return getDriver();
	}

	public static  WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties initProp() {

		FileInputStream ip = null;
		prop = new Properties();
		// envName=qa,stag,prod,uat,dev
		// mav clean install -Denv="qa"

		String envName = System.getProperty("env");
		System.out.println("Running test on Env:" + envName);
		try {
			if (envName == null) {
				System.out.println("No env is given ... hence running it on qa env...");
				ip = new FileInputStream(".//src//test//resources//config.properties");// qa properies
			} else {
				switch (envName.toLowerCase().trim()) {
				case "int":
					ip = new FileInputStream(".//src//test//resources//config.int.properties");// int properies
					break;
				case "qa":
					ip = new FileInputStream(".//src//test//resources//config.qa.properties");// current properies
					break;
				case "dev":
					ip = new FileInputStream(".//src//test//resources//config.dev.properties");// current properies
					break;
				case "stg":
					ip = new FileInputStream(".//src//test//resources//config.stg.properties");// current properies
					break;
				case "prod":
					ip = new FileInputStream(".//src//test//resources//config.prod.properties");// current properies
					break;
				default:
					System.out.println("Please pass theright env name ... " + envName);
					throw new FrameworkException(AppError.EVN_NAME_NOT_FOUND + ":" + envName);

				}
			}
			prop.load(ip);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // ./src/test/resource/config.propeties
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}

	/**
	 * take screenshot
	 */
	
public static String getScreenshot(String methodName) {
	
	File srcFile=((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
	String path=System.getProperty("user.dir")+"/screenshots/"+methodName+"_"+System.currentTimeMillis()+".png";
	
	File destination=new File(path);
	try {
		FileHandler.copy(srcFile, destination);
	}catch(IOException e) {
		e.printStackTrace();
		}
	return path;
	}

}
