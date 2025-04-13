package tests;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.AddContactPage;
import pages.ContactListPage;
import pages.LoginPage;

public class TC_009_PhoneNumberExtention extends ProjectSpecificationMethods{

	@BeforeTest
	public void setup() throws IOException {
		filepath = "C:\\Users\\mraj2\\eclipse-workspace\\ContactListApp\\src\\test\\resources\\ContactData.properties";
		readFromFile(filepath);
	}
	
	@Test
	public void testAddContactCancelButton() {
	    LoginPage obj = new LoginPage(driver);
	    ContactListPage obj2 = new ContactListPage(driver);
	    
	    
	    obj.enterEmail(prop.getProperty("emailValid"))
		.enterPassword(prop.getProperty("passwordValid"))
		.clickLoginSubmit();
	    
	    List<String> phoneNumbers = obj2.getAllContactPhoneNumbers();

        for (String phone : phoneNumbers) {
            System.out.println("Checking phone number: " + phone);
            Assert.assertTrue(phone.matches("^\\+\\d{1,4}.*"), 
                "Phone number does not start with country code: " + phone);
        }

        System.out.println("Note: All phone numbers display valid country code extensions - Test Passed");
    
	}
}
