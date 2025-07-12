package testCases;

import org.testng.annotations.Test;

import pageObjects.ForGovernment;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_008_ForGovernment extends BaseClass{
	
	@Test
	public void Government_Form()
	{
		String country = "Israel";
		HomePage hp = new HomePage(driver);
		hp.clickForGovernment();
		
		ForGovernment fg = new ForGovernment(driver);
		fg.clickContactSales();
		fg.setFirstName(randomeString());
		fg.setLastName(randomeString());
		fg.setEmail(randomEmail());
		fg.setMobileNo(randomMobileNumber());
		fg.selectOrgType(randomNumberInRange(1,2)); //Hard Coded
		fg.setJobTitle(randomeString()+randomeString());
		fg.setOrgName(randomeString());
		fg.selectOrgSize(randomNumberInRange(1,5)); // Hard Coded
		fg.selectAboutYou(randomNumberInRange(1,5)); // Hard Coded
		fg.selectCountry(country); //HardCoded
		fg.clickRequestInfo();
		
		System.out.println(fg.getConfirmationMessage()); //Later Excel Write Conversion
		
	}

}
