package tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.LoginPage;

public class TC_004_LoginFunctionality extends ProjectSpecificationMethods{
	
	List<Object[]> loginTestData = new ArrayList<>();
	
	@BeforeTest
	public void setup() throws IOException {
		filepath = "C:\\Users\\mraj2\\eclipse-workspace\\ContactListApp\\src\\test\\resources\\LoginData.properties";
		readFromFile(filepath);
		
		
		loginTestData.add(new Object[]{"", prop.getProperty("passwordValid"), "error"});
		loginTestData.add(new Object[]{prop.getProperty("emailValid"),"", "error"});
		loginTestData.add(new Object[]{prop.getProperty("emailValid"), prop.getProperty("passwordInvalid"), "error"});
		loginTestData.add(new Object[]{prop.getProperty("emailInvalid"), prop.getProperty("passwordValid"), "error"});
		loginTestData.add(new Object[]{prop.getProperty("emailValid"), prop.getProperty("passwordValid"), "success"});
	}
	
	@DataProvider(name = "loginData")
    public Object[][] fetchLoginData() {
        return loginTestData.toArray(new Object[0][]);
    }
	
	@AfterMethod
	public void captureFailureScreenshot(ITestResult result) {
	    if (ITestResult.FAILURE == result.getStatus()) {
	        takeScreenshot(result.getName());
	    }
	}
	
	@Test(dataProvider = "loginData")
	public void testLogin(String email, String password, String expectedResult) {
		
		LoginPage obj = new LoginPage(driver);
		obj.enterEmail(email)
		.enterPassword(password)
		.clickLoginSubmit();
		
		if (expectedResult.equals("success")) {
		    Assert.assertTrue(driver.getCurrentUrl().contains("contact"), "User not redirected to Contact List after signup.");
		} else {
		    Assert.assertTrue(obj.isErrorMessageDisplayed(), "Expected error message not displayed.");
		}
		
		
		
	}

}
