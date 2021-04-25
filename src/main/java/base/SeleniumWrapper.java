package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import design.Browser;
import design.Element;

public class SeleniumWrapper implements Browser, Element {
	
	public static RemoteWebDriver dr;
	public static WebDriverWait wt;
	public static int uiCel, uiFar;

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
			return dr.findElement(By.id(loc));
		case "xpath":
			return dr.findElement(By.xpath(loc));
		case "linktxt":
			return dr.findElement(By.linkText(loc));
		}
		return null;
	}

	public String getText(WebElement wb) {
		return wb.getText().trim();
	}

	public void launchBrowser(String browser, String path) {
		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", path);
			dr = new ChromeDriver();
		}
		
	}

	public void maximize() {
		dr.manage().window().maximize();
		
	}

	public void get(String url) {
		dr.get(url);
		
	}

	public void closeBrowser() {
		dr.close();
		
	}

}
