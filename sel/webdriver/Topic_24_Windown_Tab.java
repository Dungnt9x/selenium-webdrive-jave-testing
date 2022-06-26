package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_24_Windown_Tab {
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
	public void TC_01_Naukry_ID() { 
		// 1:14:56, 1:17:32. 1:21:04
		//Trang A
		driver.get("https://www.naukri.com/");
		//Lấy ra ID của windown rồi lưu vào
		String homePageWindown = driver.getWindowHandle();
		System.out.println("Page A" + homePageWindown);
		
		//Click vào job link (Trang B)
		driver.findElement(By.xpath("//a[@title='Search Jobs']")).click();
		sleepInSecond(3);
		System.out.println("Page A" + driver.getCurrentUrl());
		
		//Switch qua trang B
		switchToWindowByID(homePageWindown);
		
		//Sau khi Switch qua B thì in ra địa chỉ windown
		System.out.println("Page B" + driver.getCurrentUrl());
		
		//Switch về trang A
		String jobWindownID = driver.getWindowHandle();
		switchToWindowByID(jobWindownID);
		
		//Sau khi Switch về A thì in ra địa chỉ windown
		System.out.println("Page A" + driver.getCurrentUrl());
	}

	@Test
	public void TC_02_Naukry_Title() {
		//1:28:27 1:31:40 1:36:24
		//Trang A
		driver.get("https://www.naukri.com/");
		sleepInSecond(3);
				
		//Click vào job link (Trang B)
		driver.findElement(By.xpath("//a[@title='Search Jobs']")).click();
		sleepInSecond(3);
		System.out.println("naukri.com" + driver.getCurrentUrl());
		
		//Switch sang link B
		switchTowindownByLink("browse-jobs");
		System.out.println("browse-jobs" + driver.getCurrentUrl());
		
		//Về trang A
		//Để lấy được title dang tab consolor gõ document.title -> enter
		switchTowindownByTitle("Jobs - Recruitment - Job Search - Employment - Job Vacancies - Naukri.com");
		System.out.println("naukri.com" + driver.getCurrentUrl());
		
		//Click vaof register button link
		driver.findElement(By.xpath("//div[text()='Register']")).click();
		sleepInSecond(3);
		
		//Switch về trang A
		switchTowindownByTitle("Jobs - Recruitment - Job Search - Employment - Job Vacancies - Naukri.com");
		System.out.println("naukri.com" + driver.getCurrentUrl());
	}

	//@Test
	public void TC_03_() { 
	//1:52:56
		//Home
		driver.get("https://dictionary.cambridge.org/vi/");
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Đăng nhập']")).click();
		sleepInSecond(3);
		
		//Switch qua trang login
		switchTowindownByTitle("Login");
		
		//Login
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//form[@id='gigya-login-form']//input[@name='username']/following-sibling::span")).getText(), "This field is required");
		Assert.assertEquals(driver.findElement(By.xpath("//form[@id='gigya-login-form']//input[@name='password']/following-sibling::span")).getText(), "This field is required");
		
		driver.findElement(By.xpath("//form[@id='gigya-login-form']//input[@name='username']")).sendKeys("automationfc.com@gmail.com");
		driver.findElement(By.xpath("//form[@id='gigya-login-form']//input[@name='password']")).sendKeys("Automation000***");
		sleepInSecond(3);
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		
		//Switch về trang home
		//Để lấy được title dang tab consolor gõ document.title -> enter
		switchTowindownByTitle("Cambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa");
		sleepInSecond(3);
		
		//Verify Login thành công
		Assert.assertEquals(driver.findElement(By.cssSelector("header#header span.cdo-username")).getText(), "Automation FC");
		

	}
	//Có thể viêt thành 1 hàm khi nào dùng thì gọi hàm đó
	//Chỉ ap dụng khi mở 2 Tab/Windown
	public void switchToWindowByID(String currentWindownId) {
		//Lấy hết tất cả windown đang có ra và lưu vào biến
		Set<String> allWindownIDs = driver.getWindowHandles();
		
		//Dùng 1 biến tạm để duyệt qua các phần tử của Set<String>
		for (String id : allWindownIDs) {
			//Nếu có ID mà khác với ID của page hiện tại
			if (!id.equals(currentWindownId)) {
				//Switch qua ID của tab đó
				driver.switchTo().window(id);
			}
		}
	}
	
	//Có thể dùng cho nhiều tab
	public void switchTowindownByTitle(String expectedTitle) {
		//Lấy hết tất cả các ID đang có
		Set<String> allWindownIDs = driver.getWindowHandles();
		
		//Dùng 1 biến tạm để duyệt qua các phần tử của Set<String>
		for (String id : allWindownIDs) {
			//Switch vào trước mới kiểm tra điều kiện
			driver.switchTo().window(id);
			//Lấy title của page đó ra
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(expectedTitle)) {
				//Thỏa mãn đúng điều kiện là cái page/Tab m cần
				break;
			}
		}
	}
	
	public void switchTowindownByLink(String expectedRelativeLink) {
		//Lấy hết tất cả các ID đang có
		Set<String> allWindownIDs = driver.getWindowHandles();
		
		//Dùng 1 biến tạm để duyệt qua các phần tử của Set<String>
		for (String id : allWindownIDs) {
			//Switch vào trước mới kiểm tra điều kiện
			driver.switchTo().window(id);
			//Lấy Link của page đó ra
			String actualLink = driver.getCurrentUrl();
			if (actualLink.contains(expectedRelativeLink)) {
				//Thỏa mãn đúng điều kiện là cái page/Tab m cần
				break;
			}
		}
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
