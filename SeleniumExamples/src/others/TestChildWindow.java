package others;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utilityScripts.BrowserSetup;
import utilityScripts.BrowserSetup.Browser;
import utilityScripts.Log4j2;

public class TestChildWindow {
	WebDriver wd;
	BrowserSetup b;
	
	Object[] winHandle;
	String windowCaption1, windowCaption2;

	@BeforeClass
	public void beforeClass() throws Exception {


		b = new BrowserSetup(Browser.CHROME, false); 
		wd = b.invokeBrowser("https://www.lambdatest.com/selenium-playground/");
		wd.manage().window().maximize();
		wd.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

	}
	@AfterClass
	public void afterClass() {
		wd.quit();
	}
	
	@Test(priority= 1)
	public void TestLaunchLambdatest() {
		WebElement captionElement = wd.findElement(By.xpath("//h1"));
		Assert.assertEquals(captionElement.getText(), "Selenium Playground");

	}
	
	@Test(priority = 2, dependsOnMethods = "TestLaunchLambdatest")
	public void TestSwitchToSecondBrowser() throws InterruptedException {
		wd.findElement(By.xpath("//a[contains(text(),'Window Popup Modal')]")).click();
		wd.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		
		windowCaption1 = wd.findElement(By.xpath("//section[3]//div[2]/h2")).getText();
		Assert.assertTrue(windowCaption1.contains("Window popup Modal"));
		
		wd.findElement(By.cssSelector("[title*='Facebook']")).click();
		Set<String> windows = wd.getWindowHandles(); 
		Log4j2.info("First Window url:"+ wd.getCurrentUrl()); 
		winHandle = new Object[windows.size()];
		winHandle = windows.toArray();
		wd.switchTo().window(String.valueOf(winHandle[1]));
		Log4j2.info("Second window url:"+ wd.getCurrentUrl());
		Assert.assertTrue(wd.getCurrentUrl().contains("facebook"));
	
	}
}
