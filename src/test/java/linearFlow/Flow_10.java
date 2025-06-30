package linearFlow;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Flow_10 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://www.coursera.org/");
		WebElement appStore = driver.findElement(By.xpath("//a[@data-click-key='front_page.front_page_story.click.mobile_app_badges_ios']"));
        js.executeScript("arguments[0].scrollIntoView(false);", appStore);
        appStore.click();
        TakesScreenshot ts = (TakesScreenshot)driver;
        File Sourcefile1 = ts.getScreenshotAs(OutputType.FILE);
        File targetFile1 = new File(System.getProperty("user.dir")+"\\Validation_ScreenShot\\Apple.png");
        Sourcefile1.renameTo(targetFile1);
        driver.navigate().back();
        WebElement playStore = driver.findElement(By.xpath("//a[@data-click-key='front_page.front_page_story.click.mobile_app_badges_android']"));
        playStore.click();
        File Sourcefile2 = ts.getScreenshotAs(OutputType.FILE);
        File targetFile2 = new File(System.getProperty("user.dir")+"\\Validation_ScreenShot\\Android.png");
        Sourcefile2.renameTo(targetFile2);
        driver.navigate().back();
        driver.quit();
        
        
        

	}

}
