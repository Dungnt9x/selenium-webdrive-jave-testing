package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Handle_custom_dropdown {
	WebDriver driver;
	WebDriverWait explicitWait;
	//JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
	    System.setProperty("webdriver.gecko.driver", projectPath + "/browserdriver/geckodriver");
	    driver = new FirefoxDriver();
	    //Luôn khởi tạo sau driver -> nó cần giá trị driver để khởi tạo giá trị expliciWait lên
	    //Wait cho các element theo điều kiện có sẵn visible, invisible, precense,...
	    explicitWait = new WebDriverWait(driver, 15);
	    
	    //Ép kiểu tường minh trong Java
	    //jsExecutor = (JavascriptExecutor) driver;
		
		// System.setProperty("webdriver.chrome.driver", projectPath + "/browserdriver/chromedriver");
		// driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

//	@Test
	public void TC_01_Jquery_01() { 
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		/*
		// Click vaof dropdown cho xổ hết item ra
		driver.findElement(By.cssSelector("span#number-button")).click();
		// Chờ cho tất cả các item con bên trong được load ra
		//By locator = đại diện "cho tất cả các item con bên trong"
		//Lấy cái locator đến cái thẻ chứa text item
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#number-menu>li>div")));
		//Tìm các item mong muốn (nếu như không hiển thị cần cuộn chuột để tìm)
		//Lấy hết tất cả các item a lưu vào 1 WebElement
		List<WebElement> allDropdownItems = driver.findElements(By.cssSelector("ul#number-menu>li>div"));
		//Duyệt qua: dùng vòng lặp
		for (WebElement item : allDropdownItems) {
			String atualTextItem = item.getText();
		//Thấy item cần chọn thì click vào => so sánh với item mong muốn sau dó Click vào
			if (atualTextItem.equals("14")) {
				item.click();
				//Thoát khỏi vòng lặp
				break;
			}
		}*/
		
		/* Chonj item 10 */
		selectItemInCustomDropdown("span#number-button", "ul#number-menu>li>div", "10");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "10");
		
		/* Chonj item 5 */
		selectItemInCustomDropdown("span#number-button", "ul#number-menu>li>div", "5");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "5");
		/* Chon item 14 */
		selectItemInCustomDropdown("span#number-button", "ul#number-menu>li>div", "14");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "14");
	}


	//@Test
	public void TC_02_Jquery_02() { 
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		selectItemInCustomDropdown("i.dropdown", "div.item>span", "Elliot Fu");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Elliot Fu");
		
		selectItemInCustomDropdown("i.dropdown", "div.item>span", "Justen Kitsune");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Justen Kitsune");
		
		selectItemInCustomDropdown("i.dropdown", "div.item>span", "Stevie Feliciano");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Stevie Feliciano");
	}
	
	//@Test
		public void TC_03_Vuejs() { 
			driver.get("https://mikerodham.github.io/vue-dropdowns/");
			selectItemInCustomDropdown("li.dropdown-toggle", "ul.dropdown-menu>li>a", "Second Option");
			Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");
			
			selectItemInCustomDropdown("li.dropdown-toggle", "ul.dropdown-menu>li>a", "First Option");
			Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");
			
			selectItemInCustomDropdown("li.dropdown-toggle", "ul.dropdown-menu>li>a", "Third Option");
			Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");
			
		}
	
	@Test
	public void TC_04_Editable() { 
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		enterItemInCustomDropdown("input.search", "div.item>span", "Afghanistan");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Afghanistan");
		
		enterItemInCustomDropdown("input.search", "div.item>span", "Australia");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Australia");
		
		
	}
	//Tạo hàm truyền qua tham số
	public void selectItemInCustomDropdown (String parentLocator, String chldLocator, String expectedTextItem) {
				driver.findElement(By.cssSelector(parentLocator)).click();
				sleepInSecond(1);
				
				explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(chldLocator)));
				List<WebElement> allDropdownItems = driver.findElements(By.cssSelector(chldLocator));
				
				for (WebElement item : allDropdownItems) {
					String atualTextItem = item.getText();
					if (atualTextItem.equals(expectedTextItem)) {
						//jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
						item.click();
						sleepInSecond(1);
						break;
					}
				}
	}
	
	public void enterItemInCustomDropdown (String editableLocator, String chldLocator, String expectedTextItem) {
		driver.findElement(By.cssSelector(editableLocator)).sendKeys(expectedTextItem);
		sleepInSecond(1);
		
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(chldLocator)));
		List<WebElement> allDropdownItems = driver.findElements(By.cssSelector(chldLocator));
		
		for (WebElement item : allDropdownItems) {
			String atualTextItem = item.getText();
			if (atualTextItem.equals(expectedTextItem)) {
				//jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				item.click();
				sleepInSecond(1);
				break;
			}
		}
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
