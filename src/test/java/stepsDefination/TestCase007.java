package stepsDefination;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.cucumber.java.en.*;
import pageObjects.CourseContent;
import pageObjects.HomePage;
import utilities.FileConstants;
import utilities.ExcelUtils;
import factory.BaseClass;

public class TestCase007 {

    HomePage hp;
    CourseContent coursePage;
    WebDriver driver;
    ExcelUtils xl = new ExcelUtils(FileConstants.EXCEL_FILE);
    private static final Logger logger = LogManager.getLogger(TestCase007.class);

    @When("selects the first course from the results")
    public void selects_the_first_course_from_the_results() {
        driver = BaseClass.getDriver();
        coursePage = new CourseContent(driver);
        coursePage.getTitle();

        int i = Integer.parseInt(xl.getCellData(FileConstants.SHEET_CourseDetailedInfo, FileConstants.ROW_DATA, FileConstants.COL_COURSE_POS));
        logger.info("Selecting course at position: " + i);

        coursePage.getCourseIndex(i);
        coursePage.getAllWindows();
        coursePage.switchToCoursePage();
        logger.info("Switched to course detail page.");
    }

    @Then("the course page should display the course title")
    public void the_course_page_should_display_the_course_title() throws IOException {
        String title = coursePage.getCourseTitle();
        logger.info("Course Title: " + title);

        try {
            xl.setCellData(FileConstants.SHEET_CourseDetailedInfo, FileConstants.ROW_DATA, FileConstants.COL_COURSE_TITLE, title);
        } catch (IOException e) {
            logger.error("Failed to write course title to Excel", e);
        }
    }

    @Then("the total number of modules")
    public void the_total_number_of_modules() throws IOException {
        String totalModule = coursePage.getTotalModules();
        logger.info("Total Modules: " + totalModule);

        try {
            xl.setCellData(FileConstants.SHEET_CourseDetailedInfo, FileConstants.ROW_DATA, FileConstants.COL_MODULE, totalModule);
        } catch (IOException e) {
            logger.error("Failed to write module count to Excel", e);
        }
    }

    @Then("the course rating")
    public void the_course_rating() throws IOException {
        String rating = coursePage.getRating();
        logger.info("Course Rating: " + rating);

        try {
            xl.setCellData(FileConstants.SHEET_CourseDetailedInfo, FileConstants.ROW_DATA, FileConstants.COL_COURSE_RATING, rating);
        } catch (IOException e) {
            logger.error("Failed to write course rating to Excel", e);
        }
    }

    @Then("the total number of reviews")
    public void the_total_number_of_reviews() throws IOException {
        String review = coursePage.getTotalReviews();
        logger.info("Total Reviews: " + review);

        try {
            xl.setCellData(FileConstants.SHEET_CourseDetailedInfo, FileConstants.ROW_DATA, FileConstants.COL_REVIEW, review);
        } catch (IOException e) {
            logger.error("Failed to write review count to Excel", e);
        }
    }

    @Then("the user clicks on {string}")
    public void the_user_clicks_on(String buttonText) {
        logger.info("Clicking on button: " + buttonText);
        coursePage.clickViewMore();
    }

    @Then("the skills and learning outcomes should be displayed")
    public void the_skills_and_learning_outcomes_should_be_displayed() throws IOException {
        List<WebElement> skills = coursePage.setAllSkills();
        int index = FileConstants.ROW_DATA;

        logger.info("Extracting skills...");
        for (WebElement skill : skills) {
            String skillText = skill.getText();
            logger.debug("Skill: " + skillText);
            try {
                xl.setCellData(FileConstants.SHEET_CourseDetailedInfo, index, FileConstants.COL_SKILLS, skillText);
            } catch (IOException e) {
                logger.error("Failed to write skill to Excel at row " + index, e);
            }
            index += 1;
        }

        index = FileConstants.ROW_DATA;
        List<WebElement> weLearn = coursePage.setAllWeLearn();

        logger.info("Extracting learning outcomes...");
        for (WebElement learn : weLearn) {
            String learnText = learn.getText();
            logger.debug("Learning Outcome: " + learnText);
            try {
                xl.setCellData(FileConstants.SHEET_CourseDetailedInfo, index, FileConstants.COL_WELEARN, learnText);
            } catch (IOException e) {
                logger.error("Failed to write learning outcome to Excel at row " + index, e);
            }
            index += 1;
        }

        xl.closeFile();
        logger.info("Course details successfully written to Excel.");
    }
}
