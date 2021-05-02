package testcases;

import org.testng.Reporter;
import org.testng.annotations.Test;

import annotations.Annotations;
import pages.HomePage;

public class UITestCase extends Annotations {

	@Test(dataProvider = "data")
	public void TC001(String city, String units, String tolerance) throws InterruptedException {
		new HomePage().goToWeather().searchCity(city).selectCity().expandCityDetail().getTemp();

		if (units.equals("metric")) {
			Reporter.log("Temperature of " + city + " in " + units + " is " + uiCel);
		} else if (units.equals("imperial")) {
			Reporter.log("Temperature of " + city + " in " + units + " is " + uiFar);
		}
	}

}
