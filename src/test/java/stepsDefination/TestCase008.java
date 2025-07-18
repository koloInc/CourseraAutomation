package stepsDefination;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import factory.BaseClass;
import io.cucumber.java.en.*;
import pageObjects.ForGovernment;
import utilities.FileConstants;
import utilities.ExcelUtils;
import utilities.XMLUtils;

public class TestCase008 {

    WebDriver driver;
    ForGovernment forGovernment;
    XMLUtils xml = new XMLUtils(FileConstants.XML_FILE);
    ExcelUtils xl = new ExcelUtils(FileConstants.EXCEL_FILE);
    private static final Logger logger = LogManager.getLogger(TestCase008.class);

    @Then("the user navigates to the For Government section")
    public void the_user_navigates_to_the_for_government_section() {
        logger.info("Navigating to the For Government section.");
        CommonSteps.homePage.clickForGovernment();
        driver = BaseClass.getDriver();
        forGovernment = new ForGovernment(driver);
    }

    @When("the user clicks the Contact Sales button")
    public void the_user_clicks_the_contact_sales_button() {
        logger.info("Clicking the Contact Sales button.");
        forGovernment.clickContactSales();
    }

    @When("fills out the government form with valid details")
    public void fills_out_the_government_form_with_valid_details() {
        logger.info("Filling out government form with valid details.");

        String firstName = BaseClass.randomeString();
        String lastName = BaseClass.randomeString();
        String email = BaseClass.randomEmail();
        String mobile = BaseClass.randomMobileNumber();
        String jobTitle = BaseClass.randomeString() + BaseClass.randomeString();
        String orgName = BaseClass.randomeString();
        String country = xml.getElementValue("country");

        forGovernment.setFirstName(firstName);
        forGovernment.setLastName(lastName);
        forGovernment.setEmail(email);
        forGovernment.setMobileNo(mobile);
        forGovernment.selectOrgType(BaseClass.randomNumberInRange(1, 2));
        forGovernment.setJobTitle(jobTitle);
        forGovernment.setOrgName(orgName);
        forGovernment.selectOrgSize(BaseClass.randomNumberInRange(1, 5));
        forGovernment.selectAboutYou(BaseClass.randomNumberInRange(2, 4));
        forGovernment.selectCountry(country);

        logger.debug("Form Data → FirstName: {}, LastName: {}, Email: {}, Mobile: {}, JobTitle: {}, OrgName: {}, Country: {}",
                firstName, lastName, email, mobile, jobTitle, orgName, country);
    }

    @When("submits the government request form")
    public void submits_the_government_request_form() {
        logger.info("Submitting the government request form.");
        forGovernment.clickRequestInfo();
    }

    @Then("the application should display a confirmation message")
    public void the_application_should_display_a_confirmation_message() {
        logger.info("Capturing confirmation message after form submission.");
        int row_no = 1;
        String message = forGovernment.getConfirmationMessage();
        logger.info("Confirmation Message: " + message);

        try {
            xl.setCellData(FileConstants.SHEET_GovtFileValidation, row_no, "Confirmation Message", message);
            logger.info("Confirmation message written to Excel.");
        } catch (IOException e) {
            logger.error("Failed to write confirmation message to Excel", e);
        }
    }
}
