package webdriver;

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

public class Topic_18_web_element_exercise2 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
	    System.setProperty("webdriver.gecko.driver", projectPath + "/browserdriver/geckodriver");
	    driver = new FirefoxDriver();
		
		// System.setProperty("webdriver.chrome.driver", projectPath + "/browserdriver/chromedriver");
		// driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Displayed() { 
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement emailTextbox = driver.findElement(By.cssSelector("#mail"));
		if (emailTextbox.isDisplayed()) {
			emailTextbox.sendKeys("Automation Testing");
			System.out.print("Email Textbox is Displayed");
		}else {
			System.out.print("Email Textbox is not Displayed");
		}
		
		WebElement ageUnder18Radio = driver.findElement(By.cssSelector("#under_18"));
		if (ageUnder18Radio.isDisplayed()) {
			ageUnder18Radio.click();
			System.out.print("Age Under 18 radio is Displayed");
		}else {
			System.out.print("Age Under 18 radio is not Displayed");
		}
		
		WebElement educationTextarea = driver.findElement(By.cssSelector("#edu"));
		if (educationTextarea.isDisplayed()) {
			educationTextarea.sendKeys("Automation Testing");
			System.out.print("Education Textarea is Displayed");
		}else {
			System.out.print("Education Textarea is not Displayed");
		}
		
		WebElement name5Text = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
		if (name5Text.isDisplayed()) {
			System.out.print("Name 5 text is Displayed");
		}else {
			System.out.print("Name 5 text is not Displayed");
		}
	}

	//@Test
	public void TC_02_Enable_Disable() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement emailTextbox = driver.findElement(By.cssSelector("#mail"));
		if (emailTextbox.isEnabled()) {
			System.out.println("Email Textbox is Enable");
		} else {
			System.out.println("Email Textbox is Disable");
		}
		WebElement ageUnder18Radio = driver.findElement(By.cssSelector("#under_18"));
		if (ageUnder18Radio.isEnabled()) {
			System.out.println("Radio Age under 18 is Enable");
		} else {
			System.out.println("Radio Age under 18 is Disable");
		}
		WebElement educationTextarea = driver.findElement(By.cssSelector("#edu"));
		if (educationTextarea.isEnabled()) {
			System.out.println("Education is Enable");
		} else {
			System.out.println("Education is Disable");
		}
		WebElement JobRole1Textbox = driver.findElement(By.cssSelector("#job1"));
		if (JobRole1Textbox.isEnabled()) {
			System.out.println("Job Role 1 is Enable");
		} else {
			System.out.println("Job Role 1 is Disable");
		}
		WebElement interestsDevelopmentCheckbox = driver.findElement(By.cssSelector("#development"));
		if (interestsDevelopmentCheckbox.isEnabled()) {
			System.out.println("Interests Development Checkbox is Enable");
		} else {
			System.out.println("Interests Development Checkbox is Disable");
		}
		
		WebElement slide01 = driver.findElement(By.cssSelector("#slider-1"));
		Assert.assertTrue(slide01.isEnabled());
		//Disable
		
		WebElement passwordTextbox = driver.findElement(By.cssSelector("#disable_password"));
		if (passwordTextbox.isEnabled()) {
			System.out.println("Pass is Enable");
		} else {
			System.out.println("Pass is Disable");
		}
		WebElement jo3Textbox = driver.findElement(By.cssSelector("#job3"));
		Assert.assertFalse(jo3Textbox.isEnabled());
	}

	
	//@Test
	public void TC_03_Selected() { 
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement ageUnder18Radio = driver.findElement(By.cssSelector("#under_18"));
		WebElement javaChecbox = driver.findElement(By.cssSelector("#java"));
		
		ageUnder18Radio.click();
		javaChecbox.click();
		
		//Verify 2 fieid đã select
		Assert.assertTrue(ageUnder18Radio.isSelected());
		Assert.assertTrue(javaChecbox.isSelected());
		if (ageUnder18Radio.isSelected()) {
			System.out.println("Age Under 18 is Selected");
		} else {
			System.out.println("Age Under 18 is de-Selected");
		}
		
		if (javaChecbox.isSelected()) {
			System.out.println("java checkbox is Selected");
		} else {
			System.out.println("Java Checkbox is de-Selected");
		}
		
		ageUnder18Radio.click();
		javaChecbox.click();
		
		Assert.assertTrue(ageUnder18Radio.isSelected());
		Assert.assertFalse(javaChecbox.isSelected());
		
		if (ageUnder18Radio.isSelected()) {
			System.out.println("Age Under 18 is Selected");
		} else {
			System.out.println("Age Under 18 is de-Selected");
		}
		
		if (javaChecbox.isSelected()) {
			System.out.println("java checkbox is Selected");
		} else {
			System.out.println("Java Checkbox is de-Selected");
		}
		
	}
	
	@Test
	public void TC_04_Mailchimp() { 
		driver.get("https://login.mailchimp.com/signup/");
		driver.findElement(By.cssSelector("input#email")).sendKeys("dungnt@gmail.com");
		driver.findElement(By.cssSelector("input#new_username")).sendKeys("Dung");
		WebElement passTextbox = driver.findElement(By.cssSelector("input#new_password"));
		WebElement signupButton = driver.findElement(By.xpath("//button[@id='create-account']"));

		//lower case
		passTextbox.sendKeys("auto");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")).isDisplayed());
		Assert.assertFalse(signupButton.isEnabled());
		//Upper case
		passTextbox.clear();
		passTextbox.sendKeys("AUTO");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")).isDisplayed());
		Assert.assertFalse(signupButton.isEnabled());

		//number case
		passTextbox.clear();
		passTextbox.sendKeys("1234");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed' and text()='One number']")).isDisplayed());
		Assert.assertFalse(signupButton.isEnabled());

		//special case
		passTextbox.clear();
		passTextbox.sendKeys("%&$");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed' and text()='One special character']")).isDisplayed());
		Assert.assertFalse(signupButton.isEnabled());

		//8 chars
		passTextbox.clear();
		passTextbox.sendKeys("12345678");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed' and text()='One number']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']")).isDisplayed());
		Assert.assertFalse(signupButton.isEnabled());

		//Combine
		passTextbox.clear();
		sleepInSecond(2);
		passTextbox.sendKeys("Dung123$%&");
		driver.findElement(By.cssSelector("input#marketing_newsletter")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()=\"Your password is secure and you're all set!\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("input#marketing_newsletter")).isSelected());
		Assert.assertTrue(signupButton.isEnabled());

					
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
