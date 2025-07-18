package stepsDefination;

import java.util.List;
import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import factory.BaseClass;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.CareersPage;
import utilities.FileConstants;
import utilities.ExcelUtils;
import utilities.XMLUtils;

public class TestCase012 {

    WebDriver driver = BaseClass.getDriver();
    XMLUtils xml = new XMLUtils(FileConstants.XML_FILE);
    ExcelUtils xl = new ExcelUtils(FileConstants.EXCEL_FILE);
    CareersPage careersPage;
    String mainWindow;
    private static final Logger logger = LogManager.getLogger(TestCase012.class);

    @When("the user navigates to the Careers page")
    public void navigate_to_careers() {
        logger.info("Navigating to the Careers page.");
        careersPage = new CareersPage(driver);
        mainWindow = driver.getWindowHandle();
        careersPage.clickCareersLink();
    }

    @When("clicks on See Open Positions")
    public void click_open_positions() {
        logger.info("Clicking on 'See Open Positions'.");
        careersPage.clickOpenPositions();
        careersPage.switchToNewTab(mainWindow);
        logger.info("Switched to new tab for job listings.");
    }

    @When("filters jobs by country India")
    public void filter_jobs_india() throws InterruptedException {
        String country = xml.getElementValue("country_short");
        logger.info("Filtering jobs by country: " + country);
        careersPage.filterByCountry(country);
    }

    @Then("the user should see a list of job openings in India")
    public void display_job_listings() {
        logger.info("Extracting job listings for India.");
        List<Map<String, String>> jobs = careersPage.getJobListings();
        int rowIndex = 1;

        for (Map<String, String> job : jobs) {
            String title = job.get("Title");
            String department = job.get("Department");
            String location = job.get("Location");

            logger.debug("Job " + rowIndex + " → Title: " + title + ", Department: " + department + ", Location: " + location);

            try {
                xl.setCellData(FileConstants.SHEET_CareersJobListings, rowIndex, "Title", title);
                xl.setCellData(FileConstants.SHEET_CareersJobListings, rowIndex, "Department", department);
                xl.setCellData(FileConstants.SHEET_CareersJobListings, rowIndex, "Location", location);
            } catch (IOException e) {
                logger.error("Failed to write job listing to Excel at row " + rowIndex, e);
            }

            rowIndex++;
        }

        xl.closeFile();
        logger.info("Job listings successfully written to Excel.");
    }
}
