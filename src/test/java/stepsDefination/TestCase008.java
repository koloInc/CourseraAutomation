package stepsDefination;

import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.en.*;
import pageObjects.ForGovernment;

public class TestCase008 {

	WebDriver driver;
	ForGovernment forGovernment;

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
		forGovernment.setMobileNo(BaseClass.randomeNumber());
		forGovernment.selectOrgType(BaseClass.randomNumberInRange(1, 2));
		forGovernment.setJobTitle(BaseClass.randomeString() + BaseClass.randomeString());
		forGovernment.setOrgName(BaseClass.randomeString());
		forGovernment.selectOrgSize(BaseClass.randomNumberInRange(1, 5));
		forGovernment.selectAboutYou(BaseClass.randomNumberInRange(1, 5));
		forGovernment.selectCountry("Israel");
	}

	@When("submits the government request form")
	public void submits_the_government_request_form() {
		forGovernment.clickRequestInfo();
	}

	@Then("the application should display a confirmation message")
	public void the_application_should_display_a_confirmation_message() {
		System.out.println("Confirmation Message: " + forGovernment.getConfirmationMessage());
	}
}
