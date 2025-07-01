//INDIVIDUAL -> PARTNERS -> INDIA -> LOGO | NAME | LINK
package linearFlow;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Flow_4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://www.coursera.org/");
		WebElement partner = driver.findElement(By.xpath("//*[@id='rendered-content']/div/main/section[1]/h2/div/a"));		
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(false);", partner);
        //partner.click();
        partner.sendKeys(Keys.ENTER);
        WebElement country = driver.findElement(By.xpath("//a[normalize-space()='India']"));
        js.executeScript("arguments[0].scrollIntoView(false);", country);
        country.click();
        List<WebElement> availableLinks= driver.findElements(By.xpath("//*[@class='horizontal-box wrap css-jqim4p']//a"));
        int count0 = 0;
        for(WebElement ele: availableLinks)
        {
        		System.out.println(ele.getAttribute("href"));
        		count0 += 1;
        }
        System.out.println(count0);
        List<WebElement> availableImg= driver.findElements(By.xpath("//*[@class='horizontal-box wrap css-jqim4p']//a//img"));
        int count1 = 0;
        for(WebElement ele: availableImg)
        {
        		System.out.println(ele.isDisplayed());
        		count1 += 1;
        }
        System.out.println(count1);
        List<WebElement> availableName= driver.findElements(By.xpath("//*[@class='horizontal-box wrap css-jqim4p']//a//p"));
        int count2 = 0;
        for(WebElement ele: availableName)
        {
        		System.out.println(ele.getText());
        		count2 += 1;
        }
        System.out.println(count2);
        driver.quit();
      
        
        
        
        
	}

}
