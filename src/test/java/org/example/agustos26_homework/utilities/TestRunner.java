package org.example.agustos26_homework.utilities;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/org/example/agustos26_homework/features",
        glue = "org.example.agustos26_homework.steps",
        plugin = {"pretty", "html:target/cucumber-reports", "json:target/cucumber.json", "junit:target/cucumber.xml"},
        monochrome = true,
        dryRun = false
)
public class TestRunner {
}
