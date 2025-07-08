package stepsDefination;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import factory.BaseClass;
import io.cucumber.java.en.*;
import pageObjects.PartnersPage;

public class TestCase004 {

    WebDriver driver;
    PartnersPage partnersPage;

    @Then("the user navigates to the Partners section")
    public void the_user_navigates_to_the_partners_section() {
        CommonSteps.homePage.clickPartners();
    }

    @And("opens the India Partners list")
    public void opens_the_india_partners_list() {
        driver = BaseClass.getDriver();
        partnersPage = new PartnersPage(driver);
        partnersPage.openIndiaPartners();
    }

    @Then("the application should display all partner links, logo visibility, and names")
    public void the_application_should_display_all_partner_links_logo_visibility_and_names() {
        List<WebElement> links = partnersPage.getAllPartnerLinks();
        List<WebElement> logos = partnersPage.getAllPartnerLogos();
        List<WebElement> names = partnersPage.getAllPartnerNames();

        int total = Math.min(Math.min(links.size(), logos.size()), names.size());

        System.out.println(String.format("%-80s\t%-15s\t%-40s", "Partner Link", "Logo Displayed", "Partner Name"));
        System.out.println("=".repeat(140));

        for (int i = 0; i < total; i++) {
            String url = links.get(i).getAttribute("href");
            String visible = String.valueOf(logos.get(i).isDisplayed());
            String label = names.get(i).getText();

            System.out.println(String.format("%-80s\t%-15s\t%-40s", url, visible, label));
        }
    }
}
