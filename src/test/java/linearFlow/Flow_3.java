//3. Business -> FILL FORM -> INVALID INPUT -> PRINT ERROR MESSAGE

package linearFlow;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Flow_3 {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://www.coursera.org/");
		WebElement forBusiness = driver.findElement(By.xpath("//a[@data-click-key='front_page.front_page_story.click.navigation_meta_nav_Business']"));
		forBusiness.click();
		WebElement firstName = driver.findElement(By.id("FirstName"));
		firstName.sendKeys("S Victor");
		WebElement lastName = driver.findElement(By.id("LastName"));
		lastName.sendKeys("Kumar");
		WebElement email = driver.findElement(By.id("Email"));
		email.sendKeys("victorkumar007.com");
		WebElement phoneNo = driver.findElement(By.id("Phone"));
		phoneNo.sendKeys("ABCDEF");
		WebElement orgTypeEle = driver.findElement(By.id("rentalField9"));
		Select orgTypeSelect = new Select(orgTypeEle);
		orgTypeSelect.selectByValue("Government");
		WebElement jobTitle = driver.findElement(By.id("Title"));
		jobTitle.sendKeys("Junior Cleark");
		WebElement orgName = driver.findElement(By.id("Company"));
		orgName.sendKeys("SPOO Coimbatore");
		WebElement orgSizeEle = driver.findElement(By.id("Employee_Range__c"));
		Select orgSizeSelect = new Select(orgSizeEle);
		orgSizeSelect.selectByValue("501-1000");
		WebElement aboutYouEle = driver.findElement(By.id("What_the_lead_asked_for_on_the_website__c"));
		Select aboutYouSelect = new Select(aboutYouEle);
		aboutYouSelect.selectByValue("Existing customer support");
		WebElement countryEle = driver.findElement(By.id("Country"));
		Select countrySelect = new Select(countryEle);
		countrySelect.selectByValue("Israel");
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		WebElement invalidEmail =driver.findElement(By.id("ValidMsgEmail"));
		System.out.println(invalidEmail.getText());
		Actions act = new Actions(driver);
		act.keyDown(Keys.TAB).keyUp(Keys.TAB).perform();
		WebElement invalidPhone =driver.findElement(By.id("ValidMsgPhone"));
		System.out.println(invalidPhone.getText());
		

	}

}
