package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.LoginPage;

public class TC_010_ContactClicking extends ProjectSpecificationMethods{

	@BeforeTest
	public void setup() throws IOException {
		filepath = "C:\\Users\\mraj2\\eclipse-workspace\\ContactListApp\\src\\test\\resources\\ContactData.properties";
		readFromFile(filepath);
	}
	
	@Test
	public void testAddContactCancelButton() {
	    LoginPage obj = new LoginPage(driver);
	    
	    
	    obj.enterEmail(prop.getProperty("emailValid"))
		.enterPassword(prop.getProperty("passwordValid"))
		.clickLoginSubmit()
	    .clickFirstElement();
	    Assert.assertTrue(driver.getCurrentUrl().contains("contactDetails"), "User not redirected to Contact Details page after click");
	}
}
