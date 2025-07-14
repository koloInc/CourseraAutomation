package stepsDefination;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
    private static final Logger logger = LogManager.getLogger(TestCase011.class);

    @When("the user scrolls to the footer")
    public void scroll_to_footer() throws InterruptedException {
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
        List<Map<String, String>> results = footerPage.getSocialMediaResults();
        ExcelUtils xl = new ExcelUtils(Constants.EXCEL_FILE);
        int rowIndex = 1;

        for (Map<String, String> row : results) {
            try {
                String predictedTitle = row.get("Predicted Title");
                String actualTitle = row.get("Actual Title");
                String titleValidation = row.get("VALIDATION Title");
                String predictedLink = row.get("Predicted Link");
                String actualLink = row.get("Actual Link");
                String linkValidation = row.get("VALIDATION Link");

                logger.debug("Row " + rowIndex + " → Title: " + actualTitle + " | Link: " + actualLink);

                xl.setCellData(Constants.SHEET_FooterSocialLinkValidation, rowIndex, "Predicted Title", predictedTitle);
                xl.setCellData(Constants.SHEET_FooterSocialLinkValidation, rowIndex, "Actual Title", actualTitle);
                xl.setCellData(Constants.SHEET_FooterSocialLinkValidation, rowIndex, "VALIDATION Title", titleValidation);
                xl.setCellData(Constants.SHEET_FooterSocialLinkValidation, rowIndex, "Predicted Link", predictedLink);
                xl.setCellData(Constants.SHEET_FooterSocialLinkValidation, rowIndex, "Actual Link", actualLink);
                xl.setCellData(Constants.SHEET_FooterSocialLinkValidation, rowIndex, "VALIDATION Link", linkValidation);

                rowIndex++;
            } catch (IOException e) {
                logger.error("Failed to write social media validation data to Excel at row " + rowIndex, e);
            }
        }

        xl.closeFile();
        logger.info("Social media validation results successfully written to Excel.");
    }
}
