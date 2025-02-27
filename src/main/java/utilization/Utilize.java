package utilization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;

import bases.BaseConfig;

public class Utilize extends BaseConfig{
	private static XSSFWorkbook wb;
	//public static String file1, file2;
	
	public static String getAbsolutePath(String relativePath) {
        String projectRoot = System.getProperty("user.dir");
        
        File file = new File(projectRoot, relativePath);
        return file.getAbsolutePath();
    }
	
	public static String getPathFile(String fileName) {
		String filePath = getAbsolutePath(fileName);
		
		try {
			File src = new File(filePath);
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return filePath;
	}
	
	public static File getscreenshot() throws IOException 
	{
		File des = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		return des;
	}
	
	public static Object[][] getDataFromExcel(String filePath){
		FileInputStream file = null;
		Workbook book = null;
		Sheet sheet = null;
		
		try {
			file = new FileInputStream(getPathFile(filePath));
		}
		catch(FileNotFoundException e){
			Assert.assertNotNull(file, e.getMessage());
		}
		try {
			book = WorkbookFactory.create(file);
		}
		catch (InvalidFormatException e) {
			Assert.assertNotNull(book, e.getMessage());
		}
		catch (IOException e) {
			Assert.assertNotNull(book, e.getMessage());
		}
		sheet = book.getSheetAt(0);
		
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for(int i = 0; i < sheet.getLastRowNum(); i++) {
			for(int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
				data[i][j] = sheet.getRow(i + 1).getCell(j, Row.CREATE_NULL_AS_BLANK).toString();
			}
		}
		return data;
	}
	
	public static Object[][] getDataFromExcel(String filePath, String SheetName){
		FileInputStream file = null;
		Workbook book = null;
		Sheet sheet = null;
		
		try {
			file = new FileInputStream(getPathFile(filePath));
		}
		catch(FileNotFoundException e){
			Assert.assertNotNull(file, e.getMessage());
		}
		try {
			book = WorkbookFactory.create(file);
		}
		catch (InvalidFormatException e) {
			Assert.assertNotNull(book, e.getMessage());
		}
		catch (IOException e) {
			Assert.assertNotNull(book, e.getMessage());
		}
		sheet = book.getSheet(SheetName);
		
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for(int i = 0; i < sheet.getLastRowNum(); i++) {
			for(int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
				data[i][j] = sheet.getRow(i + 1).getCell(j, Row.CREATE_NULL_AS_BLANK).toString();
				//System.out.println(data[i][j]);
			}
		}
		return data;
	}
	
	public static XSSFWorkbook workBook() {
		return wb;
	}
}
