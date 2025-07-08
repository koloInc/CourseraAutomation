package stepsDefination;

import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.en.Given;
import pageObjects.HomePage;

public class CommonSteps {

    WebDriver driver;
    public static HomePage homePage;

    @Given("the user is on the homepage")
    public void the_user_is_on_the_homepage() {
        driver = BaseClass.getDriver();
        homePage = new HomePage(driver);
    }
}
