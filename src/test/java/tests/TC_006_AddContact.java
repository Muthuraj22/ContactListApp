package tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.AddContactPage;
import pages.ContactListPage;
import pages.LoginPage;

public class TC_006_AddContact extends ProjectSpecificationMethods{
	
	List<Object[]> addContactData = new ArrayList<>();
	boolean isDataLoaded = false;

	@BeforeTest
	public void setup() throws IOException {
		
		if (isDataLoaded) return;
		isDataLoaded = true;
		
		filepath = "C:\\Users\\mraj2\\eclipse-workspace\\ContactListApp\\src\\test\\resources\\ContactData.properties";
		readFromFile(filepath);
		
		
		
		//invaliddob
		addContactData.add(new Object[]
				{
						prop.getProperty("fname"),
						prop.getProperty("lname"),
						prop.getProperty("invaliddob"),
						prop.getProperty("email"),
						prop.getProperty("phone"),
						prop.getProperty("address1"),
						prop.getProperty("address2"),
						prop.getProperty("city"),
						prop.getProperty("state"),
						prop.getProperty("postalcode"),
						prop.getProperty("country"),
						"error"});
		
		//emptyFirstname
		addContactData.add(new Object[]
				{
						"",
						prop.getProperty("lname"),
						prop.getProperty("dob"),
						prop.getProperty("email"),
						prop.getProperty("phone"),
						prop.getProperty("address1"),
						prop.getProperty("address2"),
						prop.getProperty("city"),
						prop.getProperty("state"),
						prop.getProperty("postalcode"),
						prop.getProperty("country"),
						"error"});
		
		//emptyLastname
		addContactData.add(new Object[]
				{
						prop.getProperty("fname"),
						"",
						prop.getProperty("dob"),
						prop.getProperty("email"),
						prop.getProperty("phone"),
						prop.getProperty("address1"),
						prop.getProperty("address2"),
						prop.getProperty("city"),
						prop.getProperty("state"),
						prop.getProperty("postalcode"),
						prop.getProperty("country"),
						"error"});
		
		//emptyFirstname and emptyLastname
		addContactData.add(new Object[]
				{
						"",
						"",
						prop.getProperty("dob"),
						prop.getProperty("email"),
						prop.getProperty("phone"),
						prop.getProperty("address1"),
						prop.getProperty("address2"),
						prop.getProperty("city"),
						prop.getProperty("state"),
						prop.getProperty("postalcode"),
						prop.getProperty("country"),
						"error"});
		
		//invalidphone
		addContactData.add(new Object[]
				{
						prop.getProperty("fname"),
						prop.getProperty("lname"),
						prop.getProperty("dob"),
						prop.getProperty("email"),
						prop.getProperty("invalidphone"),
						prop.getProperty("address1"),
						prop.getProperty("address2"),
						prop.getProperty("city"),
						prop.getProperty("state"),
						prop.getProperty("postalcode"),
						prop.getProperty("country"),
						"error"});
		
		//invalidemail
		addContactData.add(new Object[]
				{
						prop.getProperty("fname"),
						prop.getProperty("lname"),
						prop.getProperty("dob"),
						prop.getProperty("invalidemail"),
						prop.getProperty("phone"),
						prop.getProperty("address1"),
						prop.getProperty("address2"),
						prop.getProperty("city"),
						prop.getProperty("state"),
						prop.getProperty("postalcode"),
						prop.getProperty("country"),
						"error"});
		
		//validData
		addContactData.add(new Object[]
				{
						prop.getProperty("fname"),
						prop.getProperty("lname"),
						prop.getProperty("dob"),
						prop.getProperty("email"),
						prop.getProperty("phone"),
						prop.getProperty("address1"),
						prop.getProperty("address2"),
						prop.getProperty("city"),
						prop.getProperty("state"),
						prop.getProperty("postalcode"),
						prop.getProperty("country"),
						"success"});
		
		//validData
		addContactData.add(new Object[]
				{
						prop.getProperty("fname2"),
						prop.getProperty("lname2"),
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"success"});
	}
	
	@DataProvider(name = "addContactData")
    public Object[][] fetchLoginData() {
		System.out.println("🧪 Total Test Data Sets: " + addContactData.size());
        return addContactData.toArray(new Object[0][]);
    }
	
	@Test(dataProvider = "addContactData")
	public void testAddContact(String fname, String lname, String dob, String email, String phone, String Address1, String Address2, String city, String state, String postalcode, String country, String expectedResult) {
		LoginPage obj = new LoginPage(driver);
		ContactListPage obj2 = new ContactListPage(driver);
		AddContactPage obj3 = new AddContactPage(driver);
		
		obj.enterEmail(prop.getProperty("emailValid"))
		.enterPassword(prop.getProperty("passwordValid"))
		.clickLoginSubmit();
		
		obj2.clickAddContactButton();
		obj3.addContact(fname, lname, dob, email, phone, Address1, Address2, city, state, postalcode, country);
	    
		
	    if (expectedResult.equals("success")) {
	    	 try {
	             Assert.assertTrue(driver.getCurrentUrl().contains("contact"), "User not redirected to Contact List after submit.");
	             System.out.println("✅ Contact added successfully with data: " + fname + " " + lname);
	         } catch (AssertionError e) {
	             System.out.println("❌ Failed to add contact with valid data: " + fname + " " + lname);
	             throw e;
	         }
	    } else {
	        try {
	            Assert.assertTrue(obj3.isErrorMessageDisplayed(), "Expected error message not displayed.");
	            // Print based on input scenario
	            if (dob.equals(prop.getProperty("invaliddob"))) {
	                System.out.println("Note: System restricts due to Invalid date format - Negative Test Passed");
	            } else if (fname.isEmpty() && lname.isEmpty()) {
	                System.out.println("Note: System restricts empty Firstname and Lastname - Negative Test Passed");
	            } else if (fname.isEmpty()) {
	                System.out.println("Note: System restricts empty Firstname - Negative Test Passed");
	            } else if (lname.isEmpty()) {
	                System.out.println("Note: System restricts empty Lastname - Negative Test Passed");
	            } else if (phone.equals(prop.getProperty("invalidphone"))) {
	                System.out.println("Note: System restricts due to Invalid Phone - Negative Test Passed");
	            } else if (email.equals(prop.getProperty("invalidemail"))) {
	                System.out.println("Note: System restricts due to Invalid Email - Negative Test Passed");
	            } else {
	                System.out.println("Note: System restricts due to invalid data - Negative Test Passed");
	            }
	        } catch (AssertionError e) {
	            System.out.println("Note: Negative test failed for data: " + fname + " " + lname);
	            throw e;
	        }
	    }
	}
	
}
