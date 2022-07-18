package webdriver;

import java.io.File;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_27_Wait_P3_Explicit {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String aFile = "01A535E0-3EA0-4918-9376-68382FBEA1F2.jpeg";
	String bFile = "254162169_2107203636103098_6988641684673644280_n.jpeg";
	String cFile = "272747844_1895905023927614_2742019531332076772_n.jpeg";
	
	// Dùng file.separator để tự nhận dạng trình duyệt khi chạy
	String uploadFileFolderPath = projectPath + File.separator + "Uploadfiles" + File.separator;
	String aFilePath = uploadFileFolderPath + aFile;
	String bFilePath = uploadFileFolderPath + bFile;
	String cFilePath = uploadFileFolderPath + cFile;
	
	By loadingIcon = By.cssSelector("div#loading");
	By helloText = By.cssSelector("div#finish>h4");

	@BeforeClass
	public void beforeClass() {
	    System.setProperty("webdriver.gecko.driver", projectPath + "/browserdriver/geckodriver");
	    driver = new FirefoxDriver();
		
		// System.setProperty("webdriver.chrome.driver", projectPath + "/browserdriver/chromedriver");
		// driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//driver.get("https://automationfc.github.io/dynamic-loading/");
	}

	//@Test
	public void TC_01_Equal() {
		explicitWait = new WebDriverWait(driver, 5);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		//loading icon biến mất sau 5s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	}
	//@Test
	public void TC_02_less() {
		explicitWait = new WebDriverWait(driver, 3);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		//loading icon biến mất sau 3s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	}
	
	//@Test
	public void TC_03_Invisible_Greate() {
		explicitWait = new WebDriverWait(driver, 30);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		//loading icon biến mất sau 30s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		
		Assert.assertEquals(driver.findElement(helloText).getText(), "Hello World!");
	}
	
	//@Test
	public void TC_04_Visible() {
		explicitWait = new WebDriverWait(driver, 30);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		//loading icon biến mất sau 15s
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloText));
		
		Assert.assertEquals(driver.findElement(helloText).getText(), "Hello World!");
	}
	
	//@Test
	public void TC_04_Number() {
		explicitWait = new WebDriverWait(driver, 30);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		//Hello text element = 1
		explicitWait.until(ExpectedConditions.numberOfElementsToBe(helloText, 1));
		
		Assert.assertEquals(driver.findElement(helloText).getText(), "Hello World!");
	}

	//@Test
	public void TC_05_Exercise_Ajax_Loading() {
		explicitWait = new WebDriverWait(driver, 30);
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		//Wait cho Datepicker xuất hiện
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceholder1_RadCalendar1")));
		
		//Element này được tìm lại tại thời điểm chưa click lên ngày 11
		WebElement selectDateText = driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"));
		Assert.assertEquals(selectDateText.getText(), "No Selected Dates to display.");
		
		//Wait cho ngày 11 có thể click và sau đó click nó lên
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='11']"))).click();
		//wait loading icon biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1']>div.raDiv")));
		
		//Sau khi click vào ngày 11 thì element có text được cập nhật lại
		//Nếu như dùng lại element đã được gọi ở trên để get text là sai -> gọi lại element đó
		selectDateText = driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"));
		//Verify ngày được update
		Assert.assertEquals(selectDateText.getText(), "Monday, July 11, 2022");
		
		//Wait cho này được selected thành công
		WebElement todaySelected = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='rcSelected']/a[text()='11']")));
		
		//Verify ngày được chọn
		Assert.assertTrue(todaySelected.isDisplayed());
		
	}

	@Test
	public void TC_06_Loading() {
		explicitWait = new WebDriverWait(driver, 30);
		driver.get("https://gofile.io/uploadFiles");
		By uploadFile = By.xpath("//input[@type='file']");
		//Load file lên
		driver.findElement(uploadFile).sendKeys(aFilePath + "\n" + bFilePath + "\n" + cFilePath);
		
		//Wait cho file được upload thành công trong 30s
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress"))));
		
		//Wait cho text đực visible
		WebElement uploadFile1 = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Your files have been successfully uploaded']")));
		Assert.assertTrue(uploadFile1.isDisplayed());
		
		//Mở link
		driver.get(driver.findElement(By.xpath("//a[@id='rowUploadSuccess-downloadPage']")).getAttribute("href"));
		
		//Verify download button
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='" + bFile + "']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='" + bFile + "']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='" + cFile + "']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']")).isDisplayed());
		
		//Verify play button
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='" + aFile + "']/parent::a/parent::div/following-sibling::div//button[contains(@class,'contentPlay')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='" + bFile + "']/parent::a/parent::div/following-sibling::div//button[contains(@class,'contentPlay')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='" + cFile + "']/parent::a/parent::div/following-sibling::div//button[contains(@class,'contentPlay')]")).isDisplayed());
		
		
		
		
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
