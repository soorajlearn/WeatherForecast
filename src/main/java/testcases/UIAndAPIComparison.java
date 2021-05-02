package testcases;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import annotations.Annotations;
import base.RestAssuredWrapper;
import io.restassured.response.Response;
import pages.HomePage;

public class UIAndAPIComparison extends Annotations{
	
	
	@Test(dataProvider = "data")
	public void TC003(String city, String units, String tolerance) throws InterruptedException {
		
		
		
		Map<String, String> map = new HashMap<>();
		map.put("q", city);
		map.put("appid", RestAssuredWrapper.apiKey);
		map.put("units", units);
		
		new HomePage().goToWeather().searchCity(city).selectCity().expandCityDetail().getTemp();
		
		Response resp = RestAssuredWrapper.getWithQueryParams(map, RestAssuredWrapper.resources);
		
		String temp  = RestAssuredWrapper.getContentWithKey(resp, "main.temp");
		
		if(units.equals("metric")){
			Assert.assertTrue(Math.abs((uiCel)-Float.parseFloat(temp))<Integer.parseInt(tolerance),"Actual UI output: " + uiCel + " Actual API output: " + temp  );				
			//Assert.assertEquals(Integer.toString(uiCel), temp);
		}else if(units.equals("imperial")){
			Assert.assertTrue(Math.abs((uiFar)-Float.parseFloat(temp))<Integer.parseInt(tolerance),"Actual UI output: " + uiFar + " Actual API output: " + temp );
			//Assert.assertEquals(Integer.toString(uiFar), temp);
		}
	}

}
