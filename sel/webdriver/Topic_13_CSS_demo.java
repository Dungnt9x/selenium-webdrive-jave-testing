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

public class Topic_13_CSS_demo {
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
		//Direct Child -> đi 1 nốt (http://live.techpanda.org/index.php/customer/account/login/) -> button create an account)
		  //a[title='Create an Account']>span>span
		//Child or SubChild -> Đi xuống nhiều nốt
		  //form[id='login-form'] a[title='Create an Account']
		//Dùng ID
				//input[id='email']
				//input#email
				//#email
		//Dùng Class
				//p[class='form-instructions']
				//p.form-instructions
				//.form-instructions
		//Dùng Form Atribute
		      //input[id='email'][name='login[username]'] -> Mutil atribute
		//nth -> Index bất kỳ (https://automationfc.github.io/jquery-selectable/)
		   //li:nth-child(6)
		   //li:first-child
		   //li:last-child
		//xes
		    //li:nth-child(6) + li //(dùng + lấy node em gần nhất) - (following-sibling)
		    //li:nth-child(6)~li //(dùng ~ lấy hết các node em) - (following-sibling)
		//contains ((http://live.techpanda.org/index.php/customer/account/login/) -> button creat an acount)
				//a[title*= 'an Account']
		//Starts-with
				//a[title^='Create an']
	    //End-with (Xpath không hỗ trợ nhưng Xpath có thể thay thế bằng contains)
	    		//a[title$='Account']
	    //Hoặc
	    		//input[id='email'],[id='pass']
	    //not
	    		//input:not([id='email'])
	    		//input:not(#email)
		//Muốn debug với item như tootip, loading có các cách
	    		//C1: Vào tab network và custom thêm 1 network để làm chậm tốc độ internet (loading)
	    		//C2: Vào tab console gõ đoạn lênh: setTimeout(() => {debugger;},4000); => hover chuột vào tootip, sau 4s debug cái tooltip
	    		//C3: bật debug bên tab Source dùng cho các menu dạng hover
	    		
		


	}

	@AfterClass
	public void afterClass() {
		driver.quit(); 
	}
}
