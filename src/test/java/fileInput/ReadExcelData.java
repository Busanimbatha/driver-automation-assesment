package fileInput;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelData {
	public static Object[][] readExcelFormData() throws IOException {
		String path = "src\\test\\java\\testData\\data.xlsx";
		XSSFCell cell;
		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet2 = wb.getSheetAt(0);
		XSSFRow Row=sheet2.getRow(0);

		int RowNum = sheet2.getPhysicalNumberOfRows();// count my number of Rows
		int ColNum= Row.getLastCellNum(); // get last ColNum 
		Object Data[][]= new Object[RowNum][ColNum];

		for(int i=1; i<=sheet2.getLastRowNum(); i++){
			for(int j=0; j<sheet2.getRow(i).getLastCellNum(); j++){
				cell = sheet2.getRow(i).getCell(j);
				switch (j) {				
				case 0:
					Data[i][j]=cell.getStringCellValue();
					break;
				case 1:
					Data[i][j]=cell.getStringCellValue();
					break;
				default:
					break;
				}

			}
		}
		return Data;
	}
}
