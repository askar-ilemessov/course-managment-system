package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class EnvironmentManager {

	public static ChromeDriver initWebDriver() {
		System.setProperty("webdriver.chrome.driver", 
				"src/test/resources/chromedriverv86.exe");
	    return new ChromeDriver();
	}
	
	public static void shutDownDriver(WebDriver driver) {
	    driver.quit();
	}
}
