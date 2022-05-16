package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Careers{

	@FindBy(xpath="//form[@id='wpjb-apply-form']")
	WebElement applicationForm;

	@FindBy(xpath="//a[normalize-space()='South Africa']")
	WebElement southAfricanLink;

	@FindBy(xpath="(//div[@class='wpjb-line-major'])[1]")
	WebElement firstJobLink;

	@FindBy(xpath="//div[@id=\"wpjb-scroll\"]/div[1]/a")
	WebElement applyOnlineButton;

	@FindBy(xpath="//input[@id='applicant_name']")
	WebElement nameTextBox;

	@FindBy(xpath="//input[@id='email']")
	WebElement emailTextBox;

	@FindBy(xpath="//input[@id='phone']")
	WebElement phoneNumberTextBox;

	@FindBy(xpath="//textarea[@id='message']")
	WebElement workWithUsDescTextBox;

	@FindBy(xpath="//div[@id='wpjb-upload-container-file']//input[@type='file']")
	WebElement fileUpload;

	@FindBy(xpath="//input[@id='wpjb_submit']")
	WebElement sendButton;

	@FindBy(xpath="//li[normalize-space()='You need to upload at least one file.']")
	WebElement errorMessage;

	//	(//*[@id="wpjb-apply-form"]//li)[3]


	//==================================================
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest test;
	public WebDriver driver;
	
	public Careers(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
// Method ro generate random 10 phone numbers 
	public static String generatePhoneNumber() {
		String phoneNo =  String.format("%03d %03d %04d", 
				0+(int) Math.floor(99*Math.random()), 
				(int) Math.floor(999*Math.random()),
				(int) Math.floor(9999*Math.random()));
		return phoneNo;
	}



	// ============================Tests Methods==========================================
	
	public void clickSouthAfricanLink() {
		//		verifyBrowserElement(southAfricanLink, "South African Link");
		clickWebElement(southAfricanLink,"South African Link");
	}

	public void clickFirstJobLink() {
		clickWebElement(firstJobLink,"First Job Link");
	}

	public void clickApplyOnlineButton() {
		clickWebElement(applyOnlineButton,"Apply Online Button");
	}

	public void scrollToTheForm() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", applicationForm);
	}

	public void fillForm(String name,String email){
		String phoneNumber =generatePhoneNumber();

		this.sendKeys(nameTextBox, "name text box",name);
		this.sendKeys(emailTextBox, "email text box",email);
		this.sendKeys(phoneNumberTextBox, "phone number text box",phoneNumber);
	}

	public void clickSendButton()  {
		clickWebElement(sendButton,"Send Application Button");
		sendButton.click();
	}

	public void verifyErrorMessage() {
		String actual = errorMessage.getText();
		String expected = "You need to upload at least one file.";	
		
		Assert.assertEquals(actual, expected);
		verifyBrowserElement(errorMessage, "Error Message");
	}
	
	public void flushReport() {
		extent.flush();
	}
	
	public void closeBrowser() {
		driver.close();
		driver.quit();
	}
	
	
	// ============================Extent report Methods==========================================
		public void initilizeExtentReport() {
			htmlReporter = new ExtentHtmlReporter("src\\test\\resources\\report\\testReport.html");
			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);	
		}

		public ExtentTest getTest(String elementName, String action) {
			return test = extent.createTest(elementName,action);
		}

		public void verifyBrowserElement(WebElement element, String Name) {
			if (element.getSize().getWidth()>0) {
				System.out.println(Name+" was displayed");
				test.log(Status.PASS, Name+" was displayed");
			}
			else {
				System.out.println(Name+" element could not be found");
				test.log(Status.FAIL, Name+ " element could not be found");
			}
		}


		// ===============Web Elements Click and Verify elements Methods============================		
	public void clickWebElement(WebElement element, String Name) {
		if (element.getSize().getWidth()>0) {
			element.click();
			System.out.println(Name+" was clicked");
			test.log(Status.PASS, Name+" was clicked");			
		}
		else {
			System.out.println(Name+" was clicked");
			test.log(Status.FAIL,element+ " element could not be clicked");
		}		
	}

	public void sendKeys(WebElement element, String Name, String Text)
	{
		if (element.getSize().getWidth()>0) {
			element.clear();
			element.sendKeys(Text);
			System.out.println("The text: '"+Text +"' was sent to "+ Name);
			test.log(Status.PASS, "The text: '"+Text +"' was sent to "+ Name);		
		}
		else {
			System.out.println(Name+" was clicked");
			test.log(Status.FAIL, "The text: '"+Text +"' could not be sent to "+ Name);
		}

	}	

}
