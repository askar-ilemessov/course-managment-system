package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPageTest {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", 
				"./src/main/java/selenium/chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.google.com");
		System.out.println(driver.getTitle());
	}
}
