package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import design.Browser;
import design.Element;

public class SeleniumWrapper extends ThreadLocalImpl implements Browser, Element {
	
	public static RemoteWebDriver dr;
	public static WebDriverWait wt;
	public static int uiCel, uiFar;
	public static String url, browser;

	public void sendKeys(WebElement wb, String input) {
		wb.clear();
		wb.sendKeys(input);	
	}

	public void click(WebElement wb) {
		wb.click();
		
	}

	public WebElement findElement(String type, String loc) {
		switch (type) {
		case "id":
			return getDriver().findElement(By.id(loc));
		case "xpath":
			return getDriver().findElement(By.xpath(loc));
		case "linktxt":
			return getDriver().findElement(By.linkText(loc));
		}
		return null;
	}

	public String getText(WebElement wb) {
		return wb.getText().trim();
	}

	public void launchBrowser(String browser, String path) {
		switch (browser) {
		case "chrome":
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			System.setProperty("webdriver.chrome.driver", path);
			setDriver(new ChromeDriver(options));
		}
		
	}

	public void maximize() {
		getDriver().manage().window().maximize();
		
	}

	public void get(String url) {
		getDriver().get(url);
		
	}

	public void closeBrowser() {
		getDriver().close();
		
	}

}
