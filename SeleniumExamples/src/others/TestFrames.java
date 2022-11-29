package others;

import org.testng.annotations.Test;

import utilityScripts.BrowserSetup;
import utilityScripts.BrowserSetup.Browser;

import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class TestFrames {
	
	public BrowserSetup browserSetup;
	public static WebDriver wd;
	List<WebElement> framesList;

	@BeforeClass
	public void beforeClass() throws Exception {
		browserSetup =new BrowserSetup(Browser.EDGE, false);
		wd=browserSetup.invokeBrowser("https://www.selenium.dev/selenium/docs/api/java/index.html?overview-summary.html");
		
	}

	@AfterClass
	public void afterClass() {
		browserSetup.closeBrowser();

	}
	
	@Test(priority=1)
	public static void TestLaunchFrames() {

		
		wd.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		Assert.assertEquals(wd.getTitle(), "Overview");

	}

	@Test(priority = 2 , dependsOnMethods="TestLaunchFrames")
	public void TestFramesInPage() {

		framesList = wd.findElements(By.tagName("iframe"));
		Assert.assertEquals(framesList.size(),3);

	}
	
	@Test(priority=3, dependsOnMethods="TestFramesInPage")
	public void TestFrameNavigation() {

		for(WebElement frame:framesList) {
			String frameName = frame.getAttribute("name");
			System.out.println(" --"+frameName+"-----");
			wd.switchTo().frame(frameName);
			List<WebElement> linksList = wd.findElements(By.tagName("a"));
			for (WebElement link:linksList) {
				System.out.println(link.getText());
			}
			wd.switchTo().defaultContent();

	}
	}

}
