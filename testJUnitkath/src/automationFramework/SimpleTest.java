package automationFramework;

import junit.framework.TestCase;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.JavascriptExecutor;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class SimpleTest extends TestCase {
	private WebDriver driver;
	
	private static Logger logger = LogManager.getLogger(SimpleTest.class);
   /* static {
        try { 
        	logger.debug("This will be printed on debug");
        	logger.info("This will be printed on info");
        	logger.warn("This will be printed on warn");
        	logger.error("This will be printed on error");
        	logger.fatal("This will be printed on fatal");

        	logger.info("Appending string: {}.", "Hello, World");

          } catch (Exception e) {
        	  fail("Test Log");
               // throw new HRException("Cannot load the log property file", th);
        }
    }*/

	@Before
	public void setUp() throws Exception {
		 
       	driver = new FirefoxDriver();
       	driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void test() {
		//Navigates to www.worldbank.org
		driver.get("http://www.worldbank.org");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Click Data tab
        driver.findElement(By.xpath(".//*[@id='hf_header_wrapper']/div/ul[2]/li[3]/a")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        //Click Visit the old site here
        driver.findElement(By.xpath(" .//*[@id='app']/div/div/div[2]/div/span/a")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        //Click by Country
        driver.findElement(By.xpath(".//*[@id='navigation']/div/ul[1]/li[1]/a")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        //navigate to Income Level HIC if the element is on bottom
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("scroll(0, 250)");
        driver.findElement(By.xpath("//a[@href='http://archive.data.worldbank.org/income-level/HIC']")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        //Scrolls Windows until Andorra is visible, Click Andorra
        WebElement element = driver.findElement(By.xpath("//a[@href='http://archive.data.worldbank.org/country/andorra']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        
        js.executeScript("scroll(0, 250)");                    
        driver.findElement(By.xpath("//a[@href='http://archive.data.worldbank.org/country/andorra']")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        
        //gets value from  High income-GDP at market prices (current US$) 
        //String gdp = driver.findElement(By.xpath(".//*[@id='block-views-44fcb918e09e0c366853ab1749e6380f']/div/div/div[1]/div/div[2]/span/span/a/span")).getText()
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //assertEquals("$3.249 billion", driver.findElement(By.cssSelector(".human-readable")).getText());
        
        
        //getting text from population
        String population = driver.findElement(By.xpath(".//*[@id='block-views-44fcb918e09e0c366853ab1749e6380f']/div/div/div[2]/div/div/div/div[2]/span/span/a/span")).getText();
        
        
        //getting text from C02
        String C02= driver.findElement(By.xpath(".//*[@id='boxes-box-country_wdi_block2']/div/div[2]/div/div/div/span[1]")).getText();

        
        js.executeScript("window.history.go(-2)");
        
        //test ends here

	}

	@After
	
	public void tearDown() throws Exception {
		driver.manage().deleteAllCookies();
		driver.close();
	}



}
