package stepsDefination;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import factory.BaseClass;
import io.cucumber.java.en.*;
import pageObjects.CoursePage;
import pageObjects.HomePage;
import utilities.ExcelUtils;
import utilities.Constants;
public class TestCase001 {

    WebDriver driver = BaseClass.getDriver();
    HomePage homePage;
    CoursePage coursePage;
    List<Map<String, String>> courseDetailsList = new ArrayList<>();
    ExcelUtils xl=new ExcelUtils(Constants.EXCEL_FILE);

    @When("the user searches for course")
    public void the_user_searches_for() {
    		String courseName=xl.getCellData(Constants.SHEET_CourseDetails,Constants.ROW_DATA,Constants.COL_SEARCH);
        CommonSteps.homePage.setSearchBar(courseName);
        CommonSteps.homePage.submitSearch();
    }

    @When("filters the results by language")
    public void filters_the_results_by_language() {
    		String language=xl.getCellData(Constants.SHEET_CourseDetails,Constants.ROW_DATA,Constants.COL_LANGUAGE);  //here
        driver=BaseClass.getDriver();
    		coursePage = new CoursePage(driver);
        coursePage.clickallLang();
        coursePage.getLanguages();
        coursePage.selectLang(language);
    }

    @When("filters the results by level")
    public void filters_the_results_by_level() {
    		String level=xl.getCellData(Constants.SHEET_CourseDetails,Constants.ROW_DATA,Constants.COL_LEVEL);  //here
        coursePage.setLevel(level);
    }


	@When("selects the first {int} courses from the results")
	public void selects_the_first_n_courses_from_the_results(int count) {
		int totalCourse=Integer.parseInt(xl.getCellData(Constants.SHEET_CourseDetails,Constants.ROW_DATA,Constants.COL_COURSE_NUMBER));
		for (int i = 0; i < totalCourse; i++) {
			WebElement card = coursePage.getCard(i);
			String title = coursePage.getTitle(card);
			String rating = coursePage.getRating(card);
			String duration = coursePage.getDuration(card);
	
			Map<String, String> courseData = new HashMap<>();
			courseData.put("title", title);
			courseData.put("rating", rating);
			courseData.put("duration", duration);
			courseDetailsList.add(courseData);
	
			System.out.println("Course " + (i + 1) + ": " + courseData);
		}
	}


    @Then("the course titles, ratings, and durations should be displayed")
    public void the_course_titles_ratings_and_durations_should_be_displayed() throws IOException {

    		int index=Constants.ROW_DATA;
    		Assert.assertFalse("Course details should not be empty", courseDetailsList.isEmpty());
    		
    		for (Map<String, String> course : courseDetailsList) {
    			Assert.assertTrue("Title should not be empty", course.get("title") != null && !course.get("title").isEmpty());
    			Assert.assertTrue("Rating should not be empty", course.get("rating") != null && !course.get("rating").isEmpty());
    			Assert.assertTrue("Duration should not be empty", course.get("duration") != null && !course.get("duration").isEmpty());
    			
    			xl.setCellData(Constants.SHEET_CourseDetails, index, Constants.COL_TITLE, course.get("title"));
    			xl.setCellData(Constants.SHEET_CourseDetails, index, Constants.COL_RATING, course.get("rating"));
    			xl.setCellData(Constants.SHEET_CourseDetails, index, Constants.COL_DURATION, course.get("duration"));
    			index+=1;
    			System.out.println("Validated course: " + course);
    		}

    }
}
