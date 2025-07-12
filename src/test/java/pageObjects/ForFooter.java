package pageObjects;

import java.time.Duration;
//import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import linearFlow.WaitUtils;

public class ForFooter extends BasePage {

    public ForFooter(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
        mainWindow = driver.getWindowHandle();
    }

    JavascriptExecutor js;
    String mainWindow;

    // Social Media Wrapper
    @FindBy(xpath = "//div[@class='lazyload-wrapper']")
    WebElement socialMediaWrapper;

    // Action Methods

    public void scrollToFooter() throws InterruptedException {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(3000);
    }

    public void openSocialMediaLinks() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String[] socialMediaAlts = {
            "Coursera Facebook", "Coursera Linkedin", "Coursera YouTube",
             "Coursera Instagram", "Coursera TikTok"
        };

        for (String alt : socialMediaAlts) {
            try {
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@alt='" + alt + "']")));
                WebElement icon = driver.findElement(By.xpath("//img[@alt='" + alt + "']"));
                WebElement link = icon.findElement(By.xpath("./ancestor::a"));
                String href = link.getAttribute("href");

                js.executeScript("window.open(arguments[0]);", href);
//                Thread.sleep(2000);
                WaitUtils.waitForDuration(driver, 2);

                Set<String> allWindows = driver.getWindowHandles();
                for (String window : allWindows) {
                    if (!window.equals(mainWindow)) {
                        driver.switchTo().window(window);
                        break;
                    }
                }

                Thread.sleep(3000);
                System.out.println(driver.getTitle() + " - " + driver.getCurrentUrl());
//                System.out.println(driver.getTitle());

                driver.close();
                driver.switchTo().window(mainWindow);
            } catch (NoSuchElementException e) {
                System.out.println("Social media icon not found for alt: " + alt);
            }
        }
    }


    public void verifyTitlesAndUrls() {
        // Placeholder for future validation logic
        System.out.println("Verification logic can be added here.");
    }
}
