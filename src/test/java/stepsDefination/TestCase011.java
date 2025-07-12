package stepsDefination;

import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.en.*;
import pageObjects.ForFooter;

public class TestCase011 {

    WebDriver driver = BaseClass.getDriver();
    
    ForFooter footerPage;

    @When("the user scrolls to the footer")
    public void scroll_to_footer() throws InterruptedException {
        footerPage = new ForFooter(driver);
        footerPage.scrollToFooter();
    }

    @Then("the user opens each social media link in a new tab")
    public void open_social_links() throws InterruptedException {
        footerPage.openSocialMediaLinks();
    }

    @Then("verifies the title and URL of each page")
    public void verifies_the_title_and_url_of_each_page() {
        footerPage.verifyTitlesAndUrls();
    }
}
