package hooks;
 
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import factory.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
 
public class Hooks {
 
	WebDriver driver;
	Properties p;
	String sparkFolder;
 
	@Before
	public void setup() throws IOException {
		driver = BaseClass.initilizeBrowser();
		BaseClass.setDriver(driver);
		p = BaseClass.getProperties();
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
	}
 
	@After
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			System.out.println("❎ Scenario failed: " + scenario.getName());
										
			
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String screenshotName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_") + ".png";
			String timestamp = java.time.LocalDateTime.now()
					.format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss"));
			String sparkFolder = System.getProperty("user.dir") + "/test-output/SparkReport " + timestamp
					+ "/Screenshots";
			new File(sparkFolder).mkdirs();
			String screenshotPath = sparkFolder + "/" + screenshotName;
 
			try {
				FileUtils.copyFile(src, new File(screenshotPath));

				if (ExtentCucumberAdapter.getCurrentStep() != null) {
				        ExtentCucumberAdapter.addTestStepScreenCaptureFromPath("Screenshots/" + screenshotName);
				    }
				
				    // Add to Report
				    byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				    scenario.attach(screenshotBytes, "image/png", "Failure Screenshot");

			} catch (IOException e) {
				e.printStackTrace();
			}   

		} else {
			System.out.println("✅ Scenario passed: " + scenario.getName());
		}
 
		if (driver != null) {
			driver.quit();
		}
	}
 

}