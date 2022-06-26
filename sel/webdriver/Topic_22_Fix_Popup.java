package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_22_Fix_Popup {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
	    System.setProperty("webdriver.gecko.driver", projectPath + "/browserdriver/geckodriver");
	    driver = new FirefoxDriver();
		
		// System.setProperty("webdriver.chrome.driver", projectPath + "/browserdriver/chromedriver");
		// driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Ngoai_Ngu_24h() { 
		driver.get("https://ngoaingu24h.vn/");
		By loginPopup = By.xpath("//div[@class='modal fade in']");
		
		//Verify login popup not displayed
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		sleepInSecond(2);
		
		driver.findElement(By.cssSelector("button.login_")).click();
		sleepInSecond(2);
		
		//Verify login popup Displayed
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		
		
		
		//Verify login popup displayed
		
	}

	@Test
	public void TC_02_() {
		driver.get("https://jtexpress.vn/");
		sleepInSecond(2);
		By popupLg = By.xpath("//div[@class='flex flex-col lg:flex-row items-center']");
		//Verify popup display
		Assert.assertTrue(driver.findElement(popupLg).isDisplayed());
		
		driver.findElement(By.cssSelector("button.rotate-45")).click();
		//Verify popup not displayed
		Assert.assertFalse(driver.findElement(popupLg).isDisplayed());
		
		String billCode = "840000598444";
		driver.findElement(By.name("billcode")).sendKeys(billCode);
		
		
		

		
	}

	@Test
	public void TC_03_() { 
	
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
