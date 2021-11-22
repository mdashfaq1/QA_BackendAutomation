package com.qa.api.challenge.apputils;

import static org.testng.Assert.assertEquals;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.qa.api.challenge.models.RoomInfo;
import com.qa.api.challenge.report.ReportLogger;
import com.qa.api.challenge.utils.ConfigUtil;
import com.qa.api.challenge.utils.Utility;

import io.restassured.response.Response;
import lombok.extern.log4j.Log4j;

@Log4j
public class HotelSearchSyncUtil {

	
	public String getPlaceIdFromAutocompleteApi(String placeSearchString) throws Exception, ParseException {
		ReportLogger.info("Get Response from Automcomplete API");
		String endpointAutocomplete = ConfigUtil.getInstance().getConfigValue("url.automcomplete");
		String placeId = null;
		Map<String, String> queryParam = new HashMap<String, String>();
		queryParam.put("query", placeSearchString);
		Response response = ApiResponseUtil.getResponseForGETRequestWithQueryParams(true, endpointAutocomplete, queryParam);
		
		log.info("Automcomplete Request url: " + endpointAutocomplete);
		log.info("Automcomplete API Response: "+response.body().prettyPrint());

		assertEquals(response.getStatusCode(), 200);
		ReportLogger.info("Automcomplete response code is 200 OK");
		
		JSONObject parent = (JSONObject) new JSONParser().parse(response.asString());
		JSONArray locations = (JSONArray) parent.get("locations");
		for(int i = 0; i<locations.size();i++  ) {
			JSONObject obj = (JSONObject) locations.get(i);
			if(placeSearchString.equals(obj.get("name").toString())) {
				placeId = obj.get("placeId").toString();
				ReportLogger.info("Found placeId: " + placeId);
				break;
			}
		}
		return placeId;
	}
	
	public String getHotelNameFromAutocompleteApi(String placeSearchString) throws Exception, ParseException {
		ReportLogger.info("Get Response from Automcomplete API");
		String endpointAutocomplete = ConfigUtil.getInstance().getConfigValue("url.automcomplete");
		String name = null;
		Map<String, String> queryParam = new HashMap<String, String>();
		queryParam.put("query", placeSearchString);
		Response response = ApiResponseUtil.getResponseForGETRequestWithQueryParams(true, endpointAutocomplete, queryParam);
		
		log.info("Automcomplete Request url: " + endpointAutocomplete);
		log.info("Automcomplete API Response: "+response.body().prettyPrint());

		assertEquals(response.getStatusCode(), 200);
		ReportLogger.info("Automcomplete response code is 200 OK");
		
		name = response.jsonPath().getString("hotels.name[0]");
		log.info("First Hotel name: "+ name);
		
		ReportLogger.info("Hotel name: " + name);
		return name;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject prepaprePayloadForHotelSearchAsync(List<RoomInfo> rooms, String placeId, String query) throws Exception {
		JSONObject request = new JSONObject();
		request.put("checkIn", Utility.getDateFromCurrentDate(6));
		request.put("checkOut", Utility.getDateFromCurrentDate(8));
		if(Objects.nonNull(placeId))
			request.put("placeId", placeId);
		if(Objects.nonNull(query))
			request.put("query", URLEncoder.encode(query, "UTF-8"));
		
		JSONArray roomArray = new JSONArray();
		for(RoomInfo room: rooms) {
			JSONObject roomObj = new JSONObject();
			roomObj.put("adultsCount", room.adultsCount);
			JSONArray kidArray = new JSONArray();
			if(room.kidsAges.size() > 0) {
				for(Integer kid: room.kidsAges) {
					kidArray.add(kid);
				}
			}
			roomObj.put("kidsAges", kidArray);
			
			roomArray.add(roomObj);
		}
		request.put("roomsInfo", roomArray);
		
		log.info("Request Payload: "+request);
		return request;
	}
}
