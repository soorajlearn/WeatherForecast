package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.SeleniumWrapper;

public class WeatherPage extends SeleniumWrapper {
	
	String city;

	public WeatherPage searchCity(String city) {
		this.city = city;
		wt.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
		WebElement search = findElement("id", "searchBox");
		sendKeys(search, city);
		return this;
	}

	public WeatherPage selectCity() {
		WebElement searchCity = wt.until(ExpectedConditions.visibilityOf(findElement("id", city)));
		click(searchCity);
		return this;
	}

	public WeatherPage expandCityDetail() {
		WebElement cty = wt.until(ExpectedConditions
				.visibilityOf(findElement("xpath", "//div[@class='cityText' and text()='" + city + "']")));
		click(cty);
		return this;
	}

	public WeatherPage getTemp() throws InterruptedException {
		Thread.sleep(1000);
		WebElement deg = findElement("xpath", "//b[starts-with(text(),'Temp in Degrees')]");
		uiCel = Integer.parseInt(getText(deg).replaceAll("\\D+", ""));

		WebElement far = findElement("xpath", "//b[starts-with(text(),'Temp in Fahrenheit')]");
		uiFar = Integer.parseInt(getText(far).replaceAll("\\D+", ""));
		
		return this;
	}

}
