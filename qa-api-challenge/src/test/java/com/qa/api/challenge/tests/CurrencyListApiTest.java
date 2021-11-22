package com.qa.api.challenge.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.api.challenge.apputils.ApiResponseUtil;
import com.qa.api.challenge.models.CurrencyInfo;
import com.qa.api.challenge.report.ReportLogger;
import com.qa.api.challenge.utils.ConfigUtil;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j;

@Log4j
public class CurrencyListApiTest {
	
	String endpoint;
	
	@BeforeClass
	public void setUp() {
		try {
			endpoint = ConfigUtil.getInstance().getConfigValue("url.currencylist");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	@Test(priority = 9)
	public void test_currencylist_api_response_is_200_Ok() throws Exception {
		Response response = ApiResponseUtil.getResponseForGETRequest(endpoint);
		
		log.info("Request url: " + endpoint);
		log.info("Response: "+response.body().prettyPrint());
		ReportLogger.info("Request url: " + endpoint);

		assertEquals(response.getStatusCode(), 200);
		ReportLogger.info("Response code validated successfully");
	}
	
	@Test(priority = 10)
	public void test_currencylist_api_response_headers() throws Exception {
		Response response = ApiResponseUtil.getResponseForGETRequest(endpoint);
		
		log.info("Request url: " + endpoint);
		log.info("Response: "+response.body().prettyPrint());
		ReportLogger.info("Request url: " + endpoint);

		assertEquals(response.getStatusCode(), 200);
		assertEquals(response.getHeader("Content-Type"), "application/json");
		assertEquals(response.getHeader("Content-Encoding"), "gzip");
		assertEquals(response.getHeader("Cache-Control"), "no-cache, private");
		ReportLogger.info("Response headers validated successfully");
	}
	
	@Test(priority = 11)
	public void test_currencylist_api_returns_valid_response() throws Exception {
		Response response = ApiResponseUtil.getResponseForGETRequest(endpoint);
		
		log.info("Request url: " + endpoint);
		log.info("Response: "+response.body().prettyPrint());
		
		ReportLogger.info("Request url: " + endpoint);
		assertEquals(response.getStatusCode(), 200);
		ReportLogger.info("Response code validated successfully");
		
		JsonPath path = response.jsonPath();
		assertEquals(3, path.getMap("$").size());
		ReportLogger.info("root node count validated successfully");
		
		assertTrue(path.getMap("$").keySet().contains("base"), "'Base' node key is not available in response");
		assertTrue(path.getMap("$").keySet().contains("equivalent"), "'equivalent' node key is not available in response");
		assertTrue(path.getMap("$").keySet().contains("failed"), "'failed' node key is not available in response");
		ReportLogger.info("Root node keys validated successfully");
	}
	
	@Test(priority = 12)
	public void test_currencylist_api_base_node() throws Exception {
		Response response = ApiResponseUtil.getResponseForGETRequest(endpoint);
		
		log.info("Request url: " + endpoint);
		log.info("Response: "+response.body().prettyPrint());
		
		ReportLogger.info("Request url: " + endpoint);
		assertEquals(response.getStatusCode(), 200);
		ReportLogger.info("Response code validated successfully");
		
		JsonPath path = response.jsonPath();
		CurrencyInfo base = path.getObject("base", CurrencyInfo.class);
		assertEquals(base.symbol, "SAR");
		assertEquals(base.name, "Saudi Riyal");
		assertEquals(base.name_ar, "ريال سعودي");
		assertEquals(base.symbol_native, "ر.س.‏");
		assertEquals(base.decimal_digits, 2);
		assertEquals(base.rounding, 0);
		assertEquals(base.code, "SAR");
		assertEquals(base.name_plural, "Saudi riyals");
		ReportLogger.info("Response details of 'base' node validated successfully");
	}
	
	@Test(priority = 13)
	public void test_currencylist_api_USDollar_equivalent() throws Exception {
		Response response = ApiResponseUtil.getResponseForGETRequest(endpoint);
		
		log.info("Request url: " + endpoint);
		log.info("Response: "+response.body().prettyPrint());
		
		ReportLogger.info("Request url: " + endpoint);
		assertEquals(response.getStatusCode(), 200);
		ReportLogger.info("Response code validated successfully");
		
		JsonPath path = response.jsonPath();
		
		List<CurrencyInfo> list = path.getList("equivalent", CurrencyInfo.class);
		for(CurrencyInfo info: list) {
			if("US Dollar".equals(info.name)) {
				assertEquals(info.symbol, "$");
				assertEquals(info.name, "US Dollar");
				assertEquals(info.name_ar, "دولار أمريكي");
				assertEquals(info.symbol_native, "$");
				assertEquals(info.decimal_digits, 2);
				assertEquals(info.rounding, 0);
				assertEquals(info.code, "USD");
				assertEquals(info.name_plural, "US dollars");
				break;
			}
		}
		
		ReportLogger.info("US Dollar equivalent validated successfully");
	}
	
}
