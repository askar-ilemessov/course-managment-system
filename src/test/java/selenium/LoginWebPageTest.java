package selenium;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginWebPageTest {

	WebDriver driver;
	@Before
    public void startBrowser() {
        driver = EnvironmentManager.initWebDriver();
    }

    @Test
    public void test() {
    	driver.get("http://localhost:8080/cms/");
    	
    	//INVALID
//    	driver.findElement(By.name("uname")).sendKeys("admin");;
//		driver.findElement(By.name("psw")).sendKeys("admin");;
//		driver.findElement(By.name("login")).click();
		
//		assertTrue(driver.findElement(By.name("error")).isDisplayed());
    	
    	//ADMIN
//    	driver.findElement(By.name("uname")).sendKeys("admin");;
//		driver.findElement(By.name("psw")).sendKeys("admin");;
//		driver.findElement(By.name("login")).click();
		
		//assertEquals(driver.getTitle(), "Admin Page");
		//logout. go back to login page
		
		//STUDENT
//    	driver.findElement(By.name("uname")).sendKeys("student");;
//		driver.findElement(By.name("psw")).sendKeys("student");;
//		driver.findElement(By.name("login")).click();
		
		//assertEquals(driver.getTitle(), "Student Page");
		
		//REGISTER
		driver.findElement(By.linkText("Register")).click();
		assertEquals(driver.getTitle(), "Registration");
    }
    
    @After
    public void shutDown() {
    	EnvironmentManager.shutDownDriver(driver);
    }

}
