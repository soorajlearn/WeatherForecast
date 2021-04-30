package testcases;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import annotations.Annotations;
import base.RestAssuredWrapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class APITestCase extends Annotations{

	@Test(dataProvider="data")
	public void TC002(String city, String units) {
		


		Map<String, String> map = new HashMap<>();
		map.put("q", city);
		map.put("appid", "7fe67bf08c80ded756e598d6f8fedaea");
		map.put("units", units);
		
		Response resp = RestAssuredWrapper.getWithQueryParams(map, RestAssuredWrapper.resources);
		
		String temp  = RestAssuredWrapper.getContentWithKey(resp, "main.temp");
		
		System.out.println(temp);
	}

}
