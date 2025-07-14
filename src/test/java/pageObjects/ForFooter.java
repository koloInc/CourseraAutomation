package pageObjects;

import java.io.IOException;
import java.time.Duration;
import java.util.*;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import utilities.WaitUtils;
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
        WaitUtils.waitForDuration(driver, 2);
    }

    public void openSocialMediaLinks() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String sheet = Constants.SHEET_FooterSocialLinkValidation;
        int rowCount = xl.getRowCount(sheet);

        String[] socialMediaAlts = {
            "Coursera Facebook",
            "Coursera Linkedin",
            "Coursera YouTube",
            "Coursera Instagram",
            "Coursera TikTok"
        };

        for (String alt : socialMediaAlts) {
            int rowIndex = findRowIndexByAlt(sheet, alt);
            if (rowIndex == -1) {
                System.out.println("No matching row found in Excel for alt: " + alt);
                continue;
            }

            String predictedTitle = xl.getCellData(sheet, rowIndex, xl.getColumnIndex(sheet, "Predicted Title"));
            String predictedLink = xl.getCellData(sheet, rowIndex, xl.getColumnIndex(sheet, "Predicted Link"));

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
                result.put("RowIndex", String.valueOf(rowIndex));
                result.put("Predicted Title", predictedTitle);
                result.put("Actual Title", actualTitle);
                result.put("VALIDATION Title", actualTitle.equals(predictedTitle) ? "PASS" : "FAIL");
                result.put("Predicted Link", predictedLink);
                result.put("Actual Link", actualLink);
                result.put("VALIDATION Link", actualLink.equals(predictedLink) ? "PASS" : "FAIL");
                socialMediaResults.add(result);

                driver.close();
                driver.switchTo().window(mainWindow);
            } catch (Exception e) {
                System.out.println("Error processing: " + alt + " - " + e.getMessage());
                driver.switchTo().window(mainWindow); // Ensure return to main window
            }
        }

        writeResultsToExcel(sheet);
        xl.closeFile();
    }

    private void writeResultsToExcel(String sheet) {
        for (Map<String, String> result : socialMediaResults) {
            try {
                int rowIndex = Integer.parseInt(result.get("RowIndex"));
                xl.setCellData(sheet, rowIndex, "Actual Title", result.get("Actual Title"));
                xl.setCellData(sheet, rowIndex, "Actual Link", result.get("Actual Link"));
                xl.setCellData(sheet, rowIndex, "VALIDATION Title", result.get("VALIDATION Title"));
                xl.setCellData(sheet, rowIndex, "VALIDATION Link", result.get("VALIDATION Link"));

                int titleCol = xl.getColumnIndex(sheet, "VALIDATION Title");
                int linkCol = xl.getColumnIndex(sheet, "VALIDATION Link");

                if ("PASS".equals(result.get("VALIDATION Title"))) {
                    xl.fillGreenColor(sheet, rowIndex, titleCol);
                } else {
                    xl.fillRedColor(sheet, rowIndex, titleCol);
                }

                if ("PASS".equals(result.get("VALIDATION Link"))) {
                    xl.fillGreenColor(sheet, rowIndex, linkCol);
                } else {
                    xl.fillRedColor(sheet, rowIndex, linkCol);
                }
            } catch (IOException e) {
                System.out.println("Excel write error: " + e.getMessage());
            }
        }
    }

    private int findRowIndexByAlt(String sheet, String alt) {
        int rowCount = xl.getRowCount(sheet);
        String altKey = alt.replace("Coursera ", "").toLowerCase().trim();

        for (int i = 1; i < rowCount; i++) {
            String predictedTitle = xl.getCellData(sheet, i, xl.getColumnIndex(sheet, "Predicted Title")).toLowerCase().trim();
            if (predictedTitle.contains(altKey)) {
                return i;
            }
        }
        return -1;
    }

    public void verifyTitlesAndUrls() {
        System.out.println("Verification logic completed. Results stored in Excel.");
    }
}
