package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.ProjectSpecificationMethods;

public class SignUpPage extends ProjectSpecificationMethods{
	
	private WebDriverWait Wait;
	
	@FindBy(id = "firstName")
    private WebElement firstnameField;

    @FindBy(id = "lastName")
    private WebElement lastnameField;
    
    @FindBy(id = "email")
    private WebElement emailField;
    
    @FindBy(id = "password")
    private WebElement passwordField;
    
    @FindBy(id = "submit")
    private WebElement submitSignupButton;
	
    @FindBy(id = "cancel")
    private WebElement cancelButton;
    
    @FindBy(id = "error")
	private static WebElement errorMessage;

	
    
    
    
	
	public SignUpPage(WebDriver driver) {
		this.driver = driver;
		this.Wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver,this);
	}
	
	public SignUpPage enterFirstName(String firstname) {
		firstnameField.sendKeys(firstname);
		return this;
	}
	
	public SignUpPage enterLastName(String lastname) {
		lastnameField.sendKeys(lastname);
		return this;
	}
	
	public SignUpPage enterEmail(String email) {
		emailField.sendKeys(email);
		return this;
	}
	
	public SignUpPage enterPassword(String password) {
		passwordField.sendKeys(password);
		return this;
	}
	
	public SignUpPage clickSignupSubmit() {
		submitSignupButton.click();
		return this;
	}
	
	public SignUpPage clickCancel() {
		cancelButton.click();
		return this;
	}
	
	public void signup(String firstName, String lastName, String email, String password) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterEmail(email);
        enterPassword(password);
        clickSignupSubmit();
    }

	public boolean isErrorMessageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            return wait.until(ExpectedConditions.visibilityOf(errorMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

	
}