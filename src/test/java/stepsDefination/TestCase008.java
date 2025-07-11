package stepsDefination;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.en.*;
import pageObjects.ForGovernment;
import utilities.Constants;
import utilities.ExcelUtils;
import utilities.XMLUtils;

public class TestCase008 {

	WebDriver driver;
	ForGovernment forGovernment;
	XMLUtils xml = new XMLUtils(Constants.XML_FILE);
	ExcelUtils xl=new ExcelUtils(Constants.EXCEL_FILE);

	@Then("the user navigates to the For Government section")
	public void the_user_navigates_to_the_for_government_section() {
		CommonSteps.homePage.clickForGovernment();
		driver = BaseClass.getDriver();
		forGovernment = new ForGovernment(driver);
	}

	@When("the user clicks the Contact Sales button")
	public void the_user_clicks_the_contact_sales_button() {
		forGovernment.clickContactSales();
	}

	@When("fills out the government form with valid details")
	public void fills_out_the_government_form_with_valid_details() {
		forGovernment.setFirstName(BaseClass.randomeString());
		forGovernment.setLastName(BaseClass.randomeString());
		forGovernment.setEmail(BaseClass.randomEmail());
		forGovernment.setMobileNo(BaseClass.randomMobileNumber());
		forGovernment.selectOrgType(BaseClass.randomNumberInRange(1, 2));
		forGovernment.setJobTitle(BaseClass.randomeString() + BaseClass.randomeString());
		forGovernment.setOrgName(BaseClass.randomeString());
		forGovernment.selectOrgSize(BaseClass.randomNumberInRange(1, 5));
		forGovernment.selectAboutYou(BaseClass.randomNumberInRange(1, 4));
		String country = xml.getElementValue("country") ;
        forGovernment.selectCountry(country);
	}

	@When("submits the government request form")
	public void submits_the_government_request_form() {
		forGovernment.clickRequestInfo();
	}

	@Then("the application should display a confirmation message")
	public void the_application_should_display_a_confirmation_message() {
	    int row_no = 1;

	    // Get full structured message
	    String message = forGovernment.getConfirmationMessage();

	 	    try {
	        xl.setCellData(Constants.SHEET_GovtFileValidation, row_no, "Confirmation Message", message);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

}
