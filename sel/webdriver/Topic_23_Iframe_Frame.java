package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_23_Iframe_Frame {
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
	public void TC_01_Kyna() { 
		//36, 47:20, 52:56
		driver.get("https://kyna.vn/");
		
		//Switch từ main vào iframe đầu tiên
		//Switch vào iframe rồi mới tháo tác lên iframe/element
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.face-content>iframe")));
		//Verify số lượng like trên facebook là 167.000 like
		Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText(), "166K likes");
		
		//Switch từ Iframe về main
		driver.switchTo().defaultContent();
		//Switch vào Iframe chat trước
		driver.switchTo().frame("cs_chat_iframe");
		//Click vào chat để bật popup lên
		driver.findElement(By.cssSelector("div.meshim_widget_widgets_BorderOverlay")).click();
		
		//Nhập thông tin
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("Justin");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0397888888");
		driver.findElement(By.cssSelector("textarea[name='message']")).sendKeys("study");
		sleepInSecond(2);
		
		//Từ Iframe Chat về main
		driver.switchTo().defaultContent();
		
		String keyword = "Excel";
		
		//Nhập và search
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys(keyword);
		driver.findElement(By.cssSelector("button.search-button")).click();
		
		//Verify course name chứa từ khóa vừa chọn
		List <WebElement> courseName = driver.findElements(By.cssSelector("div.content>h4"));
		for (WebElement course : courseName) {
			System.out.println(course.getText());
			Assert.assertTrue(course.getText().contains(keyword));
		}
	}

	//@Test
	public void TC_02__Automationfc() {
		driver.get("https://automationfc.com/2020/02/18/training-online-automation-testing/");
		
		//Switch sang Iframe youtube.com
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.wp-block-embed__wrapper iframe")));
		
		driver.findElement(By.cssSelector("button.ytp-large-play-button")).click();
		sleepInSecond(3);
		
		//Switch từ iframe về main
		driver.switchTo().defaultContent();
		
		//Từ main switch về iframe
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.fb-page  iframe")));
		//Verify iframe
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Automation FC']")).isDisplayed());
	}

	@Test
	public void TC_03_Frame() { 
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		//Switch sang Frame 
		driver.switchTo().frame("login_page");
		
		driver.findElement(By.name("fldLoginUserId")).sendKeys("automationtest");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(5);
		
		//Pass textbox is Displayed
		Assert.assertTrue(driver.findElement(By.name("fldPassword")).isDisplayed());
		
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
