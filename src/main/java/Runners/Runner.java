package Runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/java/Features",
        glue="StepDefinitions",
        monochrome=true,
        plugin = { "pretty", "html:target/cucumber-report_example.html"
        }
)
public class Runner {


}
