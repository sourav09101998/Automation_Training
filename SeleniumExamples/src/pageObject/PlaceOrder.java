package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PlaceOrder {
	
	WebDriver driver;

	@FindBy( how=How.CSS, using="[value='Proceed With Order']")
	private WebElement buttonProceedWithOrder;

	public PlaceOrder(WebDriver driver) {
	    this.driver=driver;

	}

	public void proceedwithOrder(){
		buttonProceedWithOrder.click();

	}

}
