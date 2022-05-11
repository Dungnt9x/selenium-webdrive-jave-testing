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

public class Topic_00_Template_java {
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
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01_() { 
		//Id
		driver.findElement(By.id("email")).sendKeys("dungnt@gmail.com");
		
		//Class
		driver.findElement(By.className("fb_logo")).isDisplayed();
		//Name
		driver.findElement(By.name("email")).isDisplayed();
		
		//Tagname: Tìm xem có bn element cùng loại trên page HTML
		driver.findElements(By.tagName("a"));
		
		//Link text: Truyền cả text vào
		driver.findElement(By.linkText("Tiếng Việt"));
		
		//Css
		  driver.findElement(By.cssSelector("input[id='email']"));
		  driver.findElement(By.cssSelector("input#email"));
		  driver.findElement(By.cssSelector("#email"));
		  
		  driver.findElement(By.cssSelector("img[class='fb_logo _8ilh img']"));
		  driver.findElement(By.cssSelector("img.fb_logo"));
		  driver.findElement(By.cssSelector(".fb_logo"));

		  driver.findElement(By.cssSelector("input[name='email']"));
		  driver.findElement(By.cssSelector("a"));
		  
		  //css k làm việc với  text (dùng thuộc tính khác của thẻ a để thao tác)
		  driver.findElement(By.cssSelector("a[title='Vietnamese']"));
		  driver.findElement(By.cssSelector("a[onclick*='vi_VN']"));
		  driver.findElement(By.cssSelector("a[title*='Vietnam']"));


		//Xpath
		  driver.findElement(By.xpath("//input[@id='email']"));
		  driver.findElement(By.xpath("//img[class='fb_logo _8ilh img']"));
		  driver.findElement(By.xpath("//img[contains(@class,'fb_logo')]"));
		  driver.findElement(By.xpath("//img[starts-with(@class,'fb_logo')]"));
		  driver.findElement(By.xpath("//input[@name='email']"));
		  driver.findElement(By.xpath("//a"));


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
}
