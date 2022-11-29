package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Receipt {

	WebDriver driver;

	@FindBy(how = How.NAME, using = "bSubmit")
	private WebElement buttonReturnToHomePage;

	public Receipt(WebDriver driver) {
		    this.driver=driver;

		}

	public void proceedwithOrder() {
		buttonReturnToHomePage.click();

	}

}
