package others;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;

public class DeviceMode {
	
	static String path = "C:\\Selenium\\CoreFiles\\";
	static ChromeDriver wd;
	
	public static void main(String[] args) throws Exception {

		System.setProperty("webdriver.chrome.driver", path + "chromedriver.exe");
		wd = new ChromeDriver();

		DevTools devTools = ((HasDevTools) wd).getDevTools();

		devTools.createSession();
		setScreenMode();
//		mockGeddevTools);
//		getPerfMetrics(devTools); 
//		CaptureRequest(devTools);
//		CaptureConsoleLog(devTools);
//		UserAuthenticate(devTools);
		Thread.sleep(10000);

		wd.quit();
	}
	
	private static void setScreenMode() {

		System.out.println("Testing - Screen mode-" + wd);
		wd.get("https://www.google.com");
		Map<String, Object> scrMode= new HashMap<String, Object>();
		scrMode.put("width", 600);
		scrMode.put("height", 1000);
		scrMode.put("mobile", true);
		scrMode.put("deviceScaleFactor", 50);
		wd.executeCdpCommand("Emulation.setDeviceMetricsOverride", scrMode);

		}
}
