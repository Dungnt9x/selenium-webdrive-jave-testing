package webdriver;

import java.nio.channels.SeekableByteChannel;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_25_Javascript_Executor_2 {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String emailAddress;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
	    System.setProperty("webdriver.gecko.driver", projectPath + "/browserdriver/geckodriver");
	    driver = new FirefoxDriver();
	    //Ép kiểu
	    jsExecutor = (JavascriptExecutor) driver;
	    emailAddress = "Jonh" + getRandomNumber() + "@hotmail.vn";
		
		// System.setProperty("webdriver.chrome.driver", projectPath + "/browserdriver/chromedriver");
		// driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	//@Test
	//1:21:45
	public void TC_01_() { 
		//navigativeToUrlByJs("http://live.techpanda.org/");
		driver.get("http://live.techpanda.org/");
		sleepInSecond(5);
		//Lấy domain
		String homePageDomain = (String) executeForBrowser("return document.domain;");
		Assert.assertEquals(homePageDomain, "live.techpanda.org");
		
		//Lấy URL
		String homePageUrl = (String) executeForBrowser("return document.URL;");
		Assert.assertEquals(homePageUrl, "http://live.techpanda.org/");
		
		clickToElementByJs("//a[text()='Mobile']");
		sleepInSecond(3);
		
		clickToElementByJs("//a[text()='IPhone']/parent::h2/following-sibling::div/button");
		sleepInSecond(3);
		
		String shoppingCartText = getInnerText();
		Assert.assertTrue(shoppingCartText.contains("IPhone was added to your shopping cart."));
		
		clickToElementByJs("//a[text()='Customer Service']");
		sleepInSecond(5);
		
		String customerTitle = (String) executeForBrowser("return document.title;");
		Assert.assertEquals(customerTitle, "Customer Service");
		
		scrollOnToElementOnDown("//input[@id='newsletter']");
		sleepInSecond(3);
		
		sendkeyToElementByJs("//input[@id='newsletter']", emailAddress);
		sleepInSecond(3);
		
		clickToElementByJs("//button[@title='Subscribe']");
		sleepInSecond(3);
		
		Assert.assertTrue(areExoectedTextInInnerText("Thank you for your subscription."));
		sleepInSecond(3);
		
		//navigativeToUrlByJs("http://demo.guru99.com/v4/");
		driver.get("http://demo.guru99.com/v4/");
		sleepInSecond(5);
		
		homePageDomain = (String) executeForBrowser("return document.domain;");
		Assert.assertEquals(homePageDomain, "demo.guru99.com");
		
	}

	@Test
	public void TC_02_HTML5_Validation_message() {
		//1:33:03
		driver.get("https://www.pexels.com/vi-vn/join-contributor/");
		
		By firstName = By.id("user_first_name");
		By email = By.id("user_email");
		By password = By.id("user_password");
		By createButton = By.xpath("//button[contains(text(),'Tạo tài khoản mới')]");
		
		driver.findElement(createButton).click();
		sleepInSecond(3);
		Assert.assertEquals(getElementValidationMess(firstName), "Please fill out this field.");
		
		driver.findElement(firstName).sendKeys("automation");
		driver.findElement(createButton).click();
		sleepInSecond(3);
		Assert.assertEquals(getElementValidationMess(email), "Please fill out this field.");
		
		driver.findElement(email).sendKeys("123@123@456");
		driver.findElement(createButton).click();
		sleepInSecond(3);
		Assert.assertEquals(getElementValidationMess(email), "Please enter an email address.");
		
		driver.findElement(email).clear();
		driver.findElement(email).sendKeys(emailAddress);
		sleepInSecond(3);
		Assert.assertEquals(getElementValidationMess(password), "Please fill out this field.");
		
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
	public boolean areExoectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}
	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}
	public void navigativeToUrlByJs(String url) {
		jsExecutor.executeScript("windown.location = '" + url + "'");
	}
	// lấy domain
	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}
	// Click vào Element
	public void clickToElementByJs(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}
	// Scroll lên mép trên
	public void scrollOnToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}
	// Scroll xuống mép dưới
	public void scrollOnToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}
	public void sendkeyToElementByJs(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}
	public void removeAttributeInDom(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('value', '" + attributeRemove + "');", getElement(locator));
	}
	public String getElementValidationMess(By byLocator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", driver.findElement(byLocator));
	}
    //Hàm khai báo getElement
	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
}
