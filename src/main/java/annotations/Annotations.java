package annotations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import base.SeleniumWrapper;
import utils.DataInputProvider;
import utils.ReadConfig;

public class Annotations extends SeleniumWrapper {
	
	@DataProvider(parallel = false)
	public Object[] data() throws FileNotFoundException, IOException {
		return DataInputProvider.getSheet("TC001");
		
	}
	
	@BeforeTest
	public void setConfigProperties() throws FileNotFoundException, IOException{
		ReadConfig.readProperty();
	}

	@BeforeMethod
	public void before() {
		System.out.println(browser);
		launchBrowser(browser, "./drivers/chromedriver.exe");
		maximize();
		dr.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wt = new WebDriverWait(dr, 20);
		get(url);
	}

	@AfterMethod
	public void after() {
		//closeBrowser();
	}

}
