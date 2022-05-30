package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Handle_default_dropdown {
	WebDriver driver;
	Select select;
	String firstName, lastName, day, month, year, emailAddress, company, password;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
	    System.setProperty("webdriver.gecko.driver", projectPath + "/browserdriver/geckodriver");
	    driver = new FirefoxDriver();
		
		// System.setProperty("webdriver.chrome.driver", projectPath + "/browserdriver/chromedriver");
		// driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		firstName = "Barac";
		lastName = "Obama";
		day = "20";
		month = "May";
		year = "1960";
		emailAddress = "Obama"  + generateRandomNumber() + "@gmail.com";
		company = "white house"; 
		password = "123456";

	}

	@Test
	public void TC_01_Nop_Commerce() { 
		// I.input
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys(firstName);
		driver.findElement(By.cssSelector("input#LastName")).sendKeys(lastName);
		
		//dropdown
		  //<select name="DateOfBirthDay">
				//<option value="0">Day</option>
				//<option value="1">1</option>
				//<option value="2">2</option>
				//<option value="3">3</option>
				//<option value="4">4</option>
				//<option value="5">5</option>
		  //</select>
		//Kho nào dùng để thao tác với element mới khởi tạo
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		
		//Thao tác với dropdown
		//Thứ tự của thẻ option
		   //select.selectByIndex(1);
		   //select.selectByIndex(5);
		
		//Value: Gá trị của thuộc tính value
			//select.selectByValue("5");
		
		//text: Item text (nên dùng)
			//select.selectByVisibleText("Day");
			//select.selectByVisibleText("5");
		
		//Chọn item Day
		select.selectByVisibleText(day);
		//Kiểm tra đã chọn được hay chưa?
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);
		//Kiểm tra 1 dropdown có phải Multical không?
		Assert.assertFalse(select.isMultiple());
		 
		//Khởi tạo lại Month
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText(month);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		
		//Khởi tạo cho Year
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText(year);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		
		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Company")).sendKeys(company);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		driver.findElement(By.id("register-button")).click();
		
		// II.Verify 
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
		
		driver.findElement(By.cssSelector("a.ico-account")).click();
		// Verify đăng ký thành công
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), emailAddress);
		Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"), company);


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
	public int generateRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}
}
