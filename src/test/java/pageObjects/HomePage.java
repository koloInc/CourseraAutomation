package pageObjects;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

	
	public HomePage(WebDriver driver)
	{
		super(driver);
	}

	TakesScreenshot ts = (TakesScreenshot)driver;
	
//Search Bar
@FindBy(id="search-autocomplete-input")
WebElement searchBar ;

//For Business
@FindBy(xpath="//a[@data-click-key='front_page.front_page_story.click.navigation_meta_nav_Business']")
WebElement forBusiness;

//For Government
@FindBy(xpath="//a[@data-click-key='front_page.front_page_story.click.navigation_meta_nav_Government']")
WebElement forGovernment ;

//Online Degrees
@FindBy(xpath="//a[normalize-space()='Online Degrees']")
WebElement onlineDegree;

//Bottom IOS Store
@FindBy(xpath="//a[@data-click-key='front_page.front_page_story.click.mobile_app_badges_ios']")
WebElement appStore;

//Bottom Play Store
@FindBy(xpath="//a[@data-click-key='front_page.front_page_story.click.mobile_app_badges_android']")
WebElement playStore;

@FindBy(css ="button[aria-label='Show more Language options'] span[class='cds-button-label']")
WebElement allLang ;
@FindBy(xpath="//*[@class='css-q5d1os']/div")
List <WebElement> lang ;

@FindBy(xpath="//div[@data-testid='search-filter-group-Level']//div/span")
List <WebElement> level ;

@FindBy(xpath="//*[@class='cds-ProductCard-gridCard']")
List<WebElement> cards ;

//Action Methods

public void clickForBusiness()
{
	forBusiness.click();
}


public void clickForGovernment()
{
	forGovernment.click();
}


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


//IOS Store Click
public void clickAppStore()
{
	js.executeScript("arguments[0].scrollIntoView(false);", appStore);
    appStore.click();
}
//TakeScrrenShot
public void clickScreenShotIOS()
{
	File Sourcefile1 = ts.getScreenshotAs(OutputType.FILE);
    File targetFile1 = new File(System.getProperty("user.dir")+"\\Validation_ScreenShot\\Apple.png");
    Sourcefile1.renameTo(targetFile1);
}
//PlayStore Click
public void clickPlayStore()
{
	playStore.click();
}

//TakeScreenShot
public void clickScreenShotAndroid()
{
	File Sourcefile2 = ts.getScreenshotAs(OutputType.FILE);
    File targetFile2 = new File(System.getProperty("user.dir")+"\\Validation_ScreenShot\\Android.png");
    Sourcefile2.renameTo(targetFile2);
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