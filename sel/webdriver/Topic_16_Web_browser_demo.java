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

public class Topic_16_Web_browser_demo {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
	    System.setProperty("webdriver.gecko.driver", projectPath + "/browserdriver/geckodriver");
	    driver = new FirefoxDriver();
		
	}

	@Test
	public void TC_01_() { 
		//Dùng để close broser/tab
		//Nếu chỉ có 1 tab: close browser
		//Nếu có nhiều tab: close tab đang active
			driver.close();
		//Dùng để đón browser luôn
		//Không quan tâm bao nhiêu tab
			driver.quit();
		//Mở 1 URL
			driver.get("");
		//********** WAIT ************
		//Tìm 1 element
			driver.findElement(By.xpath(""));
		//Tìm nhiều element
			driver.findElements(By.xpath(""));	
		//Lấy ra URL của page hiên tại
			driver.getCurrentUrl();
		//Lấy ra Source code (HTML, CSS, JS) của Page hiện tại
			driver.getPageSource();
		//Lấy ra title của page hiện tại
			driver.getTitle();
		//********** Handle WINDOWN/TAB ************
		//Dùng để xử lý windown/tab
		//Lấy ra ID của tab đang active
			driver.getWindowHandle();
		//Lấy ra ID của tất cả các tab/windown đang có
			driver.getWindowHandles();
		//Cookie lưu phiên đăng nhập/sessin/dữ liệu duyệt web
			driver.manage().deleteAllCookies();
		//************ Framework log ************
		     //driver.manage().logs().get(log.);
		//Chờ cho findElement/findElements
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		//Chờ 1 page load thành công
			driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		//************** JS Executor ***************
		//Chờ cho 1 đoạn code JS thực thi thành công
			driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
		//Full hết toàn màn hình
			driver.manage().window().fullscreen();
		//Mở rộng hết cửa sổ
			driver.manage().window().maximize();
		//Set vị trí của browser so với độ phân giải màn hình (Resolution)
			//driver.manage().window().setPosition(new Point(0, 0)); //lấy theo độ phân giải màn hình, 0,0 là vị trí màn hình ở cao nhất
			//driver.manage().window().getPosition()
			
		//Mở browser với kích thước bao nhiêu
		//Test Responsive (độ phân giải)
			//driver.manage().window().getSize(new Dimension(1920, 1080));
			driver.manage().window().getSize();
		//Tracking History tốt hơn 
			driver.navigate().back();  //quay trở lại
			driver.navigate().forward(); //đi đến bước tiếp
			driver.navigate().refresh();
			driver.navigate().to("https://www.facebook.com/");
			
		//Alert
			driver.switchTo().alert(); 
		//Frame/Iframe
			driver.switchTo().frame(0);
		//Windown/Tab
			driver.switchTo().window("");

	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
