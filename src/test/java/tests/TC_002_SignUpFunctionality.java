package tests;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.LoginPage;
import pages.SignUpPage;


public class TC_002_SignUpFunctionality extends ProjectSpecificationMethods{

	List<Object[]> signupTestData = new ArrayList<>();

    @BeforeTest
    public void setup() throws IOException {
        filepath = "C:\\Users\\mraj2\\eclipse-workspace\\ContactListApp\\src\\test\\resources\\SignUpData.properties";
        readFromFile(filepath);

        
        signupTestData.add(new Object[]{"", prop.getProperty("lastname"), prop.getProperty("email"), prop.getProperty("password"), "error"});
        signupTestData.add(new Object[]{prop.getProperty("firstname"), "", prop.getProperty("email"), prop.getProperty("password"), "error"});
        signupTestData.add(new Object[]{prop.getProperty("firstname"), prop.getProperty("lastname"), "", prop.getProperty("password"), "error"});
        signupTestData.add(new Object[]{prop.getProperty("firstname"), prop.getProperty("lastname"), prop.getProperty("email"), "", "error"});
        signupTestData.add(new Object[]{prop.getProperty("firstname"), prop.getProperty("lastname"), prop.getProperty("email"), prop.getProperty("password"), "success"});
    }
    
    @DataProvider(name = "signupData")
    public Object[][] fetchSignUpData() {
        return signupTestData.toArray(new Object[0][]);
    }
	
	@Test(dataProvider = "signupData")
	public void testSignUp(String firstName, String lastName, String email, String password, String expectedResult) {
	LoginPage obj = new LoginPage(driver);
	SignUpPage obj2 = new SignUpPage(driver);
	
	obj.clickSignUp()
	.enterFirstName(firstName)
	.enterLastName(lastName)
	.enterEmail(email)
	.enterPassword(password)
	.clickSignupSubmit();
	
	if (expectedResult.equals("success")) {
	    Assert.assertTrue(driver.getCurrentUrl().contains("contact"), "User not redirected to Contact List after signup.");
	} else {
	    Assert.assertTrue(obj2.isErrorMessageDisplayed(), "Expected error message not displayed.");
	}
	}
	
	
}
