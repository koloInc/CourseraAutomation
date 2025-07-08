package stepsDefination;

import java.util.List;

import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.en.*;
import pageObjects.OnlineDegrees;

public class TestCase005 {

    WebDriver driver;
    OnlineDegrees onlineDegrees;

    @When("the user navigates to the Online Degrees section")
    public void the_user_navigates_to_the_online_degrees_section() {
        CommonSteps.homePage.clickOnlineDegree();
        driver = BaseClass.getDriver();
        onlineDegrees = new OnlineDegrees(driver);
    }

    @When("selects a random program level filter")
    public void selects_a_random_program_level_filter() {
        onlineDegrees.openProgramLevelDropdown();
        onlineDegrees.selectProgramLevelByIndex(BaseClass.randomNumberInRange(0, 1));
        onlineDegrees.applyFilters();
    }

    @When("selects a random subject filter")
    public void selects_a_random_subject_filter() {
        onlineDegrees.openSubjectDropdown();
        onlineDegrees.selectSubjectByIndex(BaseClass.randomNumberInRange(0, 6));
        onlineDegrees.applyFilters();
    }

    @Then("the application should display the total number of results and degree card details")
    public void the_application_should_display_the_total_number_of_results_and_degree_card_details() {
        System.out.println("Total Results: " + onlineDegrees.getTotalResults());

        System.out.println("College Degree Cards:");
        List<String> cardDetails = onlineDegrees.getCollegeDetails();
        for (String detail : cardDetails) {
            System.out.println(detail);
            System.out.println();
        }
    }
}
