package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_27_Wait_P1_Element_Status {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait expliciWait;

	@BeforeClass
	public void beforeClass() {
	    System.setProperty("webdriver.gecko.driver", projectPath + "/browserdriver/geckodriver");
	    driver = new FirefoxDriver();
	    expliciWait = new WebDriverWait(driver, 15);
		
		// System.setProperty("webdriver.chrome.driver", projectPath + "/browserdriver/chromedriver");
		// driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.facebook.com/");
	}

	//@Test
	public void TC_01_Invisible() { 
		//Visible cos treen UI và có trong DOM/HTML
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='email']")).isDisplayed());

	}

	@Test
	public void TC_02_Invisible_Indom() {
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		//invisible: Không có trên UI nhưng có trong DOM (không bắt buộc)
		//Kết quả như nhau nhưng thời gian chạy mỗi case khác nhau
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		//Không hiển thị -> pass
		Assert.assertFalse(driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']")).isDisplayed());
		
	}

	@Test
	public void TC_02_Invisible_Not_In_Dom() { 
		//Invisible không có trên UI và không có trên DOM
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		sleepInSecond(2);
		
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		//Không hiển thị -> Fail
		Assert.assertFalse(driver.findElement(By.xpath("//input[@name='reg_email_confirmation__'")).isDisplayed());
	}
	//@Test
	public void TC_03_Presence() { 
		//Presence có trong DOM/HTML và có trên UI -> pass
		expliciWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='email']")));
		
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		
		//Presence: Có trong DOM HTML và không có trên UI
		expliciWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		sleepInSecond(2);
		//Không hiển thị
		//Assert.assertFalse(driver.findElement(By.xpath("//input[@name='reg_email_confirmation__'")).isDisplayed());
	}
	//@Test
	public void TC_04_Staleness() { 
		//Bật popup đăng ký
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		sleepInSecond(3);
		//Tại thời điểm này element này đang có trong DOM. KHởi tạo biến lưu
		WebElement confirmationEmailTextbox = driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']"));
		
		//Đóng form lại
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		sleepInSecond(2);
		
		//Wait cho confirm email textbox không còn trong DOM nữa
		//Vì 1 lý do nào đó mình cần wait cho nó không còn tồn tại trong DOM nữa
		expliciWait.until(ExpectedConditions.stalenessOf(confirmationEmailTextbox));
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
