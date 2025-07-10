package stepsDefination;

import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.en.*;
import pageObjects.ForBusiness;
import utilities.Constants;
import utilities.ExcelUtils;
import utilities.TextFileUtils;

public class TestCase006 {

    WebDriver driver;
    ForBusiness forBusiness;
    ExcelUtils xl=new ExcelUtils(Constants.EXCEL_FILE);

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
      
    }

    @When("enters a random number of users")
    public void enters_a_random_number_of_users_between_and() {
        forBusiness.clickGetStarted();
        String min = xl.getCellData(Constants.SHEET_BusinessPlan, 1, 0);
        String max = xl.getCellData(Constants.SHEET_BusinessPlan, 1, 1);
        int users = BaseClass.randomNumberInRange(Integer.parseInt(min), Integer.parseInt(max));
        forBusiness.enterNumberOfUsers(users);
    }

    @When("selects the payment option")
    public void selects_the_quarterly_payment_option() {
    		String time1 =	xl.getCellData(Constants.SHEET_BusinessPlan, 1, 2);
        forBusiness.selectQuarterlyPayment(time1);
    }

    @Then("the application should display the purchase summary")
    public void the_application_should_display_the_purchase_summary() {
        testUtils.WaitUtils.waitForDuration(driver, 2);
        String summary = forBusiness.getPurchaseSummary();

        // Optional console log
        System.out.println("Purchase Summary:\n" + summary);

        // Add timestamped entry
        String formatted = "\n--- Purchase Summary (TC006) ---\n"
                         + java.time.LocalDateTime.now()
                         + "\n\n" + summary
                         + "\n" + "=".repeat(80) + "\n";

        // Save to txt file
        TextFileUtils.writeToTextFile("PartnerData_TC6.txt", formatted, true);
    }


}
