package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import utilities.ReportGenerator;

import org.testng.annotations.*;

@CucumberOptions(
    features = {
//    		"features/testCase001.feature",
//    		"features/testCase002.feature",
//    		"features/testCase003.feature",
//         "features/testCase004.feature",
//         "features/testCase005.feature",
//         "features/testCase006.feature",
//         "features/testCase007.feature",
//         "features/testCase008.feature",
//         "features/testCase009.feature",
         "features/testCase010.feature"
    		
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
           // "rerun:target/rerun.txt",
    },
    monochrome = true
//    	    dryRun = false,
//    	    publish = true,
    			//tags="@sanity"  // this will execute scenarios tagged with @sanity
			//tags="@regression"
			//tags="@sanity and @regression" //Scenarios tagged with both @sanity and @regression
			//tags="@sanity and not @regression" //Scenarios tagged with @sanity but not tagged with @regression
			//tags="@sanity or @regression" //Scenarios tagged with either @sanity or @regression
)
public class TestRunner_TestNG extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
    @AfterClass
    public static void generateReport() {
        ReportGenerator.runAllureBatchFile();
    }
}
