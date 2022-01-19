/*Author Name: Nagaraj Gowtham N, Vignesh Kumar,
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


public class HomePage  
{
	
   WebDriver driver;
   ReadConfigProperties rcp;
   Properties prop;
   //Creating a constructor to invoke driver and to read config properties file 
   public HomePage(WebDriver driver) 
   {
	  this.driver=driver;
	  rcp = new ReadConfigProperties();
	  prop = rcp.inputSetup();
   }
  
   // creating a method for verifying homepage title by using Assert statement
   public void verifyHomePageTitle() 
   {
	  //Checking expected tilte with actual title
	 Assert.assertEquals(driver.getTitle(), "ZigWheels - New Cars, Used Cars, Bikes Prices, News, Reviews, Forum");
	 System.out.println("================================================");
	 System.out.println("Zig Wheels Homepage Title is verified successfully");
   }
 
   //creating a method to enter input in the search text box  
   public void enterSearchboxinput() throws Exception 
   {
	  driver.findElement(By.id(prop.getProperty("hondabikes_id"))).sendKeys("Honda Bikes");
	  TimeUnit.SECONDS.sleep(2);
   }
    //creating a method to click on the search button
   	public void clickSearchButton()
   	{
	 driver.findElement(By.xpath(prop.getProperty("searchbutton_xpath"))).click(); 
   	}
  
   	//creating a method for verifying HondaBikesPage title by using Assert statement
   	public void verifyHondaBikesPageTitle()
   	{
	  Assert.assertEquals(driver.getTitle(), "Honda Bikes Price in India, Honda New Models 2022, User Reviews, Offers and comparisons");
	  System.out.println("Honda Bikes page Title is verified Successfully");
   	}
   	//creating a method to click on the upcoming honda bike web element by using JavascriptExecutor
   	public void clickUpcomingHondaBikes() 
   	{
	  JavascriptExecutor js = (JavascriptExecutor) driver;
	  WebElement Element = driver.findElement(By.linkText(prop.getProperty("upcominghondabikes_linkText")));
	  js.executeScript("arguments[0].scrollIntoView();", Element);
	  WebDriverWait wait=new WebDriverWait(driver,50);
	  wait.until(ExpectedConditions.elementToBeClickable(Element));
	  js.executeScript("arguments[0].click();", Element);
   	}
  
  
}
