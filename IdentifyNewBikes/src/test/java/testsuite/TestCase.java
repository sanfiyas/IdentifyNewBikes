/*Author Name: Nagaraj Gowtham N, Vignesh Kumar, Swathika D , Sanfiya S , Suryaa Kannan , Surendarkumar G
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
package testsuite;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import browserconfiguration.Browser;
import pages.HomePage;
import pages.LoginPage;
import pages.UpcomingHondaBikes;
import pages.UsedCars;

public class TestCase {
	//initializing objects to the classes and interface
	WebDriver driver;
	HomePage homepage;
	UpcomingHondaBikes uhbp;
	UsedCars usedcars;
	LoginPage loginpage;
	
	//creating a BeforeTest annotation to call the browser
    @BeforeTest(groups= {"BaseMethods"})
    public void callingBrowser() throws Exception
    {
    	driver=Browser.setDriver();	
    	//Reporter.log(" Browser opened Successfully");
    }
   
    //Smoketest
   //creating a Test annotation to verify upcoming honda bikes test case
    @Test(enabled=false,priority = 0,groups= {"Smoke"})
   public void verifyingUpComingHondaBikesTest() throws Exception
   {
    	
    	homepage=new HomePage(driver);
    	//calling all the methods from the HomePage class using homepage object 
		homepage.verifyHomePageTitle();
		homepage.enterSearchboxinput();
		homepage.clickSearchButton();
		homepage.verifyHondaBikesPageTitle();
		homepage.clickUpcomingHondaBikes();
		
		uhbp=new UpcomingHondaBikes(driver);
		
		TimeUnit.SECONDS.sleep(2);
		//calling all the methods from the  UpcomingHondaBikes class using uhbp object
		//uhbp.verifyUpcomingBikesPageTitle();
		Reporter.log("smoke test of UpcomingHondaBikes functionality is verified Successfully");
    	
 }
    
    //creating a Test annotation to verify upcoming honda bikes test case
    @Test (priority = 0,groups= {"Regression"})
    public void displayUpcomingHondaBikesTest() throws Exception
    {
    	homepage=new HomePage(driver);
    	//calling all the methods from the HomePage class using homepage object 
		homepage.verifyHomePageTitle();
		homepage.enterSearchboxinput();
		homepage.clickSearchButton();
		homepage.verifyHondaBikesPageTitle();
		homepage.clickUpcomingHondaBikes();
		
		uhbp=new UpcomingHondaBikes(driver);
		//calling all the methods from the  UpcomingHondaBikes class using uhbp object
		uhbp.verifyUpcomingBikesPageTitle();
		uhbp.clickViewMoreBikes();
		TimeUnit.SECONDS.sleep(2);
		uhbp.displayUpcomingBikesInIndia();
		Reporter.log("Regression test of UpcomingHondaBikes test is verified Successfully and Bike details are printed on both Console and Excel");
    }

    //smoketest
    //creating a Test annotation to verify UsedCars test case
    @Test(enabled=false,priority = 1,groups= {"Smoke"})
    public void verifyPopularCarModel() throws Exception 
    {
    	usedcars = new UsedCars(driver);
    	UsedCars.usedCarsPage();
    	UsedCars.verifyUsedCarsPageTitle();
    	UsedCars.navigatingToHomepage();
    	Reporter.log("smoke test of verifying Popular Car Models page functionality is verified Successfully");
    	
    }
    //Regression Test
    //creating a Test annotation to verify UsedCars test case
    @Test (priority = 1,groups= {"Regression"})
    public void displayPopularUsedCarsModelsTest() throws Exception
    {
    	usedcars = new UsedCars(driver);
    	UsedCars.usedCarsPage();
    	UsedCars.verifyUsedCarsPageTitle();
    	UsedCars.usedCars();	
    	UsedCars.navigatingToHomepage();
    	Reporter.log("Regression test of PopularUsedCarsModels test is verified Successfully and Popular Car Models are printed on both Console and Excel");
    }
    //Smoke Test
  //creating a Test annotation to verify UsedCars test case
    @Test(enabled=false,priority = 2,groups= {"Smoke"})
    public void verifyLoginFunctionalTest() throws Exception
    {
    	loginpage = new LoginPage(driver);
    	LoginPage.navigateToLoginPage();
    	LoginPage.verifyLoginPageTitle();
    	LoginPage.returnHomePage();
    	Reporter.log("smoke test of verifyLoginFunctionality is verified Successfully");
    	Reporter.log("Regression test of verifyLoginFunctional test is verified Successfully and screenshot has taken");
    
    }
    
     //Regression Test
    //creating a Test annotation to verify Login test case
    @Test (priority = 2,groups= {"Regression"})
    public void verifyLoginTest() throws Exception
    {
    	loginpage = new LoginPage(driver);
    	LoginPage.navigateToLoginPage();
    	LoginPage.loginPage();
    	LoginPage.returnHomePage();
    }
    
    //creating a AfterTest annotation to call the close browser method
    @AfterTest(groups= {"BaseMethods"})
    public void closingBrowser()
    {
    	Browser.closeBrowser();
    }
}
