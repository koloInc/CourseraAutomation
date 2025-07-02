package testCases;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import pageObjects.CoursePage;
import pageObjects.HomePage;
import testBase.BaseClass;
public class TC_001_CourseDetails extends BaseClass{
	@Test
	public void Courses()
	{
		HomePage hp = new HomePage(driver);
		hp.setSearchBar("Web Development");
		hp.clickSearchBar();
		
		CoursePage page= new CoursePage (driver);
		page.clickallLang();
		page.getLanguages();
		page.selectLang("English");
		page.setLevel("Beginner");
		WebElement card =page.getCard(0);
		String title=page.getTitle(card);
		String rating=page.getRating(card);
		String duration=page.getDuration(card);
		
		System.out.println(title + "__" + rating+ "__"+duration);
	}
}
