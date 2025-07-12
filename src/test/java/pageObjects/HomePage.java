package pageObjects;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    //  Navigation Links
    @FindBy(id = "search-autocomplete-input")
    WebElement searchBar;
    
    @FindBy(xpath="//*[@id='react-autowhatever-1']")
    WebElement suggestion;
    
    @FindBy(xpath="//*[@id='react-autowhatever-1']//div[2]/span")
    List <WebElement> suggestionList;

    @FindBy(xpath = "//a[@data-click-key='front_page.front_page_story.click.navigation_meta_nav_Business']")
    WebElement forBusiness;

    @FindBy(xpath = "//a[@data-click-key='front_page.front_page_story.click.navigation_meta_nav_Government']")
    WebElement forGovernment;

    @FindBy(xpath = "//a[normalize-space()='Online Degrees']")
    WebElement onlineDegree;
    
    @FindBy(xpath="//*[@id='rendered-content']/div/main/section[1]/h2/div/a")
    WebElement partners;

    //  Mobile Store Badges
    @FindBy(xpath = "//a[@data-click-key='front_page.front_page_story.click.mobile_app_badges_ios']")
    WebElement appStoreBadge;

    @FindBy(xpath = "//a[@data-click-key='front_page.front_page_story.click.mobile_app_badges_android']")
    WebElement playStoreBadge;
    
    @FindBy(xpath="//*[@class=\"cds-9 rc-SubFooterSection lohp-rebrand css-0 cds-11 cds-grid-item cds-61\"]")
    List<WebElement> footers;
    
    Map<String, String> footerList;
    //  Action Methods

    public void clickForBusiness() {
        forBusiness.click();
    }

    public void clickForGovernment() {
        forGovernment.click();
    }

    public void setSearchBar(String value) {
        searchBar.sendKeys(value);
    }

    public void submitSearch(String value) throws InterruptedException {
		Thread.sleep(4000);    //remove it
    		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    		wait.until(ExpectedConditions.visibilityOf(suggestion));
//    		System.out.println(wt.isDisplayed());
    		for(WebElement w:suggestionList) {
    			if(w.getText().equalsIgnoreCase(value)) {
    				w.click();
    				break;
    			}
    		}
    }

    public void clickOnlineDegree() {
        onlineDegree.click();
    }
    
    public void clickPartners() {
        scrollIntoView(partners);
        partners.sendKeys(Keys.ENTER);
    }

    public void clickAppStore() {
        scrollIntoView(appStoreBadge);
        appStoreBadge.click();
    }

    public void clickPlayStore() {
        scrollIntoView(playStoreBadge);
        playStoreBadge.click();
    }

    public void takeScreenshotIOS() {
        captureScreenshot("Apple.png");
    }

    public void takeScreenshotAndroid() {
        captureScreenshot("Android.png");
    }
    
    public List<WebElement> getAllFooter() {
    		return footers;
    }
    public String getFooterTitle(WebElement footer) {
    		return footer.findElement(By.xpath("./p")).getText();
    }
    
    public List<WebElement> getContentList(WebElement footer){
	    	return footer.findElements(By.xpath(".//a"));
    }
    
    public void getFooters(WebElement unit){
//    		footerList=new HashMap<>();
    			String valText=unit.getText();
    			String valLink=unit.getAttribute("href");
    			System.out.println(valText + "\t" + valLink);
//    			footerList.put(valText, valLink);
    		}
    
    public void getFooterData() {
    		List<WebElement>footers= getAllFooter();
    		for(WebElement footer:footers) {
    			String title=getFooterTitle(footer);
    			System.out.println(title);
    			List<WebElement> units=getContentList(footer);
    			for(WebElement unit:units) {
    				getFooters(unit);
    			}
    		}
    		
    }
}
