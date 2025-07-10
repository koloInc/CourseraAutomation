package stepsDefination;

import java.io.IOException;
import java.util.Map;
import org.junit.Assert;
import factory.BaseClass;
import io.cucumber.java.en.Then;
import pageObjects.CoursePage;
import utilities.ExcelUtils;

public class TestCase002 {
    ExcelUtils xl=new ExcelUtils("CourseraAutomationData.xlsx");
    @Then("the list of available languages should be displayed")
    public void the_list_of_available_languages_should_be_displayed() throws IOException {
    		int index=1;
        CoursePage coursePage = new CoursePage(BaseClass.getDriver());
        Map<String, Integer> languages = coursePage.getLanguages();

        Assert.assertFalse("Languages list should not be empty", languages.isEmpty());

        System.out.println("📚 Available Languages:");
        for (Map.Entry<String, Integer> entry : languages.entrySet()) {
			xl.setCellData("CourseLanguage", index, "Language", entry.getKey());
			xl.setCellData("CourseLanguage", index, "Available Course", Integer.toString(entry.getValue()));
            System.out.println(entry.getKey() + " - " + entry.getValue());
            index+=1;
        }
    }
}
