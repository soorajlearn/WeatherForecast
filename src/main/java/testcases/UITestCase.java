package testcases;

import org.testng.annotations.Test;

import annotations.Annotations;
import pages.HomePage;

public class UITestCase extends Annotations {
	
	@Test(dataProvider = "data")
	public void TC001(String city) {
		new HomePage().goToWeather().searchCity(city).selectCity().expandCityDetail().getTemp();
	}

}
