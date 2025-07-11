package testRunner_JUnit;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import utilities.ReportGenerator;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = {
        //"features/testCase006.feature",
         "features/testCase006.feature",
        // "features/testCase005.feature",
        // "features/testCase006.feature",
        // "features/testCase007.feature",
        // "features/testCase010.feature",
    },
    glue = {
        "stepsDefination",
        "hooks"
    },
    plugin = {
        "pretty",
        "html:test-output/cucumber-reports.html",
        "json:test-output/cucumber.json",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        
    },
    monochrome = true
//  dryRun = false,
//  publish = true
)
public class JunitTestRunner {
	@AfterClass
    public static void generateReport() {
        ReportGenerator.runAllureBatchFile();
    }
}
