package testRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = {"C:\\Users\\2403720\\eclipse-workspace\\CourseraAutomation\\features\\testCase10.feature"},
    glue = {"stepsDefination"},
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
