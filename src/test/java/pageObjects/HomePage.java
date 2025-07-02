package pageObjects;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

	
	public HomePage(WebDriver driver)
	{
		super(driver);
	}
	
@FindBy(id="search-autocomplete-input")
WebElement searchBar ;
@FindBy(xpath="//a[normalize-space()='Online Degrees']")
WebElement onlineDegree;

@FindBy(css ="button[aria-label='Show more Language options'] span[class='cds-button-label']")
WebElement allLang ;
@FindBy(xpath="//*[@class='css-q5d1os']/div")
List <WebElement> lang ;

@FindBy(xpath="//div[@data-testid='search-filter-group-Level']//div/span")
List <WebElement> level ;

@FindBy(xpath="//*[@class='cds-ProductCard-gridCard']")
List<WebElement> cards ;
	
public void setSearchBar(String searchValue)
{
	searchBar.sendKeys(searchValue);
}

public void clickSearchBar()
{
	searchBar.sendKeys(Keys.ENTER);
}

public void clickOnlineDegree()
{
	onlineDegree.click();
}

public void clickallLang()
{
	allLang.click();
}

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
	
	





}