package pageObjects;

import java.io.File;

import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    //  Navigation Links
    @FindBy(id = "search-autocomplete-input")
    WebElement searchBar;

    @FindBy(xpath = "//a[@data-click-key='front_page.front_page_story.click.navigation_meta_nav_Business']")
    WebElement forBusiness;

    @FindBy(xpath = "//a[@data-click-key='front_page.front_page_story.click.navigation_meta_nav_Government']")
    WebElement forGovernment;

    @FindBy(xpath = "//a[normalize-space()='Online Degrees']")
    WebElement onlineDegree;

    //  Mobile Store Badges
    @FindBy(xpath = "//a[@data-click-key='front_page.front_page_story.click.mobile_app_badges_ios']")
    WebElement appStoreBadge;

    @FindBy(xpath = "//a[@data-click-key='front_page.front_page_story.click.mobile_app_badges_android']")
    WebElement playStoreBadge;

    //  Action Methods

    public void clickForBusiness() {
        forBusiness.click();
    }

    public void clickForGovernment() {
        forGovernment.click();
    }

    public void setSearchBar(String value) {
        searchBar.sendKeys(value);
    }

    public void submitSearch() {
        searchBar.sendKeys(Keys.ENTER);
    }

    public void clickOnlineDegree() {
        onlineDegree.click();
    }

    public void clickAppStore() {
        scrollIntoView(appStoreBadge);
        appStoreBadge.click();
    }

    public void clickPlayStore() {
        scrollIntoView(playStoreBadge);
        playStoreBadge.click();
    }

    public void takeScreenshotIOS() {
        captureScreenshot("Apple.png");
    }

    public void takeScreenshotAndroid() {
        captureScreenshot("Android.png");
    }
}
