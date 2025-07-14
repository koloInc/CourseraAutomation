package stepsDefination;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.en.*;
import pageObjects.ForFooter;
import utilities.Constants;
import utilities.ExcelUtils;

public class TestCase011 {

    WebDriver driver = BaseClass.getDriver();
    ForFooter footerPage;

    @When("the user scrolls to the footer")
    public void scroll_to_footer() throws InterruptedException {
        footerPage = new ForFooter(driver);
        footerPage.scrollToFooter();
    }

    @Then("the user opens each social media link in a new tab")
    public void open_social_links() throws InterruptedException {
        footerPage.openSocialMediaLinks();
    }

    @Then("verifies the title and URL of each page")
    public void verifies_the_title_and_url_of_each_page() {
        List<Map<String, String>> results = footerPage.getSocialMediaResults();
        ExcelUtils xl = new ExcelUtils(Constants.EXCEL_FILE);
        int rowIndex = 1;

        for (Map<String, String> row : results) {
            try {
                xl.setCellData(Constants.SHEET_FooterSocialLinkValidation, rowIndex, "Predicted Title", row.get("Predicted Title"));
                xl.setCellData(Constants.SHEET_FooterSocialLinkValidation, rowIndex, "Actual Title", row.get("Actual Title"));
                xl.setCellData(Constants.SHEET_FooterSocialLinkValidation, rowIndex, "VALIDATION Title", row.get("VALIDATION Title"));
                xl.setCellData(Constants.SHEET_FooterSocialLinkValidation, rowIndex, "Predicted Link", row.get("Predicted Link"));
                xl.setCellData(Constants.SHEET_FooterSocialLinkValidation, rowIndex, "Actual Link", row.get("Actual Link"));
                xl.setCellData(Constants.SHEET_FooterSocialLinkValidation, rowIndex, "VALIDATION Link", row.get("VALIDATION Link"));
                rowIndex++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        xl.closeFile();
    }
}