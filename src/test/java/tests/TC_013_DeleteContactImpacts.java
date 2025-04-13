package tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.ContactDetailsPage;
import pages.ContactListPage;
import pages.LoginPage;

public class TC_013_DeleteContactImpacts extends ProjectSpecificationMethods{
	
	@BeforeMethod
	public void setup() throws IOException {
		filepath = "C:\\\\Users\\\\mraj2\\\\eclipse-workspace\\\\ContactListApp\\\\src\\\\test\\\\resources\\\\ContactData.properties";
		readFromFile(filepath);
	}
	
	@Test
	public void testDeleteContact() throws InterruptedException {
	    LoginPage obj1 = new LoginPage(driver);
	    ContactListPage obj2 = new ContactListPage(driver);
	    ContactDetailsPage obj3 = new ContactDetailsPage(driver);
	    
	    
	    obj1.enterEmail(prop.getProperty("emailValid"))
		.enterPassword(prop.getProperty("passwordValid"))
		.clickLoginSubmit();
	    
	    // Step 1: Capture all contact names before deletion
	    List<String> originalContacts = obj2.getAllContactLastNames(); // or get full names if needed
	    Assert.assertTrue(originalContacts.size() > 1, "Not enough contacts to validate deletion");

	    // Step 2: Pick a contact to delete (e.g., last one)
	    String contactToDelete = originalContacts.get(originalContacts.size() - 1);
	    System.out.println(contactToDelete);

	 // Build a locator using the contact name (assuming contact names are inside <span> elements)
	 By contactLocator = By.xpath("//tr/td[contains(text(),'" + contactToDelete + "')]");

	 // Wait until the contact element is visible and clickable
	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	 WebElement contactElement = wait.until(ExpectedConditions.elementToBeClickable(contactLocator));

	 // Click the contact
	 contactElement.click();

	 // Proceed to delete
	 obj3.clickDeleteContactButton();

	    // Confirm the alert
	    
	    Alert alert = wait.until(ExpectedConditions.alertIsPresent());
	    alert.accept();

	    // Step 3: Capture contacts after deletion
	    Thread.sleep(2000);
	    List<String> updatedContacts = obj2.getAllContactLastNames();

	    // Step 4: Validation
	    Assert.assertFalse(updatedContacts.contains(contactToDelete), "Deleted contact is still present");
	    
	    for (int i = 0; i < originalContacts.size() - 1; i++) {
	        Assert.assertEquals(updatedContacts.get(i), originalContacts.get(i), "Data mismatch for contact at index " + i);
	    }

	    System.out.println("Contact '" + contactToDelete + "' deleted successfully without affecting others.");
	}

}
