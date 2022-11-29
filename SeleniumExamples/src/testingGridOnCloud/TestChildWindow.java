package testingGridOnCloud;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import utilityScripts.Log4j2;

public class TestChildWindow extends TestBase {
	
	
	
	Object[] winHandle;
	String windowCaption1, windowCaption2;

	
	
	@Test(priority= 1)
	public void TestLaunchLambdatest() {
		wd.get("https://www.lambdatest.com/selenium-playground/");
		wd.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
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
