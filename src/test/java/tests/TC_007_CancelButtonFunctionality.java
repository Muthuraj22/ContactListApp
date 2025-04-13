package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.AddContactPage;
import pages.ContactListPage;
import pages.LoginPage;

public class TC_007_CancelButtonFunctionality extends ProjectSpecificationMethods{

	@BeforeTest
	public void setup() throws IOException {
		filepath = "C:\\Users\\mraj2\\eclipse-workspace\\ContactListApp\\src\\test\\resources\\ContactData.properties";
		readFromFile(filepath);
	}
	
	@Test
	public void testAddContactCancelButton() {
	    LoginPage obj = new LoginPage(driver);
	    ContactListPage obj2 = new ContactListPage(driver);
	    AddContactPage obj3 = new AddContactPage(driver);

	    obj.enterEmail(prop.getProperty("emailValid"))
	       .enterPassword(prop.getProperty("passwordValid"))
	       .clickLoginSubmit();

	    obj2.clickAddContactButton();

	    obj3.enterFirstName("John")
	        .enterLastName("Doe")
	        .enterEmail("john@example.com");

	    obj3.clickContactCancelButton(); 

	    Assert.assertTrue(driver.getCurrentUrl().contains("contact"), "User not redirected to contact list after clicking cancel.");
	}
}
