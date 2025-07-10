package stepsDefination;

import java.io.IOException;
import java.util.Map;
import org.junit.Assert;
import factory.BaseClass;
import io.cucumber.java.en.Then;
import pageObjects.CoursePage;
import utilities.Constants;
import utilities.ExcelUtils;

public class TestCase002 {
	ExcelUtils xl=new ExcelUtils(Constants.EXCEL_FILE);
    @Then("the list of available languages should be displayed")
    public void the_list_of_available_languages_should_be_displayed() throws IOException {
    		int index=Constants.ROW_DATA;
        CoursePage coursePage = new CoursePage(BaseClass.getDriver());
        Map<String, Integer> languages = coursePage.getLanguages();

        Assert.assertFalse("Languages list should not be empty", languages.isEmpty());

        System.out.println("Available Languages:");
        for (Map.Entry<String, Integer> entry : languages.entrySet()) {
			xl.setCellData(Constants.SHEET_CourseLanguage, index, Constants.COL_LANG_LIST, entry.getKey());
			xl.setCellData(Constants.SHEET_CourseLanguage, index, Constants.COL_NUM_COURSE, Integer.toString(entry.getValue()));
            System.out.println(entry.getKey() + " - " + entry.getValue());
            index+=1;
        }
    }
}
