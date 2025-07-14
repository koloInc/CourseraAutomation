package stepsDefination;

import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import factory.BaseClass;
import io.cucumber.java.en.*;

public class TestCase010 {

    WebDriver driver;
    private static final Logger logger = LogManager.getLogger(TestCase010.class);

    @Then("the user clicks the App Store link")
    public void the_user_clicks_the_app_store_link() {
        logger.info("Clicking the App Store link.");
        CommonSteps.homePage.clickAppStore();
    }

    @Then("a screenshot of the App Store page is taken")
    public void a_screenshot_of_the_app_store_page_is_taken() {
        logger.info("Capturing screenshot of the App Store page.");
        CommonSteps.homePage.takeScreenshotIOS();
    }

    @When("the user navigates back")
    public void the_user_navigates_back() {
        driver = BaseClass.getDriver();
        logger.info("Navigating back from App Store page.");
        driver.navigate().back();
    }

    @Then("the user clicks the Play Store link")
    public void the_user_clicks_the_play_store_link() {
        logger.info("Clicking the Play Store link.");
        CommonSteps.homePage.clickPlayStore();
    }

    @Then("a screenshot of the Play Store page is taken")
    public void a_screenshot_of_the_play_store_page_is_taken() {
        logger.info("Capturing screenshot of the Play Store page.");
        CommonSteps.homePage.takeScreenshotAndroid();
    }

    @Then("the user navigates back to the homepage")
    public void the_user_navigates_back_to_the_homepage() {
        driver = BaseClass.getDriver();
        logger.info("Navigating back to the homepage.");
        driver.navigate().back();
    }
}
