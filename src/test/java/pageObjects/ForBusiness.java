package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForBusiness extends BasePage {

    public ForBusiness(WebDriver driver) {
        super(driver);
    }
    
    // Navigation
    @FindBy(xpath = "//a[normalize-space()='For Teams']")
    WebElement forTeams;

    @FindBy(xpath = "(//a[@href='https://www.coursera.org/purchase/plan'])[1]")
    WebElement getStarted;

    @FindBy(xpath = "//input[@aria-label='Number of users' and @placeholder='eg. 10']")
    WebElement noOfUsers;

    @FindBy(xpath = "//label[@for='billingType']/following-sibling::div/fieldset/span")
    List<WebElement> paymentOptions;

    @FindBy(xpath = "//div[@class='rc-TeamsPurchaseSummary vertical-box align-items-vertical-center rc-TeamsPurchaseSummary--redesign desktopOnly']")
    WebElement purchaseSummary;
    
    @FindBy(xpath ="//div[@class='rc-TeamsPurchaseSummary vertical-box align-items-vertical-center rc-TeamsPurchaseSummary--redesign desktopOnly']//span[@class='font-weight-bold']//span[1]")
    WebElement totalPrice;
    
    //Invalid Error Email Message
    @FindBy(id="ValidMsgEmail")
    WebElement invalidEmail ;
    
  //Invalid Error Phone Message
    @FindBy(id="ValidMsgPhone")
    WebElement invalidPhone ;

    // Actions
    public void clickForTeams() {
        forTeams.click();
    }

    public String getEmailError()
    {
    		return invalidEmail.getText();
    }
    	
    public String getPoneError()
    {
    		return invalidPhone.getText();
    }
    
    public void clickGetStarted() {
        js.executeScript("arguments[0].scrollIntoView(false);", getStarted);
        getStarted.click();
    }

    public void enterNumberOfUsers(int value) {
        noOfUsers.sendKeys(String.valueOf(value));
    }

    public void selectQuarterlyPayment(String time) {
        for (WebElement option : paymentOptions) {
            if (option.getText().contains(time)){
                option.click();
                break;
            }
        }
    }
    public String getTotalPrice() {
        String result = totalPrice.getText();
        //System.out.println("Raw text: [" + result + "]");

        return result; 
    }

    public String getPurchaseSummary() {
        return purchaseSummary.getText();
    }
}
	