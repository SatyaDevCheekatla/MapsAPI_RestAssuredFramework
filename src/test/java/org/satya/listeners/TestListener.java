package org.satya.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.satya.utils.ReportManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    public static ExtentReports extent = ReportManager.getReportInstance();
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();


    public void onTestStart(ITestResult result) {

    }

    public void onTestSuccess(ITestResult result) {

    }

    public void onTestFailure(ITestResult result) {

    }

    public void onFinish(ITestContext context) {
        // Final flush is already done in Hooks.afterMethod()
        extent.flush();
    }
}


