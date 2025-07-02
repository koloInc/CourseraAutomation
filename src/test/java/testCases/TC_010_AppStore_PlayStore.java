package testCases;

import org.testng.annotations.Test;

import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_010_AppStore_PlayStore extends BaseClass{
	
	@Test
	public void take_screenShot_Apple_Android()
	{
		HomePage hp = new HomePage(driver);
		hp.clickAppStore();
		hp.clickScreenShotIOS();
		navigateBack();
		hp.clickScreenShotAndroid();
		navigateBack();
		tearDown();
		
	}

}
