package stepsDefination;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.*;
import pageObjects.CourseContent;
import pageObjects.HomePage;
import utilities.ExcelUtils;
import factory.BaseClass;

public class TestCase007 {

    HomePage hp;
    CourseContent coursePage;
    WebDriver driver;
    ExcelUtils xl=new ExcelUtils("CourseraAutomationData.xlsx");

    @When("selects the first course from the results")
    public void selects_the_first_course_from_the_results() {
    		driver = BaseClass.getDriver();
        coursePage = new CourseContent(driver);
        coursePage.getTitle();
        int i=Integer.parseInt(xl.getCellData("CourseDetailedInfo",1,0)); 
        coursePage.getCourseIndex(i);
        coursePage.getAllWindows();
        coursePage.switchToCoursePage();
    }

    @Then("the course page should display the course title")
    public void the_course_page_should_display_the_course_title() throws IOException {
        String title=coursePage.getCourseTitle();
    		System.out.println("Course Title: " + title);
        xl.setCellData("CourseDetailedInfo", 1, "Title",title);
    }

    @Then("the total number of modules")
    public void the_total_number_of_modules() throws IOException {
    		String totalModule=coursePage.getTotalModules();
        System.out.println("Modules: " + totalModule);
        xl.setCellData("CourseDetailedInfo", 1, "Total Module",totalModule);
    }

    @Then("the course rating")
    public void the_course_rating() throws IOException {
    		String rating=coursePage.getRating();
        System.out.println("Rating: " + rating);
        xl.setCellData("CourseDetailedInfo", 1, "Rating", rating);
        
    }

    @Then("the total number of reviews")
    public void the_total_number_of_reviews() throws IOException {
    		String review=coursePage.getTotalReviews();
        System.out.println("Reviews: " + review);
        xl.setCellData("CourseDetailedInfo", 1, "Total Reviews", review);
    }

    @Then("the user clicks on {string}")
    public void the_user_clicks_on(String buttonText) {
        coursePage.clickViewMore();
    }

    @Then("the skills and learning outcomes should be displayed")
    public void the_skills_and_learning_outcomes_should_be_displayed() throws IOException {
    		List <WebElement> skills=coursePage.setAllSkills();
    		int index=1;
        for(WebElement skill:skills) {
        		xl.setCellData("CourseDetailedInfo", index, "Skills",skill.getText());
			System.out.println(skill.getText());
			index+=1;
		}
        index=1;
        List <WebElement>weLearn=coursePage.setAllWeLearn();
		for(WebElement learn:weLearn) {
			xl.setCellData("CourseDetailedInfo", index, "We Learn",learn.getText());
			System.out.println(learn.getText());
			index+=1;
		}
    }
}
