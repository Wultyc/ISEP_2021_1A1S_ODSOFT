package gradle.selenium;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

// @CucumberOptions(format = {"json:target/cucumber.json"}, plugin = {"pretty", "html:target/cucumber"})
// @CucumberOptions(plugin = {"pretty", "html:target/cucumber"})
@RunWith(Cucumber.class)
@CucumberOptions(format = {"pretty", "html:build/cucumber/selenium", "json:build/cucumber/selenium.json"})
public class CucumberRunner {
}
