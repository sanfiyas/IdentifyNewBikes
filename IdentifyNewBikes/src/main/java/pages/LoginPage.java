/*Author Name: Sanfiya S, Surendarkumar G
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
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import browserconfiguration.Browser;
import utils.ReadConfigProperties;
import utils.ReadExcel;
import utils.ScreenShot;
//Class for checking login functionality
public class LoginPage
{
	static WebDriver driver;
	static ReadConfigProperties rcp;
	static Properties prop;
	static String parent;
	static Set<String> allWindows;
	//This method is used to create a constructor to invoke driver
	public LoginPage(WebDriver driver)
	{
		LoginPage.driver=driver;
		rcp = new ReadConfigProperties();
		//To get data from object repository
		prop = rcp.inputSetup();
	}
	
	
	//This method is used for navigating to Home page for clicking login button
	public static void navigateToLoginPage() throws Exception
	{	
		
	     parent = driver.getWindowHandle();

		//To click on Login button
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("login/signUp_id"))));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpat
		//clicking login/signup button by using ID
		
		driver.findElement(By.xpath(prop.getProperty("login/signUp_id"))).click();
		TimeUnit.SECONDS.sleep(2);
		driver.switchTo().activeElement();
		//clicking continue with google button by using xpath
		driver.findElement(By.xpath(prop.getProperty("googlesignin_xpath"))).click();
		Browser.timeManager();
	}
	
	//Creating a method to do window handling and verifying Title of login page 
	public static void verifyLoginPageTitle()
	{
		//It is used for storing all the windows as a set
		allWindows = driver.getWindowHandles();
		// Find the window whose title is equal to "Sign in – Google accounts"
		String expTitle = "Sign in – Google accounts";
		if(!allWindows.isEmpty()) 
		{
			for(String allWindowsIns : allWindows)
			{
					String actualTitle = driver.switchTo().window(allWindowsIns).getTitle();
					if(expTitle.equalsIgnoreCase(actualTitle))
					{
						System.out.println("Login Page Title is verified Successfully");
						System.out.println("================================================");
						driver.close();
						driver.switchTo().window(parent);
					}	
			}
		}
	}
	
	//Creating a method to give an invalid email and to take the screenshot
	public static void loginPage() throws Exception 
	{
		// Get all windows and type user name and password 
		allWindows = driver.getWindowHandles();
		// Find the window whose title is equal to "Sign in – Google accounts"
		String expTitle = "Sign in – Google accounts";
		if(!allWindows.isEmpty()) {
			for(String allWindowsIns : allWindows) 
			{
				try {
					//This is used to get title from all the windows
					String actualTitle = driver.switchTo().window(allWindowsIns).getTitle();
					if(expTitle.equalsIgnoreCase(actualTitle)) {
						System.out.println("Login Page Title is verified Successfully");
						System.out.println("Google Sign In Window Found");
						System.out.println("Actual Title = "+ actualTitle);
						//Getting wrong Email-ID from ExcelSheet 
						String data = ReadExcel.readExcel();
						driver.findElement(By.id(prop.getProperty("emailid_id"))).sendKeys(data);
						
						//Click on Next button
						driver.findElement(By.xpath(prop.getProperty("nextbutton_xpath"))).click();					
						Browser.timeManager();
						
						//Capturing the error message 
						ScreenShot.captureScreenShot(driver);
						System.out.println("Screenshot Taken");
						
						//Close the child window
						driver.close();
						System.out.println("Google SignIn Webpage closed");
						System.out.println("================================================");
						
						driver.switchTo().window(parent);
						
					}				
			    }
				catch(NoSuchWindowException nswe) 
				{
				System.out.println(nswe);
			    } 
			} // end of for loop
		} // end of if
		}
	
	//Creating a method to return to homepage
	public static void returnHomePage() throws Exception
	{
		//Returning to homepage
		driver.findElement(By.id(prop.getProperty("submitcloselogin_id"))).click();
	}

}
