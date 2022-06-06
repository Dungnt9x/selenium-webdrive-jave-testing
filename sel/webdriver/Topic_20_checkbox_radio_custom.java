package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_checkbox_radio_custom {
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Custom_Checkbox() { 
		driver.get("https://material.angular.io/components/checkbox/examples");
		//Hàm is.Selected chỉ work với loại Element là checkbox/radio (phải là thẻ input)
		
		//Case 1
		//Thẻ input không click được -> Failed
		//Thẻ input dùng verify dc
		//driver.findElement(By.xpath("//span[text()='Check me!']/preceding-sibling::span/input")).click();
		
		//Không dùng thẻ input để click - dùng thẻ span chứa text => passed
		//Không dùng thẻ input để verify => Faild
		//By checkedCheckbox = By.xpath("//span[text()='Check me!']");
		//driver.findElement(checkedCheckbox).click();
		//sleepInSecond(3);
		
		//Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());

		//Case 3: thỏa mãn điều kiện vừa click vừa verify
		//Vấn đề 1: 1 element mà phải define tới 2 locator/by
		//Maitain code sẽ sinh ra nhiều phụ thuộc
		//By checkedCheckboxText = By.xpath("//span[text()='Check me!']");
		//driver.findElement(checkedCheckboxText).click();
		//sleepInSecond(3);
		//By checkedCheckbox = By.xpath("//span[text()='Check me!']/preceding-sibling::span/input");
		//driver.findElement(checkedCheckbox).isSelected();
		
		//Case 4: thỏa mãn điều kiện vừa click vừa verify
		//Ther input để click => Pass (JS executor)
		//JS k quan tâm element bị che hay không (vẫn click được)
		By checkedCheckbox = By.xpath("//span[text()='Check me!']/preceding-sibling::span/input");
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(checkedCheckbox));
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());
		
		//Radio button
		By beforeRadio = By.xpath("//span[text()='Before']/preceding-sibling::span/input");
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(beforeRadio));
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(beforeRadio).isSelected());
		
	}

	@Test
	public void TC_02_Not_The_Input() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		
		//Radio
		By canthoRadio = By.xpath("//div[@data-value='Cần Thơ']");
		driver.findElement(canthoRadio).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(canthoRadio).getAttribute("aria-checked"), "true");
		
		//Radio
		By miquangRadio = By.xpath("//div[@aria-label='Mì Quảng']/parent::div");
		driver.findElement(miquangRadio).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@aria-label='Mì Quảng']")).getAttribute("aria-checked"), "true");
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
