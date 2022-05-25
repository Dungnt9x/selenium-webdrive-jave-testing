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

public class Topic_03_Xpath_partII_exercise_1 {
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

	@Test
	public void Register_01_Empty_Data() {
		//Mở app ra
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//Click vào đăng ký button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		//Kiểm tra mess lỗi hiển thị ở các field bắt buộc
		//driver.findElement(By.id("txtFirstname-error")).getText();
		//Kiểm tra 1 điều kiện trả về là bằng với điều kiện mong muốn
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");
	}

	@Test
	public void Register_02_Invalid_Email() {
		//Mở app ra
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//Nhập liệu
		driver.findElement(By.id("txtFirstname")).sendKeys("Hoang Nguyen");
		driver.findElement(By.id("txtEmail")).sendKeys("123@456@789");
		driver.findElement(By.id("txtCEmail")).sendKeys("123@456@789");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("0978564321");
		//Click vào đăng ký button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		//Kiểm tra mess lỗi hiển thị ở các field bắt buộc
		//driver.findElement(By.id("txtFirstname-error")).getText();
		//Kiểm tra 1 điều kiện trả về là bằng với điều kiện mong muốn
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập nhập lại email hợp lệ");
		
	}

	@Test
	public void Register_03_Incorect_Confirm_Email() { 
		//Mở app ra
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//Nhập liệu
		driver.findElement(By.id("txtFirstname")).sendKeys("Hoang Nguyen");
		driver.findElement(By.id("txtEmail")).sendKeys("hoangnguyen@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("hoangnguyen@gmail.vn");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("0978564321");
		//Click vào đăng ký button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		//Kiểm tra mess lỗi hiển thị ở các field bắt buộc
		//driver.findElement(By.id("txtFirstname-error")).getText();
		//Kiểm tra 1 điều kiện trả về là bằng với điều kiện mong muốn
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
	
	}

	@Test
	public void Register_04_Password_Less_Than_6_Character() { 
		//Mở app ra
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//Nhập liệu
		driver.findElement(By.id("txtFirstname")).sendKeys("Hoang Nguyen");
		driver.findElement(By.id("txtEmail")).sendKeys("hoangnguyen@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("hoangnguyen@gmail.vn");
		driver.findElement(By.id("txtPassword")).sendKeys("123");
		driver.findElement(By.id("txtCPassword")).sendKeys("123");
		driver.findElement(By.id("txtPhone")).sendKeys("0978564321");
		//Click vào đăng ký button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		//Kiểm tra mess lỗi hiển thị ở các field bắt buộc
		//driver.findElement(By.id("txtFirstname-error")).getText();
		//Kiểm tra 1 điều kiện trả về là bằng với điều kiện mong muốn
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
	
	}
	
	@Test
	public void Register_05_Incorect_Conffirm_Password() { 
		//Mở app ra
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//Nhập liệu
		driver.findElement(By.id("txtFirstname")).sendKeys("Hoang Nguyen");
		driver.findElement(By.id("txtEmail")).sendKeys("hoangnguyen@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("hoangnguyen@gmail.vn");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("abc789");
		driver.findElement(By.id("txtPhone")).sendKeys("0978564321");
		//Click vào đăng ký button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		//Kiểm tra mess lỗi hiển thị ở các field bắt buộc
		//driver.findElement(By.id("txtFirstname-error")).getText();
		//Kiểm tra 1 điều kiện trả về là bằng với điều kiện mong muốn
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Mật khẩu bạn nhập không khớp");
	
	}
	
	@Test
	public void Register_06_Invalid_Phone_Number() { 
		//Mở app ra
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//Nhập liệu
		driver.findElement(By.id("txtFirstname")).sendKeys("Hoang Nguyen");
		driver.findElement(By.id("txtEmail")).sendKeys("hoangnguyen@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("hoangnguyen@gmail.vn");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123789");
		driver.findElement(By.id("txtPhone")).sendKeys("097856432128");
		//Click vào đăng ký button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		//Kiểm tra mess lỗi hiển thị ở các field bắt buộc
		//driver.findElement(By.id("txtFirstname-error")).getText();
		//Kiểm tra 1 điều kiện trả về là bằng với điều kiện mong muốn
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");
		
	}
	@Test
	public void Register_07_Invalid_Phone_Number_2() {
		//Mở app ra
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//Nhập liệu mobile đầu số không phải của nhà mạng
		driver.findElement(By.id("txtFirstname")).sendKeys("Hoang Nguyen");
		driver.findElement(By.id("txtEmail")).sendKeys("hoangnguyen@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("hoangnguyen@gmail.vn");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123789");
		driver.findElement(By.id("txtPhone")).sendKeys("5323456789");
		//Click vào button đăng ký
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		//Kiểm tra 1 điều kiện trả về bằng với điều kiện mong muốn
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
 }

