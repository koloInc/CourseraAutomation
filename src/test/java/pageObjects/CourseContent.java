package pageObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CourseContent extends BasePage{
	String title;
	List<String> windows;
	JavascriptExecutor js;
	public CourseContent(WebDriver driver) {
		super(driver);
		js=(JavascriptExecutor)driver;
	}
	
	@FindBy(xpath="//*[@class='cds-ProductCard-gridCard']")
	List <WebElement> cards;
	
	@FindBy(xpath="//*[@data-e2e='hero-title']")
	WebElement courseTitle;
	
	//getting frame
	@FindBy(xpath="//div[@class='css-2qp4i2 cds-168']")
	WebElement ele;
	
	//getting no. of module
	public WebElement module() {
		return ele.findElement(By.xpath(".//*[@class='css-fk6qfz']"));
	}
	
	//getting rating
	public WebElement rating() {
		return ele.findElement(By.xpath(".//*[@class='cds-119 cds-Typography-base css-h1jogs cds-121']"));
	}
	
	//getting reviews
	public WebElement reviews() {
		return ele.findElement(By.xpath(".//*[@class='css-vac8rf']"));
	}
	
	//show button
	@FindBy(xpath="//span[normalize-space()='View all skills']")
	WebElement showBtn;
	
	//skills
	@FindBy(xpath="//*[@id=\"about\"]//div[2]/ul/li")
	List <WebElement> skills;
	
	//We will learn
	@FindBy(xpath="//*[@class=\"cds-9 css-7avemv cds-10\"]/li")
	List <WebElement> weLearn;
	
	public void getTitle() {
		title=driver.getTitle();
	}
	
	public void getCourseIndex(int num) {
		cards.get(num).click();    //clicking on first card	let it be 1	
	}
	
	public void getAllWindows() {
		Set<String>win=driver.getWindowHandles();
		windows=new ArrayList<String>(win);
	}
	
	public void switchToCoursePage() {
		for(Object w:windows) {
			driver.switchTo().window((String) w);  
			if(!driver.getTitle().equals(title)) {
				break;
			}
		}
	}
	
	public String getCourseTitle() {
		return courseTitle.getText();
	}
	
	public String getTotalModules() {
		return module().getText();
	}
	
	public String getRating() {
		return rating().getText();
	}
	
	public String getTotalReviews() {
		return reviews().getText();
	}
	
	public void clickViewMore() {
		js.executeScript("arguments[0].click()", showBtn);
	}
	
	public void setAllSkills() {
		for(WebElement skill:skills) {
			System.out.println(skill.getText());
		}
	}
	
	public void setAllWeLearn() {
		for(WebElement learn:weLearn) {
			System.out.println(learn.getText());
		}
	}
}