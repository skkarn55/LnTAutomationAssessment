package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LandingPage;

public class BaseClass {
	
	protected Properties prop;
	String browser;
	WebDriver driver;
	
	public WebDriver initializeDriver() throws FileNotFoundException, IOException {
		FileReader reader = new FileReader(".\\src\\test\\resources\\propertiesFiles\\GlobalParameters.properties");
		prop = new Properties();
		prop.load(reader);
		
		browser = prop.getProperty("browserName");
		switch(browser) {
		case "chrome": WebDriverManager.chromedriver().setup();
						driver = new ChromeDriver();
						break;
		case "firefox": WebDriverManager.firefoxdriver().setup();
						driver = new FirefoxDriver();
						break;
		case "edge": WebDriverManager.edgedriver().setup();
						driver = new EdgeDriver();
						break;
		default: System.err.println("Invalid Browser Selection. Proceeding with Chrome Browser");
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver();
					break;
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
		
	}
	
	
	public LandingPage launchApplication() throws FileNotFoundException, IOException {
		driver = initializeDriver();
		driver.get("https://www.amazon.com");
		return new LandingPage(driver);
	}
	
	
	public void closeBrowser() {
		driver.quit();
	}

}
