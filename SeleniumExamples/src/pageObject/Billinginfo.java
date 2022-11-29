package pageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;


public class Billinginfo {

	WebDriver driver;

	@FindBy(how = How.NAME, using = "bSubmit")
	private WebElement buttonPlaceTheOrder;
	
	@FindBy( how=How.NAME, using="billName")
	private WebElement billName;
	@FindBy( how=How.NAME, using="billAddress")
	private WebElement billAddress;
	@FindBy( how=How.NAME, using="billCity")
	private WebElement billCity;
	@FindBy( how=How.NAME, using="billState")
	private WebElement billState;
	@FindBy( how=How.NAME, using="billZipCode")
	private WebElement billZipCode;
	@FindBy(how=How.NAME, using="billPhone")
	private WebElement billPhone;
	@FindBy(how=How.NAME,using="billEmail")
	private WebElement billEmail;
	@FindBy(how=How.NAME,using="CardType")
	private WebElement CardType;
	@FindBy(how=How.NAME,using="CardNumber")
	private WebElement CardNumber;
	@FindBy(how=How.NAME,using="CardDate")
	private WebElement CardDate;
	@FindBy(how=How.NAME,using="shipSameAsBill")
	private WebElement shipSameAsBill;
	
	public Billinginfo(WebDriver driver) {
		this.driver=driver;
	}
	
	public void placetheOrder() {
		buttonPlaceTheOrder.click();
	}
	
	public void selectShipSameAsBill() {
		shipSameAsBill.click();
	}
	
	//setters for billAddress
	public void setbillName(String data) {
		billName.clear();
		billName.sendKeys(data);
	}
	public void setbillAddress(String data) {
		billAddress.clear();
		billAddress.sendKeys(data);
	}
	public void setbillCity(String data) {
		billCity.clear();
		billCity.sendKeys(data);
	}
	public void setbillState(String data) {
		billState.clear();
		billState.sendKeys(data);
	}
	public void setbillZipCode(String data) {
		billZipCode.clear();
		billZipCode.sendKeys(data);
	}
	public void setbillPhone(String data) {
		billPhone.clear();
		billPhone.sendKeys(data);
	}
	public void setbillEmail(String data) {
		billEmail.clear();
		billEmail.sendKeys(data);
	}
	public void setCardNumber(String data) {
		CardNumber.clear();
		CardNumber.sendKeys(data);
	}
	public void setCardDate(String data) {
		CardDate.clear();
		CardDate.sendKeys(data);
	}
	
	public int getNoofCardType() {
		return new Select(CardType).getOptions().size();
	}
	
	public List<WebElement> getListedCardTypes() {
//		Select selectCard=new Select(CardType);
//		List<WebElement> optionsElements=selectCard.getOptions();
//		return optionsElements;
		return new Select(CardType).getOptions();
	}
	
	public String getCardSelected() {
		return CardType.getAttribute("value");
	}
	
	public void setCardType(String data) {
		CardType.sendKeys(data);
	}
	
	public void setCardTypeByIndex(int index) {
		Select selectCardSelect =new Select(CardType);
		List<WebElement> optionsElements =selectCardSelect.getOptions();
		if (index<=optionsElements.size()) {
			selectCardSelect.selectByIndex(index);
		}
		else {
			throw new IndexOutOfBoundsException("Index should be between 0 and "+(optionsElements.size()-1));
		}
	}

}
