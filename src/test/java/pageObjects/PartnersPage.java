package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PartnersPage extends BasePage {

    public PartnersPage(WebDriver driver) {
        super(driver);
    }

    //  Country Selector
    @FindBy(xpath = "//a[normalize-space()='India']")
    WebElement country;

    //  Partner Cards
    @FindBy(xpath = "//*[@class='horizontal-box wrap css-jqim4p']//a")
    List<WebElement> availableLinks;

    @FindBy(xpath = "//*[@class='horizontal-box wrap css-jqim4p']//a//img")
    List<WebElement> availableLogos;

    @FindBy(xpath = "//*[@class='horizontal-box wrap css-jqim4p']//a//p")
    List<WebElement> availableNames;

    // Actions
    public void openIndiaPartners() {
        scrollIntoView(country);
        country.click();
    }

    public List<WebElement> getAllPartnerLinks() {
        return availableLinks;
    }

    public List<WebElement> getAllPartnerLogos() {
        return availableLogos;
    }

    public List<WebElement> getAllPartnerNames() {
        return availableNames;
    }
}
