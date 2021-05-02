package testcases;

import java.util.HashMap;
import java.util.Map;

import org.testng.Reporter;
import org.testng.annotations.Test;

import annotations.Annotations;
import base.RestAssuredWrapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class APITestCase extends Annotations{

	@Test(dataProvider="data")
	public void TC002(String city, String units, String tolerance) {
		


		Map<String, String> map = new HashMap<>();
		map.put("q", city);
		map.put("appid", RestAssuredWrapper.apiKey);
		map.put("units", units);
		
		Response resp = RestAssuredWrapper.getWithQueryParams(map, RestAssuredWrapper.resources);
		
		String temp  = RestAssuredWrapper.getContentWithKey(resp, "main.temp");
		
		if (units.equals("metric")) {
			Reporter.log("Temperature of " + city + " in " + units + " is " + temp);
		} else if (units.equals("imperial")) {
			Reporter.log("Temperature of " + city + " in " + units + " is " + temp);
		}
	}

}
