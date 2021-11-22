package com.qa.api.challenge.apputils;

import com.qa.api.challenge.utils.ConfigUtil;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ApiRequestUtil {
	
	public static RequestSpecification getRequestSpec() throws Exception {
		RequestSpecBuilder requestSpec = new RequestSpecBuilder();
		requestSpec.setContentType(ContentType.JSON)
		.setBaseUri(ConfigUtil.getInstance().getConfigValue("url.host"));
		
		return requestSpec.build();
	}
	
	public static RequestSpecification getRequestSpecWithToken() throws Exception {
		RequestSpecBuilder requestSpec = new RequestSpecBuilder();
		requestSpec.setContentType(ContentType.JSON)
		.addHeader("token", ConfigUtil.getInstance().getConfigValue("token"))
		.setBaseUri(ConfigUtil.getInstance().getConfigValue("url.host"));
		
		return requestSpec.build();
	}
	

}
