/*Author Name: Swathika D
 * Module Creation Date:03/01/2022
 * Module Modification Date:18/01/2022
 * Browsers Used:Chrome ,Opera and MS Edge
 * Browser Versions:Chrome(Version Version 95.0.4638.69 (Official Build) (64-bit)) and
 * Opera(Version 90.0.4430.85 (64-bit))
 * MS Edge Version  89.0.774.54(Official build) (64-bit)
 * TestNG version 7.4.0
 * Apache Poi version:poi-bin-5.1.0-20211024
 * Jenkins version:Jenkins 
 */
package utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


//Creating a class to Read data from Config properties file
public class ReadConfigProperties
{
	FileInputStream fis = null;
	Properties prop = null;

	//Setting properties file for input
	public Properties inputSetup()
	{
		//Assiging config.properties file location
		String filelocation = System.getProperty("user.dir") + "\\ObjectRepository\\config.properties";
		File file = new File(filelocation);

		try {
			fis = new FileInputStream(file);
			prop = new Properties();
			prop.load(fis);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	//to return the URL from properties file
		public String getURL()
		{
			inputSetup();
			String URL = prop.getProperty("URL");
			return URL;
		}
		
		//To return the browser select value from config properties file
		public String getBrowserSelect() 
		{
			inputSetup();
			String k = prop.getProperty("browserselect");
			return k;
		}
		
		//to return the ChromeDriver location from properties file
		public String getChromeDriverLocation()
		{
			inputSetup();
			String location = prop.getProperty("chrome");
			return location;
		}
		//to return the operaDriver location from properties file
		public String getOperaLocation()
		{
			inputSetup();
			String location = prop.getProperty("opera");
			return location;
		}
		//to return the edgeDriver location from properties file
		public String getEdgeDriverLocation() 
		{
			inputSetup();
			String location = prop.getProperty("edge");
			return location;
		}

}
