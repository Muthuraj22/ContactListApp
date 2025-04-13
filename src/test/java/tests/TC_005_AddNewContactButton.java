package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.ContactListPage;
import pages.LoginPage;


public class TC_005_AddNewContactButton extends ProjectSpecificationMethods{
	
	@BeforeMethod
	public void setup() throws IOException {
		filepath = "C:\\Users\\mraj2\\eclipse-workspace\\ContactListApp\\src\\test\\resources\\LoginData.properties";
		readFromFile(filepath);
		
		LoginPage obj = new LoginPage(driver);
		
		System.out.println("Email: " + prop.getProperty("emailValid"));
		System.out.println("Password: " + prop.getProperty("passwordValid"));
		
		obj.enterEmail(prop.getProperty("emailValid"))
		.enterPassword(prop.getProperty("passwordValid"))
		.clickLoginSubmit();
		
	}
	
	
	@Test(priority = 1)
	public void testAddContactButtonIsVisible() {
		
		
		
		System.out.println("Email: " + prop.getProperty("emailValid"));
		System.out.println("Password: " + prop.getProperty("passwordValid"));
		
		ContactListPage obj2 = new ContactListPage(driver);
	    Assert.assertTrue(obj2.isAddContactButtonVisible(), "'Add a new Contact' button is not visible.");
	}
	
	@Test(priority = 2)
	public void testAddContactButtonIsClickable() {
		
		
		ContactListPage obj2 = new ContactListPage(driver);
		Assert.assertTrue(obj2.isAddContactButtonClickable(), "'Add a new Contact' button is not clickable.");
	}
	

}
