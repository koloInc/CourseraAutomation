package stepsDefination;

import java.util.Map;
import org.junit.Assert;
import factory.BaseClass;
import io.cucumber.java.en.Then;
import pageObjects.CoursePage;

public class TestCase002 {

    @Then("the list of available languages should be displayed")
    public void the_list_of_available_languages_should_be_displayed() {
        CoursePage coursePage = new CoursePage(BaseClass.getDriver());
        Map<String, Integer> languages = coursePage.getLanguages();

        Assert.assertFalse("Languages list should not be empty", languages.isEmpty());

        System.out.println("📚 Available Languages:");
        for (Map.Entry<String, Integer> entry : languages.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }
}
