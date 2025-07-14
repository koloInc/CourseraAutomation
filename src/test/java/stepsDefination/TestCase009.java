package stepsDefination;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HomePage;
import utilities.Constants;
import utilities.ExcelUtils;

public class TestCase009 {

    WebDriver driver;
    HomePage homePage;
    ExcelUtils xl = new ExcelUtils(Constants.EXCEL_FILE);
    private static final Logger logger = LogManager.getLogger(TestCase009.class);

    Map<String, Map<String, String>> footerData = new HashMap<>();
    int index;

    @When("the user retrieves all footer sections")
    public void the_user_retrieves_all_footer_sections() throws IOException {
        logger.info("Retrieving all footer sections.");
        List<WebElement> footers = CommonSteps.homePage.getAllFooter();
        index = Constants.ROW_DATA;

        for (WebElement footer : footers) {
            String title = CommonSteps.homePage.getFooterTitle(footer);
            List<WebElement> units = CommonSteps.homePage.getContentList(footer);

            Map<String, String> linksMap = new HashMap<>();
            for (WebElement unit : units) {
                String text = unit.getText().trim();
                String href = unit.getAttribute("href");
                linksMap.put(text, href);
                logger.debug("Section: " + title + " → " + text + " → " + href);
            }

            footerData.put(title, linksMap);
        }

        logger.info("Total footer sections retrieved: " + footerData.size());
    }

    @Then("the footer titles and their links should be printed in the console")
    public void the_footer_titles_and_their_links_should_be_printed_in_the_console() throws IOException {
        logger.info("Validating and writing footer titles and links to Excel.");

        int rowIndex = Constants.ROW_DATA;

        for (Map.Entry<String, Map<String, String>> entry : footerData.entrySet()) {
            String sectionTitle = entry.getKey();
            Map<String, String> linksMap = entry.getValue();

            logger.info("Section: " + sectionTitle);
            xl.setCellData(Constants.SHEET_FooterValidation, rowIndex, Constants.COL_SECTIONS, sectionTitle);
            xl.fillGreenColor(Constants.SHEET_FooterValidation, rowIndex, 0);

            for (Map.Entry<String, String> linkEntry : linksMap.entrySet()) {
                String text = linkEntry.getKey();
                String href = linkEntry.getValue();

                xl.setCellData(Constants.SHEET_FooterValidation, rowIndex, Constants.COL_FOOTER_TITLE, text);
                xl.setCellData(Constants.SHEET_FooterValidation, rowIndex, Constants.COL_FOOTER_LINK, href);

                if (href == null || href.isEmpty()) {
                    logger.error("Missing link for footer item: " + text);
                    throw new AssertionError("No Link Found for: " + text);
                } else {
                    logger.debug("Validated link: " + text + " → " + href);
                }

                rowIndex++;
            }

            logger.info("Completed section: " + sectionTitle);
        }

        if (footerData.isEmpty()) {
            logger.error("No footer data found.");
            throw new AssertionError("No footer data found.");
        } else {
            logger.info("Footer data validation passed.");
        }

        xl.closeFile();
        logger.info("Footer data successfully written to Excel.");
    }
}
