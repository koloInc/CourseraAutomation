package stepsDefination;

import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.*;
import pageObjects.CourseContent;
import pageObjects.HomePage;
import factory.BaseClass;

public class TestCase007 {

    HomePage hp;
    CourseContent coursePage;
    WebDriver driver;


    @When("selects the first course from the results")
    public void selects_the_first_course_from_the_results() {
    		driver = BaseClass.getDriver();
        coursePage = new CourseContent(driver);
        coursePage.getTitle();
        coursePage.getCourseIndex(1);
        coursePage.getAllWindows();
        coursePage.switchToCoursePage();
    }

    @Then("the course page should display the course title")
    public void the_course_page_should_display_the_course_title() {
        System.out.println("Course Title: " + coursePage.getCourseTitle());
    }

    @Then("the total number of modules")
    public void the_total_number_of_modules() {
        System.out.println("Modules: " + coursePage.getTotalModules());
    }

    @Then("the course rating")
    public void the_course_rating() {
        System.out.println("Rating: " + coursePage.getRating());
    }

    @Then("the total number of reviews")
    public void the_total_number_of_reviews() {
        System.out.println("Reviews: " + coursePage.getTotalReviews());
    }

    @Then("the user clicks on {string}")
    public void the_user_clicks_on(String buttonText) {
        coursePage.clickViewMore();
    }

    @Then("the skills and learning outcomes should be displayed")
    public void the_skills_and_learning_outcomes_should_be_displayed() {
        coursePage.setAllSkills();
        coursePage.setAllWeLearn();
    }
}
