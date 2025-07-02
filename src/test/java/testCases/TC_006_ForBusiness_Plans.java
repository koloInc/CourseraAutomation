package testCases;

import org.testng.annotations.Test;

import pageObjects.ForBusiness;
import pageObjects.HomePage;
import testBase.BaseClass;
import testUtils.WaitUtils;

public class TC_006_ForBusiness_Plans extends BaseClass {
	
	@Test
	public void planDetails()
	{
		HomePage hp = new HomePage(driver);
		hp.clickForBusiness();
		
		ForBusiness business = new ForBusiness(driver);

		business.clickForTeams();
		business.clickGetStarted(); // You can call this without passing js!
		business.enterNumberOfUsers(randomNumberInRange(6,300)); //Hard Coded
		business.selectQuarterlyPayment();

		//Thread.sleep(5000); // consider using WebDriverWait instead
		WaitUtils.waitForDuration(driver, 2);
		System.out.println(" Purchase Summary:\n" + business.getPurchaseSummary());

	}
	
	

}
