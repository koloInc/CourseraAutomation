package testRunner_JUnit;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = {
    		"features/testCase003.feature",
//    		".\\features\\testCase004.feature",
//    		".\\features\\testCase005.feature",
//    		".\\features\\testCase006.feature",
//    		".\\features\\testCase007.feature",
//    		".\\features\\testCase010.feature",
    		
    		
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
//    dryRun = false,
//    publish = true
)
public class JunitTestRunner {
}
