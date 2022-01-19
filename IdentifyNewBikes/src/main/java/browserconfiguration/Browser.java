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
package browserconfiguration;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

import utils.ReadConfigProperties;
//Creating a class to do Browser Configurations
public class Browser
{
	//Initializing objects to the below classes and interfaces
	 static WebDriver driver;
	 static String path; 
	 static String url;
	 static ReadConfigProperties read = new ReadConfigProperties();
	 
	//WebDriver Setup Selection Method
	 public static WebDriver setDriver() throws Exception
	 {
		    System.out.println("By default chrome browser will be launched....");
		    String k=read.getBrowserSelect();
			if(k.equalsIgnoreCase("chrome"))
				driver = startChromeBrowser();
			else if(k.equalsIgnoreCase("edge"))
				driver = startEdgeBrowser();
			else if(k.equalsIgnoreCase("opera"))
				driver = startOperaBrowser();
			else
			    System.out.println("invalid browser");
			
			driver.manage().window().maximize();
			url=read.getURL();
			driver.get(url);
			//driver.get("https://www.zigwheels.com/");

			timeManager();
			//driver.findElement(By.id("alternate-login-close")).click();
			
		    return driver;
	 }
	 
     //Initializing the CHROME Driver
	 public static WebDriver startChromeBrowser() throws Exception
	 {
		  path = read.getChromeDriverLocation();
		 //It will set the path for the chrome driver 
		 System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+path);
		 ChromeOptions chromeOptions = new ChromeOptions();
		 //It will disable the notifications in the Browser
		 chromeOptions.addArguments("--disable-notifications");
		 //It will disable the info bars in the browser
		 chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
		 driver = new ChromeDriver(chromeOptions);
	     return driver;
	}
	
	 //Initializing the FIREFOX Driver
	 public static WebDriver startOperaBrowser()
	 {
		path = read.getOperaLocation();
		System.setProperty("webdriver.opera.driver", System.getProperty("user.dir")+path);
		OperaOptions operaOptions = new OperaOptions();
		operaOptions.addArguments("--disable-notifications");
		driver = new OperaDriver(operaOptions);
		
	    return driver;
	  }
	  
	 //Initializing the MS EDGE Driver
	 public static WebDriver startEdgeBrowser()
	 {
		 path = read.getEdgeDriverLocation();
		 //It will set the path for the Edge driver path
		 System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+path);
		 EdgeOptions edgeOptions = new EdgeOptions();
		 //It will disable the infobars in the Browser
		 edgeOptions.setCapability("disable-infobars", false);
		 //It will disable the notifications in the Browser
		 edgeOptions.setCapability("--disable-notifications", true);
		 driver=new EdgeDriver(edgeOptions);
		
	     return driver;
	 }
	  //creating a method implicit timeout for proper execution of the application 
	  public static void timeManager()
	  {
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	  }
	
	 //Method to close the Browser
	 public static void closeBrowser()
	 {
		//Closing the Browser
	    driver.quit();
	 }
	
}
