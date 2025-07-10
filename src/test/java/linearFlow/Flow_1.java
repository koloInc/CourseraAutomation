// 1. SEARCH -> WEB DEVELOPMENT(SELECT FROM SUGGESTION)-> CLICK ENGLISH FROM LIST-> CLICK BEGINEER FROM LIST-> SCRAAPE FIRST2-> PRINT NAME| RATING | DURATION

package linearFlow;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Flow_1 {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver =new ChromeDriver();
		driver.get("https://www.coursera.org/");
		driver.manage().window().maximize();
		
		WebElement searchBar=driver.findElement(By.id("search-autocomplete-input"));
		searchBar.sendKeys("Web Development");
		driver.findElement(By.xpath("//*[@class=\"search-form\"]//*[@type=\"button\"][1]")).click();
		
//		searchBar.sendKeys(Keys.ENTER);
		
		//For More Languages
		WebElement allLang=driver.findElement(By.cssSelector("button[aria-label='Show more Language options'] span[class='cds-button-label']"));
		allLang.click();
		
		System.out.println(driver.getTitle());
		//For ALl languages in list
		List <WebElement> lang=driver.findElements(By.xpath("//*[@class=\"css-q5d1os\"]/div"));
		for (WebElement ele: lang) {
			if(ele.getText().contains("English")) {
				ele.click();
			}
			
		}
//		driver.findElement(By.xpath("//*[contains(@magnifier-wrapper, 'Submit Search' )][1]")).click();
//		driver.findElement(By.xpath("//*[@id=\"rendered-content\"]/div/div/span/div[1]/header/div[1]/div/div[2]/div/div/div/div/div/div[3]/div/div[2]/form/div/button[2]/div")).click();	
//		Actions act= new Actions(driver);
//		act.keyDown(Keys.ENTER);
		
		//Select Level
		List <WebElement> lvl=driver.findElements(By.xpath("//div[@data-testid='search-filter-group-Level']//div/span"));
		for(WebElement ele:lvl) {
			if(ele.getText().contains("Beginner")) {
				ele.click();
			}
		}

		//wait for loading element
		WaitUtils.waitForDuration(driver, 5); //to be implemented in @Test
		
		//Get card
		List <WebElement> cards=driver.findElements(By.xpath("//*[@class='cds-ProductCard-gridCard']"));
		
		//for each card
		for(WebElement card:cards) {
			//get title for card
			WebElement tit=card.findElement(By.xpath(".//h3"));  ///getting title
			System.out.println(tit.getText());

			WebElement rating = card.findElement(By.xpath(".//*[@class='css-6ecy9b']")); //getting rating
			System.out.println(rating.getText());
		

			WebElement duration = card.findElement(By.xpath(".//*[@class='cds-CommonCard-metadata']"));  //getting duration
			String dur=duration.getText();
			String[] parts = dur.split("·");
			
			// Trim and get the last part
			String time = parts[parts.length - 1].trim();

			System.out.println(time);
			
		}
	}

}
