package pages;

import org.openqa.selenium.WebElement;

import base.SeleniumWrapper;

public class HomePage extends SeleniumWrapper {
	
	public HomePage expandMenu() {
		WebElement menu = findElement("id", "h_sub_menu");
		click(menu);
		return this;
	}
	
	public HomePage clickWeather() {
		WebElement weather = findElement("linktxt", "WEATHER");
		click(weather);
		return this;
	}
	
	public WeatherPage goToWeather() {
		expandMenu().clickWeather();
		return new WeatherPage();
	}

}
