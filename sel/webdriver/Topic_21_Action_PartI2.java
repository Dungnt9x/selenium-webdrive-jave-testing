package webdriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_21_Action_PartI2 {
	WebDriver driver;
	Actions action;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
	    System.setProperty("webdriver.gecko.driver", projectPath + "/browserdriver/geckodriver");
	    driver = new FirefoxDriver();
	    action = new Actions(driver);
	    jsExecutor = (JavascriptExecutor) driver;
	    explicitWait = new WebDriverWait(driver, 10);
		// System.setProperty("webdriver.chrome.driver", projectPath + "/browserdriver/chromedriver");
		// driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Right_Click() { 
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		action.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
		sleepInSecond(3);
		
		//Hover vaaof paste
		action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
		sleepInSecond(3);
		
		//Verify Paste được hover thành công
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-paste.context-menu-hover.context-menu-visible")).isDisplayed());
		sleepInSecond(3);
		
		//Click vao Paste
		action.click(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
		
		//Verify Alert
		explicitWait.until(ExpectedConditions.alertIsPresent()).accept();
	}

	//@Test
	public void TC_02_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement doubleClickButton = driver.findElement(By.xpath("//button[text()='Double click me']"));
		//Scoll xuống Element này
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", doubleClickButton);
		sleepInSecond(3);
		
		action.doubleClick(doubleClickButton).perform();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")), "Hello Automation Guys!");
	}

	//@Test
	public void TC_03_Drag_And_Drop_HTML4() { 
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		
		WebElement smallCircle = driver.findElement(By.id("draggable"));
		WebElement bigCircle = driver.findElement(By.id("droptarget"));
		
		action.dragAndDrop(smallCircle, bigCircle).perform();
		sleepInSecond(3);
		
		//Text
		Assert.assertEquals(bigCircle.getText(), "You did great!");
		
		//Back ground
		Assert.assertEquals(Color.fromString(bigCircle.getCssValue("background-color")).asHex().toLowerCase(), "#03a9f4");
	}
	//@Test
	public void TC_04_Drag_And_Drop_HTML5_Css() throws IOException{ 
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		String squareA = "#column-a";
		String squareB = "#column-b";
		
		//Lấy tàn bộ nội dung trong file này ra
		String dragDropHelperContent = getContentFile(projectPath + "/dragAndDrop/drag_and_drop_helper.js");
		dragDropHelperContent = dragDropHelperContent + "$(\"" + squareA + "\").simulateDragDrop({ dropTarget: \"" + squareB + "\"});";
		
		//A to B
		jsExecutor.executeScript(dragDropHelperContent);
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']//header[text()='B']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']//header[text()='A']")).isDisplayed());

		
		
	}
	@Test
	public void TC_05_Drag_And_Drop_HTML5_Xpath() throws AWTException { 
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		dragAndDropHTML5ByXpath("//div[@id='column-a']", "//div[@id='column-b']");
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']//header[text()='B']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']//header[text()='A']")).isDisplayed());
		
	}
	public void dragAndDropHTML5ByXpath(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();
		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
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
	public String getContentFile(String filePath) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(filePath);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}
}
