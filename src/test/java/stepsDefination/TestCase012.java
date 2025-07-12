package stepsDefination;

import java.util.List;
import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.CareersPage;
import utilities.Constants;
import utilities.ExcelUtils;
import utilities.XMLUtils;

public class TestCase012 {

    WebDriver driver = BaseClass.getDriver();
    XMLUtils xml = new XMLUtils(Constants.XML_FILE);
    ExcelUtils xl = new ExcelUtils(Constants.EXCEL_FILE);
    CareersPage careersPage;
    String mainWindow;

    @When("the user navigates to the Careers page")
    public void navigate_to_careers() {
        careersPage = new CareersPage(driver);
        mainWindow = driver.getWindowHandle();
        careersPage.clickCareersLink();
    }

    @When("clicks on See Open Positions")
    public void click_open_positions() {
        careersPage.clickOpenPositions();
        careersPage.switchToNewTab(mainWindow);
    }

    @When("filters jobs by country India")
    public void filter_jobs_india() throws InterruptedException {
        String country = xml.getElementValue("country_short");
        careersPage.filterByCountry(country);
    }

    @Then("the user should see a list of job openings in India")
    public void display_job_listings() {
        List<Map<String, String>> jobs = careersPage.getJobListings();
        int rowIndex = 1;

        for (Map<String, String> job : jobs) {
            try {
                xl.setCellData(Constants.SHEET_JOB_LISTINGS, rowIndex, "Title", job.get("Title"));
                xl.setCellData(Constants.SHEET_JOB_LISTINGS, rowIndex, "Department", job.get("Department"));
                xl.setCellData(Constants.SHEET_JOB_LISTINGS, rowIndex, "Location", job.get("Location"));
                rowIndex++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        xl.closeFile();
    }
}
