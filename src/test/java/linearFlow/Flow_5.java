package linearFlow;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Flow_5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://www.coursera.org/");
		WebElement onlineDegree = driver.findElement(By.xpath("//a[normalize-space()='Online Degrees']"));
		onlineDegree.click();
		WebElement programLevel = driver.findElement(By.xpath("//button[@id='react-aria-:R159l6kala:']//span[@class='cds-selectController-icon']//*[name()='svg']"));
		programLevel.click();
		List<WebElement> progamLevelList=driver.findElements(By.xpath("//div[@role='presentation' and @dir='ltr']//li/div/div/div/div[1]"));
		for(WebElement ele : progamLevelList)
		{
			System.out.println(ele.getText());
			if(ele.getText().equals("Master's Degree"))
			{
				ele.click();
			}
		}
		driver.findElement(By.xpath("//span[normalize-space()='Apply']")).click();
		WebElement subject = driver.findElement(By.xpath("//button[@id='react-aria-:R179l6kala:']//span[@class='cds-selectController-icon']//*[name()='svg']"));
		subject.click();
		List<WebElement> subjectList = driver.findElements(By.xpath("//div[@class='cds-popoverContainer-inner']//li/div/div/div/div[1]"));
		for(int i=0 ; i<subjectList.size();i++)
		{
			System.out.println(subjectList.get(i).getText());
			if( (i==2) && (i<subjectList.size()))
			{
				subjectList.get(i).click();
			}
		}
		driver.findElement(By.xpath("//span[normalize-space()='Apply']")).click();
		
		System.out.println(driver.findElement(By.xpath("//div[@id=\"DegreeFilters\"]/div[2]/div[1]//span")).getText());
		
	}

}
