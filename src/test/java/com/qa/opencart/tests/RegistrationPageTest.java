package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.CSVUtils;
import com.qa.opencart.utils.ExcelUtils;
import com.qa.opencart.utils.StringUtils;

public class RegistrationPageTest extends BaseTest {

	@BeforeClass
	public void registrationSetup() {
		registrationPage = loginPage.navigateToRegisterPage();
	}

	@DataProvider(name = "userRegistrationTestData")
	public Object[][] getUserRegTestData() {
		return new Object[][] { { "Gaurab", "gupta", "8987656765", "gam@123", "yes" },
				{ "shilpa", "sharma", "8987656765", "shilpa@123", "yes" },	
				{ "prme", "nath", "89822656765", "gam@123", "yes" },

		};
	}
//Excel sheet
	@DataProvider(name = "userRegistrationTestDataFromExcel")
	public Object[][] getUserRegTestDataFromExcel() {
		return ExcelUtils.getTestData(AppConstants.REGISTER_SHEET_NAME);
	}

	//CSV file
	
	@DataProvider(name = "userRegistrationTestDataFromCSV")
	public Object[][] getUserRegTestDataFromCSV() {
		return CSVUtils.cvsData(AppConstants.REGISTER_SHEET_NAME);
	}
	@Test(dataProvider = "userRegistrationTestDataFromCSV")
	public void userRegistrationPageTest(String firstName, String lastName, String telephone, String password,
			String subcribe) {
		Assert.assertTrue(registrationPage.userRegistrationPage(firstName, lastName, StringUtils.getRandomEmailId(),
				telephone, password, subcribe));
	}

}
