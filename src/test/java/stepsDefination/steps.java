package stepsDefination;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HomePage;

public class steps {
	HomePage hp;
	@Given("User is on homepage")
	public void user_is_on_homepage() {
		hp = new HomePage();
	}

	@When("search course in search bar")
	public void search_course_in_search_bar() {
		hp.setSearchBar("Web Development");
		hp.submitSearch();
		
	}

	@Then("course details page opens")
	public void course_details_page_opens() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("hidden languages in page need to click to show all")
	public void hidden_languages_in_page_need_to_click_to_show_all() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("click on specific language")
	public void click_on_specific_language() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("language get selected")
	public void language_get_selected() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("levels section")
	public void levels_section() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("click on specific level")
	public void click_on_specific_level() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("level get selected")
	public void level_get_selected() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("cards of first course")
	public void cards_of_first_course() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("get title rating and duration")
	public void get_title_rating_and_duration() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("title rating and duration for first course are fetched")
	public void title_rating_and_duration_for_first_course_are_fetched() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("cards of second course")
	public void cards_of_second_course() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("title rating and duration for second course are fetched")
	public void title_rating_and_duration_for_second_course_are_fetched() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
}
