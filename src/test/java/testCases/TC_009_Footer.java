package testCases;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_009_Footer extends BaseClass{
	@Test
	public void footerData() {
		HomePage hp = new HomePage(driver);
//		hp.getFooterData();
		List<WebElement>footers= hp.getAllFooter();
		for(WebElement footer:footers) {
			String title=hp.getFooterTitle(footer);
			System.out.println(title);
			List<WebElement> units=hp.getContentList(footer);
			for(WebElement unit:units) {
				hp.getFooters(unit);
			}
		}
	}
}
