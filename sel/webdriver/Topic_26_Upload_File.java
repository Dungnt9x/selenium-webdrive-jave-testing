package webdriver;

import java.io.File;
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

public class Topic_26_Upload_File {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String aFile = "01A535E0-3EA0-4918-9376-68382FBEA1F2.jpeg";
	String bFile = "254162169_2107203636103098_6988641684673644280_n.jpeg";
	String cFile = "272747844_1895905023927614_2742019531332076772_n.jpeg";
	
	// Dùng file.separator để tự nhận dạng trình duyệt khi chạy
	String uploadFileFolderPath = projectPath + File.separator + "Uploadfiles" + File.separator;
	String aFilePath = uploadFileFolderPath + aFile;
	String bFilePath = uploadFileFolderPath + bFile;
	String cFilePath = uploadFileFolderPath + cFile;
	

	@BeforeClass
	public void beforeClass() {
	    System.setProperty("webdriver.gecko.driver", projectPath + "/browserdriver/geckodriver");
	    driver = new FirefoxDriver();
		
	    // Khi chrome tăng phiên bản thì xem lại video bài này để update version cho chrome driver
		 //System.setProperty("webdriver.chrome.driver", projectPath + "/browserdriver/chromedriver");
		 //driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	//@Test
	public void TC_01_One_File_One_time() { 
		//40:16
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		//Selenium Sendkey method
		// Không có click vào button Add file để bật Open file dialog lên
		By uploadFile = By.xpath("//input[@type='file']");
		//Load file lên
		driver.findElement(uploadFile).sendKeys(aFilePath);
		driver.findElement(uploadFile).sendKeys(bFilePath);
		driver.findElement(uploadFile).sendKeys(cFilePath);
		sleepInSecond(3);
		
		//Verify
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + aFile + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + bFile + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + cFile + "']")).isDisplayed());
		
		//Click to upload button at each file
		List<WebElement> uplaodButton = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement button : uplaodButton) {
			button.click();
			sleepInSecond(2);
		}
		
		//Verify upload success
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + aFile + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + bFile + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + cFile + "']")).isDisplayed());

	}

	//@Test
	public void TC_02_Multiple_File_One_Time() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		
		By uploadFile = By.xpath("//input[@type='file']");
		//Load file lên
		driver.findElement(uploadFile).sendKeys(aFilePath + "\n" + bFilePath + "\n" + cFilePath);
		sleepInSecond(3);
		
		//Verify
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + aFile + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + bFile + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + cFile + "']")).isDisplayed());
		
		//Click to upload button at each file
		List<WebElement> uplaodButton = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement button : uplaodButton) {
			button.click();
			sleepInSecond(2);
		}
		
		//Verify upload success
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + aFile + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + bFile + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + cFile + "']")).isDisplayed());
	}

	@Test
	public void TC_03_Go_File() { 
		driver.get("https://gofile.io/uploadFiles");
		sleepInSecond(3);
		//Thẻ input bị ẩn khi bật inspect -> gõ //input[@type='file'] để find
		By uploadFile = By.xpath("//input[@type='file']");
		//Load file lên
		driver.findElement(uploadFile).sendKeys(aFilePath + "\n" + bFilePath + "\n" + cFilePath);
		sleepInSecond(10);
		Assert.assertTrue(driver.findElement(By.xpath("//h5[text()='Your files have been successfully uploaded']")).isDisplayed());
		
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
