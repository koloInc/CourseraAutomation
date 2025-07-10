package stepsDefination;

import java.io.IOException;
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
import utilities.ExcelUtils;

public class TestCase009 {
	
	WebDriver driver;
	HomePage homePage;
	
	// Store footer data
//	Map<String, List<String>> footerData = new HashMap<>();
    ExcelUtils xl=new ExcelUtils("CourseraAutomationData.xlsx");
    Map<String, Map<String, String>> footerData = new HashMap<>();
	
    @When("the user retrieves all footer sections")
	public void the_user_retrieves_all_footer_sections() throws IOException {
		List<WebElement> footers = CommonSteps.homePage.getAllFooter();
//		Map<String, Map<String, String>> footerData = new HashMap<>();
		int index=1;
		for (WebElement footer : footers) {
			String title = CommonSteps.homePage.getFooterTitle(footer);
			List<WebElement> units = CommonSteps.homePage.getContentList(footer);

			index+=1;
			
			Map<String, String> linksMap = new HashMap<>();
			for (WebElement unit : units) {
				String text = unit.getText().trim();
				String href = unit.getAttribute("href");
				index+=1;
				linksMap.put(text, href);
			}
			footerData.put(title, linksMap);
		}
	}


	@Then("the footer titles and their links should be printed in the console")
	public void the_footer_titles_and_their_links_should_be_printed_in_the_console() throws IOException {
		System.out.println("Footer Data:");
		int index = 1;

		for (Map.Entry<String, Map<String, String>> entry : footerData.entrySet()) {
			String title = entry.getKey();
			Map<String, String> linksMap = entry.getValue();

			System.out.println("Section: " + title);
			xl.setCellData("FooterValidation", index, "Section", title);
			xl.fillGreenColor("FooterValidation", index, 0);

			for (Map.Entry<String, String> linkEntry : linksMap.entrySet()) {
				String text = linkEntry.getKey();
				String href = linkEntry.getValue();

				System.out.println(text + " -> " + href);
				xl.setCellData("FooterValidation", index, "Title", text);
				xl.setCellData("FooterValidation", index, "Link", href);
				if (href.isEmpty()) {
					throw new AssertionError("No Link Found");
				} else {
					System.out.println("Footer Link Validation Failed");
				}
				
				index++;
			}
			System.out.println("-----");
		}

		if (footerData.isEmpty()) {
			throw new AssertionError("No footer data found.");
		} else {
			System.out.println("Footer data validation passed.");
		}

		xl.closeFile();
}


}
