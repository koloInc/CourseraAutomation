package stepsDefination;

import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.en.*;
import pageObjects.ForBusiness;

public class TestCase006 {

    WebDriver driver;
    ForBusiness forBusiness;

    @Then("the user navigates to the For Business section")
    public void the_user_navigates_to_the_for_business_section() {
        CommonSteps.homePage.clickForBusiness();
    }

    @When("the user selects the {string} plan")
    public void the_user_selects_the_plan(String planType) {
        driver = BaseClass.getDriver();
        forBusiness = new ForBusiness(driver);

        if (planType.equalsIgnoreCase("For Teams")) {
            forBusiness.clickForTeams();
        }
        // You can extend this for other plan types if needed
    }

    @When("enters a random number of users between {int} and {int}")
    public void enters_a_random_number_of_users_between_and(Integer min, Integer max) {
        forBusiness.clickGetStarted();
        int users = BaseClass.randomNumberInRange(min, max);
        forBusiness.enterNumberOfUsers(users);
    }

    @When("selects the quarterly payment option")
    public void selects_the_quarterly_payment_option() {
        forBusiness.selectQuarterlyPayment();
    }

    @Then("the application should display the purchase summary")
    public void the_application_should_display_the_purchase_summary() {
        testUtils.WaitUtils.waitForDuration(driver, 2);
        System.out.println("Purchase Summary:\n" + forBusiness.getPurchaseSummary());
    }
}
