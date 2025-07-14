package stepsDefination;

import java.io.IOException;
import java.util.Map;

import org.junit.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import factory.BaseClass;
import io.cucumber.java.en.Then;
import pageObjects.CoursePage;
import utilities.Constants;
import utilities.ExcelUtils;

public class TestCase002 {

    ExcelUtils xl = new ExcelUtils(Constants.EXCEL_FILE);
    private static final Logger logger = LogManager.getLogger(TestCase002.class);

    @Then("the list of available languages should be displayed")
    public void the_list_of_available_languages_should_be_displayed() throws IOException {
        int index = Constants.ROW_DATA;
        CoursePage coursePage = new CoursePage(BaseClass.getDriver());
        Map<String, Integer> languages = coursePage.getLanguages();

        logger.info("Extracting list of available languages.");
        Assert.assertFalse("Languages list should not be empty", languages.isEmpty());
        logger.info("Total languages found: " + languages.size());

        for (Map.Entry<String, Integer> entry : languages.entrySet()) {
            String language = entry.getKey();
            String courseCount = Integer.toString(entry.getValue());

            try {
                xl.setCellData(Constants.SHEET_CourseLanguage, index, Constants.COL_LANG_LIST, language);
                xl.setCellData(Constants.SHEET_CourseLanguage, index, Constants.COL_NUM_COURSE, courseCount);
                logger.debug("Row " + index + " → Language: " + language + ", Courses: " + courseCount);
            } catch (IOException e) {
                logger.error("Failed to write language data to Excel at row " + index, e);
            }

            index += 1;
        }

        xl.closeFile();
        logger.info("Language list successfully written to Excel.");
    }
}
