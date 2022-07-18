package webdriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_27_Wait_P4_Mix_Implicit_Explicit {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
	    System.setProperty("webdriver.gecko.driver", projectPath + "/browserdriver/geckodriver");
	    driver = new FirefoxDriver();
		
		// System.setProperty("webdriver.chrome.driver", projectPath + "/browserdriver/chromedriver");
		// driver = new ChromeDriver();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	//@Test
	public void TC_01_found_element() { 
		driver.get("https://www.facebook.com/");
		By emailId = By.id("email");
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		System.out.println("Start Implicit= " + getCurrentTime());
		driver.findElement(emailId).isDisplayed();
		System.out.println("End Implicit= " + getCurrentTime());
		
		explicitWait = new WebDriverWait(driver, 15);
		System.out.println("Start explicit= " + getCurrentTime());
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailId));
		System.out.println("End explicit= " + getCurrentTime());
	}

	//@Test
	public void TC_02_Element_Not_Found_Only_Implicit() {
		driver.get("https://www.facebook.com/");
		By emailId = By.id("vietnam");
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		System.out.println("Start Implicit= " + getCurrentTime());
		try {
			driver.findElement(emailId).isDisplayed();
		} catch (Exception e) {
			
		}
		System.out.println("End Implicit= " + getCurrentTime());
		
	}

	//@Test
	public void TC_03_Element_Not_Found_Only_Explicit() { 
		driver.get("https://www.facebook.com/");
		By emailId = By.id("vietnam");
		
		explicitWait = new WebDriverWait(driver, 15);
		System.out.println("Start explicit= " + getCurrentTime());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailId));
		} catch (Exception e) {
			
		}
		System.out.println("End explicit= " + getCurrentTime());
	}
	//@Test
	public void TC_04_Element_Not_Found_Only_Explicit_Implicit() { 
		driver.get("https://www.facebook.com/");
		By emailId = By.id("vietnam");
		
		//Xét các case
		//1. Implicit < Explicit
		//1. Implicit = Explicit
		//1. Implicit > Explicit
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 2);
		System.out.println("Start explicit= " + getCurrentTime());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailId));
		} catch (Exception e) {
			
		}
		System.out.println("End explicit= " + getCurrentTime());
	}
	@Test
	public void TC_05_Element_Not_Found_Only_Explicit_Webelement() { 
		driver.get("https://www.facebook.com/");
		
		explicitWait = new WebDriverWait(driver, 15);
		System.out.println("Start explicit= " + getCurrentTime());
		explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("vietnam"))));
		System.out.println("End explicit= " + getCurrentTime());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getCurrentTime() {
		Date date = new Date();
		return date.toString();
	}
}
