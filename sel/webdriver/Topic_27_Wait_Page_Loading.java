package webdriver;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_27_Wait_Page_Loading {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	Actions action;

	@BeforeClass
	public void beforeClass() {
	    System.setProperty("webdriver.gecko.driver", projectPath + "/browserdriver/geckodriver");
	    driver = new FirefoxDriver();
	    action = new Actions(driver);
		
		// System.setProperty("webdriver.chrome.driver", projectPath + "/browserdriver/chromedriver");
		// driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_OrangeHRM_Implicit() { 
		driver.get("https://api.orangehrm.com/#api-_");

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#project h1")).getText(), "OrangeHRM REST API Documentation");
	}

	//@Test
	public void TC_02_Explicit() {
		driver.get("https://api.orangehrm.com/#api-_");
		
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 30);

		//Wait cho spinner invisible
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner")));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#project h1")).getText(), "OrangeHRM REST API Documentation");
	}

	//@Test
	public void TC_03_Page_Ready() { 
		driver.navigate().refresh();
		
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

		//Wait cho page load success/ready
		Assert.assertTrue(jsJQueryAndPageLoadedSucces());
		
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#project h1")).getText(), "OrangeHRM REST API Documentation");
	}
	
	//@Test
		public void TC_04_OrangeHRM_Page_Ready() { 
			driver.get("https://opensource-demo.orangehrmlive.com");
			
			driver.findElement(By.id("txtUsername")).sendKeys("Admin");
			driver.findElement(By.id("txtPassword")).sendKeys("admin123");
			driver.findElement(By.id("btnLogin")).click();

			//C1: Wait cho page ready
			Assert.assertTrue(jsJQueryPageLoadingSuccess());
			
			//C2: Wait cho icon loading biến mất (Invisible)
			//explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.loadmask"))));
			
			Assert.assertTrue(driver.findElement(By.cssSelector("div#div_graph_display_emp_distribution")).isDisplayed());
		}
	@Test
	public void TC_05_Test_Project_page_Ready() throws InterruptedException { 
		driver.get("https://blog.testproject.io/");
		
		//Handle popup
		if(driver.findElement(By.cssSelector("div#mailch-bg")).isDisplayed()) {
			driver.findElement(By.cssSelector("div#close-mailch")).click();
		}
		//Hover mouse to search textbox
		action.moveToElement(driver.findElement(By.cssSelector("section#search-2 input.search-field"))).perform();
		Assert.assertTrue(jsJQueryPageLoadingSuccess());
		
		driver.findElement(By.cssSelector("section#search-2 input.search-field")).sendKeys("Selenium");
		driver.findElement(By.cssSelector("section#search-2 span.glass")).click();
		
		Assert.assertTrue(jsJQueryPageLoadingSuccess());
		
		List<WebElement> firstAllPostTitle = driver.findElements(By.cssSelector("h3.post-title a"));
		for (WebElement postTitle : firstAllPostTitle) {
			Assert.assertTrue(postTitle.getText().contains("Selenium"));
		}
			
	}
	
	//jQuery + Javascript
	public boolean jsJQueryPageLoadingSuccess() {
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				//return (Boolean) jsExecutor.executeScript("return (windown.jQuery != null) && (jQuery.active === 0);");
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
			
		};
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}
	
	//jQuery + Javascript
	public boolean jsJQueryAndPageLoadedSucces() {
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;
		
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") ==0 );
				} catch (Exception e) {
					return true;
				}
			}
			
		};
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
		
	}
	//jQuery + Element
	public boolean jsJQueryAndAjaxIconLoadingSucces() {
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;
		
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") ==0 );
				} catch (Exception e) {
					return true;
				}
			}
			
		};
		ExpectedCondition<Boolean> ajaxIconLoading = new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return $('.raDiv').is.(':visible')").toString().equals("false");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(ajaxIconLoading);
		
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
}
