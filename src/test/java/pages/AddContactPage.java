package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.ProjectSpecificationMethods;

public class AddContactPage extends ProjectSpecificationMethods{

	@FindBy(id = "firstName")
    private WebElement firstNameInput;

    @FindBy(id = "lastName")
    private WebElement lastNameInput;

    @FindBy(id = "birthdate")
    private WebElement birthDateInput;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "phone")
    private WebElement phoneInput;
    
    @FindBy(id = "street1")
    private WebElement street1Input;
    
    @FindBy(id = "street2")
    private WebElement street2Input;
    
    @FindBy(id = "city")
    private WebElement cityInput;
    
    @FindBy(id = "stateProvince")
    private WebElement stateInput;
    
    @FindBy(id = "postalCode")
    private WebElement postalCodeInput;
    
    @FindBy(id = "country")
    private WebElement countryInput;
    
    @FindBy(id = "submit")
    private WebElement contactSubmitButton;
    
    @FindBy(id = "cancel")
    private WebElement contactCancelButton;
    
    @FindBy(id = "error")  
    WebElement errorMsg;

	private WebDriverWait Wait;
    
    
    
    public AddContactPage(WebDriver driver) {
        this.driver = driver;
        this.Wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    
    
    public AddContactPage enterFirstName(String firstname) {
		firstNameInput.sendKeys(firstname);
		return this;
	}
    
    public AddContactPage enterLastName(String lastname) {
		lastNameInput.sendKeys(lastname);
		return this;
	}
    
    public AddContactPage enterDOB(CharSequence dob) {
		birthDateInput.sendKeys(dob);
		return this;
	}
    
    public AddContactPage enterEmail(String email) {
		emailInput.sendKeys(email);
		return this;
	}
    
    public AddContactPage enterPhone(String phone) {
		phoneInput.sendKeys(phone);
		return this;
	}
    
    public AddContactPage enterStreetAddress1(String address1) {
		street1Input.sendKeys(address1);
		return this;
	}
    
    public AddContactPage enterStreetAddress2(String address2) {
		street2Input.sendKeys(address2);
		return this;
	}
    
    public AddContactPage enterCity(String city) {
		cityInput.sendKeys(city);
		return this;
	}
    
    public AddContactPage enterStateOrProvince(String state) {
		stateInput.sendKeys(state);
		return this;
	}
    
    public AddContactPage enterPostalCode(String postalcode) {
		postalCodeInput.sendKeys(postalcode);
		return this;
	}
    
    public AddContactPage enterCountry(String country) {
		countryInput.sendKeys(country);
		return this;
	}
    
    public AddContactPage clickContactSubmitButton() {
    	contactSubmitButton.click();
		return this;
	}
    
    public AddContactPage clickContactCancelButton() {
    	contactCancelButton.click();
		return this;
	}
    
    public void addContact(String fname, String lname, String dob, String email, String phone, String Address1, String Address2, String city, String state, String postalcode, String country) {
    	enterFirstName(fname)
    	.enterLastName(lname)
    	.enterDOB(dob)
    	.enterEmail(email)
    	.enterPhone(phone)
    	.enterStreetAddress1(Address1)
    	.enterStreetAddress2(Address2)
    	.enterCity(city)
    	.enterStateOrProvince(state)
    	.enterPostalCode(postalcode)
    	.enterCountry(country)
    	.clickContactSubmitButton();
    	
    }
    
    public boolean isErrorMessageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOf(errorMsg));
            return errorMsg.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
