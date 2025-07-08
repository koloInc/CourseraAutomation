package stepsDefination;

import org.openqa.selenium.WebDriver;
import factory.BaseClass;
import io.cucumber.java.en.*;
import pageObjects.ForBusiness;
import pageObjects.ForGovernment;
import pageObjects.HomePage;

public class TestCase003 {

    WebDriver driver;
    HomePage homePage;
    ForGovernment forGovernment;
    ForBusiness forBusiness;

    @Given("the user is on the homepage and navigates to the For Business section")
    public void the_user_is_on_the_homepage_and_navigates_to_the_for_business_section() {
        driver = BaseClass.getDriver();
        homePage = new HomePage(driver);
        homePage.clickForBusiness();
    }

    @When("the user fills out the government request form with invalid email and phone number")
    public void the_user_fills_out_the_government_request_form_with_invalid_email_and_phone_number() {
        forGovernment = new ForGovernment(driver);
        forGovernment.setFirstName(BaseClass.randomeString());
        forGovernment.setLastName(BaseClass.randomeString());
        forGovernment.setEmail(BaseClass.randomeNumber());
        forGovernment.setMobileNo(BaseClass.randomAlphaNumeric());
        forGovernment.selectOrgType(BaseClass.randomNumberInRange(3, 4));
        forGovernment.setJobTitle(BaseClass.randomeString() + BaseClass.randomeString());
        forGovernment.setOrgName(BaseClass.randomeString());
        forGovernment.selectOrgSize(BaseClass.randomNumberInRange(1, 5));
        forGovernment.selectAboutYou(BaseClass.randomNumberInRange(1, 5));
        forGovernment.selectCountry("Israel");
    }

    @When("submits the request for information")
    public void submits_the_request_for_information() {
        forGovernment.clickRequestInfo();
    }

    @Then("the application should display appropriate error messages for email and phone number")
    public void the_application_should_display_appropriate_error_messages_for_email_and_phone_number() {
        forBusiness = new ForBusiness(driver);
        System.out.println("Email Error Message: " + forBusiness.getEmailError());
        BaseClass.pressTabKey();
        System.out.println("Phone Error Message: " + forBusiness.getPoneError());
    }
}
