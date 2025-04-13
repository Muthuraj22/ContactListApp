package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.ProjectSpecificationMethods;

public class EditContactPage extends ProjectSpecificationMethods{

	@FindBy (id = "firstName")
	WebElement firstName;
	
	@FindBy (id = "lastName")
	WebElement lastName;
	
	@FindBy (id = "email")
	WebElement Email;
	
	@FindBy (id = "phone")
	WebElement Phone;
	
	@FindBy (id = "submit")
	WebElement EditSubmitButton;
	
	
	
	
	 private WebDriverWait Wait;

	public EditContactPage(WebDriver driver) {
	        this.driver = driver;
	        this.Wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        PageFactory.initElements(driver, this);
	 }
	
	public EditContactPage updateFirstName(String fname) throws InterruptedException {
		Thread.sleep(200);
		firstName.clear();
	    firstName.sendKeys(fname);
	    return this;
	}

	public EditContactPage updateLastName(String lname) {
		lastName.clear();
		lastName.sendKeys(lname);
	    return this;
	}

	public EditContactPage updateEmail(String email) {
	    Email.clear();
	    Email.sendKeys(email);
	    return this;
	}

	public EditContactPage updatePhone(String phone) {
	    Phone.clear();
	    Phone.sendKeys(phone);
	    return this;
	}
	
	public void clickEditSubmitButton() {
		EditSubmitButton.click();
		
	}

	
}
