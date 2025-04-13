package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.AddContactPage;
import pages.ContactDetailsPage;
import pages.ContactListPage;
import pages.EditContactPage;
import pages.LoginPage;

public class TC_011_EditContact extends ProjectSpecificationMethods{

	@BeforeMethod
	public void setup() throws IOException {
		filepath = "C:\\Users\\mraj2\\eclipse-workspace\\ContactListApp\\src\\test\\resources\\EditContact.properties";
		readFromFile(filepath);
	}
	
	@Test
	public void testContactEdit() throws InterruptedException {
	    LoginPage obj1 = new LoginPage(driver);
	    ContactListPage obj2 = new ContactListPage(driver);
	    AddContactPage obj3 = new AddContactPage(driver);
	    ContactDetailsPage obj4 = new ContactDetailsPage(driver);
	    EditContactPage obj5 = new EditContactPage(driver);
	    
	    obj1.enterEmail(prop.getProperty("email"))
		.enterPassword(prop.getProperty("password"))
		.clickLoginSubmit();
	    
	    obj2.clickAddContactButton();
	    obj3.enterFirstName("John")
        .enterLastName("Doe")
        .enterEmail("john@gmail.com")
        .enterPhone("9933215487")
        .clickContactSubmitButton();
	    
	    
	    obj2.clickContactElement("John");
	    
	    obj4.clickEditContactButton();
	    
	    String updatedFirstName = "Johny";
        String updatedLastName = "John";
        String updatedPhone = "9876543210";
        
        obj5.updateFirstName(updatedFirstName)
        .updateLastName(updatedLastName)
        .updatePhone(updatedPhone)
        .clickEditSubmitButton();
        
        obj4.clickReturnToContactButton();
     
        
        // Verification
        
        String fullName = obj2.getContactNameByName(updatedFirstName); 
        String phone = obj2.getContactPhoneByName(updatedFirstName);   
        
        System.out.println("Expected: " + updatedFirstName + " " + updatedLastName);
        System.out.println("Actual full name: " + fullName);
        System.out.println("Actual phone: " + phone);

        Assert.assertTrue(fullName.contains(updatedFirstName));
        Assert.assertTrue(fullName.contains(updatedLastName));
        Assert.assertEquals(phone, updatedPhone);

        System.out.println("Note: Contact edited and verified successfully - Test Passed");
	    
	}
}
