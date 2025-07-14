package pageObjects;

//import java.io.IOException;
import java.time.Duration;
import java.util.*;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import linearFlow.WaitUtils;
import utilities.Constants;
import utilities.ExcelUtils;

public class ForFooter extends BasePage {

    public ForFooter(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
        mainWindow = driver.getWindowHandle();
    }

    JavascriptExecutor js;
    String mainWindow;
    ExcelUtils xl = new ExcelUtils(Constants.EXCEL_FILE);
    private List<Map<String, String>> socialMediaResults = new ArrayList<>();

    @FindBy(xpath = "//div[@class='lazyload-wrapper']")
    WebElement socialMediaWrapper;

    public List<Map<String, String>> getSocialMediaResults() {
        return socialMediaResults;
    }

    public void scrollToFooter() throws InterruptedException {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        WaitUtils.waitForDuration(driver, 3);
//        Thread.sleep(3000);
    }

    public void openSocialMediaLinks() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String sheet = Constants.SHEET_FooterSocialLinkValidation;
        int rowCount = xl.getRowCount(sheet);

        for (int i = 1; i < rowCount; i++) {
            String predictedTitle = xl.getCellData(sheet, i, xl.getColumnIndex(sheet, "Predicted Title"));
            String predictedLink = xl.getCellData(sheet, i, xl.getColumnIndex(sheet, "Predicted Link"));
            String alt = "Coursera " + predictedTitle.split("\\|")[0].trim(); // e.g., "Coursera Facebook"

            try {
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@alt='" + alt + "']")));
                WebElement icon = driver.findElement(By.xpath("//img[@alt='" + alt + "']"));
                WebElement link = icon.findElement(By.xpath("./ancestor::a"));
                String href = link.getAttribute("href");

                js.executeScript("window.open(arguments[0]);", href);
                WaitUtils.waitForDuration(driver, 2);

                Set<String> allWindows = driver.getWindowHandles();
                for (String window : allWindows) {
                    if (!window.equals(mainWindow)) {
                        driver.switchTo().window(window);
                        break;
                    }
                }

                WaitUtils.waitForDuration(driver, 3);
                String actualTitle = driver.getTitle();
                String actualLink = driver.getCurrentUrl();

                Map<String, String> result = new HashMap<>();
                result.put("Predicted Title", predictedTitle);
                result.put("Actual Title", actualTitle);
                result.put("VALIDATION Title", actualTitle.equals(predictedTitle) ? "PASS" : "FAIL");
                result.put("Predicted Link", predictedLink);
                result.put("Actual Link", actualLink);
                result.put("VALIDATION Link", actualLink.equals(predictedLink) ? "PASS" : "FAIL");
                socialMediaResults.add(result);

                // Write to Excel
                xl.setCellData(sheet, i, "Actual Title", actualTitle);
                xl.setCellData(sheet, i, "Actual Link", actualLink);
                xl.setCellData(sheet, i, "VALIDATION Title", result.get("VALIDATION Title"));
                xl.setCellData(sheet, i, "VALIDATION Link", result.get("VALIDATION Link"));

                driver.close();
                driver.switchTo().window(mainWindow);
            } catch (Exception e) {
                System.out.println("Error processing: " + alt + " - " + e.getMessage());
            }
        }

        xl.closeFile();
    }

    public void verifyTitlesAndUrls() {
        System.out.println("Verification logic completed. Results stored in Excel.");
    }
}
