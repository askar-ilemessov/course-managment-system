package selenium;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPageTest {

	WebDriver driver;
	@Before
    public void startBrowser() {
        driver = EnvironmentManager.initWebDriver();
    }

    @Test
    public void test() {
    	driver.get("http://localhost:8080/cms/");
    	
		//STUDENT
//    	driver.findElement(By.name("name")).sendKeys("student");;
//		driver.findElement(By.name("lastName")).sendKeys("student");;
//		driver.findElement(By.name("email")).sendKeys("email@email.ca");;
//		driver.findElement(By.name("submit")).click();
// 		check DB for new entry
//		assertEquals(entry is in DB);
    }
    
    @After
    public void shutDown() {
    	EnvironmentManager.shutDownDriver(driver);
    }

}

