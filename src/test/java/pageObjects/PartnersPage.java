package pageObjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PartnersPage extends BasePage {

    public PartnersPage(WebDriver driver) {
        super(driver);
    }

    // Country Selector
    @FindBy(xpath = "//div[@data-e2e='country-tags']")
    private WebElement countryTagsContainer;

    @FindBy(xpath = "//div[@data-e2e='country-tags']//a")
    private List<WebElement> countryList;

    // Partner Cards
    @FindBy(xpath = "//*[@class='horizontal-box wrap css-jqim4p']//a")
    private List<WebElement> availableLinks;

    @FindBy(xpath = "//*[@class='horizontal-box wrap css-jqim4p']//a//img")
    private List<WebElement> availableLogos;

    @FindBy(xpath = "//*[@class='horizontal-box wrap css-jqim4p']//a//p")
    private List<WebElement> availableNames;

    // Actions

    /** Scrolls to country selector and selects the specified country */
    public void openPartnersByCountry(String countryName) {
        scrollIntoView(countryTagsContainer);
        for (WebElement country : countryList) {
            if (country.getText().trim().equalsIgnoreCase(countryName)) {
                country.click();
                break;
            }
        }
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
