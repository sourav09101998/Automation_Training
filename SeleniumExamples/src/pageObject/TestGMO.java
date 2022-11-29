package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utilityScripts.Log4j2;

public class TestGMO {

	// Create a WebDriver reference variable.
	WebDriver driver;
	GMOHome objHomePage;

	@BeforeTest
	public void setup() {

		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\CoreFiles\\chromedriver.exe");
		ChromeOptions chromeOptions = new ChromeOptions();
		driver = new ChromeDriver(chromeOptions);
		driver.get("https://demo.borland.com/gmopost/");
		objHomePage =PageFactory.initElements(driver,GMOHome.class);
		Log4j2.info("Home Page Title:"+objHomePage.getPageTitle());
	}
	@AfterTest
	public void after() {
		driver.quit();
	}

	@Test(priority = 0)
	public void test_GMO_Page() {

		objHomePage.enterAboutTheGMOSite();;
		objHomePage.enterBrowserTestPage();
		objHomePage.enterGMOOnline();
		String OnLineCatalog=objHomePage.getPageTitle();
		Assert.assertEquals(OnLineCatalog,"OnLine Catalog");
		
	}
	@Test(priority = 1)
	public void TestCatalogPage() {
		Catalog catalog=PageFactory.initElements(driver, Catalog.class);
		catalog.setOrderQtyBOOTS("2");
		catalog.setOrderQtyBACKPACKS("1");
		for(int i=1;i<=catalog.getTableRowCount();i++) {
			for(int j=1;j<=catalog.getTableColCount();j++) {
				Log4j2.info(catalog.getCellData(i, j));
			}
		}
		catalog.placeOrder();
	}
	@Test(priority = 2)
	public void TestPlaceOrderPage() {
		PlaceOrder placeOrder=PageFactory.initElements(driver, PlaceOrder.class);
		String titleString=objHomePage.getPageTitle();
		Assert.assertEquals(titleString,"Place Order");
		placeOrder.proceedwithOrder();
	}
	@Test(priority = 3)
	public void TestBillingInformationPage() {
		Billinginfo billinginfo=PageFactory.initElements(driver, Billinginfo.class);
		String titleString=objHomePage.getPageTitle();
		Assert.assertEquals(titleString,"Billing Information");
		billinginfo.setbillName("name");
		billinginfo.setbillAddress("Address");
		billinginfo.setbillCity("city");
		billinginfo.setbillState("State");
		billinginfo.setbillZipCode("12343");
		billinginfo.setbillPhone("1234567890");
		billinginfo.setbillEmail("e@gmail.com");
		billinginfo.selectShipSameAsBill();
		billinginfo.setCardTypeByIndex(1);
		billinginfo.setCardNumber("1234567890123456");
		billinginfo.setCardDate("12/25");
		billinginfo.placetheOrder();
	}
	@Test(priority = 4)
	public void TestReceipt() {
		Receipt receipt=PageFactory.initElements(driver, Receipt.class);
		String titleString=objHomePage.getPageTitle();
		Assert.assertEquals(titleString,"OnLine Store Receipt");
		receipt.proceedwithOrder();
		
	}

}
