package others;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import utilityScripts.BrowserSetup;
import utilityScripts.BrowserSetup.Browser;
import utilityScripts.Log4j2;

public class AutoSuggest1 {
	public static void main(String[] args) throws Exception {
		WebDriver wd=null;
		BrowserSetup b;
		b= new BrowserSetup(Browser.CHROME,true);
		wd=b.invokeBrowser("https://www.google.co.in/");
		Log4j2.info("opened browser -"+wd);
		Log4j2.info("navigate to google");
		WebElement sbox=wd.findElement(By.name("q"));
		sbox.sendKeys("java 8");
		Log4j2.info("Search key entered");
		sbox.sendKeys(Keys.DOWN);
		Log4j2.info(sbox.getAttribute("value")+"selected");
		Log4j2.info("Key down pressed");
		Log4j2.info(sbox.getAttribute("value")+"selected");
		Thread.sleep(1000);
		sbox.sendKeys(Keys.DOWN);
		Log4j2.info("Key down pressed");
		Log4j2.info(sbox.getAttribute("value")+"selected");
		Thread.sleep(1000);
		sbox.sendKeys(Keys.DOWN);
		Log4j2.info("Key down pressed");
		Log4j2.info(sbox.getAttribute("value")+"selected");
		Thread.sleep(1000);
		sbox.sendKeys(Keys.DOWN);
		Log4j2.info("Key down pressed");
		Log4j2.info(sbox.getAttribute("value")+"selected");
		Thread.sleep(1000);
		sbox.sendKeys(Keys.UP);
		Log4j2.info("Key up pressed");
		Log4j2.info(sbox.getAttribute("value")+"selected");
		Thread.sleep(1000);
		
		wd.quit();
		Log4j2.info("quit");
		
	}
}
