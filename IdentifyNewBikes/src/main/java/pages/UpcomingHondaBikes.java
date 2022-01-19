/*Author Name: Nagaraj Gowtham N, Vignesh Kumar
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utils.ReadConfigProperties;
import utils.WriteExcel;

//Creating a class for extracting upcoming hondabikes details
public class UpcomingHondaBikes
{
	WebDriver driver;
	ReadConfigProperties rcp;
	Properties prop;
	String[] bNames = new String[30];
	String[] bPrice = new String[30];
	String[] bLaunch = new String[30];
	int count=0;
	String path=System.getProperty("user.dir");
	
	//Creating a constructor to invoke driver and to read config properties file 
	public UpcomingHondaBikes(WebDriver driver) 
	{
	   this.driver=driver;
	   rcp = new ReadConfigProperties();
	   prop = rcp.inputSetup();
	   
	}
	
	// creating a method for verifying  UpcomingBikes title by using Assert statement
	public void verifyUpcomingBikesPageTitle()
	{
		//Checking Actual title with expected title
		Assert.assertEquals(driver.getTitle(), "Upcoming Honda Bikes in India 2022/23, See Price, Launch Date, Specs @ ZigWheels");
		System.out.println("Upcoming HondaBikes Page Title is verified");
	}

	//creating a method to click on the ViewMoreBikes web element by using JavascriptExecutor
	public void clickViewMoreBikes() throws Exception
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//It will find the view More Bikes element in the page
		WebElement Element = driver.findElement(By.xpath(prop.getProperty("viewmorebikes_xpath")));
		//It will scroll down the page until expected element is present 
		js.executeScript("arguments[0].scrollIntoView();", Element);
		//it will wait until expected element is found
		WebDriverWait wait=new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.elementToBeClickable(Element));
		//It will click on view more bikes after finding the element
		js.executeScript("arguments[0].click();", Element);
		TimeUnit.SECONDS.sleep(2);
		
	}
	
	//creating a method to display upcoming honda bikes in india and to print the bike details to print in both console and Excel Sheet
	public void displayUpcomingBikesInIndia() throws Exception
	{
		//Creating a list to store Bike names with the help of xpath
		List<WebElement> bikenames = driver.findElements(By.xpath(prop.getProperty("bikenames_xpath")));
		//Creating a list to store Bike prices with the help of xpath
		List<WebElement> bikeprice = driver.findElements(By.xpath(prop.getProperty("bikeprices_xpath")));
		//Creating a list to store Bike launch date with the help of xpath
		List<WebElement> bikelaunchdate = driver.findElements(By.xpath(prop.getProperty("bikelaunch_xpath")));
		System.out.println("Details of Bike List whose price should be less than 4 Lakh");
		System.out.println("***********************************************************");
		count=bikenames.size();
		//Using for loop printing all the details in console
		for (int i=0;i<bikenames.size();i++)
		{
			String pricetext = bikeprice.get(i).getText();
			float price = Float.parseFloat(pricetext.replaceAll("Rs. ", "").replaceAll(" Lakh", ""));
			if (price < 4) 
			{
            System.out.println("Bike name: "+bikenames.get(i).getText()+"/-  Bike price: "+bikeprice.get(i).getText()+"/-  "+bikelaunchdate.get(i).getText());
            System.out.println();
            //Storing all the bike details in Array
            bNames[i] = bikenames.get(i).getText();
			bPrice[i] = bikeprice.get(i).getText();
			bLaunch[i] = bikelaunchdate.get(i).getText();
			
			}
	    }
		//invoking the Write excel data method from write excel class and also to print the output in ExcelSheet
		WriteExcel.WriteExcelData(path+"\\OutputExcelFiles\\TestUpcomingBikesOutput.xlsx", bNames, bPrice, bLaunch);
		
	}
	
}
