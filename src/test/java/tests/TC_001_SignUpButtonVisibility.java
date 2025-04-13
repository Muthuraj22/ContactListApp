package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.LoginPage;


public class TC_001_SignUpButtonVisibility extends ProjectSpecificationMethods{

	@BeforeTest
	public void setup() throws IOException {
		filepath = "C:\\Users\\mraj2\\eclipse-workspace\\ContactListApp\\src\\test\\resources\\SignUpData.properties";
		readFromFile(filepath);
	}
	
	@BeforeMethod
    public void initPageObject() {
    }
	
	@AfterMethod
	public void captureFailureScreenshot(ITestResult result) {
	    if (ITestResult.FAILURE == result.getStatus()) {
	        takeScreenshot(result.getName()); // Capture screenshot with test name
	    }
	}

	@Test
	public void testSignupButtonIsVisible() {
		LoginPage obj = new LoginPage(driver);
	    Assert.assertTrue(obj.isSignUpButtonVisible(), "Sign Up button is not visible.");
	}
	
	@Test(priority = 2)
	public void testSignupButtonIsClickable() {
		LoginPage obj = new LoginPage(driver);
		Assert.assertTrue(obj.isSignUpButtonClickable(), "Sign Up button is not clickable.");
	}

}