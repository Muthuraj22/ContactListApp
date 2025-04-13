package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.ProjectSpecificationMethods;

public class ContactListPage extends ProjectSpecificationMethods{

    private WebDriver driver;
    
    @FindBy(xpath = "//h1[text()='Contact List']")
    private WebElement contactListHeader;

	private WebDriverWait Wait;
    
    @FindBy(id = "add-contact")
    private WebElement addContactButton;
    
    @FindBy(xpath = "(//tr[@class='contactTableBodyRow'])[last()]/td[2]")
    WebElement FirstElement;
    
    @FindBy(id = "logout")
    private WebElement logoutButton;
    
    

    public ContactListPage(WebDriver driver) {
        this.driver = driver;
        this.Wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    

    public boolean isAtContactListPage() {
        return contactListHeader.isDisplayed();
    }
    
    public AddContactPage clickAddContactButton() {
    	addContactButton.click();
		return new AddContactPage(driver);
	}
    
    public boolean isAddContactButtonVisible() {
        try {
            Wait.until(ExpectedConditions.visibilityOf(addContactButton));
            return addContactButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAddContactButtonClickable() {
        try {
            Wait.until(ExpectedConditions.elementToBeClickable(addContactButton));
            return addContactButton.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getContactNameByName(String expectedName) {
        return driver.findElement(By.xpath("//tr[td[contains(text(),'" + expectedName + "')]]/td[2]")).getText();
    }

    public String getContactPhoneByName(String expectedName) {
        return driver.findElement(By.xpath("//tr[td[contains(text(),'" + expectedName + "')]]/td[5]")).getText().replace("Phone: ", "").trim();
    }
    	
 //       return driver.findElement(By.xpath("//tr[@class='contactTableBodyRow'][last()]/td[5]"))
  //                   .getText().replace("Phone: ", "").trim();
    
    
    public List<String> getAllContactLastNames() {
        List<WebElement> rows = driver.findElements(By.xpath("//tr[contains(@class,'contactTableBodyRow')]"));
        List<String> lastNames = new ArrayList<>();

        for (WebElement row : rows) {
            String fullName = row.findElement(By.xpath("./td[2]")).getText().trim(); // assuming full name is in td[2]
            if (!fullName.isEmpty() && fullName.contains(" ")) {
                String lastName = fullName.substring(fullName.lastIndexOf(" ") + 1);
                lastNames.add(lastName);
            }
        }

        return lastNames;
    }
    
    public List<String> getAllContactPhoneNumbers() {
        List<WebElement> rows = driver.findElements(By.xpath("//tr[contains(@class,'contactTableBodyRow')]"));
        List<String> phoneNumbers = new ArrayList<>();

        for (WebElement row : rows) {
            String phoneRaw = row.findElement(By.xpath("./td[5]")).getText(); // assuming phone is in td[5]
            String phone = phoneRaw.replace("Phone: ", "").trim();
            if (!phone.isEmpty()) {
                phoneNumbers.add(phone);
            }
        }

        return phoneNumbers;
    }
    
    public ContactDetailsPage clickFirstElement() {
    	FirstElement.click();
    	return new ContactDetailsPage(driver);
    }
    
    public void clickContactElement(String name) {
        driver.findElement(By.xpath("//tr[td[contains(text(),'"+name+"')]]")).click();
    }
    
    public boolean isLogoutButtonVisible() {
        return logoutButton.isDisplayed();
    }
    
    public ContactListPage clickLogoutButton() {
    	logoutButton.click();
    	System.out.println("Logout button clicked");
    	return this;
    }

    
}
