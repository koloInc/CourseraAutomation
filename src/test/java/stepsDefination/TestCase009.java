package stepsDefination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HomePage;

public class TestCase009 {
	
	WebDriver driver;
	HomePage homePage;

	// Store footer data
	Map<String, List<String>> footerData = new HashMap<>();


	@When("the user retrieves all footer sections")
	public void the_user_retrieves_all_footer_sections() {
		List<WebElement> footers = CommonSteps.homePage.getAllFooter();

		for (WebElement footer : footers) {
			String title = CommonSteps.homePage.getFooterTitle(footer);
			List<WebElement> units = CommonSteps.homePage.getContentList(footer);

			List<String> links = new ArrayList<>();
			for (WebElement unit : units) {
				String text = unit.getText().trim();
				String href = unit.getAttribute("href");
				links.add(text + " -> " + href);
			}
			footerData.put(title, links);
		}
	}

	@Then("the footer titles and their links should be printed in the console")
	public void the_footer_titles_and_their_links_should_be_printed_in_the_console() {
		System.out.println("Footer Data:");
		for (Map.Entry<String, List<String>> entry : footerData.entrySet()) {
			System.out.println(entry.getKey());
			for (String link : entry.getValue()) {
				System.out.println(link);
			}
		}

		// Example validation: check if at least one footer section exists
		if (footerData.isEmpty()) {
			throw new AssertionError("No footer data found.");
		} else {
			System.out.println("Footer data validation passed.");
		}
	}

}
