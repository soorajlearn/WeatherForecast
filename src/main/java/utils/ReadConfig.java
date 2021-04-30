package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import annotations.Annotations;
import base.RestAssuredWrapper;
import base.SeleniumWrapper;

public class ReadConfig {
	
	
	public static void readProperty() throws FileNotFoundException, IOException{
		
		Properties prop = new Properties();
		prop.load(new FileInputStream(new File("./resources/config/config.properties")));
		//Object[] data = new Object[4];
		SeleniumWrapper.url = prop.get("url").toString();
		SeleniumWrapper.browser = prop.get("browser").toString();
		RestAssuredWrapper.URI = prop.get("baseURI").toString();
		RestAssuredWrapper.resources = prop.get("resources").toString();
		
		
	}

}
