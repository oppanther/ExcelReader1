package com.dataprovider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SpreadSheetReader {
	
	public static Object[][] spreadSheetReader(String path) throws IOException {
		FileInputStream fis = null;
		File file = null;
		XSSFWorkbook book = null;
		XSSFSheet sheet = null;
		Object[][] ob = null;
		
		try {
			file = new File(path);
			fis = new FileInputStream(file);
			book = new XSSFWorkbook(fis);
			sheet = book.getSheetAt(0);
			int rowNum = sheet.getLastRowNum();
			int cellNum = sheet.getRow(0).getLastCellNum();
			ob = new Object[rowNum][cellNum];
			for(int r = 1; r <=rowNum; r++) {
				for(int c = 0; c<=cellNum; c++) {
					XSSFCell cell = sheet.getRow(r).getCell(c);
					switch (cell.getCellType()) {
					case XSSFCell.CELL_TYPE_NUMERIC:
						ob[r-1][c] = cell.getNumericCellValue();
						break;
					case XSSFCell.CELL_TYPE_STRING:
						ob[r-1][c] = cell.getStringCellValue();
						break;

					default:
						break;
					}
				}
			}
		} catch (Exception e) {
			//none 
			
		} finally {
			book.close();
			fis.close();
		}
		
		return ob;
		
	}

}
