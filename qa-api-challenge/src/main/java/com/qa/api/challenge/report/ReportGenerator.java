package com.qa.api.challenge.report;

import java.util.Objects;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public final class ReportGenerator {

	private ReportGenerator() {}
	
	private static ExtentReports reports;
	
	public static void initReport() {
		if(Objects.isNull(reports)) {
			reports = new ExtentReports();
			ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/index.html"); 
			reports.attachReporter(spark);
			spark.config().setTheme(Theme.STANDARD);
			spark.config().setDocumentTitle("Automation Report");
			spark.config().setReportName("QA API Automation");
		}
	}
	
	public static void flushReport() {
		if(Objects.nonNull(reports)) {
			reports.flush();
		}
		ReportManager.unload();
	}
	
	public static void initTest(String testCaseName) {
		ReportManager.setTestExecutor(reports.createTest(testCaseName));
	}
	
}
