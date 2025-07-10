package stepsDefination;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.*;
import pageObjects.CourseContent;
import pageObjects.HomePage;
import utilities.Constants;
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
        int i=Integer.parseInt(xl.getCellData(Constants.SHEET_CourseDetailedInfo,Constants.ROW_DATA,Constants.COL_COURSE_POS)); 
        coursePage.getCourseIndex(i);
        coursePage.getAllWindows();
        coursePage.switchToCoursePage();
    }

    @Then("the course page should display the course title")
    public void the_course_page_should_display_the_course_title() throws IOException {
        String title=coursePage.getCourseTitle();
    		System.out.println("Course Title: " + title);
        xl.setCellData(Constants.SHEET_CourseDetailedInfo, Constants.ROW_DATA, Constants.COL_COURSE_TITLE,title);
    }

    @Then("the total number of modules")
    public void the_total_number_of_modules() throws IOException {
    		String totalModule=coursePage.getTotalModules();
        System.out.println("Modules: " + totalModule);
        xl.setCellData(Constants.SHEET_CourseDetailedInfo, Constants.ROW_DATA, Constants.COL_MODULE,totalModule);
    }

    @Then("the course rating")
    public void the_course_rating() throws IOException {
    		String rating=coursePage.getRating();
        System.out.println("Rating: " + rating);
        xl.setCellData(Constants.SHEET_CourseDetailedInfo, Constants.ROW_DATA, Constants.COL_COURSE_RATING,rating);
    }

    @Then("the total number of reviews")
    public void the_total_number_of_reviews() throws IOException {
    		String review=coursePage.getTotalReviews();
        System.out.println("Reviews: " + review);
        xl.setCellData(Constants.SHEET_CourseDetailedInfo, Constants.ROW_DATA, Constants.COL_REVIEW,review);
    }

    @Then("the user clicks on {string}")
    public void the_user_clicks_on(String buttonText) {
        coursePage.clickViewMore();
    }

    @Then("the skills and learning outcomes should be displayed")
    public void the_skills_and_learning_outcomes_should_be_displayed() throws IOException {
    		List <WebElement> skills=coursePage.setAllSkills();
    		int index=Constants.ROW_DATA;
        for(WebElement skill:skills) {
        		xl.setCellData(Constants.SHEET_CourseDetailedInfo, index, Constants.COL_SKILLS,skill.getText());
			System.out.println(skill.getText());
			index+=1;
		}
        index=Constants.ROW_DATA;
        List <WebElement>weLearn=coursePage.setAllWeLearn();
		for(WebElement learn:weLearn) {
			xl.setCellData(Constants.SHEET_CourseDetailedInfo, index, Constants.COL_WELEARN,learn.getText());
			System.out.println(learn.getText());
			index+=1;
		}
    }
}
