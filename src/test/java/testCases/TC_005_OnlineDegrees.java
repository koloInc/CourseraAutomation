package testCases;

import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.OnlineDegrees;
import testBase.BaseClass;

public class TC_005_OnlineDegrees extends BaseClass {

    @Test
    public void extractOnlineDegreesData() {

        // Initialize home page and navigate to Online Degrees section
        HomePage hp = new HomePage(driver);
        hp.clickOnlineDegree();

        // Initialize Online Degrees page object
        OnlineDegrees od = new OnlineDegrees(driver);

        // Open and select the desired program level filter
        od.openProgramLevelDropdown();
        od.selectProgramLevelByIndex(randomNumberInRange(0,1)); 
        od.applyFilters();

        // Open and select subject by index (e.g., third subject)
        od.openSubjectDropdown();
        od.selectSubjectByIndex(randomNumberInRange(0,6));
        od.applyFilters();

        // Display total number of filtered results
        System.out.println("Total Results: " + od.getTotalResults());

        // Print details of each college card listed
        System.out.println("College Degree Cards:");
        for (String cardDetail : od.getCollegeDetails()) {
            System.out.println(cardDetail);
            System.out.println();
        }
    }
}
