package others;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utilityScripts.BrowserSetup;
import utilityScripts.BrowserSetup.Browser;

public class AutoSuggest {
	
	private static WebDriver wd;
	private static WebElement we;

	public static void main(String[] args) throws Exception {
		BrowserSetup browserSetup = new BrowserSetup(Browser.CHROME, false);
		wd = browserSetup.invokeBrowser("https://www.google.co.in/");
		we=wd.findElement(By.name("q"));
		
		we.sendKeys("jdk");
		we.sendKeys(Keys.DOWN);
		System.out.println("value selected: "+ we.getAttribute("value"));
		Thread.sleep(1000);
		we.sendKeys(Keys.DOWN);
		System.out.println("value selected: "+ we.getAttribute("value"));
		Thread.sleep(1000);
		we.sendKeys(Keys.DOWN);
		System.out.println("value selected: "+ we.getAttribute("value"));
		Thread.sleep(1000);
		we.sendKeys(Keys.DOWN);
		System.out.println("value selected: "+ we.getAttribute("value"));
		Thread.sleep(1000);
		we.sendKeys(Keys.DOWN);
		System.out.println("value selected: "+ we.getAttribute("value"));
		Thread.sleep(1000);
		we.sendKeys(Keys.DOWN);
		System.out.println("value selected: "+ we.getAttribute("value"));
		Thread.sleep(1000);
		we.sendKeys(Keys.DOWN);
		System.out.println("value selected: "+ we.getAttribute("value"));
		Thread.sleep(1000);
		we.sendKeys(Keys.UP);
		System.out.println("value selected: "+ we.getAttribute("value"));
		Thread.sleep(1000);
		we.sendKeys(Keys.UP);
		System.out.println("value selected: "+ we.getAttribute("value"));
		Thread.sleep(1000);
		we.sendKeys(Keys.ENTER);
		we=wd.findElement(By.name("q"));
		System.out.println("value selected: "+ we.getAttribute("value"));
		Thread.sleep(2000);
		wd.quit();
	}


}
