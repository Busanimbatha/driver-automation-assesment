package test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import browserFactory.Browsers;
import fileInput.ReadExcelData;
import fileInput.WriteDataToExcel;
import pages.Careers;

public class CarreersTest {
	Browsers browser = new Browsers();
	Careers carrers;
	ExtentTest test;

	@BeforeClass
	public void setup() {
		browser.StartBrowser("chrome", "https://www.ilabquality.com/careers/");
		carrers= new Careers(Browsers.driver);
				;
		carrers.initilizeExtentReport();

		test = carrers.getTest("Assesment Test Report", "Driver Automation Assessment For ILAB Website");
	}
	
	// Change data provider to "applicationFormDataFromExcelFile" to read from excel file
	@Test(dataProvider="applicationFormDataFromObjectFile")
	public void navigateToOnlineApplication(String name, String email) throws Exception {

		carrers.clickSouthAfricanLink();
		carrers.clickFirstJobLink();
		carrers.clickApplyOnlineButton();

		carrers.scrollToTheForm();
		WriteDataToExcel.writrToExcelTestData();

		carrers.fillForm(name,email);
		carrers.clickSendButton();
		carrers.verifyErrorMessage();
	}
	
	@AfterMethod
	public void flushTestReport() {
		carrers.flushReport();
	}

	@AfterClass
	public void tearDown() throws InterruptedException {
		Thread.sleep(6000);
		//		 Browsers.driver.close();
		carrers.closeBrowser();
	}


	//	Used to read data as ObjectFile 
	@DataProvider(name = "applicationFormDataFromObjectFile")

	public static Object[][] readObjectDataFile() {

		return new Object[][] { { "busani", "automationAssessment@iLABQuality.com" }};

	}

	//	used to read data from ExcelSheet
	@DataProvider(name="applicationFormDataFromExcelFile")
	public Object[][] readExcelData() throws Exception
	{
		Object[][] data = ReadExcelData.readExcelFormData();
		return data;
	}


}
