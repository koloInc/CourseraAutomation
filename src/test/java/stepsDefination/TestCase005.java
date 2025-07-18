package stepsDefination;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import factory.BaseClass;
import io.cucumber.java.en.*;
import pageObjects.OnlineDegrees;
import utilities.FileConstants;
import utilities.ExcelUtils;

public class TestCase005 {

    WebDriver driver;
    OnlineDegrees onlineDegrees;
    ExcelUtils xl = new ExcelUtils(FileConstants.EXCEL_FILE);
    private static final Logger logger = LogManager.getLogger(TestCase005.class);

    @When("the user navigates to the Online Degrees section")
    public void the_user_navigates_to_the_online_degrees_section() {
        logger.info("Navigating to the Online Degrees section.");
        CommonSteps.homePage.clickOnlineDegree();
        driver = BaseClass.getDriver();
        onlineDegrees = new OnlineDegrees(driver);
    }

    @When("selects a random program level filter")
    public void selects_a_random_program_level_filter() {
        logger.info("Selecting a random program level filter.");
        onlineDegrees.openProgramLevelDropdown();
        int index = BaseClass.randomNumberInRange(0, 1);
        logger.debug("Program level index selected: " + index);
        onlineDegrees.selectProgramLevelByIndex(index);
        onlineDegrees.applyFilters();
    }

    @When("selects a random subject filter")
    public void selects_a_random_subject_filter() {
        logger.info("Selecting a random subject filter.");
        onlineDegrees.openSubjectDropdown();
        int index = BaseClass.randomNumberInRange(0, 6);
        logger.debug("Subject index selected: " + index);
        onlineDegrees.selectSubjectByIndex(index);
        onlineDegrees.applyFilters();
    }

    @Then("the application should display degree card details")
    public void the_application_should_display_the_total_number_of_results_and_degree_card_details() {
        logger.info("Extracting degree card details.");
        List<String> cardDetails = onlineDegrees.getCollegeDetails();
        logger.debug("Total degree cards found: " + cardDetails.size());

        int startingRow = FileConstants.ROW_DATA;

        for (int i = 0; i < cardDetails.size(); i++) {
            String detail = cardDetails.get(i);
            logger.debug("Card " + (i + 1) + ": " + detail);

            try {
                xl.setCellData(FileConstants.SHEET_OnlineDegree, startingRow + i, FileConstants.COL_CARD_DETAILS, detail);
            } catch (IOException e) {
                logger.error("Failed to write degree card detail to Excel at row " + (startingRow + i), e);
            }
        }

        xl.closeFile();
        logger.info("Degree card details successfully written to Excel.");
    }
}
