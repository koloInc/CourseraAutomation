package stepsDefination;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import factory.BaseClass;
import io.cucumber.java.en.*;
import pageObjects.ForFooter;
import utilities.FileConstants;
import utilities.ExcelUtils;

public class TestCase011 {

    WebDriver driver = BaseClass.getDriver();
    ForFooter footerPage;
    ExcelUtils xl = new ExcelUtils(FileConstants.EXCEL_FILE);
    private static final Logger logger = LogManager.getLogger(TestCase011.class);

    @When("the user scrolls to the footer")
    public void scroll_to_footer() {
        logger.info("Scrolling to the footer section.");
        footerPage = new ForFooter(driver);
        footerPage.scrollToFooter();
    }

    @Then("the user opens each social media link in a new tab")
    public void open_social_links() throws InterruptedException {
        logger.info("Opening each social media link in a new tab.");
        footerPage.openSocialMediaLinks();
    }

    @Then("verifies the title and URL of each page")
    public void verifies_the_title_and_url_of_each_page() {
        logger.info("Verifying titles and URLs of opened social media pages.");

        List<String> actualTitles = footerPage.getActualTitles();
        List<String> actualLinks = footerPage.getActualLinks();

        boolean hasValidationFailure = false;

        for (int i = 0; i < actualTitles.size(); i++) {
            try {
                int excelRow = i + 1; // Assuming row 0 is header

                String expectedTitle = xl.getCellData(FileConstants.SHEET_FooterSocialLinkValidation, excelRow, FileConstants.EXPECTED_TITLE);
                String expectedLink = xl.getCellData(FileConstants.SHEET_FooterSocialLinkValidation, excelRow, FileConstants.EXPECTED_LINK);

                String actualTitle = actualTitles.get(i);
                String actualLink = actualLinks.get(i);

                xl.setCellData(FileConstants.SHEET_FooterSocialLinkValidation, excelRow, "Actual Title", actualTitle);
                xl.setCellData(FileConstants.SHEET_FooterSocialLinkValidation, excelRow, "Actual Link", actualLink);

                boolean titlePassed = actualTitle.contains(expectedTitle);
                boolean linkPassed = actualLink.contains(expectedLink);

                xl.setCellData(FileConstants.SHEET_FooterSocialLinkValidation, excelRow, "Title Validation", titlePassed ? "Passed" : "Failed");
                xl.setCellData(FileConstants.SHEET_FooterSocialLinkValidation, excelRow, "Link Validation", linkPassed ? "Passed" : "Failed");


                int titleCol = FileConstants.TITLE_VALIDATION; // "VALIDATION Title"
                int linkCol = FileConstants.LINK_VALIDATION;  // "VALIDATION Link"

                if (titlePassed) {
                    xl.fillGreenColor(FileConstants.SHEET_FooterSocialLinkValidation, excelRow, titleCol);
                } else {
                    xl.fillRedColor(FileConstants.SHEET_FooterSocialLinkValidation, excelRow, titleCol);
                    hasValidationFailure = true;
                }

                if (linkPassed) {
                    xl.fillGreenColor(FileConstants.SHEET_FooterSocialLinkValidation, excelRow, linkCol);
                } else {
                    xl.fillRedColor(FileConstants.SHEET_FooterSocialLinkValidation, excelRow, linkCol);
                    hasValidationFailure = true;
                }

                logger.debug("Row {} → Title: {} | Link: {}", excelRow, actualTitle, actualLink);
            } catch (IOException e) {
                logger.error("Failed to write validation data to Excel at row " + (i + 1), e);
            }
        }

        xl.closeFile();
        logger.info("Social media validation results successfully written to Excel.");

        if (hasValidationFailure) {
            throw new AssertionError("One or more social media validations failed. See logs and Excel for details.");
        }
    }

}
