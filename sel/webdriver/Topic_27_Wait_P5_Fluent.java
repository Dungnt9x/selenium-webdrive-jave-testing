package webdriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_27_Wait_P5_Fluent {
	WebDriver driver;
	WebDriverWait explicitWait;
	FluentWait<WebDriver> fluentDriver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
	    System.setProperty("webdriver.gecko.driver", projectPath + "/browserdriver/geckodriver");
	    driver = new FirefoxDriver();
		
		// System.setProperty("webdriver.chrome.driver", projectPath + "/browserdriver/chromedriver");
		// driver = new ChromeDriver();
		driver.manage().window().maximize();;
	}

	@Test
	public void TC_01_() { 
		//58:32
		//1:10:32
		driver.get("https://automationfc.github.io/dynamic-loading/");
		fluentDriver = new FluentWait<WebDriver>(driver);
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		//Sau khi bấm loading icon xuất hiện và mất đi sau 5s
		//Icon loading biến mất = text hello world xuất hiện
		
		fluentDriver.withTimeout(Duration.ofSeconds(6))
		.pollingEvery(Duration.ofSeconds(1))
		.ignoring(NoSuchElementException.class)
		.until(new Function<WebDriver, String>() {
			@Override
			public String apply(WebDriver driver) {
				String text = driver.findElement(By.xpath("//h4[text()='Hello World!']")).getText();
				System.out.println(text);
				return text;
			}
			
		});
		


	}

	@Test
	public void TC_02_() {
		
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
