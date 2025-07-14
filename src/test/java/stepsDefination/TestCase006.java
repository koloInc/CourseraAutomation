package stepsDefination;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import factory.BaseClass;
import io.cucumber.java.en.*;
import pageObjects.ForBusiness;
import utilities.Constants;
import utilities.ExcelUtils;
import utilities.TextFileUtils;

public class TestCase006 {

    WebDriver driver;
    ForBusiness forBusiness;
    ExcelUtils xl = new ExcelUtils(Constants.EXCEL_FILE);
    private static final Logger logger = LogManager.getLogger(TestCase006.class);

    @Then("the user navigates to the For Business section")
    public void the_user_navigates_to_the_for_business_section() {
        logger.info("Navigating to the For Business section.");
        CommonSteps.homePage.clickForBusiness();
    }

    @When("the user selects the {string} plan")
    public void the_user_selects_the_plan(String planType) {
        driver = BaseClass.getDriver();
        forBusiness = new ForBusiness(driver);

        logger.info("Selecting business plan: " + planType);
        if (planType.equalsIgnoreCase("For Teams")) {
            forBusiness.clickForTeams();
            logger.debug("Clicked on 'For Teams' plan.");
        }
    }

    @When("enters a random number of users")
    public void enters_a_random_number_of_users_between_and() {
        forBusiness.clickGetStarted();
        int users = Integer.parseInt(xl.getCellData(Constants.SHEET_BusinessPlan, 1, 0));
        logger.info("Entering number of users: " + users);
        forBusiness.enterNumberOfUsers(users);
    }

    @When("selects the payment option")
    public void selects_the_quarterly_payment_option() {
        String time1 = xl.getCellData(Constants.SHEET_BusinessPlan, 1, 2);
        logger.info("Selecting payment option: " + time1);
        forBusiness.selectQuarterlyPayment(time1);
    }

    @Then("the application should display the purchase summary")
    public void the_application_should_display_the_purchase_summary() {
        utilities.WaitUtils.waitForDuration(driver, 2);
        String summary = forBusiness.getPurchaseSummary();
        int rowNo = 1;
        String expected = xl.getCellData(Constants.SHEET_BusinessPlan, 1, 3);
        String actual = forBusiness.getTotalPrice().replaceAll("[^\\d]", "").trim();

        logger.info("Expected Price: " + expected);
        logger.info("Actual Price: " + actual);

     try {
            xl.setCellData(Constants.SHEET_BusinessPlan, rowNo, "Actual", actual);
        } catch (IOException e) {
            logger.error("Failed to write actual price to Excel", e);
        }

        String columnName = "Validation";
        int columnNo = xl.getColumnIndex(Constants.SHEET_BusinessPlan, columnName);
        String status;

        try {
            Assert.assertEquals(actual, expected);
            status = "Passed";
            logger.info("Price validation passed.");
        } catch (AssertionError e) {
            status = "Failed";
            logger.warn("Price validation failed: " + e.getMessage());
        }

        try {
            xl.setCellData(Constants.SHEET_BusinessPlan, rowNo, columnName, status);

            if (status.equalsIgnoreCase("Passed")) {
                xl.fillGreenColor(Constants.SHEET_BusinessPlan, rowNo, columnNo);
            } else {
                xl.fillRedColor(Constants.SHEET_BusinessPlan, rowNo, columnNo);
            }
        } catch (IOException e) {
            logger.error("Failed to write validation result to Excel", e);
        }

        String formatted = "\n--- Purchase Summary (TC006) ---\n" + java.time.LocalDateTime.now() + "\n\n" + summary
                + "\n" + "=".repeat(80) + "\n";

        TextFileUtils.writeToTextFile("PartnerData_TC6.txt", formatted, true);
        logger.info("Purchase summary written to PartnerData_TC6.txt");
    }
}
