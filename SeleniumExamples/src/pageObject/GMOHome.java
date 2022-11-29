package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class GMOHome {
	WebDriver driver;
	@FindBy(how=How.NAME, using="bSubmit")
	private WebElement buttonEnterGMOSite;
	@FindBy(how=How.NAME, using="bAbout")
	private WebElement buttonAboutTheGMOSite;
	@FindBy(how=How.XPATH, using="//input[@name='bBrowserTest']")
	private WebElement buttonBrowserTestPage;

	public GMOHome(WebDriver driver) {
		this.driver=driver;
	}
	
	public void enterGMOOnline() {
		buttonEnterGMOSite.click();
	}
	public void enterAboutTheGMOSite() {
		buttonAboutTheGMOSite.click();
		driver.navigate().back();
	}
	public void enterBrowserTestPage() {
		buttonBrowserTestPage.click();
		driver.navigate().back();
	}
	public String getPageTitle() {
		return driver.getTitle();
	}
}
