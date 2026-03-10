package org.satya.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.satya.driver.DriverFactory;
import org.satya.driver.RestDriver;
import org.satya.utils.ReportManager;
import org.satya.utils.ScenarioContext;
import org.satya.utils.TestContext;

public class Hooks {

    private RestDriver driver;
    private TestContext testContext;
    private static final Logger logger = LogManager.getLogger(Hooks.class);
    private static TestContext staticTestContext;

    // Constructor for PicoContainer dependency injection
    public Hooks(RestDriver driver) {
        this.driver = driver;
    }

    @Before
    public void beforeMethod(Scenario scenario) {
        logger.info("Starting: " + scenario.getName());

        // Initialize driver
        DriverFactory.initDriver();
        this.driver = DriverFactory.getDriver();

        // Create test context with scenario name (API name)
        this.testContext = new TestContext(scenario.getName());

        // Store in static variable so it's accessible throughout the scenario
        staticTestContext = this.testContext;

        // Initialize ScenarioContext singleton (will be reused across scenarios)
        ScenarioContext.getInstance();
    }

    @After
    public void afterMethod(Scenario scenario) {
        try {
            logger.info("Completed: " + scenario.getName() + " - " + (scenario.isFailed() ? "FAILED" : "PASSED"));

            // Flush the report to ensure all logs are written
            ReportManager.getReportInstance().flush();
        }finally {
            // Cleanup
            DriverFactory.unload();
            staticTestContext = null;
        }
    }

    // Static getter to access TestContext from anywhere in the scenario
    public static TestContext getTestContext() {
        if (staticTestContext == null) {
            staticTestContext = new TestContext();
        }
        return staticTestContext;
    }
}




