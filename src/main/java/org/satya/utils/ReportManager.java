package org.satya.utils;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportManager {

    private static ExtentReports extent;
    private static final String REPORT_PATH = "reports/extentReport.html";

    public static ExtentReports getReportInstance() {

        if (extent == null) {
            // Create reports directory if it doesn't exist
            java.io.File reportsDir = new java.io.File("reports");
            if (!reportsDir.exists()) {
                reportsDir.mkdirs();
            }

            ExtentSparkReporter reporter = new ExtentSparkReporter(REPORT_PATH);

            // Configure report settings
            reporter.config().setTheme(Theme.STANDARD);
            reporter.config().setReportName("API Test Report");
            reporter.config().setDocumentTitle("Extent Report - API Testing");
            reporter.config().setTimeStampFormat("yyyy-MM-dd HH:mm:ss");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Tester", "QA Team");
            extent.setSystemInfo("Environment", "Test");
            extent.setSystemInfo("Browser", "REST API");
        }

        return extent;
    }

    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}

