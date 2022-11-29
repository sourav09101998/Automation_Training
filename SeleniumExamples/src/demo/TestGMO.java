package demo;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utilityScripts.BrowserSetup;
import utilityScripts.BrowserSetup.Browser;

public class TestGMO {
	private static WebDriver wd;
	private static WebElement we;
	private BrowserSetup browserSetup;

	@DataProvider(name = "billAddress")
	public Object[][] dpAddress() {
		return new Object[][] { new Object[] { "Appu", "Kamakya Layout", "Bengaluru", "Kar", "56005", "9657894110",
				"appu@gmail.in" }, };
	}

	@BeforeClass
	public void beforeClass() throws Exception {
		browserSetup = new BrowserSetup(Browser.CHROME, false);
		wd = browserSetup.invokeBrowser("https://demo.borland.com/gmopost/");
	}

	@AfterClass
	public void afterClass() {
		browserSetup.closeBrowser();

	}

	@Test(priority = 1)
	public void testNavigateToGMOSite() {
		System.out.println("Navigated to: " + wd.getTitle());
		we = wd.findElement(By.cssSelector("h1>font"));
		System.out.println("Home page caption:" + we.getText());
		Assert.assertEquals(wd.getTitle(), "Welcome to Green Mountain Outpost");
		Assert.assertTrue(we.getText().equals("GMO OnLine"));
	}

	@Test(priority = 2, dependsOnMethods = "testNavigateToGMOSite")
	public void testCatalogPage() {
		wd.findElement(By.xpath("//input[@value='Enter GMO OnLine']")).click();
		wd.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		System.out.println("Navigated to: " + wd.getTitle());
		Assert.assertEquals(wd.getTitle(), "OnLine Catalog");
	}

	@Test(priority = 3, dependsOnMethods = "testCatalogPage")
	public void testplaceorderPage() {
		enterOrderQuantity();
		wd.findElement(By.cssSelector("[value='Place An Order']")).click();
		wd.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		System.out.println("Navigated to: " + wd.getTitle());
		Assert.assertEquals(wd.getTitle(), "Place Order");
	}

	@Test(priority = 4, dataProvider = "billAddress", dependsOnMethods = "testplaceorderPage")
	public void testbillingPage(String[] billAddress) {
		wd.findElement(By.cssSelector("[value='Proceed With Order']")).click();
		wd.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		System.out.println("Navigated to: " + wd.getTitle());
		Assert.assertEquals(wd.getTitle(), "Billing Information");

		wd.findElement(By.name("billName")).sendKeys(billAddress[0]);
		wd.findElement(By.name("billAddress")).sendKeys(billAddress[1]);
		wd.findElement(By.name("billCity")).sendKeys(billAddress[2]);
		wd.findElement(By.name("billState")).sendKeys(billAddress[3]);
		wd.findElement(By.name("billZipCode")).sendKeys(billAddress[4]);
		wd.findElement(By.name("billPhone")).sendKeys(billAddress[5]);
		wd.findElement(By.name("billEmail")).sendKeys(billAddress[6]);
		String sCardType = selectCardType();
		if (sCardType.equalsIgnoreCase("Amex"))
			wd.findElement(By.name("CardNumber")).sendKeys("123456789012345");
		else
			wd.findElement(By.name("CardNumber")).sendKeys("1234567890123456");

		wd.findElement(By.name("CardDate")).sendKeys("12/24");
		wd.findElement(By.cssSelector("[name='shipSameAsBill']")).click();
		wd.findElement(By.cssSelector("[value='Place The Order']")).click();
		wd.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
	}

	@Test(priority = 5, dependsOnMethods = "testbillingPage")
	public void testReceiptPage() {
		System.out.println("Navigated to: " + wd.getTitle());
		Assert.assertEquals(wd.getTitle(), "OnLine Store Receipt");
	}

	private static void enterOrderQuantity() {

		List<WebElement> QuantityFields = wd.findElements(By.xpath("//input[@type='text']"));
		int NoOfQuantityFields = QuantityFields.size();
		System.out.println("number of rows in the catalog table: " + NoOfQuantityFields);
		int randVal = (int) (Math.random() * NoOfQuantityFields+1) + 1;
		System.out.println("random value: " + randVal);
		String qty = String.valueOf(randVal);
		enterData(QuantityFields.get(randVal), qty);
		System.out.println("item selected: " + QuantityFields.get(randVal).getAttribute("name") + "="
				+ QuantityFields.get(randVal).getAttribute("value"));

	}

	private static void enterData(WebElement we, String qty) {

		we.clear();
		we.sendKeys(qty);
	}

	private static String selectCardType() {
		WebElement cardType = wd.findElement(By.name("CardType"));
		int randVal = (int) (Math.random() * 3) + 1;
		wd.findElement(By.cssSelector("select[name='CardType']>option:nth-child(" + randVal + ")")).click();
		String valueselected = cardType.getAttribute("value");
		System.out.println("card type selected: " + valueselected);
		return valueselected;
	}
}