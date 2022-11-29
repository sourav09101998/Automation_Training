package Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Pages.Home;
import Pages.login;

public class TestingLogin_HomePage {
	
	// Create a reference variable.
	WebDriver driver;
	login objLogin;
	Home objHomePage;

	@BeforeTest
	public void setup() {

		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\CoreFiles\\chromedriver.exe");
		ChromeOptions chromeOptions = new ChromeOptions();
		driver = new ChromeDriver(chromeOptions);
		driver.get("http://demo.guru99.com/V4/");

	}
	@AfterClass
	public void afterClass() {
		driver.close();

	}

	@Test(priority = 0)
	public void test_Page() {

		// Create Login Page object
		objLogin = new login(driver);

		// Verify login page title
		String loginPageTitle = objLogin.getLoginTitle();
		Assert.assertTrue(loginPageTitle.toLowerCase().contains("guru99 bank"));

		// login to application
		
		objLogin.loginTo("mgr123", "mgr!23");

		// go the next page
		//Create Home Page object
		objHomePage = new Home(driver);

		// Verify home page
		Assert.assertTrue(objHomePage.getHomePageDashboardUserName().toLowerCase().contains("manger id : mgr123"));

	}
}