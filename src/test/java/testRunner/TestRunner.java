package testRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = {
    		".\\features\\testCase10.feature",
    		".\\features\\testCase3.feature"
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
public class TestRunner {
}
