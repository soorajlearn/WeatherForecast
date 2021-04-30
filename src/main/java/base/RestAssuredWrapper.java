package base;

import java.util.List;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestLogSpecification;
import io.restassured.specification.RequestSpecification;

public class RestAssuredWrapper {
	
	public static String URI, resources;
	
	

	private static ContentType getContentType() {
		return ContentType.JSON;
	}

	public static RequestSpecification setLogs() {
		RestAssured.baseURI = URI;
		RequestLogSpecification log = RestAssured.given().log();
		return log.all().contentType(getContentType());
	}

	public static Response get(String URL) {
		return setLogs().when().get(URL);
	}

	public static Response get() {
		return setLogs().get();
	}

	public static Response getWithQueryParams(Map<String, String> params, String URL) {
		return setLogs().queryParams(params).get(URL);
	}

	public static List<String> getContentsWithKey(Response response, String key) {
		if (response.getContentType().contains("json")) {
			JsonPath jsonPath = response.jsonPath();
			return jsonPath.getList(key);
		} else {
			return null;
		}
	}

	public static String getContentWithKey(Response response, String key) {
		if (response.getContentType().contains("json")) {
			JsonPath jsonPath = response.jsonPath();
			return jsonPath.get(key).toString();
		} else {
			return null;
		}
	}

}
