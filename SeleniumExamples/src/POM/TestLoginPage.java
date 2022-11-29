package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.testng.annotations.Test;


public class TestLoginPage {
	
	private WebDriver wd;

	@Test
	public void testvalidLoginPage()  {
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\CoreFiles\\chromedriver.exe"); 
		ChromeOptions chromeOptions = new ChromeOptions();
		wd= new ChromeDriver(chromeOptions);
		wd.manage().window().maximize();
		wd.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

		
		LoginPage loginPage =new LoginPage(wd);
		loginPage.typeUserName();
		loginPage.typePassword();
		loginPage.clickLogingButton();

		wd.close();
	}
}
