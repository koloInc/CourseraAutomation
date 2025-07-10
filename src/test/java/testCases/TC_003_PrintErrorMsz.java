package testCases;

import org.testng.annotations.Test;

import pageObjects.ForBusiness;
import pageObjects.ForGovernment;
import pageObjects.HomePage;
import testBase.BaseClass;
import utilities.XMLUtils;

public class TC_003_PrintErrorMsz extends BaseClass {
    
    @Test
    public void email_phone_error() {
        HomePage hp = new HomePage(driver);
        hp.clickForBusiness();

        // Load XML data
        XMLUtils xml = new XMLUtils("country.xml");
        String country = xml.getElementValue("country");

        ForGovernment fg = new ForGovernment(driver);
        fg.setFirstName(randomeString());
        fg.setLastName(randomeString());
        fg.setEmail(randomMobileNumber());
        fg.setMobileNo(randomAlphaNumeric());
        fg.selectOrgType(randomNumberInRange(3, 4));          // Now parameterize if needed
        fg.setJobTitle(randomeString() + randomeString());
        fg.setOrgName(randomeString());
        fg.selectOrgSize(randomNumberInRange(1, 5));          // Can be data-driven
        fg.selectAboutYou(randomNumberInRange(1, 5));         // Can be data-driven
        fg.selectCountry(country);                            //  Dynamic from XML
        fg.clickRequestInfo();

        ForBusiness fb = new ForBusiness(driver);
        System.out.println("Email Error Message: " + fb.getEmailError());
        pressTabKey();
        System.out.println("Phone Error Message: " + fb.getPoneError());
    }
}
