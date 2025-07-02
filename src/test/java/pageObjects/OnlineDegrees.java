package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OnlineDegrees extends BasePage {

    public OnlineDegrees(WebDriver driver) {
        super(driver);
    }

    //  Filters
    @FindBy(xpath = "//button[@id='react-aria-:R159l6kala:']")
    WebElement programLevelDropdown;

    @FindBy(xpath = "//div[@role='presentation' and @dir='ltr']//li/div/div/div/div[1]")
    List<WebElement> programLevelOptions;

    @FindBy(xpath = "//button[@id='react-aria-:R179l6kala:']")
    WebElement subjectDropdown;

    @FindBy(xpath = "//div[@class='cds-popoverContainer-inner']//li/div/div/div/div[1]")
    List<WebElement> subjectOptions;

    @FindBy(xpath = "//span[normalize-space()='Apply']")
    WebElement applyButton;

    @FindBy(xpath = "//div[@id='DegreeFilters']/div[2]/div[1]//span")
    WebElement totalResultsText;

    @FindBy(xpath = "//div[@data-test='TopProductsList']/div/div")
    List<WebElement> collegeCards;

    // Program Level Methods
    public void openProgramLevelDropdown() {
        scrollIntoView(programLevelDropdown);
        programLevelDropdown.click();
    }

    public void selectProgramLevelByIndex(int index) {
        if (index >= 0 && index < programLevelOptions.size()) {
            programLevelOptions.get(index).click();
        }
    }


    //  Subject Methods
    public void openSubjectDropdown() {
        scrollIntoView(subjectDropdown);
        subjectDropdown.click();
    }

    public void selectSubjectByIndex(int index) {
        if (index >= 0 && index < subjectOptions.size()) {
            subjectOptions.get(index).click();
        }
    }

    //  Apply Filters
    public void applyFilters() {
        scrollIntoView(applyButton);
        applyButton.click();
    }

    //  Results Info
    public String getTotalResults() {
        return totalResultsText.getText();
    }

    public List<String> getCollegeDetails() {
        List<String> details = new ArrayList<>();
        for (WebElement card : collegeCards) {
            details.add(card.getText());
        }
        return details;
    }
}
