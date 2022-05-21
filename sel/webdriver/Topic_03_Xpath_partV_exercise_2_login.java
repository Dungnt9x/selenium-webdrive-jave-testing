package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Xpath_partV_exercise_2_login {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String firstName, lastName, fullName, emailAddress, Password;

	@BeforeClass
	public void beforeClass() {
	    System.setProperty("webdriver.gecko.driver", projectPath + "/browserdriver/geckodriver");
	    driver = new FirefoxDriver();
		
		// System.setProperty("webdriver.chrome.driver", projectPath + "/browserdriver/chromedriver");
		// driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		firstName = "Nguyen"; 
		lastName = "Dung";
		fullName = firstName + " " + lastName;
		emailAddress = "Dungnguyen" + generateRandomNumber() + "@gmail.com";
		Password = "123456";
	}

	@Test
	public void Login_01_Empty_Data() { 
		driver.get("http://live.techpanda.org/index.php/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.name("login[password]")).sendKeys("");
		driver.findElement(By.cssSelector("button[title='Login']")).click();
		//So sánh1 chuỗi tìm được với 1 chuỗi mong muốn
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
		
       
	}

	@Test
	public void Login_02_Invalid_Email() {
		driver.get("http://live.techpanda.org/index.php/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("123456@fh");
		driver.findElement(By.name("login[password]")).sendKeys("123456");
		driver.findElement(By.cssSelector("button[title='Login']")).click();
		//So sánh1 chuỗi tìm được với 1 chuỗi mong muốn
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
	
		
	}

	@Test
	public void Login_03_Pass_Less_Than_6_Chars() { 
		driver.get("http://live.techpanda.org/index.php/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("dungnt@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("1234");
		driver.findElement(By.cssSelector("button[title='Login']")).click();
		//So sánh1 chuỗi tìm được với 1 chuỗi mong muốn
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
	
	}

	@Test
	public void Login_04_Incorrtct_Email_Pass() { 
		driver.get("http://live.techpanda.org/index.php/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("dungnt@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("1234567");
		driver.findElement(By.cssSelector("button[title='Login']")).click();
		//So sánh1 chuỗi tìm được với 1 chuỗi mong muốn
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(), "Invalid login or password.");
		
	
	}
	
	@Test
	public void Login_05_Create_New_Account() { 
		driver.get("http://live.techpanda.org/index.php/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		driver.findElement(By.name("firstname")).sendKeys(firstName);
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.name("email")).sendKeys(emailAddress);
		driver.findElement(By.name("password")).sendKeys(Password);
		driver.findElement(By.name("confirmation")).sendKeys(Password);
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		//So sánh tuyệt đối
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Thank you for registering with Main Website Store.");
		//So sánh tương đối
		String contactInforText = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		Assert.assertTrue(contactInforText.contains(fullName));
		Assert.assertTrue(contactInforText.contains(emailAddress));
		
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		

	}
	
	@Test
	public void Login_06_Valid_Email_Pass() { 
		driver.get("http://live.techpanda.org/index.php/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys(emailAddress);
		driver.findElement(By.name("login[password]")).sendKeys(Password);
		driver.findElement(By.cssSelector("button[title='Login']")).click();
		
		String contactInforText = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		Assert.assertTrue(contactInforText.contains(fullName));
		Assert.assertTrue(contactInforText.contains(emailAddress));
	}


	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public int generateRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}
 }

