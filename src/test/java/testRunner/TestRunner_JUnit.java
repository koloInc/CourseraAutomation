package testRunner;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import utilities.ReportGenerator;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = {
        // To run all tests:
        "features",
    		//"features/testCase011.feature",

        // To rerun only failed tests:
         //"@target/rerun.txt"
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
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
        "rerun:target/rerun.txt" 
    },
    monochrome = true
//    	    dryRun = false,
//    	    publish = true
)
public class TestRunner_JUnit {

    @AfterClass
    public static void generateReport() {
        ReportGenerator.runAllureBatchFile(); 
    }
}
