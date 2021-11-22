package com.qa.api.challenge.report;

import java.util.Objects;

import com.aventstack.extentreports.ExtentTest;

public class ReportManager {
	
	private ReportManager() {}
	
	private static ThreadLocal<ExtentTest> testExecutor = new ThreadLocal<>() ;
	
	static ExtentTest getTestExecutor() {
		return testExecutor.get();
	}
	
	static void setTestExecutor(ExtentTest extentTest) {
		if(Objects.nonNull(testExecutor)) {
			testExecutor.set(extentTest);
		}
	}
	
	static void unload() {
		testExecutor.remove();
	}
}
