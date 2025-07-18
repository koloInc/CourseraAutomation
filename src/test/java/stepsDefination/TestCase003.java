package stepsDefination;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import factory.BaseClass;
import io.cucumber.java.en.*;
import pageObjects.ForBusiness;
import pageObjects.ForGovernment;
import utilities.FileConstants;
import utilities.ExcelUtils;
import utilities.XMLUtils;

public class TestCase003 {

    WebDriver driver;
    ForGovernment forGovernment;
    ForBusiness forBusiness;
    XMLUtils xml = new XMLUtils(FileConstants.XML_FILE);
    ExcelUtils xl = new ExcelUtils(FileConstants.EXCEL_FILE);
    private static final Logger logger = LogManager.getLogger(TestCase003.class);

    @When("the user fills out the government request form with invalid email and phone number")
    public void the_user_fills_out_the_government_request_form_with_invalid_email_and_phone_number() {
        logger.info("Filling out government request form with invalid email and phone number.");
        driver = BaseClass.getDriver();
        forGovernment = new ForGovernment(driver);

        forGovernment.setFirstName(BaseClass.randomeString());
        forGovernment.setLastName(BaseClass.randomeString());
        forGovernment.setEmail(BaseClass.randomMobileNumber());
        forGovernment.setMobileNo(BaseClass.randomAlphaNumeric());
        forGovernment.selectOrgType(BaseClass.randomNumberInRange(3, 4));
        forGovernment.setJobTitle(BaseClass.randomeString() + BaseClass.randomeString());
        forGovernment.setOrgName(BaseClass.randomeString());
        forGovernment.selectOrgSize(BaseClass.randomNumberInRange(1, 5));
        forGovernment.selectAboutYou(BaseClass.randomNumberInRange(1, 5));
        String country = xml.getElementValue("country");
        forGovernment.selectCountry(country);

        logger.debug("Country selected: " + country);
    }

    @When("submits the request for information")
    public void submits_the_request_for_information() {
        logger.info("Submitting the government request form.");
        forGovernment.clickRequestInfo();
    }

    @Then("the application should display appropriate error messages for email and phone number")
    public void the_application_should_display_appropriate_error_messages_for_email_and_phone_number() {
        logger.info("Validating error messages for email and phone number.");
        forBusiness = new ForBusiness(driver);

        try {
            String emailError = forBusiness.getEmailError();
            xl.setCellData(FileConstants.SHEET_ErrorMessage, FileConstants.ROW_DATA, FileConstants.COL_EMAIL_ERR, emailError);
            logger.warn("Email Error Message: " + emailError);
        } catch (IOException e) {
            logger.error("Failed to write email error to Excel", e);
        }

        BaseClass.pressTabKey();

        try {
            String phoneError = forBusiness.getPoneError();
            xl.setCellData(FileConstants.SHEET_ErrorMessage, FileConstants.ROW_DATA, FileConstants.COL_PHONE_ERR, phoneError);
            logger.warn("Phone Error Message: " + phoneError);
        } catch (IOException e) {
            logger.error("Failed to write phone error to Excel", e);
        }
    }
}
