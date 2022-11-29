package OrangeHRM;

import org.testng.annotations.Test;

import utilityScripts.BrowserSetup;
import utilityScripts.BrowserSetup.Browser;
import utilityScripts.Log4j2;

import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class TestOrangeHRM {
	private BrowserSetup b;
	static Actions mouse_actions;
	private WebDriver wd = null;

	@BeforeClass
	public void beforeClass() throws Exception {
		b = new BrowserSetup(Browser.CHROME, false);
		wd = b.invokeBrowser("https://opensource-demo.orangehrmlive.com");
		mouse_actions = new Actions(wd);
		Assert.assertEquals(wd.getTitle(), "OrangeHRM");
		
	}

	@AfterClass
	public void afterClass() {
		b.closeBrowser();

	}

	@Test(priority = 1)
	public void TestLogin() {

		wd.findElement(By.name("username")).sendKeys("Admin");
		wd.findElement(By.name("password")).sendKeys("admin123");
		wd.findElement(By.cssSelector("[type='submit']")).click();
		wd.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		Assert.assertEquals(wd.findElement(By.tagName("h6")).getText(), "Dashboard");

	}

	@Test(priority = 2)
	public void TestAdmin() {
		wd.findElement(By.xpath("//span[text()='Admin']")).click();
		Assert.assertTrue(wd.findElement(By.xpath("//span/h6[text()='Admin']")).getText().equalsIgnoreCase("Admin"));
	}

	@Test(priority = 3, enabled = false)
	public void TestAddUser() throws InterruptedException {
		for(int i =0;i<4;i++) {
		wd.findElement(By.cssSelector(".oxd-icon.bi-plus.oxd-button-icon")).click();
		Assert.assertTrue(wd.findElement(By.cssSelector("div>h6")).getText().equalsIgnoreCase("Add User"));

		WebElement userRole = wd.findElement(By.xpath("//form/div[1]/div/div[1]/div/div[2]/div/div/div[2]/i"));
		selectDropdown(userRole);
		
		WebElement sbox=wd.findElement(By.xpath("//form/div[1]/div/div[2]/div/div[2]/div/div/input"));
		sbox.sendKeys("a");
		Thread.sleep(1000);
		sbox.sendKeys(Keys.DOWN);
		Thread.sleep(1000);
		sbox.sendKeys(Keys.ENTER);
		
		WebElement Username=wd.findElement(By.xpath("//form/div[1]/div/div[4]/div/div[2]/input"));
		fillBox(Username, "gggg"+i);
		
		WebElement status = wd.findElement(By.xpath("//form/div[1]/div/div[3]/div/div[2]/div/div/div[2]/i"));
		selectDropdown(status);
		
		WebElement Password=wd.findElement(By.xpath("//form/div[2]/div/div[1]/div/div[2]/input"));
		fillBox(Password, "Password@12");
		
		WebElement Confirm_Password=wd.findElement(By.xpath("//form/div[2]/div/div[2]/div/div[2]/input"));
		fillBox(Confirm_Password, "Password@12");
		
		wd.findElement(By.xpath("//form/div[3]/button[@type='submit']")).click();
		String title= wd.findElement(By.xpath("//*[@id='oxd-toaster_1']/div/div[1]/div[2]/p[1]")).getText();
//		String message= wd.findElement(By.xpath("//*[@id='oxd-toaster_1']/div/div[1]/div[2]/p[2]")).getText();
//		Log4j2.info("title of toast "+title);
//		Log4j2.info("message of toast "+message);

		Assert.assertEquals(title, "Success");
		i++;
		}
	}
	
	@Test(priority = 4, enabled = false)
	public void TestAddUserCancelButton() {
		wd.findElement(By.cssSelector(".oxd-icon.bi-plus.oxd-button-icon")).click();
		Assert.assertTrue(wd.findElement(By.cssSelector("div>h6")).getText().equalsIgnoreCase("Add User"));
		wd.findElement(By.xpath("//form/div[3]/button[@type='button']")).click();
		Assert.assertTrue(wd.findElement(By.cssSelector("div>h5")).getText().equalsIgnoreCase("System Users"));
		
	}
	@Test(priority = 5, enabled = false)
	public void TestGetElementsfromTable() {
//		WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div/h6")));
		String rows_Locator_xpath = "//div[@role='table']/div[2]/div";
		String cols_Locator_xpath = "//div[@role='table']/div[2]/div[1]/div/div";
		List<WebElement> rowElements = wd.findElements(By.xpath(rows_Locator_xpath));
		List<WebElement> colElements = wd.findElements(By.xpath(cols_Locator_xpath));
		int rows = rowElements.size();
		int columns = colElements.size();
		System.out.println("rows:" + rows + ", cols:" + columns);

		System.out.println("-----------------========================------------------");
		for (int i = 2; i <= columns - 1; i++) {

			WebElement head_col_element = wd.findElement(
					By.xpath("//div[@role='table']/div[@role='rowgroup']/div[@role='row']/div[" + i + "]"));
			String head_col_element_text = head_col_element.getText().replaceAll("\\s", " ");
			System.out.print(head_col_element_text + ", ");
		}
		System.out.println();
		int i;
		for ( i = 1; i <= rows; i++) {
			for (int j = 2; j <= columns - 1; j++) {

				WebElement col_element = wd
						.findElement(By.xpath("//div[@role='table']/div[2]/div[" + i + "]/div/div[" + j + "]"));
				String col_element_text = col_element.getText().replaceAll("\\s", " ");
				if (col_element.getText().isEmpty() == false) {
					System.out.print(col_element_text + ", ");
				} else {
					WebElement textbox = col_element.findElement(By.xpath("h1/input"));
					textbox.clear();
					textbox.sendKeys(String.valueOf((int) (Math.random() * 4)));
					System.out.print(textbox.getAttribute("name") + "=" + textbox.getAttribute("value"));
				}	
			}
			System.out.println();
		}
			Assert.assertEquals(rows,i-1);
	}
	
	@Test(priority = 6,enabled = false)
	public void TestDeleteButton() throws InterruptedException {
//		List<WebElement> rowElements = wd.findElements(By.xpath("//div[@role='table']/div[2]/div"));
//		int rows = rowElements.size();
		for (int i = 2; i <= 5; i++) {
			String uName=wd.findElement(By.xpath("//div[@role='table']/div[2]/div["+i+"]/div/div[2]")).getText();			
			System.out.println(uName);
			Thread.sleep(1000);
			if (uName == "Admin") {
				System.out.println(uName);
				wd.findElement(By.xpath("//div[@role='table']/div[2]/div["+i+"]//button[1]")).click();
				String title= wd.findElement(By.xpath("//*[@id='oxd-toaster_1']/div/div[1]/div[2]/p[1]")).getText();
				Log4j2.info("title of toast "+title);
				Assert.assertEquals(title, "Error");
				System.out.println("Admin not deleted");	
			}
			else {
				System.out.println(uName);
				WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(3));
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@role='table']/div[2]/div["+i+"]//button[1]")));
				wd.findElement(By.xpath("//div[@role='table']/div[2]/div["+i+"]//button[1]")).click();
				System.out.println("click on delete button");

				WebElement delete_yes=wd.findElement(By.xpath("//div[@role='document']/div[3]/button[2]"));

				delete_yes.click();
				String title= wd.findElement(By.xpath("//*[@id='oxd-toaster_1']/div/div[1]/div[2]/p[1]")).getText();
				Log4j2.info("title of toast "+title);
				Assert.assertEquals(title, "Success");
			}
			
		}
	}
	
	@Test(priority = 7, enabled = false)
	public void TestEditButton() throws InterruptedException {
		for(int i=1;i<2;i++) {
			wd.findElement(By.xpath("//div[@role='table']/div[2]/div["+i+"]//button[2]")).click();
			Assert.assertTrue(wd.findElement(By.cssSelector("div>h6")).getText().equalsIgnoreCase("Edit User"));
			
			WebElement userRole = wd.findElement(By.xpath("//form/div[1]/div/div[1]/div/div[2]/div/div/div[2]/i"));
			selectDropdown(userRole);
			
			wd.findElement(By.xpath("//form/div[1]/div/div[2]/div/div[2]/div/div/input")).click();
//			WebElement sbox=wd.findElement(By.xpath("//form/div[1]/div/div[2]/div/div[2]/div/div/input"));
//			sbox.sendKeys("g");
//			Thread.sleep(1000);
//			sbox.sendKeys(Keys.DOWN);
//			Thread.sleep(1000);
//			sbox.sendKeys(Keys.ENTER);
			
			WebElement Username=wd.findElement(By.xpath("//form/div[1]/div/div[4]/div/div[2]/input"));
			String nameString= Username.getAttribute("value");
			Log4j2.info(nameString);
			fillBox(Username, nameString);
			
			WebElement status = wd.findElement(By.xpath("//form/div[1]/div/div[3]/div/div[2]/div/div/div[2]/i"));
			selectDropdown(status);
			
			wd.findElement(By.xpath("//i[@class='oxd-icon bi-check oxd-checkbox-input-icon']")).click();
			
			WebElement Password=wd.findElement(By.xpath("//form/div[2]/div/div[1]/div/div[2]/input"));
			fillBox(Password, "Password@12");
			
			WebElement Confirm_Password=wd.findElement(By.xpath("//form/div[2]/div/div[2]/div/div[2]/input"));
			fillBox(Confirm_Password, "Password@12");
			
			
			wd.findElement(By.xpath("//form/div[3]/button[@type='submit']")).click();
			Thread.sleep(2000);
			String title= wd.findElement(By.xpath("//*[@id='oxd-toaster_1']/div/div[1]/div[2]/p[1]")).getText();

			Assert.assertEquals(title, "Success");
			
		}
		
	}
	@Test(priority = 8, enabled = false)
	public void TestEditUserCancelButton() {
		for (int i = 1; i < 5; i++) {
			wd.findElement(By.xpath("//div[@role='table']/div[2]/div[" + i + "]//button[2]")).click();
			Assert.assertTrue(wd.findElement(By.cssSelector("div>h6")).getText().equalsIgnoreCase("Edit User"));
			wd.findElement(By.xpath("//form/div[3]/button[@type='button']")).click();
			Assert.assertTrue(wd.findElement(By.cssSelector("div>h5")).getText().equalsIgnoreCase("System Users"));
		}
	}
	
	@Test(priority = 9, enabled = false)
	public void TestSearchButtonByUserName() throws InterruptedException {
		List<WebElement> nameList = wd.findElements(By.xpath("//div[@role='table']/div[2]/div/div/div[2]"));
		Random rand = new Random();
		String userString =nameList.get((int) (rand.nextInt((nameList.size() - 1) + 1) + 1)).getText();
		wd.findElement(By.xpath("//form/div[1]/div/div[1]/div/div[2]/input")).sendKeys(userString);
		wd.findElement(By.cssSelector("button[type='submit']")).click();
		Thread.sleep(2000);
		List<WebElement> resultList = wd.findElements(By.xpath("//div[@role='table']/div[2]/div/div/div[2]"));

		List<WebElement> filteredList = resultList.stream()
											.filter(s->s.getText().contains(userString))
											.collect(Collectors.toList());
		
		Assert.assertEquals(filteredList.size(),resultList.size());
		
	}
	@Test(priority = 10, enabled = false)
	public void TestSearchButtonByUserRole() throws InterruptedException {
		WebElement userRole = wd.findElement(By.xpath("//form/div[1]/div/div[2]/div/div[2]/div/div/div[2]/i"));
		String strvalue=selectDropdown(userRole);
		wd.findElement(By.cssSelector("button[type='submit']")).click();
		Thread.sleep(2000);
		List<WebElement> resultList = wd.findElements(By.xpath("//div[@role='table']/div[2]/div/div/div[3]"));

		List<WebElement> filteredList = resultList.stream()
											.filter(s->s.getText().contains(strvalue))
											.collect(Collectors.toList());
		
		Assert.assertEquals(filteredList.size(),resultList.size());
		
	}
	@Test(priority = 11, enabled = false)
	public void TestSearchButtonByEmployeeName() throws InterruptedException {
		List<WebElement> nameList = wd.findElements(By.xpath("//div[@role='table']/div[2]/div/div/div[4]"));
		Random rand = new Random();
		WebElement valElement=nameList.get((int) (rand.nextInt((nameList.size() - 1) + 1) + 1));
		String userString =valElement.getText();
		WebElement sbox=wd.findElement(By.xpath("//form/div[1]/div/div[3]/div/div[2]//input"));
		sbox.sendKeys(userString);
		Thread.sleep(3000);
        System.out.println(valElement);
        WebElement dElement =wd.findElement(By.xpath("//div[@role='listbox']"));
		mouse_actions.moveToElement(dElement).click().perform();
		
		wd.findElement(By.cssSelector("button[type='submit']")).click();
		Thread.sleep(2000);
		List<WebElement> resultList = wd.findElements(By.xpath("//div[@role='table']/div[2]/div/div/div[4]"));

		List<WebElement> filteredList = resultList.stream()
											.filter(s->s.getText().contains(userString))
											.collect(Collectors.toList());
		
		Assert.assertEquals(filteredList.size(),resultList.size());
		
	}
	@Test(priority = 12, enabled = false)
	public void TestSearchButtonByStatus() throws InterruptedException {
		WebElement userRole = wd.findElement(By.xpath("//form/div[1]/div/div[4]/div/div[2]/div/div/div[2]/i"));
		String strvalue=selectDropdown(userRole);
		wd.findElement(By.cssSelector("button[type='submit']")).click();
		Thread.sleep(2000);
		List<WebElement> resultList = wd.findElements(By.xpath("//div[@role='table']/div[2]/div/div/div[5]"));
		
		List<WebElement> filteredList = resultList.stream()
				.filter(s->s.getText().contains(strvalue))
				.collect(Collectors.toList());
		
		Assert.assertEquals(filteredList.size(),resultList.size());
		
	}
	
	@Test(priority = 13, enabled = false)
	public void TestSystemUsersResetButton() throws InterruptedException {
		wd.findElement(By.xpath("//form/div[2]/button[1]")).click();
		WebElement Username = wd.findElement(By.xpath("//form/div[1]/div/div[3]/div/div[2]//input"));
		Assert.assertEquals(Username.getAttribute("value"), "");
		WebElement UserRole = wd.findElement(By.xpath("//form/div[1]/div/div[2]/div/div[2]/div/div/div[2]/i"));
		Assert.assertEquals(UserRole.getAttribute("value"), null);
		WebElement EmployeeName = wd.findElement(By.xpath("//form/div[1]/div/div[3]/div/div[2]//input"));
		Assert.assertEquals(EmployeeName.getAttribute("value"), "");
		WebElement status = wd.findElement(By.xpath("//form/div[1]/div/div[4]/div/div[2]/div/div/div[2]/i"));
		Assert.assertEquals(status.getAttribute("value"), null);
	}
	
	@Test(priority = 14, enabled = true)
	public void TestMultipleSelectDeleteButton() throws InterruptedException {
		for(int i=1;i<4;i++) {
			wd.findElement(By.xpath("//div[@role='table']/div[2]/div["+i+"]/div/div[1]")).click();			
		}
		wd.findElement(By.xpath("//button[normalize-space()='Delete Selected']")).click();			
		WebElement delete_yes=wd.findElement(By.xpath("//div[@role='document']/div[3]/button[2]"));
		delete_yes.click();
		String title= wd.findElement(By.xpath("//*[@id='oxd-toaster_1']/div/div[1]/div[2]/p[1]")).getText();
		Log4j2.info("title of toast "+title);
		Assert.assertEquals(title, "Success");
		
	}
	
		
	
	private static String selectDropdown(WebElement name) {
		name.click();
		String values = null;
		if (name.getAttribute("class").contains("up")) {
			List<WebElement> userList = name.findElements(By.xpath("parent::div/parent::div/following-sibling::div/child::*"));
			Log4j2.info("User list contains " + String.valueOf(userList.size()) + " elements");
			for (WebElement user : userList) {
				Log4j2.info(user.getText());
			}
			mouse_actions.moveToElement(userList.get(1)).click().perform();
			values=name.findElement(By.xpath("parent::div/preceding-sibling::div")).getText();
			Log4j2.info("user selected:"+ values);
		}
		return values;
	}
	
	private static void fillBox(WebElement name ,String value ) {
		mouse_actions.click(name).perform();
		mouse_actions.sendKeys(name,Keys.CONTROL+"a")
		.sendKeys(name,Keys.BACK_SPACE)
		.sendKeys(name,value)
		.click().perform();
		
	}
	
}
