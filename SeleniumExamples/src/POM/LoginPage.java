package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	
	WebDriver wd;
	
	By username=By.cssSelector("input[placeholder='Username']");
	By password=By.xpath("//input[@placeholder='Password']");
	By logingButton=By.cssSelector("button[type='submit']");

	public LoginPage(WebDriver wd) {
		this.wd=wd;
	}
	
	public void typeUserName() {
		wd.findElement(username).sendKeys("admin");
	}
	
	public void typePassword() {
		wd.findElement(password).sendKeys("demo123");
		
	}
	
	public void clickLogingButton() {
		wd.findElement(logingButton).click();
	}
}
