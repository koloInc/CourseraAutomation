package stepsDefination;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.*;
import pageObjects.CoursePage;
import pageObjects.HomePage;
import factory.BaseClass;

public class steps {

    WebDriver driver;
    HomePage hp;
    CoursePage cp;
    WebElement card;

    @Given("User is on homepage")
    public void user_is_on_homepage() throws Exception {
        driver = new ChromeDriver();
        hp = new HomePage(driver);
    }

    @When("search course in search bar")
    public void search_course_in_search_bar() {
        hp.setSearchBar("Web Development");
        hp.submitSearch();
        cp = new CoursePage(driver);
    }

    @Then("course details page opens")
    public void course_details_page_opens() {
        System.out.println("Course details page opened.");
    }

    @Given("hidden languages in page need to click to show all")
    public void hidden_languages_in_page_need_to_click_to_show_all() {
        cp.clickallLang();
    }

    @When("click on specific language")
    public void click_on_specific_language() {
        cp.selectLang("English");
    }

    @Then("language get selected")
    public void language_get_selected() {
        System.out.println("Language selected.");
    }

    @Given("levels section")
    public void levels_section() {
        // Already covered
    }

    @When("click on specific level")
    public void click_on_specific_level() {
        cp.setLevel("Beginner");
    }

    @Then("level get selected")
    public void level_get_selected() {
        System.out.println("Level selected.");
    }

    @Given("cards of first course")
    public void cards_of_first_course() {
        card = cp.getCard(0);
    }

    @When("get title rating and duration")
    public void get_title_rating_and_duration() {
        String title = cp.getTitle(card);
        String rating = cp.getRating(card);
        String duration = cp.getDuration(card);
        System.out.println(title + "__" + rating + "__" + duration);
    }

    @Then("title rating and duration for first course are fetched")
    public void title_rating_and_duration_for_first_course_are_fetched() {
        System.out.println("First course details fetched.");
    }

    @Given("cards of second course")
    public void cards_of_second_course() {
        card = cp.getCard(1);
    }

    @Then("title rating and duration for second course are fetched")
    public void title_rating_and_duration_for_second_course_are_fetched() {
        String title = cp.getTitle(card);
        String rating = cp.getRating(card);
        String duration = cp.getDuration(card);
        System.out.println(title + "__" + rating + "__" + duration);
    }

}
