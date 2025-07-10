package stepsDefination;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.en.*;
import pageObjects.OnlineDegrees;
import utilities.Constants;
import utilities.ExcelUtils;

public class TestCase005 {

    WebDriver driver;
    OnlineDegrees onlineDegrees;
    ExcelUtils xl=new ExcelUtils(Constants.EXCEL_FILE);

    @When("the user navigates to the Online Degrees section")
    public void the_user_navigates_to_the_online_degrees_section() {
        CommonSteps.homePage.clickOnlineDegree();
        driver = BaseClass.getDriver();
        onlineDegrees = new OnlineDegrees(driver);
    }

    @When("selects a random program level filter")
    public void selects_a_random_program_level_filter() {
        onlineDegrees.openProgramLevelDropdown();
        onlineDegrees.selectProgramLevelByIndex(BaseClass.randomNumberInRange(0, 1));
        onlineDegrees.applyFilters();
    }

    @When("selects a random subject filter")
    public void selects_a_random_subject_filter() {
        onlineDegrees.openSubjectDropdown();
        onlineDegrees.selectSubjectByIndex(BaseClass.randomNumberInRange(0, 6));
        onlineDegrees.applyFilters();
    }

    @Then("the application should display degree card details")
    public void the_application_should_display_the_total_number_of_results_and_degree_card_details() {
        //System.out.println("Total Results: " + onlineDegrees.getTotalResults());

        List<String> cardDetails = onlineDegrees.getCollegeDetails();

        int startingRow = Constants.ROW_DATA; // Row 0 can be used for headers if needed

        for (int i = 0; i < cardDetails.size(); i++) {
            String detail = cardDetails.get(i);

            // Console logging (optional for debugging)
            // System.out.println(detail);
            // System.out.println();

            try {
                xl.setCellData(Constants.SHEET_OnlineDegree, startingRow + i, Constants.COL_CARD_DETAILS, detail);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        xl.closeFile(); // Optional cleanup
    }

}
