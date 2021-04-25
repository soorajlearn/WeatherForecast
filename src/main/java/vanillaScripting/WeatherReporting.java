package vanillaScripting;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;

public class WeatherReporting {
	
	int uiCel, uiFar;
	String apiCel, apiFar;
	String[] unit = { "metric", "imperial" };

	@Test
	public void runTestCase() throws InterruptedException {
		new WeatherReporting().UICase().APICase().comparator();
	}

	public WeatherReporting UICase() throws InterruptedException {
		
		/*WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();*/
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver dr = new ChromeDriver();
		dr.get("https://www.ndtv.com/");
		dr.manage().window().maximize();
		dr.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverWait wt = new WebDriverWait(dr, 20);

		dr.findElementById("h_sub_menu").click();
		dr.findElementByLinkText("WEATHER").click();
		wt.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
		dr.findElementById("searchBox").sendKeys("mangalore");
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.id("Mangalore"))).click();
		WebElement city = wt.until(ExpectedConditions
				.visibilityOf(dr.findElement(By.xpath("//div[@class='cityText' and text()='Mangalore']"))));
		city.click();
		String uiCelsius = dr.findElementByXPath("//b[starts-with(text(),'Temp in Degrees')]").getText().replaceAll("\\D+", "");
		System.out.println(uiCelsius);
		uiCel = Integer.parseInt(uiCelsius);
		uiFar = Integer.parseInt(dr.findElementByXPath("//b[starts-with(text(),'Temp in Fahrenheit')]").getText().replaceAll("\\D+", ""));
		Thread.sleep(5000);
		dr.close();
		return this;
	}

	public WeatherReporting APICase() {
		RestAssured.baseURI = "https://api.openweathermap.org/data/2.5";

		for (int i = 0; i < 2; i++) {
			Object ob = RestAssured
					    .given()
					    .queryParam("q", "mangalore")
					    .queryParam("appid","7fe67bf08c80ded756e598d6f8fedaea")
					    .queryParam("units", unit[i])
					    .get("weather")
					    .jsonPath()
					    .get("main.temp");
			
			unit[i] = ob.toString();
		}
		System.out.println(Arrays.toString(unit));
		return this;
	}

	public WeatherReporting comparator() {
		int cel = (int) (Math.round(Float.parseFloat(unit[0])));
		if (Math.abs(uiCel - cel) < 3)
			System.out.println("Pass. " + uiCel + "-" + cel + "->" + (Math.abs(uiCel - cel)));
		else
			System.out.println("Fail. " + uiCel + "-" + cel + "->" + (Math.abs(uiCel - cel)));

		int far = (int) (Math.round(Float.parseFloat(unit[1])));
		if (Math.abs(uiFar - far) < 3)
			System.out.println("Pass." + uiFar + "-" + far + "->" + (Math.abs(uiFar - far)));
		else
			System.out.println("Fail." + uiFar + "-" + far + "->" + (Math.abs(uiFar - far)));
		
		return this;
	}

}
