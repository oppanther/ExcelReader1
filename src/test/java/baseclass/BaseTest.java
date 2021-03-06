package baseclass;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.dataprovider.SpreadSheetReader;

public class BaseTest {
	WebDriver driver;
	
	@Test(dataProvider = "login", dataProviderClass = BaseTest.class)
	public void startUP(String username, String password) {
		System.out.println(username);
		System.out.println(password);

	}

	@DataProvider(name = "logindata")
	public Object[][] ExcelReader() throws IOException {
		FileInputStream file = null;
		XSSFWorkbook book = null;
		XSSFSheet sheet = null;
		Object[][] datas = null;

		try {
			file = new FileInputStream(new File("/Users/panther/Downloads/ReadExecl.xlsx"));
			book = new XSSFWorkbook(file);
			sheet = book.getSheetAt(0);
			datas = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			for (int i = 1; i < sheet.getLastRowNum(); i++) {
				for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
					XSSFCell cell = sheet.getRow(i).getCell(j);
					switch (cell.getCellType()) {
					case XSSFCell.CELL_TYPE_STRING: {

						datas[i - 1][j] = sheet.getRow(i).getCell(j).getStringCellValue();
						break;

					}
					case XSSFCell.CELL_TYPE_NUMERIC: {
						datas[i - 1][j] = sheet.getRow(i).getCell(j).getNumericCellValue();
						break;
					}

					default:
					}

				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (file != null) {
				file.close();

			}
			if (book != null) {
				book.close();

			}

		}
		return datas;

	}
	
	@DataProvider(name = "login")
	public Object[][] testOne() throws IOException {
		Object[][] ob = SpreadSheetReader.spreadSheetReader("/Users/panther/Downloads/ReadExecl.xlsx");
		return ob;
	}
public void hello()

{
}
}
