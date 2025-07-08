package stepsDefination;

import org.openqa.selenium.WebDriver;
import factory.BaseClass;
import io.cucumber.java.en.*;
import pageObjects.HomePage;

public class TestCase10 {

    WebDriver driver;
    HomePage homePage;

    @Given("the user is on the homepage and clicks the App Store link")
    public void the_user_is_on_the_homepage_and_clicks_the_app_store_link() {
        driver = BaseClass.getDriver();
        homePage = new HomePage(driver);
        homePage.clickAppStore();
    }

    @Then("a screenshot of the App Store page is taken")
    public void a_screenshot_of_the_app_store_page_is_taken() {
        homePage.takeScreenshotIOS();
    }

    @When("the user navigates back")
    public void the_user_navigates_back() {
        driver.navigate().back();
    }

    @When("the user clicks the Play Store link")
    public void the_user_clicks_the_play_store_link() {
        homePage.clickPlayStore();
    }

    @Then("a screenshot of the Play Store page is taken")
    public void a_screenshot_of_the_play_store_page_is_taken() {
        homePage.takeScreenshotAndroid();
    }

    @Then("the user navigates back to the homepage")
    public void the_user_navigates_back_to_the_homepage() {
        driver.navigate().back();
    }
}
