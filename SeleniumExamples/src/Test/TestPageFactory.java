package Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageFactory.HomePageFactory;
import PageFactory.LoginPageFactory;

public class TestPageFactory {

	// Create a WebDriver reference variable. 
	WebDriver driver;
	LoginPageFactory objLogin;
	HomePageFactory objHomePage;

	@BeforeTest
	public void setup() {

		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\CoreFiles\\chromedriver.exe");
		ChromeOptions chromeOptions = new ChromeOptions();
		driver = new ChromeDriver(chromeOptions);
		driver.get("http://demo.guru99.com/V4/");

	}



	@Test(priority = 0)
	public void test_Home_Page_Appear_Correct() {

		// Create Login Page object
		objLogin = new LoginPageFactory(driver);

		// Verify login page title
		String loginPageTitle = objLogin.getLoginTitle();
		Assert.assertTrue(loginPageTitle.toLowerCase().contains("guru99 bank"));

		// login to application
		objLogin.loginTo("mgr123", "mgr!23");

		// go the next page
		objHomePage = new HomePageFactory(driver);

		// Verify home page
		Assert.assertTrue(objHomePage.getHomePageDashboardUserName().toLowerCase().contains("manger id : mgr123"));

	}

}