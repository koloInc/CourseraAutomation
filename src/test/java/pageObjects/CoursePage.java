package pageObjects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CoursePage extends BasePage{
	public CoursePage(WebDriver driver) {
		super(driver);
	}
	
	//Expanding Language
	@FindBy(css ="button[aria-label='Show more Language options'] span[class='cds-button-label']")
	WebElement allLang ;
	
	//List of Languages
	@FindBy(xpath="//*[@class='css-q5d1os']/div")
	List <WebElement> lang ;

	//List of Levels
	@FindBy(xpath="//div[@data-testid='search-filter-group-Level']//div/span")
	List <WebElement> level ;

	//List of Courses
	@FindBy(xpath="//*[@class='cds-ProductCard-gridCard']")
	List<WebElement> cards ;
	
	Map<String, Integer> languageMap;
	
	//Getting all languages in HashMap
	public Map<String,Integer> getLanguages() {
		languageMap = new HashMap<>();
		Pattern pattern = Pattern.compile("^(.*)\\((\\d+)(?:,(\\d+))?\\)$");
		for(WebElement ele: lang) {
			String tex= ele.getText();
			Matcher matcher = pattern.matcher(tex.trim());//got 3 group
			
			if (matcher.find()) {
				String name = matcher.group(1).trim();  //group 1
				String number = matcher.group(3) != null ? matcher.group(2)+matcher.group(3) : matcher.group(2);  //group 2 & 3 for numbers
				languageMap.put(name, Integer.parseInt(number));	
			}
		}
		return languageMap;
	}
	
	//Clicking on All Language Expansion
	public void clickallLang()
	{
		allLang.click();
	}

	//Selecting Language
	public void selectLang(String value)
	{
		for (WebElement ele: lang) 
		{
			if(ele.getText().contains(value)) 
			{
				ele.click();
			}
		}
	}
		
	//Selecting Level
	public void setLevel(String value)
	{
		for(WebElement ele:level) 
		{
			if(ele.getText().contains(value)) 
			{
				ele.click();
			}
		}
	}
	
	//get course card by index
	public WebElement getCard(int index) {
		return cards.get(index);
	}
	
	//get title of specific course
	public String getTitle(WebElement card) {
		return card.findElement(By.xpath(".//h3")).getText();  
	}
	
	//get rating of specific course
	public String getRating(WebElement card) {
		return card.findElement(By.xpath(".//*[@class='css-6ecy9b']")).getText();
	}
	
	//get duration of specific course
	public String getDuration(WebElement card) {
		String dur = card.findElement(By.xpath(".//*[@class='cds-CommonCard-metadata']")).getText();  //getting duration
		String[] parts = dur.split("·");
				
		return parts[parts.length - 1].trim(); // Trim and get the last part
	}		
}
