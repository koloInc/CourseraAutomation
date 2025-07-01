//For Business -> For Teams -> Get Started -> No of users -> Annually/Quarterly ->Continue -> Purchase Summary Table
package linearFlow;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Flow_6 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://www.coursera.org/");
		WebElement forBusiness = driver.findElement(By.xpath("//a[@data-click-key='front_page.front_page_story.click.navigation_meta_nav_Business']"));
		forBusiness.click();
		WebElement forTeams = driver.findElement(By.xpath("//a[normalize-space()='For Teams']"));
		forTeams.click();
		WebElement getStarted = driver.findElement(By.xpath("(//a[@href='https://www.coursera.org/purchase/plan'])[1]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(false);", getStarted);
        getStarted.click();
        WebElement noOfUsers = driver.findElement(By.id("cds-react-aria-:R4ssqna:"));
        noOfUsers.sendKeys("10");
        List<WebElement> paymentOptions =driver.findElements(By.xpath("//label[@for=\"billingType\"]/following-sibling::div/fieldset/span"));
        for(WebElement ele : paymentOptions)
        {
        		if (ele.getText().contains("Quarterly"))
        		{
        			ele.click();
        		}
        }
        Thread.sleep(5000);
        WebElement purchaseSummary = driver.findElement(By.xpath("//div[@class='rc-TeamsPurchaseSummary vertical-box align-items-vertical-center rc-TeamsPurchaseSummary--redesign desktopOnly']"));
        System.out.println(purchaseSummary.getText());
	}

}
