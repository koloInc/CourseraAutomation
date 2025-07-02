package testCases;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.PartnersPage;
import testBase.BaseClass;

public class TC_004_PartnersInfo extends BaseClass {

    @Test
    public void extractPartnersData() {
        HomePage hp = new HomePage(driver);
        hp.clickPartners();

        PartnersPage pp = new PartnersPage(driver);
        pp.openIndiaPartners();

        System.out.println(String.format("%-80s\t%-15s\t%-40s", "Partner Link", "Logo Displayed", "Partner Name"));
        System.out.println("=".repeat(140));

        int total = Math.min(
            Math.min(pp.getAllPartnerLinks().size(), pp.getAllPartnerLogos().size()),
            pp.getAllPartnerNames().size());

        for (int i = 0; i < total; i++) {
            WebElement link = pp.getAllPartnerLinks().get(i);
            WebElement logo = pp.getAllPartnerLogos().get(i);
            WebElement name = pp.getAllPartnerNames().get(i);

            String url = link.getAttribute("href");
            String visible = String.valueOf(logo.isDisplayed());
            String label = name.getText();

            System.out.println(String.format("%-80s\t%-15s\t%-40s", url, visible, label));
        }
    }
}
