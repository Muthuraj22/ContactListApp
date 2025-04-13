package tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.AddContactPage;
import pages.ContactListPage;
import pages.LoginPage;

public class TC_008_ContactListSortedTest extends ProjectSpecificationMethods{

	
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
	    
	    List<String> lastNames = obj2.getAllContactLastNames();

        List<String> sortedLastNames = new ArrayList<>(lastNames);
        Collections.sort(sortedLastNames, String.CASE_INSENSITIVE_ORDER);

        Assert.assertEquals(lastNames, sortedLastNames, "Contacts are not sorted by last name.");
        System.out.println("Note: Contacts are sorted alphabetically by last name - Test Passed");
	    
	    
	}
}
	
