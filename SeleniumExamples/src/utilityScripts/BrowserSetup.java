package utilityScripts;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserSetup {
	public enum Browser {
		FIREFOX, CHROME, EDGE, IEXPLORE,SALARI}

		Browser browser;
		WebDriver wd;
//		String path = "C:\\Selenium\\CoreFiles\\";

	public BrowserSetup(Browser browser, Boolean NoBrowser) throws Exception{
		this.browser=browser;
		try {
			switch(browser) {
	
			case FIREFOX:
	
//				System.setProperty("webdriver.gecko.driver", path+"geckodriver.exe");
				FirefoxOptions firefoxOptions = new FirefoxOptions(); 
				firefoxOptions.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
				if (NoBrowser==true) firefoxOptions.setHeadless(true);
				wd= new FirefoxDriver(firefoxOptions); break;
		
			case CHROME:
	
//				System.setProperty("webdriver.chrome.driver", path+"chromedriver.exe"); 
				ChromeOptions chromeOptions = new ChromeOptions();
				if (NoBrowser==true) chromeOptions.addArguments("--headless");
				wd= new ChromeDriver(chromeOptions); break;
				
			case EDGE:
//				System.setProperty("webdriver.edge.driver", path+"msedgedriver.exe");
				EdgeOptions edgeOptions = new EdgeOptions();
				if (NoBrowser==true) edgeOptions.addArguments("--headless");
				wd= new EdgeDriver(edgeOptions); break;

			case IEXPLORE:

				//IE doesn't support headless
//				System.setProperty("webdriver.ie.driver", path+"IEDriverServer.exe"); 
				wd= new InternetExplorerDriver(); break;
				
				
			default:

				throw new Exception("Browser type unsupported or Driver for the browſer" + browser + " does not exist");

				//or this can also be used
				//throw new RuntimeException("Browser type unsupported or Driver for the browser " + browser + " does not 
			}
		}catch(Exception e) {

			throw new RuntimeException("Browser type unsupported or Driver for the browser " + browser + " does not exist" );

		}
	}

	public String getBrowser() {
		return browser.name();
	}
	
	public WebDriver invokeBrowser(String url) {

		wd.manage().window().maximize();
		wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));		
		wd.get(url);
		wd.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		return wd;
	} 
	public void closeBrowser() { 
		wd.quit();
	}


}
