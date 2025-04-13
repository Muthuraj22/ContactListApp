package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.ContactListPage;
import pages.LoginPage;

public class TC_014_LogoutButtonVisibility extends ProjectSpecificationMethods{

	@BeforeTest
	public void setup() throws IOException {
		filepath = "C:\\\\\\\\Users\\\\\\\\mraj2\\\\\\\\eclipse-workspace\\\\\\\\ContactListApp\\\\\\\\src\\\\\\\\test\\\\\\\\resources\\\\\\\\ContactData.properties";
		readFromFile(filepath);
	}
	
	@Test
	public void testLogoutButtonIsVisible() {
		LoginPage obj = new LoginPage(driver);
		ContactListPage obj2 = new ContactListPage(driver);
		
		obj.enterEmail(prop.getProperty("emailValid"))
		.enterPassword(prop.getProperty("passwordValid"))
		.clickLoginSubmit();
		
	    Assert.assertTrue(obj2.isLogoutButtonVisible(), "Logout button is not visible.");
	}

}
