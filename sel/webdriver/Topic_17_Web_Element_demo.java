package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Web_Element_demo {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
	    System.setProperty("webdriver.gecko.driver", projectPath + "/browserdriver/geckodriver");
	    driver = new FirefoxDriver();
	    driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		
	}

	public void TC_01_Define_Element() { 
		//Muốn tác được với Element thì phải tìm Element trước
		//Sau đó mới áp dụng hành vi vào Element
		//1. Tìm Element
		//2. Với loại Locator gì
		//3. Tương tác lên, kiểm tra nó
		
		//Muốn thao tác trực tiếp lên Element thì không cần khai báo biến
			driver.findElement(By.id("")).click();
		//Thao tác từ 2 lần trở lên thì cần khai báo biến để tránh vc lặp lại
			WebElement emailTextbox = driver.findElement(By.id("email"));
			emailTextbox.clear();
			emailTextbox.click();
			emailTextbox.sendKeys("");

	}

	@Test
	public void TC_02_Element_Method() {
		WebElement Element = driver.findElement(By.id(""));
		//Xóa sữ lệu trong những field cho phép nhập
		//Luôn luôn clear hết dữ liệu trước khi thao tác lên field đó
		//Textbox/textarea/Edittable dropdown
		Element.clear();
		
		//Nhập dữ liệu trong những field cho phép nhập
		Element.sendKeys("");
		Element.sendKeys(Keys.ENTER);
		
		//Tìm 2 lần element
		driver.findElement(By.className("footer")).findElement(By.cssSelector("a[title='My account']"));
		
		//Lấy placeholder
		driver.findElement(By.id("search")).getAttribute("placeholder");
		//Không khai báo biến = verify trực tiếp
		Assert.assertEquals(driver.findElement(By.id("search")).getAttribute("placeholder"), "Search entire store here...");
		//Khai báo biến để dùng nhiều lần
		String searchTextboxPlaceholderValue = driver.findElement(By.id("search")).getAttribute("placeholder");
		Assert.assertEquals(searchTextboxPlaceholderValue, "Search entire store here...");
		
		//Assert- kiểm tra tính đúng đắn của dl
		//Ktra dữ liệu mình mong muốn là đúng
		//Email Textbox hiển thị
		Assert.assertTrue(driver.findElement(By.id("email")).isDisplayed());
		//Ktra dữ liệu mình mong muốn là Sai
		//Email Textbox không hiển thị
		Assert.assertFalse(driver.findElement(By.id("email")).isDisplayed());
		//Kiểm tra dữ liệu mình mong muốn với dl thực tế là bằng nhau
		Assert.assertEquals(driver.findElement(By.id("search")).getAttribute("placeholder"), "Search entire store here...");

		//GUI:Font Type/Font Size/Color
		Element.getCssValue("background-color"); //lấy ra giá trị "rgb(51, 153, 204);"
		Element.getCssValue("font-size");  //lấy ra giá trị 13px
		
		//GUI: Position/Location/Size of Element
		Element.getLocation();
		Element.getRect();
		Element.getSize();
		
		//framework: Attach screeshot to Report HTML
		Element.getScreenshotAs(OutputType.FILE);
		
		//output của Element này là Input của Element khác
		String emailTextboxTagname = Element.getTagName();
		
		//Truyền 1 biến vào 1 chuỗi
		driver.findElement(By.xpath("//" + emailTextboxTagname + "[@id='advice-validate-password']")); //chuỗi thứ 1 + biến + chuỗi thứ 2
		
		//Lấy ra text của element hiện tại
		//text của những element con bên trong
		Element.getText();
		
		//Mong muốn 1 element hiển thị/không hiển thị
		//Người dùng nhìn thấy được, kích thước cụ thể
		//Áp dụng cho tất cả các loại Element: textbox/textarea/checkbox/radio/button,...
		Element.isDisplayed(); 
		
		//Mong muốn 1 element có thể thao tác đươcj lên hoặc không
		Element.isEnabled();
		
		//mong muốn 1 Element đã được chọn hay chưa
		//Áp dung cho 1 vài loại Element: Checkbox, radio, dropdown (Default)
		Element.isSelected();
		
		//Click vào 1 Element: Button, Link, Icon,...
		Element.click();
		
		//Giống hành vi ENTER ở các form
		//Dùng cho form đấy (element bên trong form)
		Element.submit();
		
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
