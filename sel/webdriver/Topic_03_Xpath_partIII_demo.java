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
	    //Viết Xpath lấy từ đời cha xuống (https://demo.nopcommerce.com/) với từ desktop nằm trong menu con
	    driver.findElement(By.xpath("//ul[@class='top-menu mobile']//a[text()='Desktops ']"));
	    //LẤY TƯƠNG ĐỐI (http://live.techpanda.org/)- verify text "IPhone was added to your shopping cart."
		     //li[contains(@class,'success-msg')]"));
		     //span[contains(text(),'was added to your shopping cart.')]"));
		     //div[starts-with(@class,'page-title')]"));
		     //span[starts-with(text(),'IPhone')]"));
	    //LẤY TƯƠNG ĐỐI TRÊN TRANG LAZADA, thao tác với ô nhập Phone Number ở màn signin
		      //input[starts-with(@data-spm-anchor-id,'a2o4n.login_signup')]"));
	    //LẤY TUYỆT ĐỐI VỚI TEXT()= ->(http://live.techpanda.org/)- verify text "IPhone was added to your shopping cart.")
	          //span[text()='IPhone was added to your shopping cart.']
	    
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
	    //DÙNG AND và OR (http://live.techpanda.org/) -> chonj account
	           //input[@type='email' and @id='email' and @name='login[username]'] -> toanf bộ điều kiện phải đúng
	           //input[@id='email' or @id='pass'] -> 1 trong các điều kiện đúng là được
	    //DÙNG NOT (https://automationfc.github.io/jquery-selectable/) -> chọn account
	    //lấy thằng khác điều kiện tìm
	    //Dùng inside và outside (đánh index)
	           //li[6]-> Dùng inside khi tất cả thẻ cùng nằm trong 1 cha (https://automationfc.github.io/jquery-selectable/)
	           //(//span[text()='Add to Cart'])[3]-> dùng outside khi các thẻ nằm khác cha ->(http://live.techpanda.org/)-> tính năng mobile
	           //(//h2//span[contains(@class,'a-text-normal')])[last()]-> lấy giá trị cuối cùng thuộc cùng 1 cha-> (amazon.com-> search macbook M1)
	    
	   //KỸ THUẬT AXES 
	          //a[text()='IPhone']/parent::h2/following-sibling::div[@class='actions']/button -> (http://live.techpanda.org/)-> tính năng mobile 
	          //a[text()='IPhone']/parent::h2/following-sibling::div//*[@class='price']/ancestor::div[contains(@class,'products')]/div
	          //td[text()='User ID :']/following-sibling::td - Lấy từ anh xuống em (Xpath lấy giá trị "User ID") ->(http://demo.guru99.com)
	          //span[text()='Total']/preceding-sibling::span - Lấy từ em lên anh (Xpath lấy giá trj của Total ở giỏ hàng) ->(http://automationpractice.com/index.php)



	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
