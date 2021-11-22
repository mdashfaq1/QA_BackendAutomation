package com.qa.api.challenge.tests;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;

import org.testng.annotations.Test;

import com.qa.api.challenge.apputils.ApiRequestUtil;
import com.qa.api.challenge.report.ReportLogger;
import com.qa.api.challenge.utils.ConfigUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CurrencyListApiSchemaTest {
	
	@Test(priority = 14)
	public void test_currencylist_api_response_jsonSchema() throws Exception {
		given()
			.spec(ApiRequestUtil.getRequestSpec())
		.when()
			.get(ConfigUtil.getInstance().getConfigValue("url.currencylist"))
		.then()
			.statusCode(200)
			.assertThat().body(JsonSchemaValidator.matchesJsonSchema(new FileInputStream("src/main/resources/currencyListSchema.json")));
		
		ReportLogger.info("CurrencyList API JsonSchema validated successfully");
	}

}
