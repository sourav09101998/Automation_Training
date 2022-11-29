package testingGrid;

import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class TestFrames extends TestBase {
	
	
	List<WebElement> framesList;

	
	@Test(priority=1)
	public static void TestLaunchFrames() {

		wd.get("https://www.selenium.dev/selenium/docs/api/java/index.html?overview-summary.html");
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
