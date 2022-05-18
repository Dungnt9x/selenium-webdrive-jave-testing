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

public class Topic_03_Xpath_partIII_demo {
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
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01_() { 
		//Viết Xpath ghép nhau (http://live.techpanda.org/)
	    driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']"));
	    //Viết Xpath lấy từ đời cha trở lên (https://demo.nopcommerce.com/) với từ desktop nằm trong menu con
	    driver.findElement(By.xpath("//ul[@class='top-menu mobile']//a[text()='Desktops ']"));
	    //LẤY TƯƠNG ĐỐI (http://live.techpanda.org/)- verify text "IPhone was added to your shopping cart."
		//li[contains(@class,'success-msg')]"));
		//span[contains(text(),'was added to your shopping cart.')]"));
		//div[starts-with(@class,'page-title')]"));
		//span[starts-with(text(),'IPhone')]"));
	    //LẤY TƯƠNG ĐỐI TRÊN TRANG LAZADA, thao tác với ô nhập Phone Number ở màn signin
		//input[starts-with(@data-spm-anchor-id,'a2o4n.login_signup')]"));
	
        //DÙNG TEXT (https://automationfc.github.io/) -> tính năng FORM BASIC
	    //h5[contains(text(),'Hello World!')]
	    //h5[contains(text(),"I'm a Hacker")] -> text có nháy đơn thì dùng nháy đôi thay thế
	    //h5[contains(.,"I'm a Hacker")] -> nằm trong node cha 
	    //h5[contains(.,"living in Viet Nam")] -> nằm ở bất kỳ vị trí nào trong node
	    //h5[contains(.," - 18 years old ")] -> nằm trong child node
	    //DÙNG TEXT (https://demo.nopcommerce.com/) -> tính năng DIGITAL DOWNLOAD
	    //a[contains(text(),'Digital downloads')]
	    //span[text()=concat('Hello "John", What', "'s happened?")] -> dùng hàm concat để nối chuỗi
        //DÙNG STRING(), (https://automationfc.github.io/) -> tính năng FORM BASIC (TƯƠNG TỰ TEXT().)
	    
	    

		



	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
