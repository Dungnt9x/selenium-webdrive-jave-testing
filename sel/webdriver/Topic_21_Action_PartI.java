package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_21_Action_PartI {
	WebDriver driver;
	Actions action;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
	    System.setProperty("webdriver.gecko.driver", projectPath + "/browserdriver/geckodriver");
	    driver = new FirefoxDriver();
	    action = new Actions(driver);
		// System.setProperty("webdriver.chrome.driver", projectPath + "/browserdriver/chromedriver");
		// driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Hover() { 
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		WebElement ageTextbox = driver.findElement(By.id("age"));
		//Hover chuột vào Textbox
		action.moveToElement(ageTextbox).perform();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.className("ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");

	}

	//@Test
	public void TC_02_Hover() {
		driver.get("http://www.myntra.com/");
		sleepInSecond(5);
		
		action.moveToElement(driver.findElement(By.xpath("//header//a[text()='Kids']"))).perform();
		sleepInSecond(3);
		
		//Click vào đối tượng
		action.click(driver.findElement(By.xpath("//header//a[text()='Home & Bath']"))).perform();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Kids Home Bath']")).getText(), "Kids Home Bath");
	}

	//@Test
	public void TC_03_Click_And_Hold() { 
		driver.get("https://automationfc.github.io/jquery-selectable/");
	
		//Khai báo và lưu trữ lại 12 element
		List<WebElement> allNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));
		
		//Click nd hover vào 1
		//Hover tơí 4
		//Nhả chuột trái ra
		//Thực thi câu lệnh
		action.clickAndHold(allNumbers.get(0)).moveToElement(allNumbers.get(3)).release().perform();
		sleepInSecond(3);
		
		//Verify
		List<WebElement> allNumberCheck = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(allNumberCheck.size(), 4);
	}
	@Test
	public void TC_04_Random() { 
		driver.get("https://automationfc.github.io/jquery-selectable/");
	
		//Khai báo và lưu trữ lại 12 element
		List<WebElement> allNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));
		
		Keys controlKey;
		
		if (osName.contains("Windown")) {
			controlKey = Keys.CONTROL;
		} else {
			controlKey = Keys.COMMAND;
		}
		//Chọn 1, 5 và 11
		//Nhấn phím Ctrl xuống (chưa nhả ra)
		//Click vào 1
		//Click vào 5
		//Click vào 11
		//Thực thi câu lệnh
		//Nhả phím Control
		action.keyDown(controlKey).perform();
		action.click(allNumbers.get(0)).click(allNumbers.get(4)).click(allNumbers.get(10)).perform();
		action.keyUp(controlKey).perform();
		sleepInSecond(3);
		
		//Verify
		List<WebElement> allNumberCheck = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(allNumberCheck.size(), 3);
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
