package testRunner_TestNG;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
    features = {
    		"features/testCase001.feature",
    		"features/testCase002.feature",
    		"features/testCase003.feature",
         "features/testCase004.feature",
         "features/testCase005.feature",
         "features/testCase006.feature",
         "features/testCase007.feature",
         "features/testCase008.feature",
         "features/testCase010.feature"
    },
    glue = {
        "stepsDefination",
        "hooks"
    },
    plugin = {
        "pretty",
        "html:target/cucumber-reports",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
    },
    monochrome = true
//    	    dryRun = false,
//    	    publish = true
)
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
