package functional_tests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

// @CucumberOptions(format = {"json:target/cucumber.json"}, plugin = {"pretty", "html:target/cucumber"})
// @CucumberOptions(plugin = {"pretty", "html:target/cucumber"})
@RunWith(Cucumber.class)
//@CucumberOptions(format = {"pretty", "html:build/report/selenium/selenium", "json:build/reports/selenium/selenium.json"})

@CucumberOptions(
        plugin = {"html:build/report/selenium/selenium", "json:build/reports/selenium/selenium.json"},
        features ="src/test/java/features"
)

public class CucumberRunner {
}
