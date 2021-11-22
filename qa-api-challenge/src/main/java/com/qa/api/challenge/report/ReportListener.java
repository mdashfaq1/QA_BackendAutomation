package com.qa.api.challenge.report;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import lombok.extern.log4j.Log4j;

@Log4j
public class ReportListener implements ITestListener, ISuiteListener {

	@Override
	public void onStart(ISuite suite) {
		ReportGenerator.initReport();
		log.info("Test suite started");
	}

	@Override
	public void onFinish(ISuite suite) {
		ReportGenerator.flushReport();
		log.info("Test suite completed");
	} 

	@Override
	public void onTestStart(ITestResult result) {
		log.info(result.getMethod().getMethodName()+" -> Test case execution Started");
		ReportGenerator.initTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		log.info(result.getMethod().getMethodName()+" -> Test case Passed");
		ReportManager.getTestExecutor().pass(result.getMethod().getMethodName() + " is Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		log.info(result.getMethod().getMethodName()+" -> Test case Failed. "+ result.getThrowable().getMessage());
		ReportManager.getTestExecutor().fail(result.getMethod().getMethodName() + " is Failed. "+result.getThrowable().getMessage());	
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		log.info(result.getMethod().getMethodName()+" -> Test case Skipped. " + result.getThrowable().getMessage());
		ReportManager.getTestExecutor().skip(result.getMethod().getMethodName() + " is Skipped."+result.getThrowable().getMessage());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
