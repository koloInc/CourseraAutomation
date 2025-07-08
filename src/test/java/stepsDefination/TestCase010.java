package stepsDefination;

import org.openqa.selenium.WebDriver;
import factory.BaseClass;
import io.cucumber.java.en.*;

public class TestCase010 {

    WebDriver driver;

    @Then("the user clicks the App Store link")
    public void the_user_clicks_the_app_store_link() {
        CommonSteps.homePage.clickAppStore();
    }

    @Then("a screenshot of the App Store page is taken")
    public void a_screenshot_of_the_app_store_page_is_taken() {
        CommonSteps.homePage.takeScreenshotIOS();
    }

    @When("the user navigates back")
    public void the_user_navigates_back() {
        driver = BaseClass.getDriver();
        driver.navigate().back();
    }

    @Then("the user clicks the Play Store link")
    public void the_user_clicks_the_play_store_link() {
        CommonSteps.homePage.clickPlayStore();
    }

    @Then("a screenshot of the Play Store page is taken")
    public void a_screenshot_of_the_play_store_page_is_taken() {
        CommonSteps.homePage.takeScreenshotAndroid();
    }

    @Then("the user navigates back to the homepage")
    public void the_user_navigates_back_to_the_homepage() {
        driver = BaseClass.getDriver();
        driver.navigate().back();
    }
}
