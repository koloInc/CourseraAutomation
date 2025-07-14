package pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

//import com.sun.org.apache.bcel.internal.generic.Select;

import utilities.WaitUtils;

public class CareersPage extends BasePage {

    public CareersPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@class='rc-SubFooterSection__content-column-link-text'][normalize-space()='Careers']")
    WebElement careersLink;

    @FindBy(xpath = "//a[@class='button button1']")
    WebElement openPositionsButton;

    @FindBy(xpath = "//select[@title='-Countries-']")
    WebElement countryDropdown;
    
    @FindBy(xpath = "//table//tbody/tr")
    List<WebElement> jobRows;

    public void clickCareersLink() {
        try {
            scrollIntoView(careersLink);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(careersLink));
            careersLink.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", careersLink);
        }
    }

    public void clickOpenPositions() {
        try {
            scrollIntoView(openPositionsButton);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(openPositionsButton));
            openPositionsButton.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", openPositionsButton);
        }
    }

    public void switchToNewTab(String mainWindow) {
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(mainWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }

    public void filterByCountry(String countryText) throws InterruptedException {
        Select country = new Select(countryDropdown);
    		country.selectByValue(countryText);
//        Thread.sleep(3000); // wait for table to update
        WaitUtils.waitForDuration(driver, 3);
    }

    public void printJobListings() {
        System.out.println("Job Listings in India:");
        System.out.println("------------------------");

        for (WebElement row : jobRows) {
            List<WebElement> columns = row.findElements(By.tagName("td"));
            for (WebElement col : columns) {
                System.out.print(col.getText() + " | ");
            }
            System.out.println();
        }
    }
    
    public List<Map<String, String>> getJobListings() {
        List<Map<String, String>> jobList = new ArrayList<>();

        for (WebElement row : jobRows) {
            List<WebElement> columns = row.findElements(By.tagName("td"));
            if (columns.size() >= 3) {
                Map<String, String> job = new HashMap<>();
                job.put("Title", columns.get(0).getText());
                job.put("Department", columns.get(1).getText());
                job.put("Location", columns.get(2).getText());
                jobList.add(job);
            }
        }

        return jobList;
    }

}
