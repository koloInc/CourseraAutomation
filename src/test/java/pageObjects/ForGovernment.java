package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ForGovernment extends BasePage {

    public ForGovernment(WebDriver driver) {
        super(driver);
    }

    // Contact Sales Button
    @FindBy(xpath = "//a[@data-click-key='ent_website._government.click.navigation_priority_cta']")
    WebElement contactSales;

    @FindBy(id = "FirstName")
    WebElement firstName;

    @FindBy(id = "LastName")
    WebElement lastName;

    @FindBy(id = "Email")
    WebElement email;

    @FindBy(id = "Phone")
    WebElement phoneNo;

    @FindBy(id = "rentalField9")
    WebElement orgTypeEle;

    @FindBy(id = "Title")
    WebElement jobTitle;

    @FindBy(id = "Company")
    WebElement orgName;

    @FindBy(id = "Employee_Range__c")
    WebElement orgSizeEle;

    @FindBy(id = "What_the_lead_asked_for_on_the_website__c")
    WebElement aboutYouEle;

    @FindBy(id = "Country")
    WebElement countryEle;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement requestInfoButton;

    @FindBy(xpath = "//div[@data-track-component='EntWebsiteHow_thanks_for_your_interest_in_coursera_for_government']/div[2]/div[1]/div[1]/div[1]")
    WebElement confirmationMessage;

    // Action Methods
    public void clickContactSales() {
        contactSales.click();
    }

    public void setFirstName(String value) {
        firstName.sendKeys(value);
    }

    public void setLastName(String value) {
        lastName.sendKeys(value);
    }

    public void setEmail(String value) {
        email.sendKeys(value);
    }

    public void setMobileNo(String value) {
        phoneNo.sendKeys(value);
    }

    public void selectOrgType(int value) {
        new Select(orgTypeEle).selectByIndex(value);
    }

    public void setJobTitle(String value) {
        jobTitle.sendKeys(value);
    }

    public void setOrgName(String value) {
        orgName.sendKeys(value);
    }

    public void selectOrgSize(int value) {
        new Select(orgSizeEle).selectByIndex(value);
    }

    public void selectAboutYou(int value) {
        new Select(aboutYouEle).selectByIndex(value);
    }

    public void selectCountry(String value) {
        new Select(countryEle).selectByValue(value);
    }

    public void clickRequestInfo() {
        requestInfoButton.click();
    }

    public String getConfirmationMessage() {
        return confirmationMessage.getText();
    }
}
