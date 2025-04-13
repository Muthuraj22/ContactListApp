package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.ProjectSpecificationMethods;

public class LoginPage extends ProjectSpecificationMethods{
	
	private WebDriverWait Wait;
	
	@FindBy(id = "signup")
	private WebElement SignUpButton;
	
	@FindBy(id = "submit")
	private WebElement loginSubmitButton;

	@FindBy(id = "email")
    private WebElement emailField;
    
    @FindBy(id = "password")
    private WebElement passwordField;
    
    @FindBy(id = "error")
    private WebElement loginError;
	
	
	

	
	
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.Wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver,this);
	}
	
	public SignUpPage clickSignUp() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(SignUpButton));
		SignUpButton.click();
		return new SignUpPage(driver);
	}
	
    public boolean isSignUpButtonVisible() {
        return SignUpButton.isDisplayed();
    }

    public boolean isSignUpButtonClickable() {
        return SignUpButton.isEnabled();
    }
    
    public boolean isLoginSubmitButtonVisible() {
	    return loginSubmitButton.isDisplayed();
	}
    
    public boolean isLoginSubmitButtonClickable() {
        return loginSubmitButton.isEnabled();
    }
    
    public LoginPage enterEmail(String email) {
		emailField.sendKeys(email);
		return this;
	}
	
	public LoginPage enterPassword(String password) {
		passwordField.sendKeys(password);
		return this;
	}
	
	public ContactListPage clickLoginSubmit() {
		loginSubmitButton.click();
		return new ContactListPage(driver);
	}
	
	public boolean isErrorMessageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            return wait.until(ExpectedConditions.visibilityOf(loginError)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

}
