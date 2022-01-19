/*Author Name: Sanfiya S,Surendarkumar G
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

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//Creating a class to read data from ExcelSheet 
public class ReadExcel {
	//This method will read the data from Excel Sheet
		public static String readExcel() throws IOException
		{
			//Getting the Relative path for excel from Source Excel folder
			String filepath = System.getProperty("user.dir")+"\\ReadExcelFile\\ReadExcel.xlsx";
			FileInputStream file = new FileInputStream(filepath);
			XSSFWorkbook wb = new XSSFWorkbook(file);
			XSSFSheet ws = wb.getSheetAt(0);
			//Getting data from 0th row and 0th column of Excelsheet
			String testData = String.valueOf(ws.getRow(0).getCell(0));
			wb.close();
			return testData;
			
		}
}
