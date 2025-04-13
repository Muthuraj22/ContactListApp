package tests;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.ContactListPage;
import pages.LoginPage;

public class TC_012_DeleteContactButton extends ProjectSpecificationMethods{
	
	@BeforeMethod
	public void setup() throws IOException {
		filepath = "C:\\\\\\\\Users\\\\\\\\mraj2\\\\\\\\eclipse-workspace\\\\\\\\ContactListApp\\\\\\\\src\\\\\\\\test\\\\\\\\resources\\\\\\\\ContactData.properties";
		readFromFile(filepath);
	}
	
	@Test
	public void testDeleteContact() throws InterruptedException {
	    LoginPage obj1 = new LoginPage(driver);
	    ContactListPage obj2 = new ContactListPage(driver);
	    
	    
	    obj1.enterEmail(prop.getProperty("emailValid"))
		.enterPassword(prop.getProperty("passwordValid"))
		.clickLoginSubmit();
	    
	    String contactName = driver.findElement(By.xpath("(//tr[@class='contactTableBodyRow'])[last()]/td[2]")).getText();
	   
	    obj2.clickFirstElement()
	    .clickDeleteContactButton();
	    
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
	    alert = driver.switchTo().alert();

        String alertText = alert.getText();
        Assert.assertTrue(alertText.contains("Are you sure you want to delete this contact?"), "Alert text does not match expected");

        alert.accept();
        
        boolean isContactStillPresent = driver.getPageSource().contains(contactName);
        Assert.assertFalse(isContactStillPresent, "Contact '" + contactName + "' was not deleted");
        System.out.println("Contact '" + contactName + "' deleted successfully.");
	}
        
	

}
