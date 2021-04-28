package annotations;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import base.SeleniumWrapper;

public class Annotations extends SeleniumWrapper {
	
	@DataProvider(parallel = true)
	public Object[] data() {
		return new Object[] { "Kanpur"};
	}

	@BeforeMethod
	public void before() {
		launchBrowser("chrome", "./drivers/chromedriver.exe");
		maximize();
		dr.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wt = new WebDriverWait(dr, 20);
		get("https://www.ndtv.com/");
	}

	@AfterMethod
	public void after() {
		//closeBrowser();
	}

}
