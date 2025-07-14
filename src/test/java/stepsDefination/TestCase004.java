package stepsDefination;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import factory.BaseClass;
import io.cucumber.java.en.*;
import pageObjects.PartnersPage;
import utilities.Constants;
import utilities.ExcelUtils;
import utilities.XMLUtils;

public class TestCase004 {

    WebDriver driver;
    PartnersPage partnersPage;
    XMLUtils xml = new XMLUtils(Constants.XML_FILE);
    ExcelUtils xl = new ExcelUtils(Constants.EXCEL_FILE);
    private static final Logger logger = LogManager.getLogger(TestCase004.class);

    @Then("the user navigates to the Partners section")
    public void the_user_navigates_to_the_partners_section() {
        logger.info("Navigating to the Partners section.");
        CommonSteps.homePage.clickPartners();
    }

    @And("opens the country Partners list")
    public void opens_the_country_partners_list() {
        driver = BaseClass.getDriver();
        partnersPage = new PartnersPage(driver);
        String country = xml.getElementValue("country");
        logger.info("Opening partner list for country: " + country);
        partnersPage.openPartnersByCountry(country);
    }

    @Then("the application should display all partner links, logo visibility, and names")
    public void the_application_should_display_all_partner_links_logo_visibility_and_names() {
        logger.info("Extracting partner link, logo visibility, and name details.");

        List<WebElement> links = partnersPage.getAllPartnerLinks();
        List<WebElement> logos = partnersPage.getAllPartnerLogos();
        List<WebElement> names = partnersPage.getAllPartnerNames();

        int total = Math.min(Math.min(links.size(), logos.size()), names.size());
        logger.debug("Total partners found: " + total);

        int startingRow = Constants.ROW_DATA;

        for (int i = 0; i < total; i++) {
            String url = links.get(i).getAttribute("href");
            String visible = String.valueOf(logos.get(i).isDisplayed());
            String label = names.get(i).getText().trim();

            logger.debug(String.format("Partner %d → URL: %s | Logo Visible: %s | Name: %s", i + 1, url, visible, label));

            try {
                xl.setCellData(Constants.SHEET_PartnerInfo, startingRow + i, Constants.COL_PARTNER_LINK, url);
                xl.setCellData(Constants.SHEET_PartnerInfo, startingRow + i, Constants.COL_LOGO_DISP, visible);
                xl.setCellData(Constants.SHEET_PartnerInfo, startingRow + i, Constants.COL_PARTNER_NAME, label);
            } catch (IOException e) {
                logger.error("Failed to write partner data to Excel at row " + (startingRow + i), e);
            }
        }

        xl.closeFile();
        logger.info("Partner data successfully written to Excel.");
    }
}
