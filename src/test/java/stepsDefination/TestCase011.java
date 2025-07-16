package stepsDefination;
 
import java.io.IOException;
import java.util.List;
 
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
 
import factory.BaseClass;
import io.cucumber.java.en.*;
import pageObjects.ForFooter;
import utilities.Constants;
import utilities.ExcelUtils;
 
public class TestCase011 {
 
    WebDriver driver = BaseClass.getDriver();
    ForFooter footerPage;
    ExcelUtils xl = new ExcelUtils(Constants.EXCEL_FILE);
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
 
        int rowCount = xl.getRowCount(Constants.SHEET_FooterSocialLinkValidation);
        boolean hasValidationFailure = false;
 
        for (int i = 1; i < rowCount && i - 1 < actualTitles.size(); i++) {
            try {
                String expectedTitle = xl.getCellData(Constants.SHEET_FooterSocialLinkValidation, i, xl.getColumnIndex(Constants.SHEET_FooterSocialLinkValidation, "Predicted Title"));
                String expectedLink = xl.getCellData(Constants.SHEET_FooterSocialLinkValidation, i, xl.getColumnIndex(Constants.SHEET_FooterSocialLinkValidation, "Predicted Link"));
 
                String actualTitle = actualTitles.get(i - 1);
                String actualLink = actualLinks.get(i - 1);
 
                xl.setCellData(Constants.SHEET_FooterSocialLinkValidation, i, "Actual Title", actualTitle);
                xl.setCellData(Constants.SHEET_FooterSocialLinkValidation, i, "Actual Link", actualLink);
 
                String titleValidation = actualTitle.equals(expectedTitle) ? "Passed" : "Failed";
                String linkValidation = actualLink.equals(expectedLink) ? "Passed" : "Failed";
 
                xl.setCellData(Constants.SHEET_FooterSocialLinkValidation, i, "VALIDATION Title", titleValidation);
                xl.setCellData(Constants.SHEET_FooterSocialLinkValidation, i, "VALIDATION Link", linkValidation);
 
                int titleCol = xl.getColumnIndex(Constants.SHEET_FooterSocialLinkValidation, "VALIDATION Title");
                int linkCol = xl.getColumnIndex(Constants.SHEET_FooterSocialLinkValidation, "VALIDATION Link");
 
                if ("Passed".equalsIgnoreCase(titleValidation)) {
                    xl.fillGreenColor(Constants.SHEET_FooterSocialLinkValidation, i, titleCol);
                } else {
                    xl.fillRedColor(Constants.SHEET_FooterSocialLinkValidation, i, titleCol);
                    hasValidationFailure = true;
                }
 
                if ("Passed".equalsIgnoreCase(linkValidation)) {
                    xl.fillGreenColor(Constants.SHEET_FooterSocialLinkValidation, i, linkCol);
                } else {
                    xl.fillRedColor(Constants.SHEET_FooterSocialLinkValidation, i, linkCol);
                    hasValidationFailure = true;
                }
 
                logger.debug("Row " + i + " → Title: " + actualTitle + " | Link: " + actualLink);
            } catch (IOException e) {
                logger.error("Failed to write validation data to Excel at row " + i, e);
            }
        }
 
        xl.closeFile();
        logger.info("Social media validation results successfully written to Excel.");
 
        if (hasValidationFailure) {
            throw new AssertionError("One or more social media validations failed. See logs and Excel for details.");
        }
    }
}