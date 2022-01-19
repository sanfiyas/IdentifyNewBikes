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
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

//Creating a class to take screenshots with imestamp
public class ScreenShot {
	
	public static void captureScreenShot(WebDriver driver) throws IOException
    {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        //Convert driver object to TakeScreenshot
        TakesScreenshot scrShot =((TakesScreenshot)driver);
        //Call getScreenshotAs method to create image file
                File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
            //Move image file to new destination
                File DestFile=new File(System.getProperty("user.dir")+"\\screenshot\\"+ timeStamp +".png");
                //Copy file at destination
                FileUtils.copyFile(SrcFile, DestFile);

}
}
