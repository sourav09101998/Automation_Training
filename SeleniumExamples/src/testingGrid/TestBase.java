package testingGrid;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class TestBase {
	public static WebDriver wd;

	@BeforeTest 
	@Parameters({"hubAddress","platform","browser","version"})
	public void startDriver(String hubAddress, String platform, String browser, String version) throws 
	MalformedURLException {
		
		DesiredCapabilities dc = new DesiredCapabilities();

		if (platform.equalsIgnoreCase("Windows"))
			dc.setPlatform(org.openqa.selenium.Platform.WIN10);

		if (platform.equalsIgnoreCase("Linux"))
			dc.setPlatform(org.openqa.selenium.Platform.LINUX);


		if (platform.equalsIgnoreCase("ANY"))
			dc.setPlatform(org.openqa.selenium.Platform.ANY);
		
		if (browser.equalsIgnoreCase("Firefox")) {
			FirefoxOptions Options =new FirefoxOptions();
			dc.setCapability(CapabilityType.BROWSER_NAME, "firefox");
			Options.setPageLoadStrategy(PageLoadStrategy. NORMAL);
			Options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
			Options=Options.merge(dc);
			wd = new RemoteWebDriver(new URL(hubAddress), Options);

			}

		else if(browser.equalsIgnoreCase("Chrome")) {

			ChromeOptions options = new ChromeOptions();
			dc.setCapability(CapabilityType.BROWSER_NAME, "chrome"); 
			options.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
			options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			options=options.merge(dc);
			wd = new RemoteWebDriver(new URL(hubAddress), options);

		}
		
		else if(browser.equalsIgnoreCase("MicrosoftEdge")) {
				EdgeOptions options = new EdgeOptions();
				dc.setCapability(CapabilityType.BROWSER_NAME, "MicrosoftEdge");
				options.setBinary("C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe");
				options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
				options=options.merge(dc);
				wd = new RemoteWebDriver(new URL(hubAddress), options);
		}
		wd.manage().window().maximize(); 
		wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); 
		wd.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50)); 
		System.out.println(((RemoteWebDriver)wd).getSessionId());
	}

	@AfterTest
	public void stopDriver() {
		wd.quit();
		wd = null;

	}

}
