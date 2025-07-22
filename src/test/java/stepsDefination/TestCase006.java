package stepsDefination;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import factory.BaseClass;
import io.cucumber.java.en.*;
import pageObjects.ForBusiness;
import utilities.FileConstants;
import utilities.ExcelUtils;
import utilities.TextFileUtils;

public class TestCase006 {

	WebDriver driver;
	ForBusiness forBusiness;
	ExcelUtils xl = new ExcelUtils(FileConstants.EXCEL_FILE);
	private static final Logger logger = LogManager.getLogger(TestCase006.class);

	@Then("the user navigates to the For Business section")
	public void the_user_navigates_to_the_for_business_section() {
		logger.info("Navigating to the For Business section.");
		CommonSteps.homePage.clickForBusiness();
	}

	@When("the user selects the {string} plan")
	public void the_user_selects_the_plan(String planType) {
		driver = BaseClass.getDriver();
		forBusiness = new ForBusiness(driver);

		logger.info("Selecting business plan: " + planType);
		if (planType.equalsIgnoreCase("For Teams")) {
			forBusiness.clickForTeams();
			logger.debug("Clicked on 'For Teams' plan.");
		}
	}

	@When("enters a random number of users")
	public void enters_a_random_number_of_users_between_and() {
		forBusiness.clickGetStarted();
		int users = Integer.parseInt(xl.getCellData(FileConstants.SHEET_BusinessPlan, 1, 0));
		logger.info("Entering number of users: " + users);
		forBusiness.enterNumberOfUsers(users);
	}

	@When("selects the payment option")
	public void selects_the_quarterly_payment_option() {
		String time1 = xl.getCellData(FileConstants.SHEET_BusinessPlan, 1, 2);
		logger.info("Selecting payment option: " + time1);
		forBusiness.selectQuarterlyPayment(time1);
	}

	@Then("the application should display the purchase summary")
	public void the_application_should_display_the_purchase_summary() throws IOException {
		utilities.WaitUtils.waitForDuration(driver, 2);
		String summary = forBusiness.getPurchaseSummary();
		int rowNo = 1;
		String expected = xl.getCellData(FileConstants.SHEET_BusinessPlan, 1, 3);
		String actual = forBusiness.getTotalPrice().replaceAll("[^\\d]", "").trim();
		System.out.println("Actual Amount:"+actual);
		logger.info("Expected Price: " + expected);
		logger.info("Actual Price: " + actual);
		String status;


		try {
		    xl.setCellData(FileConstants.SHEET_BusinessPlan, rowNo, "Actual", actual);
		
		    boolean isMatch = actual.equals(expected);
		    status = isMatch ? "Passed" : "Failed";
		    logger.info("Price validation " + status.toLowerCase());
		
		    xl.setCellData(FileConstants.SHEET_BusinessPlan, rowNo, FileConstants.VALIDATION_COL_NAME, status);
		
		    if (isMatch) {
		        xl.fillGreenColor(FileConstants.SHEET_BusinessPlan, rowNo, FileConstants.VALIDATION_COL);
		    } else {
		        xl.fillRedColor(FileConstants.SHEET_BusinessPlan, rowNo, FileConstants.VALIDATION_COL);
		    }
		
		    Assert.assertEquals(expected, actual);
		
		} catch (IOException e) {
		    logger.error("Excel file operation failed", e);
		    throw new RuntimeException("Excel write failed", e);
		} catch (AssertionError ae) {
		    logger.error("Assertion failed", ae);
		    throw ae;
		}


		String formatted = "\n--- Purchase Summary (TC006) ---\n" + java.time.LocalDateTime.now() + "\n\n" + summary
				+ "\n" + "=".repeat(80) + "\n";

		TextFileUtils.writeToTextFile("PartnerData_TC6.txt", formatted, true);
		logger.info("Purchase summary written to PartnerData_TC6.txt");
	}
}
