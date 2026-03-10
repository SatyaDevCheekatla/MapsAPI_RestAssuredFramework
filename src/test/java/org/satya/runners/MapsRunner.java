package org.satya.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.TestNGCucumberRunner;
import org.satya.listeners.TestListener;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;


@CucumberOptions(

        features = "src/test/features",
        glue = {"org.satya.stepDefinitions", "org.satya.hooks"},
        dryRun = false ,
        plugin = {
                "pretty",
                "html: target/cucumber-reports.html"
        }
)

@Listeners(TestListener.class)
public class MapsRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}

