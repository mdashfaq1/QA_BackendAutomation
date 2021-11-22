package com.qa.api.challenge.apputils;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.json.simple.JSONObject;

import io.restassured.response.Response;

public class ApiResponseUtil {
	
	public static Response getResponseForGETRequest(String endpoint) throws Exception {
		return given()
				.spec(ApiRequestUtil.getRequestSpec())
			.when()
				.get(endpoint)
			.then()
				.extract().response();
	}
	
	public static Response getResponseForGETRequestWithQueryParams(Boolean tokenRequired, String endpoint, Map<String, String> query) throws Exception {
		
		if(tokenRequired) {
			return given()
					.spec(ApiRequestUtil.getRequestSpecWithToken()
							.urlEncodingEnabled(true)
							.with()
							.queryParams(query))
				.when()
					.get(endpoint)
				.then()
					.extract().response();
		} else {
			return given()
				.spec(ApiRequestUtil.getRequestSpec()
						.urlEncodingEnabled(true)
						.with()
						.queryParams(query))
			.when()
				.get(endpoint)
			.then()
				.extract().response();
		}
	}
	
	public static Response getResponseForPostRequest(Boolean tokenRequired, String endpoint, JSONObject payload) throws Exception {
		
		if(tokenRequired) {
			return given()
				.spec(ApiRequestUtil.getRequestSpecWithToken().body(payload.toJSONString()))
			.when()
				.post(endpoint)
			.then()
				.extract().response();
		} else {
			return given()
					.spec(ApiRequestUtil.getRequestSpec().body(payload.toJSONString()))
				.when()
					.post(endpoint)
				.then()
					.extract().response();
		}
	}

}
