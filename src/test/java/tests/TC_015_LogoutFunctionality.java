package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.ContactListPage;
import pages.LoginPage;

public class TC_015_LogoutFunctionality extends ProjectSpecificationMethods{

	@BeforeTest
	public void setup() throws IOException {
		filepath = "C:\\\\\\\\Users\\\\\\\\mraj2\\\\\\\\eclipse-workspace\\\\\\\\ContactListApp\\\\\\\\src\\\\\\\\test\\\\\\\\resources\\\\\\\\ContactData.properties";
		readFromFile(filepath);
	}
	
	@Test
	public void testLogoutButtonIsVisible() throws InterruptedException {
		LoginPage obj = new LoginPage(driver);
		ContactListPage obj2 = new ContactListPage(driver);
		
		obj.enterEmail(prop.getProperty("emailValid"))
		.enterPassword(prop.getProperty("passwordValid"))
		.clickLoginSubmit();
		
		obj2.clickLogoutButton();
		
		Thread.sleep(1000);
		
		String currentUrl = driver.getCurrentUrl();
	    Assert.assertEquals(currentUrl, prop.getProperty("url"), "Not redirected to Login page after logout.");
	    System.out.println("Successfully redirected to Login page after logout.");
	    System.out.println();
	}

}
