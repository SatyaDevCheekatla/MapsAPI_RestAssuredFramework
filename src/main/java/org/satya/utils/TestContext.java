package org.satya.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.apache.logging.log4j.Logger;

public class TestContext {

    public Logger logger;
    public ExtentTest reportResult;

    // No-arg constructor for PicoContainer
    public TestContext() {
        // Initialize with lazy loading
        this.logger = LoggerUtil.getLogger(TestContext.class);
        ExtentReports extent = ReportManager.getReportInstance();
        this.reportResult = extent.createTest("API Test");
    }

    public TestContext(String scenarioName) {
        this.logger = LoggerUtil.getLogger(TestContext.class);
        ExtentReports extent = ReportManager.getReportInstance();
        this.reportResult = extent.createTest(scenarioName);
    }

    public TestContext(Logger logger, ExtentTest reportResult) {
        this.logger = logger;
        this.reportResult = reportResult;
    }
}


