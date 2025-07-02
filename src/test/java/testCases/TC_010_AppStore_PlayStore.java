package testCases;

import org.testng.annotations.Test;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_010_AppStore_PlayStore extends BaseClass {

    @Test
    public void take_screenShot_Apple_Android() {
        HomePage hp = new HomePage(driver);

        // Handle Apple App Store
        hp.clickAppStore();
        hp.takeScreenshotIOS();
        navigateBack();

        // Handle Android Play Store
        hp.clickPlayStore();
        hp.takeScreenshotAndroid();
        navigateBack();

        tearDown();
    }
}
