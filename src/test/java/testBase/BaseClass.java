package testBase;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {

	public WebDriver driver;
	
	@BeforeClass
	public void setup()
	{
		driver=new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://www.coursera.org/");
		driver.manage().window().maximize();
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	

	public String randomeString()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String randomNumber()
	{
		String generatedString=RandomStringUtils.randomNumeric(10);
		return generatedString;
	}
	
	public int randomNumberInRange(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Min should be less than or equal to Max");
        }
        int randomValue = ThreadLocalRandom.current().nextInt(min, max + 1);
        return randomValue;
    }
	
	public String randomAlphaNumeric()
	{
		String str=RandomStringUtils.randomAlphabetic(3);
		String num=RandomStringUtils.randomNumeric(3);
		
		return (str+"@"+num);
	}
	
	public String randomEmail()
	{
		String str=RandomStringUtils.randomAlphabetic(4);
		String num=RandomStringUtils.randomNumeric(2);
		
		return (str+num+"@"+randomeString()+".com");
	}
	
	public void navigateBack()
	{
		driver.navigate().back();
	}
	
	public void pressTabKey() 
	{
	    Actions act = new Actions(driver);
	    act.keyDown(Keys.TAB).keyUp(Keys.TAB).perform();
	}

	
}
