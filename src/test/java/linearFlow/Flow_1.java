// 1. SEARCH -> WEB DEVELOPMENT(SELECT FROM SUGGESTION)-> CLICK ENGLISH FROM LIST-> CLICK BEGINEER FROM LIST-> SCRAAPE FIRST2-> PRINT NAME| RATING | DURATION

package linearFlow;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Flow_1 {

	public static void main(String[] args) {
		WebDriver driver =new ChromeDriver();
		driver.get("https://www.coursera.org/");
		driver.manage().window().maximize();
		
		WebElement search=driver.findElement(By.id("search-autocomplete-input"));
		search.sendKeys("Web Development");
		search.sendKeys(Keys.ENTER);
		
//		driver.findElement(By.xpath("//*[contains(@magnifier-wrapper, 'Submit Search' )][1]")).click();
//		driver.findElement(By.xpath("//*[@id=\"rendered-content\"]/div/div/span/div[1]/header/div[1]/div/div[2]/div/div/div/div/div/div[3]/div/div[2]/form/div/button[2]/div")).click();	
		Actions act= new Actions(driver);
		act.keyDown(Keys.ENTER);
		
	}

}
