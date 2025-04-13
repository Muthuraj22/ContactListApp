package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.LoginPage;

public class TC_003_LoginButtonVisibility extends ProjectSpecificationMethods{
	
	
	@BeforeTest
	public void setup() throws IOException {
		filepath = "C:\\Users\\mraj2\\eclipse-workspace\\ContactListApp\\src\\test\\resources\\LoginData.properties";
		readFromFile(filepath);
	}
	
	@AfterMethod
	public void captureFailureScreenshot(ITestResult result) {
	    if (ITestResult.FAILURE == result.getStatus()) {
	        takeScreenshot(result.getName()); // Capture screenshot with test name
	    }
	}
	
	

	@Test(priority = 1)
	public void testLoginSubmitButtonIsVisible() {
		LoginPage obj = new LoginPage(driver);
	    Assert.assertTrue(obj.isLoginSubmitButtonVisible(), "Login button is not visible.");
	}
	
	@Test(priority = 2)
	public void testLoginSubmitButtonIsClickable() {
		LoginPage obj = new LoginPage(driver);
		Assert.assertTrue(obj.isLoginSubmitButtonClickable(), "Login button is not clickable.");
	}

}
