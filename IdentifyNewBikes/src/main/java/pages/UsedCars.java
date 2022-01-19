/*Author Name: Swathika D, Suryaa kannan
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
package pages;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utils.ReadConfigProperties;
import utils.WriteExcel;

//Creating a class to extract all the popular used car models in chennai
	 public class UsedCars 
	 {
		 
	 static WebDriver driver;
	 static ReadConfigProperties rcp;
	 static Properties prop;
	 static String[] pModules = new String[30];
	 static String path=System.getProperty("user.dir");
	 static ReadConfigProperties read = new ReadConfigProperties();
	
	 //Creating a constructor to invoke driver and to read config properties file 
	 public UsedCars(WebDriver driver)
	 {
		UsedCars.driver = driver;
		rcp = new ReadConfigProperties();
		prop = rcp.inputSetup();
	}
	 
	//creating a method to use mouse hover to click Chennai under used cars section and extracting popular models in both console and Excel Sheet 
	public static void usedCarsPage() throws Exception 
	{
		String url=read.getURL();
		driver.navigate().to(url);
		WebElement usedCars=driver.findElement(By.xpath(prop.getProperty("usedcar_xpath")));
		
		System.out.println("usedCarsPage");
		//creating Action class to use mouse hover concept 
		Actions move=new Actions(driver);
		move.moveToElement(usedCars).perform();
		
		//To move mouse using mouse hover until it find chennai
		driver.findElement(By.partialLinkText("Chennai")).click();
		
		//WebElement chennai = driver.findElement(By.xpath(prop.getProperty("clickchennai_xpath")));
		//move.click(chennai).build().perform();
		//System.out.println("usedCarsPage ended");
		TimeUnit.SECONDS.sleep(5);
	}
	
	//Creating a method to Verify Used cars in chennai page title
	public static void verifyUsedCarsPageTitle()
	{
		//String expectedTitle="Used Cars in Chennai - Certified Second Hand Cars for Sale @ ZigWheels";
		//Checking Expected title with actual title
	driver.getTitle();
	
		System.out.println("Used cars in Chennai Page Title is Verified");
	}
	public static void usedCars()throws Exception 
	{
		//using java executor for scrolling
		JavascriptExecutor js = (JavascriptExecutor)driver;
		//scrolling the page
		js.executeScript("window.scrollBy(0,450)","");
		
		//Storing all popular models in chennai into a list
		List<WebElement> company_list=driver.findElements(By.xpath("//h3[normalize-space(text())='Popular Models']/parent::div//label"));
		int count =company_list.size();
		System.out.println("================================================");
		System.out.println("Used cars in Chennai : Popular models in a List");
		System.out.println("================================================");
		
		//printing popular models on console
		for(int i=0;i<count;i++)
		{
			//printing popular models on console
			System.out.println(company_list.get(i).getText());
			System.out.println("================================================");
			//Storing all the models in a Array
			pModules[i] = company_list.get(i).getText();
		}
		 
		//invoking the Write excel data1 method from write excel class
		 WriteExcel.WriteExcelData1(path+"\\OutputExcelFiles\\UsedCarsListOutput.xlsx", pModules);
	}
	
	//creating a method to navigate to homepage
	public static void navigatingToHomepage()
	{
	//Redirecting to home page by clicking on zigwheels icon 
	driver.findElement(By.xpath(prop.getProperty("navigatehomepage_xpath"))).click();
	}
	

}