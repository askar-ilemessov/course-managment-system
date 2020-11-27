package selenium;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ManageApplicationsPageTest {

	WebDriver driver;
	@Before
    public void startBrowser() {
        driver = EnvironmentManager.initWebDriver();
    }

    @Test
    public void test() {
    	driver.get("http://localhost:8080/cms/");
    	
		//Delete account
//		driver.findElement(By.name("deleteButton")).click();
//    	driver.findElement(By.name("deleteConfirm")).click();
    	
//		assertEquals(entry was deleted in DB);
    	
		// Check how many rows the table has
//    	rows = driver.find_elements_by_xpath("//table/tbody/tr")
//    	# len method is used to get the size of that list
//    	assertEquals(len(rows, mongo.howmanyacounts);
    	
    	//View Applications
//		driver.findElement(By.name("viewApplications")).click();
    	//tbd 
    }
    
    @After
    public void shutDown() {
    	EnvironmentManager.shutDownDriver(driver);
    }

}


