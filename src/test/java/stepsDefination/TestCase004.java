package stepsDefination;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
    ExcelUtils xl=new ExcelUtils(Constants.EXCEL_FILE);

    @Then("the user navigates to the Partners section")
    public void the_user_navigates_to_the_partners_section() {
        CommonSteps.homePage.clickPartners();
    }

    @And("opens the country Partners list")
    public void opens_the_country_partners_list() {
        driver = BaseClass.getDriver();
        partnersPage = new PartnersPage(driver);
        partnersPage.openPartnersByCountry(xml.getElementValue("country"));
    }

    @Then("the application should display all partner links, logo visibility, and names")
    public void the_application_should_display_all_partner_links_logo_visibility_and_names() {
        List<WebElement> links = partnersPage.getAllPartnerLinks();
        List<WebElement> logos = partnersPage.getAllPartnerLogos();
        List<WebElement> names = partnersPage.getAllPartnerNames();

        int total = Math.min(Math.min(links.size(), logos.size()), names.size());

        int startingRow = Constants.ROW_DATA;

        // Uncomment if you want Excel headers automatically in row 0
       /* try {
            xl.setCellData(Constants.SHEET_PartnerInfo, 0, "Partner Link", "Partner Link");
            xl.setCellData(Constants.SHEET_PartnerInfo, 0, "Logo Displayed", "Logo Displayed");
            xl.setCellData(Constants.SHEET_PartnerInfo, 0, "Partner Name", "Partner Name");
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        for (int i = 0; i < total; i++) {
            String url = links.get(i).getAttribute("href");
            String visible = String.valueOf(logos.get(i).isDisplayed());
            String label = names.get(i).getText().trim();

            // Commented out console output
            // System.out.println(String.format("%-80s\t%-15s\t%-40s", url, visible, label));

            try {
                xl.setCellData(Constants.SHEET_PartnerInfo, startingRow + i, Constants.COL_PARTNER_LINK, url);
                xl.setCellData(Constants.SHEET_PartnerInfo, startingRow + i, Constants.COL_LOGO_DISP, visible);
                xl.setCellData(Constants.SHEET_PartnerInfo, startingRow + i, Constants.COL_PARTNER_NAME, label);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        xl.closeFile();
    }
}
