package stepsDefination;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import factory.BaseClass;
import io.cucumber.java.en.*;
import pageObjects.ForBusiness;
import pageObjects.ForGovernment;
import utilities.Constants;
import utilities.ExcelUtils;
import utilities.XMLUtils;

public class TestCase003 {

    WebDriver driver;
    ForGovernment forGovernment;
    ForBusiness forBusiness;
    XMLUtils xml = new XMLUtils(Constants.XML_FILE);
    ExcelUtils xl=new ExcelUtils(Constants.EXCEL_FILE);
    
    @When("the user fills out the government request form with invalid email and phone number")
    public void the_user_fills_out_the_government_request_form_with_invalid_email_and_phone_number() {
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
        String country = xml.getElementValue("country") ;
        forGovernment.selectCountry(country);
    }

    @When("submits the request for information")
    public void submits_the_request_for_information() {
        forGovernment.clickRequestInfo();
    }

    @Then("the application should display appropriate error messages for email and phone number")
    public void the_application_should_display_appropriate_error_messages_for_email_and_phone_number() {
        forBusiness = new ForBusiness(driver);
        //System.out.println("Email Error Message: " + forBusiness.getEmailError());
        try {
			xl.setCellData(Constants.SHEET_ErrorMessage, Constants.ROW_DATA, Constants.COL_EMAIL_ERR, forBusiness.getEmailError());
		} catch (IOException e) {
			e.printStackTrace();
		}
        BaseClass.pressTabKey();
        //System.out.println("Phone Error Message: " + forBusiness.getPoneError());
        try {
			xl.setCellData(Constants.SHEET_ErrorMessage, Constants.ROW_DATA, Constants.COL_PHONE_ERR, forBusiness.getPoneError());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
