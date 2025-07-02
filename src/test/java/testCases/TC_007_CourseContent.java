package testCases;

import org.testng.annotations.Test;

import pageObjects.CourseContent;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_007_CourseContent extends BaseClass{
	@Test
	public void aboutCourse() {
		HomePage hp = new HomePage(driver);
		hp.setSearchBar("Web Development");
		hp.submitSearch();
		
		CourseContent page=new CourseContent(driver);
		page.getTitle();
		page.getCourseIndex(1);
		page.getAllWindows();
		page.switchToCoursePage();
		System.out.println(page.getCourseTitle());
		System.out.println(page.getTotalModules());
		System.out.println(page.getRating());
		System.out.println(page.getTotalReviews());
		page.clickViewMore();
		page.setAllSkills();
		page.setAllWeLearn();
		
	}
}
