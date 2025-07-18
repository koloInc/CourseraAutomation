package stepsDefination;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import factory.BaseClass;
import io.cucumber.java.en.*;
import pageObjects.CoursePage;
import pageObjects.HomePage;
import utilities.ExcelUtils;
import utilities.FileConstants;

public class TestCase001 {

    WebDriver driver = BaseClass.getDriver();
    HomePage homePage;
    CoursePage coursePage;
    List<Map<String, String>> courseDetailsList = new ArrayList<>();
    ExcelUtils xl = new ExcelUtils(FileConstants.EXCEL_FILE);
    private static final Logger logger = LogManager.getLogger(TestCase001.class);

    @When("the user searches for course")
    public void the_user_searches_for() throws InterruptedException {
        String courseName = xl.getCellData(FileConstants.SHEET_CourseDetails, FileConstants.ROW_DATA, FileConstants.COL_SEARCH);
        logger.info("Searching for course: " + courseName);
        CommonSteps.homePage.setSearchBar(courseName);
        CommonSteps.homePage.submitSearch(courseName);
    }

    @When("filters the results by language")
    public void filters_the_results_by_language() {
        String language = xl.getCellData(FileConstants.SHEET_CourseDetails, FileConstants.ROW_DATA, FileConstants.COL_LANGUAGE);
        logger.info("Filtering results by language: " + language);
        driver = BaseClass.getDriver();
        coursePage = new CoursePage(driver);
        coursePage.clickallLang();
        coursePage.getLanguages();
        coursePage.selectLang(language);
    }

    @When("filters the results by level")
    public void filters_the_results_by_level() {
        String level = xl.getCellData(FileConstants.SHEET_CourseDetails, FileConstants.ROW_DATA, FileConstants.COL_LEVEL);
        logger.info("Filtering results by level: " + level);
        coursePage.setLevel(level);
    }

    @When("selects the first {int} courses from the results")
    public void selects_the_first_n_courses_from_the_results(int count) {
        int totalCourse = Integer.parseInt(xl.getCellData(FileConstants.SHEET_CourseDetails, FileConstants.ROW_DATA, FileConstants.COL_COURSE_NUMBER));
        logger.info("Selecting first " + totalCourse + " courses from results.");

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

            logger.debug("Course " + (i + 1) + ": Title=" + title + ", Rating=" + rating + ", Duration=" + duration);
        }
    }

    @Then("the course titles, ratings, and durations should be displayed")
    public void the_course_titles_ratings_and_durations_should_be_displayed() throws IOException {
        int index = FileConstants.ROW_DATA;
        logger.info("Validating and writing course details to Excel.");

        Assert.assertFalse("Course details should not be empty", courseDetailsList.isEmpty());

        for (Map<String, String> course : courseDetailsList) {
            Assert.assertTrue("Title should not be empty", course.get("title") != null && !course.get("title").isEmpty());
            Assert.assertTrue("Rating should not be empty", course.get("rating") != null && !course.get("rating").isEmpty());
            Assert.assertTrue("Duration should not be empty", course.get("duration") != null && !course.get("duration").isEmpty());

            try {
                xl.setCellData(FileConstants.SHEET_CourseDetails, index, FileConstants.COL_TITLE, course.get("title"));
                xl.setCellData(FileConstants.SHEET_CourseDetails, index, FileConstants.COL_RATING, course.get("rating"));
                xl.setCellData(FileConstants.SHEET_CourseDetails, index, FileConstants.COL_DURATION, course.get("duration"));
                logger.debug("Written to Excel → Row " + index + ": " + course);
            } catch (IOException e) {
                logger.error("Failed to write course data to Excel at row " + index, e);
            }

            index += 1;
        }

        xl.closeFile();
        logger.info("Course details successfully written to Excel.");
    }
}
