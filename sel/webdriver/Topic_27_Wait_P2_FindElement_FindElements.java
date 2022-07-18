package webdriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_27_Wait_P2_FindElement_FindElements {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
	    System.setProperty("webdriver.gecko.driver", projectPath + "/browserdriver/geckodriver");
	    driver = new FirefoxDriver();
		
		// System.setProperty("webdriver.chrome.driver", projectPath + "/browserdriver/chromedriver");
		// driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.facebook.com/");
	}

	//@Test
	public void TC_01_() { 
		//Có duy nhất 1 element
		//Nếu element xuất hiện ngay -> thì trả về element đó không cần hết timeout
		//Nếu element không xuất hiện -> cứ 0.5s sẽ tìm lại cho đến khi hết timeout thì thôi
		//Trả về duy nhất 1 element
		//System.out.println("Start time= " + getCurrentTime());
		//driver.findElement(By.xpath("//input[@id='email']"));
		//System.out.println("End time= " + getCurrentTime());
		
		// - Không có element nào hết
		//Nó sẽ tìm đi tìm lại cho đến khi hết timeout
		//Sau khi hết timeout thì đánh fail cả testcase này
		//Không chạy các step tiếp theo
		//Log ra 1 exception: NoSuchElement- k tìm thấy element
		//System.out.println("Start time= " + getCurrentTime());
		//driver.findElement(By.xpath("//input[@id='automation']"));
		//System.out.println("End time= " + getCurrentTime());
		
		//Nhiều hơn 1 element
		//Lấy ra element đầu tiên để thao tác
		driver.findElement(By.xpath("//div[@id='pageFooterChildren']//a[text()]")).click();
	}

	@Test
	public void TC_02_() {
		int elementNumber = 0;
		//Có duy nhất 1 element
		//Có nhiều hơn 1 element
		//Nếu element xuất hiện ngay -> thì trả về element đó không cần hết timeout
		//Nếu element không xuất hiện -> cứ 0.5s sẽ tìm lại cho đến khi hết timeout thì thôi
		elementNumber = driver.findElements(By.xpath("//input[@id='email']")).size();
		System.out.println("1 element = " + elementNumber);
		
		elementNumber = driver.findElements(By.xpath("//div[@id='pageFooterChildren']//a[text()]")).size();
		System.out.println("n element = " + elementNumber);
		
		// - Không có element nào hết
		//Nó sẽ tìm đi tìm lại cho đến khi hết timeout
		//Sau khi hết timeout thì không đánh fail step này
		//Chạy các step tiếp theo
		System.out.println("Start time= " + getCurrentTime());
		elementNumber = driver.findElements(By.xpath("//input[@id='automation']")).size();
		System.out.println("0 element = " + elementNumber);
		System.out.println("End time= " + getCurrentTime());
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
