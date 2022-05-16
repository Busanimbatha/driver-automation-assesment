package browserFactory;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Browsers {

	public static WebDriver driver;

	public WebDriver StartBrowser(String browserName, String url) {

		if (browserName.equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup();

			driver = new FirefoxDriver();

			System.out.print("FireFox Driver has started");

		}

		else if (browserName.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();

			driver = new ChromeDriver();

			System.out.print("Chrome Driver has started");

		}

		driver.manage().window().maximize();

		driver.get(url);

		return driver;

	}

}
