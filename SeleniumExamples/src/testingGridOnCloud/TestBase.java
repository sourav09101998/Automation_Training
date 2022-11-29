package testingGridOnCloud;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class TestBase {
	public static WebDriver wd;

	@BeforeTest 
	@Parameters({"testname","platform","browser","version"})
	public void startDriver(String testname, String platform, String browser, String version) throws 
	MalformedURLException {

		
		URL hubAddress = new URL("https://oauth-sourav.anand-9b85e:15e6a300-0016-4209-8969-666675e12b4b@ondemand.eu-central-1.saucelabs.com:443/wd/hub");
				
        MutableCapabilities sauceOpts = new MutableCapabilities();
        sauceOpts.setCapability("seleniumVersion", "4.5.3");
        sauceOpts.setCapability("build", "Selenium-W3C-OnCloud-Example");
        sauceOpts.setCapability("name", testname);
        sauceOpts.setCapability("tags", "cloud-tests:"+testname);

        if(browser.equalsIgnoreCase("Firefox"))  { 
                FirefoxOptions browserOptions = new FirefoxOptions();                
                browserOptions.setCapability(CapabilityType.BROWSER_NAME, "firefox");
                browserOptions.setPlatformName("Windows 10");
                browserOptions.setBrowserVersion("latest");
                browserOptions.setCapability("moz:debuggerAddress", true);
                Map<String, Object> sauceGecko = new HashMap<>();
                sauceGecko.put("geckodriverVersion", "0.31.0");
                browserOptions.setCapability("sauce:options", sauceGecko); //Wrong
                browserOptions.setCapability("sauce:options", sauceOpts);
                wd = new RemoteWebDriver(hubAddress, browserOptions);
        }
        else if(browser.equalsIgnoreCase("Chrome"))  {
                ChromeOptions browserOptions = new ChromeOptions();
                browserOptions.setCapability(CapabilityType.BROWSER_NAME, "chrome");
                browserOptions.setPlatformName("Windows 10");
                browserOptions.setBrowserVersion("latest");
                browserOptions.setCapability("sauce:options", sauceOpts);
    
                wd = new RemoteWebDriver(hubAddress, browserOptions);
        }
        else if(browser.equalsIgnoreCase("MicrosoftEdge"))  {
                EdgeOptions browserOptions = new EdgeOptions();
                browserOptions.setCapability(CapabilityType.BROWSER_NAME, "MicrosoftEdge");
                browserOptions.setPlatformName("Windows 10");
                browserOptions.setBrowserVersion("latest");
                browserOptions.setCapability("sauce:options", sauceOpts);
                wd = new RemoteWebDriver(hubAddress, browserOptions);
        }
        else if(browser.equalsIgnoreCase("Safari"))  {
                SafariOptions browserOptions = new SafariOptions();
                browserOptions.setCapability(CapabilityType.BROWSER_NAME, "Safari");
                browserOptions.setPlatformName("macOS 12");
                browserOptions.setBrowserVersion("16");
                browserOptions.setCapability("pageLoadStrategy", "normal");
                browserOptions.setCapability("sauce:options", sauceOpts);
                wd = new RemoteWebDriver(hubAddress, browserOptions);
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
