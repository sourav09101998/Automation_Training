package others;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import utilityScripts.BrowserSetup;
import utilityScripts.BrowserSetup.Browser;

public class FileUploadDownload {
	static WebDriver wd;
	static BrowserSetup b;

	public static void main(String[] args) throws Exception {
	    fileUploadSendKeys();
	    fileDownloadRobot();
//		fileDownloadPrefs();
		wd.quit();
	}

	private static void fileUploadSendKeys() throws Exception {
		b = new BrowserSetup(Browser.CHROME, false); // true - non-gui (headless), false - gui
		wd = b.invokeBrowser("https://the-internet.herokuapp.com/upload");
		WebElement btnBrowse = wd.findElement(By.cssSelector("#file-upload"));
		Thread.sleep(2000);
		btnBrowse.sendKeys("c:\\testngNotes1.txt"); // Uploading the file using sendKeys
		Thread.sleep(2000);
		wd.findElement(By.cssSelector("#file-submit")).click();
		Thread.sleep(2000);
	}

	private static void fileDownloadRobot() throws Exception {
		wd.switchTo().newWindow(WindowType.TAB);
		wd.navigate().to("https://the-internet.herokuapp.com/download");
		wd.findElement(By.linkText("datain.txt")).click();
		Thread.sleep(5000);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
	}

	private static void fileDownloadPrefs() throws Exception {
		String browser = "chrome";
		String path = "C:\\Selenium\\CoreFiles\\";
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("download.default_directory", "c:\\");
		prefs.put("download.prompt_for_download", false);
		prefs.put("plugins.always_open_pdf_externally", true); // disable preview in browser

		if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", path + "geckodriver-v0.31.0_x64.exe");
			FirefoxProfile fp = new FirefoxProfile();
			fp.setPreference("pdfjs.disabled", true); // disable preview in browser
			fp.setPreference("browser.helperApps.neverAsk.saveToDisk",
					"application/msword, application/vnd.openxmlformats-officedocument.wordprocessingml.document,"
							+ "text/plain,image/jpeg,image/png,application/pdf, application/xml,application/zip");
			fp.setPreference("browser.download.folderList", 2);
			fp.setPreference("browser.download.dir", "c:\\");

			FirefoxOptions options = new FirefoxOptions();
			options.setProfile(fp);
			wd = new FirefoxDriver(options);
		} else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", path + "chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			wd = new ChromeDriver(options);
		} else {
			System.setProperty("webdriver.edge.driver", path + "msedgedriver.exe");
			EdgeOptions options = new EdgeOptions();
			options.setExperimentalOption("prefs", prefs);
			wd = new EdgeDriver(options);
		}
		wd.get("https://the-internet.herokuapp.com/download");
		wd.manage().window().maximize();
		wd.findElement(By.linkText("testngNotes.txt")).click();
		Thread.sleep(1000);

	}
}

