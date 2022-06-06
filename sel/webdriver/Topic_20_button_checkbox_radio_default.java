package webdriver;

import java.util.Iterator;
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

public class Topic_20_button_checkbox_radio_default {
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
	public void TC_01_default_checkbox() { 
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		By gallstonesCheckbox = By.xpath("//input[@value='Gallstones']");
		By EmotionalCheckbox = By.xpath("//input[@value='Emotional Disorder']");
		By TuberculosisCheckbox = By.xpath("//input[@value='Tuberculosis']");
		
		By daysRadio = By.xpath("//input[@value='5+ days']");
		By strictDietRadio = By.xpath("//input[@value=\"I have a strict diet\"]");
		By cupsRadio = By.xpath("//input[@value='3-4 cups/day']");

		
		//1. click - chọn
		//checkbox button
		driver.findElement(gallstonesCheckbox).click();
		driver.findElement(EmotionalCheckbox).click();
		driver.findElement(TuberculosisCheckbox).click();
		sleepInSecond(3);
		
		//radio
		driver.findElement(daysRadio).click();
		driver.findElement(strictDietRadio).click();
		driver.findElement(cupsRadio).click();
		sleepInSecond(3);
		
		// 2. Verify (chọn rồi hay chưa chọn)
		Assert.assertTrue(driver.findElement(gallstonesCheckbox).isSelected());
		Assert.assertTrue(driver.findElement(EmotionalCheckbox).isSelected());
		Assert.assertTrue(driver.findElement(TuberculosisCheckbox).isSelected());
		Assert.assertTrue(driver.findElement(daysRadio).isSelected());
		Assert.assertTrue(driver.findElement(strictDietRadio).isSelected());
		Assert.assertTrue(driver.findElement(cupsRadio).isSelected());
		
		//3. Bỏ chọn
		driver.findElement(gallstonesCheckbox).click();
		driver.findElement(EmotionalCheckbox).click();
		driver.findElement(TuberculosisCheckbox).click();
		sleepInSecond(3);
				
				
		driver.findElement(daysRadio).click();
		driver.findElement(strictDietRadio).click();
		driver.findElement(cupsRadio).click();
		sleepInSecond(3);
				
		// 4. Verify (bỏ chọn rồi hay chưa chọn)
		Assert.assertFalse(driver.findElement(gallstonesCheckbox).isSelected());
		Assert.assertFalse(driver.findElement(EmotionalCheckbox).isSelected());
		Assert.assertFalse(driver.findElement(TuberculosisCheckbox).isSelected());
		
		Assert.assertTrue(driver.findElement(daysRadio).isSelected());
		Assert.assertTrue(driver.findElement(strictDietRadio).isSelected());
		Assert.assertTrue(driver.findElement(cupsRadio).isSelected());
	}

	//@Test
	public void TC_02_Select_All_checkbox() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		//Chọn all checkbox
		List<WebElement> allCheckboxs = driver.findElements(By.xpath("//input[@type='checkbox']"));
		for (WebElement checkbox : allCheckboxs) {
			if (!checkbox.isSelected()) {
				checkbox.click();
				sleepInSecond(1);
			}
			Assert.assertTrue(checkbox.isSelected());
		}
	
		
		//Bỏ chọn all checkbox
		for (WebElement checkbox : allCheckboxs) {
			if (checkbox.isDisplayed()) {
				checkbox.click();
				sleepInSecond(1);
			}
			Assert.assertFalse(checkbox.isSelected());
		}
		
	}

	@Test
	public void TC_03_Exercise_02_Checkbox() { 
	driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
	sleepInSecond(7);
	By eq5Checkbox = By.xpath("//input[@id='eq5']");
	
	//chọn checkbox
	driver.findElement(eq5Checkbox).click();
	Assert.assertTrue(driver.findElement(eq5Checkbox).isSelected());
	
	//Bỏ chọn checkbox
	driver.findElement(eq5Checkbox).click();
	Assert.assertFalse(driver.findElement(eq5Checkbox).isSelected());
	}
	
	@Test
	public void TC_04_Exercise_02_Radio() { 
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		sleepInSecond(7);
		By petrolRadio = By.xpath("//input[@id='engine3']");
		
		//chọn checkbox
		driver.findElement(petrolRadio).click();
		Assert.assertTrue(driver.findElement(petrolRadio).isSelected());
		
		//Bỏ chọn checkbox
		driver.findElement(petrolRadio).click();
		Assert.assertFalse(driver.findElement(petrolRadio).isSelected());
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
