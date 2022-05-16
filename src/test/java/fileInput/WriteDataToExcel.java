package fileInput;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteDataToExcel {
//Method to store data in an excel file
	public static void writrToExcelTestData() throws IOException {
		String path = "src\\test\\java\\testData\\data.xlsx";

		FileInputStream fs = new FileInputStream(path);
		XSSFWorkbook wb = new XSSFWorkbook(fs);
		XSSFSheet sheet1 = wb.getSheetAt(0);
		int lastRow = sheet1.getLastRowNum();

		for(int i=0; i<=lastRow; i++){
			Row row = sheet1.getRow(lastRow);
			Cell namecell = row.createCell(0);
			Cell emailcell = row.createCell(1);

			namecell.setCellValue("Busani");
			emailcell.setCellValue("automationAssessment@iLABQuality.com");

		}

		FileOutputStream fos = new FileOutputStream(path);
		wb.write(fos);
		fos.close();
	}
}
