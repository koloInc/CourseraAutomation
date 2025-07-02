package testCases;

import org.testng.annotations.Test;

import pageObjects.ForBusiness;
import pageObjects.ForGovernment;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_003_PrintErrorMsz extends BaseClass {
	
	@Test
	public void email_phone_error()
	{
		HomePage hp = new HomePage(driver);
		hp.clickForBusiness();
		
		
		ForGovernment fg = new ForGovernment(driver);
		fg.setFirstName(randomeString());
		fg.setLastName(randomeString());
		fg.setEmail(randomNumber());
		fg.setMobileNo(randomAlphaNumeric());
		fg.selectOrgType(randomNumberInRange(3,4)); //Hard Coded
		fg.setJobTitle(randomeString()+randomeString());
		fg.setOrgName(randomeString());
		fg.selectOrgSize(randomNumberInRange(1,5)); // Hard Coded
		fg.selectAboutYou(randomNumberInRange(1,5)); // Hard Coded
		fg.selectCountry("Israel"); //HardCoded
		fg.clickRequestInfo();
		
		ForBusiness fb = new ForBusiness(driver);
		System.out.println(fb.getEmailError());
		pressTabKey();
		System.out.println(fb.getPoneError());
		
		
	}

}
