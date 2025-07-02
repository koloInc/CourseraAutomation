//7. After Flow 1 -> Click on first result -> Show the details of the course (Mentor name/Rating/Level etc)
package linearFlow;



import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Flow_7 {

	public static void main(String[] args) {
		WebDriver driver =new ChromeDriver();
		driver.get("https://www.coursera.org/search?query=Web%20Development");
		driver.manage().window().maximize();
		String title=driver.getTitle();
		//Getting card of course
		
		List <WebElement> cards=driver.findElements(By.xpath("//*[@class='cds-ProductCard-gridCard']"));
		for(int card=0;card<1;card++) {
			cards.get(card).click();    //clicking on first card
		}
		
		//
		Set<String>win=driver.getWindowHandles();
		List windows=new ArrayList(win);
		
		//Switching to new tab
		for(Object w:windows) {
			driver.switchTo().window((String) w);  
			if(!driver.getTitle().equals(title)) {
				break;
			}
		}
		
		//gettting title
		String tit=driver.findElement(By.xpath("//*[@data-e2e=\"hero-title\"]")).getText();
		System.out.println(tit);
		
		System.out.println(driver.getTitle());  //just for checking new web is open
		
		//getting frame
		WebElement ele=driver.findElement(By.xpath("//div[@class='css-2qp4i2 cds-168']"));
		
		//getting no. of module
		String module=ele.findElement(By.xpath(".//*[@class='css-fk6qfz']")).getText();
		//getting rating
		String rating=ele.findElement(By.xpath(".//*[@class='cds-119 cds-Typography-base css-h1jogs cds-121']")).getText();
		//getting reviews
		String reviews=ele.findElement(By.xpath(".//*[@class='css-vac8rf']")).getText();

		System.out.println( module+" - "+rating +" - "+reviews);	

		//clicking on show more button
		WebElement showBtn=driver.findElement(By.xpath("//span[normalize-space()='View all skills']"));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", showBtn);
		
		//getting what skills we will have
		List <WebElement> skills=driver.findElements(By.xpath("//*[@id=\"about\"]//div[2]/ul/li"));
		for(WebElement skill:skills) {
			System.out.println(skill.getText());
		}
		
		//we will learn
		List <WebElement> weLearn=driver.findElements(By.xpath("//*[@class=\"cds-9 css-7avemv cds-10\"]/li"));
		for(WebElement learn:weLearn) {
			System.out.println(learn.getText());
		}
	}


}
