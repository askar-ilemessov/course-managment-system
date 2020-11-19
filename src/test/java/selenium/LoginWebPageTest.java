package selenium;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class LoginWebPageTest {

	WebDriver driver;
	@Before
    public void startBrowser() {
        driver = EnvironmentManager.initWebDriver();
    }

    @Test
    public void test() {
    	driver.get("http://www.google.com");
		System.out.println(driver.getTitle());
        assertEquals(driver.getTitle(), "Google");
    }
    
    @After
    public void shutDown() {
    	EnvironmentManager.shutDownDriver(driver);
    }

}
