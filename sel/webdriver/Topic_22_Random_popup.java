package webdriver;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_22_Random_popup {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
	    System.setProperty("webdriver.gecko.driver", projectPath + "/browserdriver/geckodriver");
	    driver = new FirefoxDriver();
	    jsExecutor = (JavascriptExecutor) driver;
		
		// System.setProperty("webdriver.chrome.driver", projectPath + "/browserdriver/chromedriver");
		// driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Random_In_Dom_VNK() { 
		driver.get("https://vnk.edu.vn/");
		sleepInSecond(15);
		
		//Step 2: Luôn có Element trong DOM dù có hiển thị hay không
		if (driver.findElement(By.cssSelector("div#tve_editor")).isDisplayed()) {
			System.out.println("Case 1- Nếu pop up hiển thị thì có thể thao tác với popup rồi clear nó đi -> qua step tiếp theo");
			//Close popup đi
			driver.findElement(By.cssSelector("div.tcb-icon-display")).click();
			sleepInSecond(2);
			
			//Expected popup không còn hiển thị nữa
			Assert.assertFalse(driver.findElement(By.cssSelector("div#tve_editor")).isDisplayed());
		} else {
			System.out.println("Case 2- Nếu pop up không hiển thị thì -> qua step tiếp theo");
		}
		
		//Step 3: Click vào trang liên hệ
		driver.findElement(By.xpath("//a[@title='Liên hệ']")).click();
		
		//Step 4: Verifyqua trang liên hệ thành công
		Assert.assertTrue(driver.findElement(By.cssSelector("div.title-content>h1")).isDisplayed());

	}

	//@Test
	public void TC_02_Random_In_Dom_Kmplayer() {
		driver.get("https://kmplayer.com/home");
		
		//Step 2: Luôn có Element trong DOM dù có hiển thị hay không
		if (driver.findElement(By.cssSelector("div.pop-layer")).isDisplayed()) {
		System.out.println("Case 1- Nếu pop up hiển thị thì có thể thao tác với popup rồi clear nó đi -> qua step tiếp theo");
		//Close popup đi
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("area#btn-r"))); 
		sleepInSecond(2);
					
		//Expected popup không còn hiển thị nữa
		Assert.assertFalse(driver.findElement(By.cssSelector("div.pop-layer")).isDisplayed());
		} else {
			System.out.println("Case 2- Nếu pop up không hiển thị thì -> qua step tiếp theo");
		}
				
		//Step 3: Click vào trang liên hệ
		driver.findElement(By.xpath("//a[contains(text(),'MOVIEBLOC')]")).click();
		sleepInSecond(7);
				
		//Step 4: Verifyqua trang liên hệ thành công
		Assert.assertTrue(driver.findElement(By.cssSelector("section.main_top_banner_img")).isDisplayed());
	}

	@Test
	public void TC_03_Random_Not_In_Dom_Dehieu() { 
		driver.get("https://dehieu.vn/");
		sleepInSecond(10);
		
		System.out.println("Find-popup Start:" + getCurrentDateTime());
		List <WebElement> popupContent = driver.findElements(By.cssSelector("div.popup-content"));
		System.out.println("Find-popup End:" + getCurrentDateTime());
		
		//Step 2: 
		//Trả về số lượng phần tử
		if (popupContent.size() > 0) {
		System.out.println("Case 1- Nếu pop up hiển thị thì có thể thao tác với popup rồi clear nó đi -> qua step tiếp theo");
		//Thao tác với popup
		driver.findElement(By.cssSelector("input#popup-name")).sendKeys("Jonh");
		driver.findElement(By.cssSelector("input#popup-email")).sendKeys("Jonh@gmail.com");
		driver.findElement(By.cssSelector("input#popup-phone")).sendKeys("0397000123");
		
		//Close popup đi
		driver.findElement(By.cssSelector("button#close-popup")).click();
					
		//Verify popup không còn hiển thị nữa
		System.out.println("Find-popup Start:" + getCurrentDateTime());
		Assert.assertEquals(driver.findElements(By.cssSelector("div.popup-content")).size(), 0);
		System.out.println("Find-popup End:" + getCurrentDateTime());
		} else {
			//Nếu setting popup vào ngày xxx nào đó để chạy chương trình maketing
			// Sau thời gian này thì settin nó k hiển thị popup nữa: mở page ra nó k render cái popup này ra nữa
			//Không có trong DOM ngay từ đầu luôn
			System.out.println("Case 2- Nếu pop up không hiển thị thì -> qua step tiếp theo");
		}
						
		//Step 3: Click vào trang liên hệ
		driver.findElement(By.xpath("//a[text()='Tất cả khóa học']")).click();
		sleepInSecond(5);
						
		//Step 4: Verifyqua trang liên hệ thành công
		Assert.assertTrue(driver.findElement(By.cssSelector("ul#category-tree")).isDisplayed());
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
	public String getCurrentDateTime() {
		return new Date().toString();
	}
}
