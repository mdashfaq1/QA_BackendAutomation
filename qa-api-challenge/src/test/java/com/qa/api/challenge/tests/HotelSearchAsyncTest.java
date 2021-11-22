package com.qa.api.challenge.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.json.simple.JSONObject;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.api.challenge.apputils.ApiResponseUtil;
import com.qa.api.challenge.apputils.HotelSearchSyncUtil;
import com.qa.api.challenge.models.RoomInfo;
import com.qa.api.challenge.report.ReportLogger;
import com.qa.api.challenge.utils.ConfigUtil;

import io.restassured.response.Response;
import lombok.extern.log4j.Log4j;

@Log4j
public class HotelSearchAsyncTest extends HotelSearchSyncUtil {
	
	String endpoint;
	
	@BeforeClass
	public void setUp() {
		try {
			endpoint = ConfigUtil.getInstance().getConfigValue("url.hotels.searchasync");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	@Test(priority = 1)
	public void test_search_async_with_placeId() throws Exception {
		String placeId = getPlaceIdFromAutocompleteApi("Bangalore");
		
		if(Objects.isNull(placeId)) {
			throw new SkipException("Unable to get the PlaceId for searched location");
		}
		
		ReportLogger.info("Request url: " + endpoint);
		List<RoomInfo> rooms = new ArrayList<RoomInfo>();
		RoomInfo room = new RoomInfo();
		room.setAdultsCount(2);
		room.setKidsAges(Arrays.asList(2));
		rooms.add(room);
		JSONObject payload = prepaprePayloadForHotelSearchAsync(rooms, placeId, null);
		
		ReportLogger.info("Request Payload:");
		ReportLogger.infoWithJson(payload.toJSONString());
		
		Response searchResponse = ApiResponseUtil.getResponseForPostRequest(true, endpoint, payload);
		log.info("searchResponse: "+ searchResponse.prettyPrint());
		
		assertEquals(searchResponse.getStatusCode(), 200);
		ReportLogger.info("Search Async response code is 200 OK");
		
		assertTrue(searchResponse.asString().contains("\"sId\""), "Response doesn't contain key \"sId\"");
		ReportLogger.info("Search Async response key validation is successful");
	}
	
	@Test(priority = 2)
	public void test_search_async_with_query() throws Exception {
		String hotelName = getHotelNameFromAutocompleteApi("Bangalore");
		
		if(Objects.isNull(hotelName)) {
			throw new SkipException("Unable to get the PlaceId for searched location");
		}
		
		ReportLogger.info("Request url: " + endpoint);
		List<RoomInfo> rooms = new ArrayList<RoomInfo>();
		RoomInfo room = new RoomInfo();
		room.setAdultsCount(2);
		room.setKidsAges(Arrays.asList(2));
		rooms.add(room);
		JSONObject payload = prepaprePayloadForHotelSearchAsync(rooms, null, hotelName);
		
		ReportLogger.info("Request Payload:");
		ReportLogger.infoWithJson(payload.toJSONString());
		
		Response searchResponse = ApiResponseUtil.getResponseForPostRequest(true, endpoint, payload);
		log.info("searchResponse: "+ searchResponse.prettyPrint());
		
		assertEquals(searchResponse.getStatusCode(), 200);
		ReportLogger.info("Search Async response code is 200 OK");
		
		assertTrue(searchResponse.asString().contains("\"sId\""), "Response doesn't contain key \"sId\"");
		ReportLogger.info("Search Async response key validation is successful");
	}

	@Test(priority = 3)
	public void test_search_async_with_placeId_withMultipleRooms() throws Exception {
		String placeId = getPlaceIdFromAutocompleteApi("Bangalore");
		
		if(Objects.isNull(placeId)) {
			throw new SkipException("Unable to get the PlaceId for searched location");
		}
		
		ReportLogger.info("Request url: " + endpoint);
		List<RoomInfo> rooms = new ArrayList<RoomInfo>();
		RoomInfo room = new RoomInfo();
		room.setAdultsCount(2);
		room.setKidsAges(Arrays.asList());
		rooms.add(room);
		
		RoomInfo room1 = new RoomInfo();
		room1.setAdultsCount(2);
		room1.setKidsAges(Arrays.asList());
		rooms.add(room1);
		
		RoomInfo room2 = new RoomInfo();
		room2.setAdultsCount(2);
		room2.setKidsAges(Arrays.asList());
		rooms.add(room2);
		JSONObject payload = prepaprePayloadForHotelSearchAsync(rooms, placeId, null);
		
		ReportLogger.info("Request Payload:");
		ReportLogger.infoWithJson(payload.toJSONString());
		
		Response searchResponse = ApiResponseUtil.getResponseForPostRequest(true, endpoint, payload);
		log.info("searchResponse: "+ searchResponse.prettyPrint());
		
		assertEquals(searchResponse.getStatusCode(), 200);
		ReportLogger.info("Search Async response code is 200 OK");
		
		assertTrue(searchResponse.asString().contains("\"sId\""), "Response doesn't contain key \"sId\"");
		ReportLogger.info("Search Async response key validation is successful");
	}
	
	@Test(priority = 4)
	public void test_search_async_with_placeId_withMax_8_Adult_0_Kids() throws Exception {
		String placeId = getPlaceIdFromAutocompleteApi("Bangalore");
		
		if(Objects.isNull(placeId)) {
			throw new SkipException("Unable to get the PlaceId for searched location");
		}
		
		ReportLogger.info("Request url: " + endpoint);
		List<RoomInfo> rooms = new ArrayList<RoomInfo>();
		RoomInfo room = new RoomInfo();
		room.setAdultsCount(8);
		room.setKidsAges(Arrays.asList());
		rooms.add(room);
		
		JSONObject payload = prepaprePayloadForHotelSearchAsync(rooms, placeId, null);
		
		ReportLogger.info("Request Payload:");
		ReportLogger.infoWithJson(payload.toJSONString());
		
		Response searchResponse = ApiResponseUtil.getResponseForPostRequest(true, endpoint, payload);
		log.info("searchResponse: "+ searchResponse.prettyPrint());
		
		assertEquals(searchResponse.getStatusCode(), 200);
		ReportLogger.info("Search Async response code is 200 OK");
		
		assertTrue(searchResponse.asString().contains("\"sId\""), "Response doesn't contain key \"sId\"");
		ReportLogger.info("Search Async response key validation is successful");
	}
	
	@Test(priority = 5)
	public void test_search_async_with_placeId_with_1_Adult_Max_7_Kids() throws Exception {
		String placeId = getPlaceIdFromAutocompleteApi("Bangalore");
		
		if(Objects.isNull(placeId)) {
			throw new SkipException("Unable to get the PlaceId for searched location");
		}
		
		ReportLogger.info("Request url: " + endpoint);
		List<RoomInfo> rooms = new ArrayList<RoomInfo>();
		RoomInfo room = new RoomInfo();
		room.setAdultsCount(1);
		room.setKidsAges(Arrays.asList(2, 4, 5, 1, 3, 6, 10));
		rooms.add(room);
		
		JSONObject payload = prepaprePayloadForHotelSearchAsync(rooms, placeId, null);
		
		ReportLogger.info("Request Payload:");
		ReportLogger.infoWithJson(payload.toJSONString());
		
		Response searchResponse = ApiResponseUtil.getResponseForPostRequest(true, endpoint, payload);
		log.info("searchResponse: "+ searchResponse.prettyPrint());
		
		assertEquals(searchResponse.getStatusCode(), 200);
		ReportLogger.info("Search Async response code is 200 OK");
		
		assertTrue(searchResponse.asString().contains("\"sId\""), "Response doesn't contain key \"sId\"");
		ReportLogger.info("Search Async response key validation is successful");
	}
	
	@Test(priority = 6)
	public void test_search_async_returns_error_when_placeId_blank() throws Exception {
		ReportLogger.info("Request url: " + endpoint);
		List<RoomInfo> rooms = new ArrayList<RoomInfo>();
		RoomInfo room = new RoomInfo();
		room.setAdultsCount(1);
		room.setKidsAges(Arrays.asList());
		rooms.add(room);
		
		JSONObject payload = prepaprePayloadForHotelSearchAsync(rooms, "", null);
		
		ReportLogger.info("Request Payload:");
		ReportLogger.infoWithJson(payload.toJSONString());
		
		Response searchResponse = ApiResponseUtil.getResponseForPostRequest(true, endpoint, payload);
		log.info("searchResponse: "+ searchResponse.prettyPrint());
		
		assertEquals(searchResponse.getStatusCode(), 400);
		ReportLogger.info("Search Async response code is 400 Bad Request");
	}
	
	@Test(priority = 7)
	public void test_search_async_returns_error_when_AdultCount_is_zero() throws Exception {
		ReportLogger.info("Request url: " + endpoint);
		List<RoomInfo> rooms = new ArrayList<RoomInfo>();
		RoomInfo room = new RoomInfo();
		room.setAdultsCount(0);
		room.setKidsAges(Arrays.asList());
		rooms.add(room);
		
		JSONObject payload = prepaprePayloadForHotelSearchAsync(rooms, "sampleplaceId", null);
		
		ReportLogger.info("Request Payload:");
		ReportLogger.infoWithJson(payload.toJSONString());
		
		Response searchResponse = ApiResponseUtil.getResponseForPostRequest(true, endpoint, payload);
		log.info("searchResponse: "+ searchResponse.prettyPrint());
		
		assertEquals(searchResponse.getStatusCode(), 400);
		ReportLogger.info("Search Async response code is 400 Bad Request");
	}
	
	@Test(priority = 8)
	public void test_search_async_returns_error_when_placeId_and_query_both_not_present() throws Exception {
		ReportLogger.info("Request url: " + endpoint);
		List<RoomInfo> rooms = new ArrayList<RoomInfo>();
		RoomInfo room = new RoomInfo();
		room.setAdultsCount(0);
		room.setKidsAges(Arrays.asList());
		rooms.add(room);
		
		JSONObject payload = prepaprePayloadForHotelSearchAsync(rooms, null, null);
		
		ReportLogger.info("Request Payload:");
		ReportLogger.infoWithJson(payload.toJSONString());
		
		Response searchResponse = ApiResponseUtil.getResponseForPostRequest(true, endpoint, payload);
		log.info("searchResponse: "+ searchResponse.prettyPrint());
		
		assertEquals(searchResponse.getStatusCode(), 400);
		ReportLogger.info("Search Async response code is 400 Bad Request");
	}
	
}
