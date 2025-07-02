package pageObjects.homePage;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageObjects.BasePage;

public class OnlineDegrees extends BasePage {
	
	public OnlineDegrees(WebDriver driver) {
		super(driver);
		
	}

	@FindBy(xpath="//button[@id='react-aria-:R159l6kala:']//span[@class='cds-selectController-icon']//*[name()='svg']")
	WebElement programLevel ;
	
	@FindBy(xpath="//div[@role='presentation' and @dir='ltr']//li/div/div/div/div[1]")
	List<WebElement> progamLevelList;
	
	@FindBy(xpath="//span[normalize-space()='Apply']")
	WebElement applyBtn;
	
	@FindBy(xpath="//button[@id='react-aria-:R179l6kala:']//span[@class='cds-selectController-icon']//*[name()='svg']")
	WebElement subject;
	
	@FindBy(xpath="//div[@class='cds-popoverContainer-inner']//li/div/div/div/div[1]")
	List<WebElement> subjectList;
	
	@FindBy(xpath="//div[@id='DegreeFilters']/div[2]/div[1]//span")
	WebElement noOfResults ;
	
	@FindBy(xpath="//div[@data-test=\"TopProductsList\"]/div/div")
	List<WebElement> collegeCards;
	
	public void dropdownProgramLevel()
	{
		programLevel.click();
	}
	
	public void selectProgramLevel(String value)
	{
		for(WebElement ele : progamLevelList)
		{
			System.out.println(ele.getText());
			if(ele.getText().contains(value))
			{
				ele.click();
			}
		}
	}
	
	public void btnApply()
	{
		applyBtn.click();
	}
	
	public void dropdownSubject()
	{
		subject.click();
	}
	
	public void selectSubject(String value)
	{
		for(WebElement ele : subjectList)
		{
			System.out.println(ele.getText());
			if(ele.getText().contains(value))
			{
				ele.click();
			}
		}
	}
	// Again apply click method
	
	public String displayNoOfResults()
	{
		return noOfResults.getText();
	}
	
	public List<String> displayCollegeDetails() 
	{
	    List<String> details = new ArrayList<>();
	    for (WebElement ele : collegeCards) 
	    {
	        details.add(ele.getText());
	    }
	    return details;
	}

	

}
