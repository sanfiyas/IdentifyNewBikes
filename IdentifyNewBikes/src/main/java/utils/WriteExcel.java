/*Author Name: Vignesh kumar, Suryaa Kannan , Swathika D, Nagaraj Gowtham N
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
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//creating a class to Write output in ExcelSheet
public class WriteExcel 
{
	//initializing objects to the classes 
	public static File src;
	public static FileInputStream fis;
	public static XSSFWorkbook workbook;
	public static XSSFSheet worksheet;
	public static XSSFRow row;
	public static FileOutputStream fos;
	
	//creating a method to write data into excel Sheet
	public static void WriteExcelData(String filePath, String[] bikenames, String[] bikeprice, String[] bikelaunchdate) throws Exception {
		try
		{
			src = new File(filePath);
			fis = new FileInputStream(src);
			workbook = new XSSFWorkbook(fis);
			worksheet = workbook.getSheetAt(0);
			row = worksheet.getRow(worksheet.getLastRowNum());
			int rowCount = bikeprice.length;
			int k=0;
			//Selecting rows and columns to print data using for loop
			for (int i = 0; i < rowCount; i++) 
			{
				if(bikenames[i] != null)
				{
				
				row = worksheet.createRow(k);
                row.createCell(0).setCellValue(bikenames[i]);
				row.createCell(1).setCellValue(bikeprice[i]);
				row.createCell(2).setCellValue(bikelaunchdate[i]);
				k++;
				}
				
			}

			fis.close();
			fos = new FileOutputStream(src);
			workbook.write(fos);
			fos.close();

		}
		catch (FileNotFoundException e)
		{
			System.out.println("The required file is not available");
			e.printStackTrace();
		}
	}
	
	//creating a method to write data into excel Sheet
	public static void WriteExcelData1(String filePath, String[] pModules) throws Exception 
	{
			try
			{
			src = new File(filePath);
			fis = new FileInputStream(src);
			workbook = new XSSFWorkbook(fis);
			worksheet = workbook.getSheetAt(0);
			row = worksheet.getRow(worksheet.getLastRowNum());
			int rowCount = pModules.length;
			//Selecting rows and columns to print data using for loop
			for (int i = 0; i < rowCount; i++) 
			{
				row = worksheet.createRow(i);
                row.createCell(0).setCellValue(pModules[i]);
			}
			fis.close();
			fos = new FileOutputStream(src);
			workbook.write(fos);
			fos.close();
			}
			catch (FileNotFoundException e)
			{
				System.out.println("The required file is not available");
				e.printStackTrace();
			}

		} 
}
