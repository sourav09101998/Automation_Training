package others;

import org.testng.annotations.Test;

import utilityScripts.BrowserSetup;
import utilityScripts.BrowserSetup.Browser;

import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class TestFileUploadDownload {
	private BrowserSetup b;
	private WebDriver wd ;
	
	@BeforeClass
	public void beforeClass() throws Exception {
		b = new BrowserSetup(Browser.CHROME, false);

	}

	@AfterClass
	public void afterClass() {
		b.closeBrowser();

	}
	@Test( priority = 1 )
	public void TestToOpenURL() {
		wd = b.invokeBrowser("https://the-internet.herokuapp.com/");
		Assert.assertEquals(wd.getTitle(), "GreenKart - veg and fruits kart");

	}


  @Test
  public void fileDownloadPrefsTest() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void fileDownloadRobotTest() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void fileUploadSendKeysTest() {
    throw new RuntimeException("Test not implemented");
  }
}
