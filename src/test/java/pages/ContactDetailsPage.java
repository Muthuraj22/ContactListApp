package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.ProjectSpecificationMethods;

public class ContactDetailsPage extends ProjectSpecificationMethods{

	@FindBy (id = "firstName")
	WebElement firstName;
	
	@FindBy (id = "lastName")
	WebElement lastName;
	
	@FindBy (id = "phone")
	WebElement phone;
	
	@FindBy (id = "edit-contact")
	WebElement editContactButton;
	
	@FindBy (id = "return")
	WebElement returnContactListButton;
	
	@FindBy (id = "delete")
	WebElement deleteContactButton;
	
	
	private WebDriverWait Wait;

	public ContactDetailsPage(WebDriver driver) {
		this.driver = driver;
		this.Wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver,this);
	}
	
	
	
	public void clickReturnToContactButton() throws InterruptedException {
		returnContactListButton.click();
		Thread.sleep(1000);
	}
	
	public ContactListPage clickEditContactButton() {
		editContactButton.click();
		return new ContactListPage(driver);
	}
	
	public ContactListPage clickDeleteContactButton() {
		deleteContactButton.click();
		return new ContactListPage(driver);
	}
	
}
