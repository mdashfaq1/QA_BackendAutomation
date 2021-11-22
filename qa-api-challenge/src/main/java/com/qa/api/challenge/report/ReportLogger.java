package com.qa.api.challenge.report;

import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public final class ReportLogger {

	private ReportLogger() {}
	
	public static void pass(String message) {
		ReportManager.getTestExecutor().pass(message);
	}
	
	public static void fail(String message) {
		ReportManager.getTestExecutor().fail(message);
	}
	
	public static void skip(String message) {
		ReportManager.getTestExecutor().skip(message);
	}
	
	public static void info(String message) {
		ReportManager.getTestExecutor().info(message);
	}
	
	public static void infoWithJson(String message) {
		ReportManager.getTestExecutor().info(MarkupHelper.createCodeBlock(message, CodeLanguage.JSON));
	}
}
