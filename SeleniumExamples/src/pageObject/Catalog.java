package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Catalog {
	
	WebDriver driver;
	
	@FindBy( how=How.NAME, using="bSubmit")private WebElement buttonPlaceAnOrder;
	@FindBy( how=How.NAME, using="bReset")private WebElement buttonResetForm; 
	@FindBy( how=How.NAME, using="QTY_TENTS")private WebElement orderQty_TENTS;
	@FindBy( how=How.NAME, using="QTY_BACKPACKS")private WebElement orderQty_BACKPACKS;
	@FindBy( how=How.NAME, using="QTY_GLASSES")private WebElement orderQty_GLASSES;
	@FindBy( how=How.NAME, using="QTY_SOCKS")private WebElement orderQty_SOCKS;
	@FindBy( how=How.NAME, using="QTY_BOOTS")private WebElement orderQty_BOOTS;
	@FindBy( how=How.NAME, using="QTY_SHORTS") private WebElement orderQty_SHORTS;
	@FindBy( how=How.XPATH, using="html/body/form/table/tbody/tr[2]/td/div/center/table")
	private WebElement catalogTable;
	@FindBy(how=How.XPATH, using= "html/body/table/tbody/tr/td[1]/h1")
	private WebElement HeaderText;
	@FindBy(how=How.XPATH, using="html/body/form/table/tbody/tr[2]/td/div/center/table/tbody/tr")
	@CacheLookup //@CacheLookup makes the lookup for the element happen just once. After that, it will be cached in the ariable and accessible much faster.
	private List<WebElement> rowList;
	@FindBy(how=How.CSS, using="a>strong")
	private List<WebElement> itemsList;

	public Catalog(WebDriver driver){
		this.driver=driver;
	}

	public void resetForm(){
		buttonResetForm.click();
	}
	public void placeOrder(){
		buttonPlaceAnOrder.click();
	}
	public String getHeaderText(){
		return HeaderText.getText();
	}
	
	public boolean isResetForm(){

		if (Integer.parseInt(orderQty_TENTS.getAttribute("value" ))== 0 &&
			Integer.parseInt(orderQty_BACKPACKS.getAttribute("value") )== 0 &&
			Integer.parseInt(orderQty_GLASSES.getAttribute("value")) == 0 && 
			Integer.parseInt(orderQty_SOCKS.getAttribute("value") )== 0 && 
			Integer.parseInt(orderQty_BOOTS.getAttribute("value" ))== 0 &&
			Integer.parseInt(orderQty_SHORTS.getAttribute("value") )== 0 ) {
	
			return true;
		}
		else {
			return false;
		}
	}
	//Setters for OrderQty

	public void setOrderQtyTENTS(String qty){ 
		orderQty_TENTS.clear();
		orderQty_TENTS.sendKeys(qty);
	}

	public void setOrderQtyBACKPACKS(String qty) {
		orderQty_BACKPACKS.clear(); 
		orderQty_BACKPACKS.sendKeys(qty);
	}

	public void setOrderQtyGLASSES(String qty){ 
		orderQty_GLASSES.clear(); 
		orderQty_GLASSES.sendKeys(qty);
	}
	
	public void setOrderQtySOCKS(String qty){
		orderQty_SOCKS.clear(); 
		orderQty_SOCKS.sendKeys(qty);
	}

	public void setOrderQtyBOOTS(String qty){ 
		orderQty_BOOTS.clear(); 
		orderQty_BOOTS.sendKeys(qty);
	}

	public void setOrderQtySHORTS(String qty){
		orderQty_SHORTS.clear(); 
		orderQty_SHORTS.sendKeys(qty);
	}
	
	//getters for OrderQty

	public String getOrderQtyTENTSOL() {
		return orderQty_TENTS.getAttribute("value");
	}

	public String getOrderQtyBACKPACKS(){
		return orderQty_BACKPACKS.getAttribute("value");
	}
	
	public String getOrderQtyGLASSES(){
		return orderQty_GLASSES.getAttribute("value");
	}
	
	public String getOrderQtySOCKS(){
		return orderQty_SOCKS.getAttribute("value");
	}
	
	public String getOrderQtyBOOTS(){
		return orderQty_BOOTS.getAttribute("value");
	}
	
	public String getOrderQtySHORTS(){
		return orderQty_SHORTS.getAttribute("value");
	}
	
	//table attributes
	public int getRowCount() {
		return rowList.size();
	}
	public int getTableRowCount() {
		List<WebElement> rows =catalogTable.findElements(By.xpath("tbody/tr"));
		return rows.size();
	}
	public int getTableColCount() {
		List<WebElement> cols =catalogTable.findElements(By.xpath("tbody/tr[1]/td"));
		return cols.size();
	}
	
	public String getCellData(int row,int col){

		WebElement col_element; 
		String cellData = null;
		col_element = catalogTable.findElement(By.xpath("tbody/tr["+row+"]/td["+col+"]"));
		cellData=col_element.getText().replaceAll("\\s","");			
		if (cellData.isEmpty() == true){
			cellData = col_element.findElement(By.xpath("h1/input")).getAttribute("value");
		}
		return cellData;

		}

		public boolean isPopupExists() {

			try {
				driver.switchTo().alert();
				return true;
			}catch(Exception e) {
				return false;
			} 
		}

		public void handlePopup() { 
			driver.switchTo().alert().accept();
		}
}
