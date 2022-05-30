package webdriver;

import java.util.Random;
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

public class Topic_19_Textbox_textarea_exercise2 {
	WebDriver driver;
	String firstName, lastName, editFirstName, editLastName;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
	    System.setProperty("webdriver.gecko.driver", projectPath + "/browserdriver/geckodriver");
	    driver = new FirefoxDriver();
		
		// System.setProperty("webdriver.chrome.driver", projectPath + "/browserdriver/chromedriver");
		// driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		firstName = "Luis" + generateRandomNumber();
		lastName = "Suarez" + generateRandomNumber();
		editFirstName = "Lisa" + generateRandomNumber();
		editLastName = "Honey" + generateRandomNumber();
	}

	@Test
	public void TC_01_Textbox_Textarea() { 
		//Lấy giá trị trong textbox hàm .getAttribute();
				//driver.findElement(By.cssSelector("input[title='First Name']")).getAttribute("value");
		
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.findElement(By.cssSelector("input#txtUsername")).sendKeys("Admin");
		driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("admin123");
		driver.findElement(By.cssSelector("input#btnLogin")).click();
		driver.findElement(By.xpath("//a[@id='menu_pim_viewPimModule']/b")).click();
		sleepInSecond(5);
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
		//Lưu giá trị employeeed vào biến
		String employeeId = driver.findElement(By.cssSelector("input#employeeId")).getText();

		driver.findElement(By.cssSelector("input#firstName")).sendKeys(firstName);
		driver.findElement(By.cssSelector("input#lastName")).sendKeys(lastName);

		driver.findElement(By.cssSelector("input#btnSave")).click();
		sleepInSecond(5);
		
		//Verify Update thành công
		Assert.assertEquals(driver.findElement(By.cssSelector("input[title='First Name']")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.cssSelector("input[title='Last Name']")).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#personal_txtEmployeeId")).getText(), employeeId);
		
		//Verify Disable
		WebElement firstName = driver.findElement(By.cssSelector("input#personal_txtEmpFirstName"));
		Assert.assertFalse(firstName.isEnabled());
		WebElement lastName = driver.findElement(By.cssSelector("input#personal_txtEmpLastName"));
		Assert.assertFalse(lastName.isEnabled());
		WebElement employeeNumber = driver.findElement(By.cssSelector("input#personal_txtEmployeeId"));
		Assert.assertFalse(employeeNumber.isEnabled());
		
		//Edit
		driver.findElement(By.cssSelector("input#btnSave")).click();
		sleepInSecond(3);
		//Khai báo biến để input
		WebElement firstNameInput = driver.findElement(By.cssSelector("input[title='First Name']"));
		WebElement lastNameInput = driver.findElement(By.cssSelector("input[title='Last Name']"));
		
		//Xóa để nhập mới
		firstNameInput.clear();
		firstNameInput.sendKeys(editFirstName);
		lastNameInput.clear();
		lastNameInput.sendKeys(editLastName);
		

		//Verify Enable
		Assert.assertTrue(firstNameInput.isEnabled());
		Assert.assertTrue(lastNameInput.isEnabled());
		
		driver.findElement(By.cssSelector("input#btnSave")).click();
		sleepInSecond(5);
		

		//verify edit thành công
		Assert.assertEquals(driver.findElement(By.cssSelector("input[title='First Name']")).getAttribute("value"), editFirstName);
		Assert.assertEquals(driver.findElement(By.cssSelector("input[title='Last Name']")).getAttribute("value"), editLastName);

		//Verify Disable
		WebElement editFirstName = driver.findElement(By.cssSelector("input#personal_txtEmpFirstName"));
		Assert.assertFalse(editFirstName.isEnabled());
		WebElement editLastName = driver.findElement(By.cssSelector("input#personal_txtEmpLastName"));
		Assert.assertFalse(editLastName.isEnabled());
		WebElement editEmployeeNumber = driver.findElement(By.cssSelector("input#personal_txtEmployeeId"));
		Assert.assertFalse(editEmployeeNumber.isEnabled());

		
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
