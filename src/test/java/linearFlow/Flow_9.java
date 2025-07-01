package linearFlow;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Flow_9 {

	public static void main(String[] args) {
		WebDriver driver =new ChromeDriver();
		driver.get("https://www.coursera.org/");
		driver.manage().window().maximize();
		
		//Entering to footer
		List<WebElement> footers= driver.findElements(By.xpath("//*[@class=\"cds-9 rc-SubFooterSection lohp-rebrand css-0 cds-11 cds-grid-item cds-61\"]"));
		
		for(WebElement footer: footers) {
//			System.out.println(footer.getText());
			
			//get all title
			WebElement title=footer.findElement(By.xpath("./p"));
			System.out.println(title.getText());
		
			//get all link and content
			List<WebElement> units=footer.findElements(By.xpath(".//a"));
			System.out.println("Length of Element: "+ units.size());
			for(WebElement unit:units) {
				
				String valLink=unit.getAttribute("href");
				String valText=unit.getText();
				System.out.println("Link:" + valLink + "Text: "+ valText);
			}
		}
	}
}
