package StepDef;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GMOStep {
	
	static WebDriver driver = null;
	static WebElement we =null;
	static String pageTitle =null;
	
	@BeforeAll
	public static void navigate_to_gmo() {
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\CoreFiles\\chromedriver.exe");
		ChromeOptions chromeOptions = new ChromeOptions();
		driver = new ChromeDriver(chromeOptions);
		driver.get("https://demo.borland.com/gmopost/");

	}
	@AfterAll
	public static void close_gmo() {
		driver.quit();
	}
	
	@Given("I am on gmo home page")
	public void i_am_on_gmo_home_page() {
		we = driver.findElement(By.cssSelector("h1>font"));
		Assert.assertEquals(driver.getTitle(), "Welcome to Green Mountain Outpost");
	}

	@When("I enter gmo online")
	public void i_enter_gmo_online() {
		driver.findElement(By.xpath("//input[@value='Enter GMO OnLine']")).click();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		
	}

	@Then("The application should display catalog page")
	public void the_application_should_display_catalog_page() {
		Assert.assertEquals(driver.getTitle(), "OnLine Catalog");
	}

	@Given("Online catalog page is displayed")
	public void online_catalog_page_is_displayed() {
		Assert.assertEquals(driver.getTitle(), "OnLine Catalog");
	}

	@When("I place order for {int} qty of {int} Person Dome Tent")
	public void i_place_order_for_qty_of_person_dome_tent(Integer int1, Integer int2) {
		List<WebElement> QuantityFields = driver.findElements(By.xpath("//input[@type='text']"));
		int NoOfQuantityFields = QuantityFields.size();
		System.out.println("number of rows in the catalog table: " + NoOfQuantityFields);
		int randVal = 0;
//				(int) (Math.random() * NoOfQuantityFields+1) + 1;
		System.out.println("random value: " + randVal);
		String qty = String.valueOf(int1);
		
		enterData(QuantityFields.get(randVal), qty);
		System.out.println("item selected: " + QuantityFields.get(randVal).getAttribute("name") + "="
				+ QuantityFields.get(randVal).getAttribute("value"));
		driver.findElement(By.cssSelector("[value='Place An Order']")).click();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		System.out.println("Navigated to: " + driver.getTitle());
		Assert.assertEquals(driver.getTitle(), "Place Order");
	
	
	}
	private static void enterData(WebElement we, String qty) {

		we.clear();
		we.sendKeys(qty);
	}

	@Then("Place Order page is displayed with the selected items")
	public void place_order_page_is_displayed_with_the_selected_items() {
		System.out.println("Navigated to: " + driver.getTitle());
		Assert.assertEquals(driver.getTitle(), "Place Order");

	}

    //gmodt.feature:12
    @When("I select {string} quantity of {string}")
    public void i_select_quantity_of(String orderQty, String itemDesc) {
        String itemSelect = "//a/strong[normalize-space(text())='"+itemDesc+"']/parent::*/parent::*/following-sibling::*/h1/input";
        we=driver.findElement(By.xpath(itemSelect));we.clear();we.sendKeys(orderQty);
    }
    
    //gmodt.feature:13
    @Then("I should see items {string} selected with quantity {string}")
    public void i_should_see_items_selected(String itemDesc, String orderQty) {
            String itemSelect = "//strong[normalize-space(text())='"+itemDesc+"']/parent::*/parent::*/following-sibling::*/h1/input";
        we=driver.findElement(By.xpath(itemSelect));
        double actualQty= Integer.valueOf(we.getAttribute("value"));
        Assert.assertEquals(Integer.valueOf(orderQty), actualQty, 0);        
    }
    
    //gmodt.feature:22
    @Given("I have selected the items on catalog page")
    public void i_have_selected_the_items_on_catalog_page() {
            List<WebElement> items= we.findElements(By.xpath("//h1/input"));
            for(WebElement item:items) {
                    if (Integer.valueOf(item.getAttribute("value"))>0){
                            Assert.assertTrue(Integer.valueOf(item.getAttribute("value"))>0);
                            break;                                
                    }
            }        
    }
    
    //gmodt.feature:23
    @When("I place order for the items")
    public void i_place_order_for_the_items() {
            driver.findElement(By.name("bSubmit")).click();
            System.out.println("clicked on place order button");
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
    }        
    
  //gmodt.feature:29
    @When("I place order for below items") 
    public void i_place_order_for_below_items (DataTable dtItems) {
    	List<Map<String, String>> catalog =dtItems.asMaps (String.class, String.class);
    	for (int index=0;index<catalog.size(); index++) {
    		String itemName=catalog.get(index).get("itemDesc"); 
    		String orderQty= catalog.get(index).get("orderQty");
    		String element="//a/strong [normalize-space(text())='"+itemName+"']/../../following-sibling::*/h1/input";
    		we=driver.findElement(By.xpath(element));
    		we.clear();
    		we.sendKeys (orderQty);
    		System.out.println("added "+orderQty+" nos of "+itemName);
    	}	
	    driver.findElement(By.name("bSubmit")).click();
	    System.out.println("clicked on place order button"); 
	    driver.manage().timeouts () .pageLoadTimeout (Duration.ofSeconds(5));
    }
	
	@Given("Place order page is displayed")
	public void place_order_page_is_displayed() {
		System.out.println("Navigated to: " + driver.getTitle());
		Assert.assertEquals(driver.getTitle(), "Place Order");
	
	}

	@When("the I proceeed to place order with the selected item")
	public void the_i_proceeed_to_place_order_with_the_selected_item() {
		driver.findElement(By.cssSelector("[value='Proceed With Order']")).click();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		
	}

	@Then("I should be taken to billing info page to provice my address")
	public void i_should_be_taken_to_billing_info_page_to_provice_my_address() {
		System.out.println("Navigated to: " + driver.getTitle());
		Assert.assertEquals(driver.getTitle(), "Billing Information");
	}

	@Given("I am on Billing page")
	public void i_am_on_billing_page() {
		System.out.println("Navigated to: " + driver.getTitle());
		Assert.assertEquals(driver.getTitle(), "Billing Information");
	}

	@When("I input below billing address")
	public void i_input_below_billing_address(DataTable dt) {
		List<Map<String,String>> addressList =dt.asMaps(String.class,String.class);
		String billName=addressList.get(0).get("billName");
		driver.findElement(By.name("billName")).sendKeys(billName);
		driver.findElement(By.name("billAddress")).sendKeys(addressList.get(0).get("billAddress"));
		driver.findElement(By.name("billCity")).sendKeys(addressList.get(0).get("billCity"));
		driver.findElement(By.name("billState")).sendKeys(addressList.get(0).get("billState"));
		driver.findElement(By.name("billZipCode")).sendKeys(addressList.get(0).get("billZipCode"));
		driver.findElement(By.name("billPhone")).sendKeys(addressList.get(0).get("billPhone"));
		driver.findElement(By.name("billEmail")).sendKeys(addressList.get(0).get("billEmail"));
		driver.findElement(By.name("CardNumber")).sendKeys(addressList.get(0).get("CardNumber"));
		driver.findElement(By.name("CardDate")).sendKeys(addressList.get(0).get("CardDate"));
		driver.findElement(By.name("shipSameAsBill")).click();
	}
	@When("I input correct billing address")
	public void i_input_correct_billing_address() {
		driver.findElement(By.name("billName")).sendKeys("sourav");
		driver.findElement(By.name("billAddress")).sendKeys("Address");
		driver.findElement(By.name("billCity")).sendKeys("city");
		driver.findElement(By.name("billState")).sendKeys("state");
		driver.findElement(By.name("billZipCode")).sendKeys("12345");
		driver.findElement(By.name("billPhone")).sendKeys("1234567890");
		driver.findElement(By.name("billEmail")).sendKeys("email@gmail.com");
		driver.findElement(By.name("CardNumber")).sendKeys("123456789012345");
		driver.findElement(By.name("CardDate")).sendKeys("12/24");
		driver.findElement(By.cssSelector("[name='shipSameAsBill']")).click();
		
	}

	@When("I checkout")
	public void i_checkout() {
		
		driver.findElement(By.cssSelector("[value='Place The Order']")).click();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
	}

	@Then("I should be taken to receipt page")
	public void i_should_be_taken_to_receipt_page() {
		System.out.println("Navigated to: " + driver.getTitle());
		Assert.assertEquals(driver.getTitle(), "OnLine Store Receipt");
	}

	@Given("I am in Receipt page")
	public void i_am_in_receipt_page() {
		System.out.println("Navigated to: " + driver.getTitle());
		Assert.assertEquals(driver.getTitle(), "OnLine Store Receipt");
	}

	@When("I opt to return to home page")
	public void i_opt_to_return_to_home_page() {
		driver.findElement(By.name("bSubmit")).click();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
	}

	@Then("I should be navigated to GMO home page")
	public void i_should_be_navigated_to_gmo_home_page() {
		System.out.println("Navigated back to : " + driver.getTitle());
		Assert.assertEquals(driver.getTitle(), "Welcome to Green Mountain Outpost");
	}

}
