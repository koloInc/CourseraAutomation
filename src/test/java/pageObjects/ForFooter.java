package pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForFooter extends BasePage {

    public ForFooter(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='lazyload-wrapper']")
    WebElement socialMediaWrapper;

    private final String[] socialMediaAlts = {
        "Coursera Facebook",
        "Coursera Linkedin",
        "Coursera YouTube",
        "Coursera Instagram",
        "Coursera TikTok"
    };

    private List<String> actualTitles = new ArrayList<>();
    private List<String> actualLinks = new ArrayList<>();

    public void scrollToFooter() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public void openSocialMediaLinks() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String mainWindow = driver.getWindowHandle();

        for (String alt : socialMediaAlts) {
            try {
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@alt='" + alt + "']")));
                WebElement icon = driver.findElement(By.xpath("//img[@alt='" + alt + "']"));
                WebElement link = icon.findElement(By.xpath("./ancestor::a"));
                String href = link.getAttribute("href");

                ((JavascriptExecutor) driver).executeScript("window.open(arguments[0]);", href);
                Thread.sleep(2000);

                Set<String> allWindows = driver.getWindowHandles();
                for (String window : allWindows) {
                    if (!window.equals(mainWindow)) {
                        driver.switchTo().window(window);
                        break;
                    }
                }

                Thread.sleep(3000);
                actualTitles.add(driver.getTitle());
                actualLinks.add(driver.getCurrentUrl());

                driver.close();
                driver.switchTo().window(mainWindow);
            } catch (Exception e) {
                actualTitles.add("Error");
                actualLinks.add("Error");
                driver.switchTo().window(mainWindow);
            }
        }
    }

    public List<String> getActualTitles() {
        return actualTitles;
    }

    public List<String> getActualLinks() {
        return actualLinks;
    }
}
