package testCases;

import java.util.Map;

import org.testng.annotations.Test;

import pageObjects.CoursePage;
import testBase.BaseClass;

public class TC_002_LanguagesList extends BaseClass{
	@Test(dependsOnMethods = {"testCases.TC_001_CourseDetails.Courses"})
	public void AllLanguages()
	{
		//HomePage hp = new HomePage(driver);
		CoursePage page=new CoursePage(driver);
		
		Map<String, Integer>languages=page.getLanguages();
		for(String lang: languages.keySet()) {
			System.out.println(lang+" - "+ languages.get(lang));
		}
	}
}
