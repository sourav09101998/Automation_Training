package OrangeHRM;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

import utilityScripts.Log4j2;

public class Loginpage {
	private static WebDriver wd;
	private static WebElement we;
	private static String path = "C:\\Selenium\\CoreFiles\\";
	static Actions ac;

	public static void main(String[] args) throws InterruptedException {

		openBrowser("chrome");
		navigateToGMOSite();
		userManagement();
		closeBrowser();
	}

	private static void openBrowser(String browserName) {
		if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", path + "geckodriver.exe");
			wd = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", path + "chromedriver.exe");
			wd = new ChromeDriver();
		} else {
			System.setProperty("webdriver.edge.driver", path + "msedgedriver.exe");
			wd = new EdgeDriver();
		}
		wd.manage().window().maximize();
		wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

	}

	private static void navigateToGMOSite() {

		wd.get("https://opensource-demo.orangehrmlive.com");
		ac = new Actions(wd);
		wd.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		System.out.println("Navigated to: " + wd.getTitle());
		we = wd.findElement(By.cssSelector("div>h5"));
		System.out.println("Home page Caption:" + we.getText());
		homePage();

	}

	private static void homePage() {

		wd.findElement(By.cssSelector("input[name=username]")).sendKeys("Admin");
		wd.findElement(By.cssSelector("input[name=password]")).sendKeys("admin123");
		wd.findElement(By.xpath("//button[@type='submit']")).click();
		wd.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		wd.findElement(By.xpath("//span[text()='Admin']")).click();

	}

	private static void userManagement() throws InterruptedException {

		addUser();
		getElementsfromTable();
		performAction();
		systemUser();

	}

	private static void systemUser() throws InterruptedException {
		checkResetForm();

	}
	private static void selectDropdown(WebElement name) {
		name.click();
		if (name.getAttribute("class").contains("up")) {
			List<WebElement> userList = name.findElements(By.xpath("parent::div/parent::div/following-sibling::div/child::*"));
			Log4j2.info("User list contains " + String.valueOf(userList.size()) + " elements");
			for (WebElement user : userList) {
				Log4j2.info(user.getText());
			}
			ac.moveToElement(userList.get(1)).click().perform();
			Log4j2.info("user selected:"+ name.findElement(By.xpath("parent::div/preceding-sibling::div")).getText());
		}
	}
	
	private static void fillBox(WebElement name ,String value ) {
		ac.click(name).perform();
		ac.sendKeys(name,value)
		.build().perform();
		
	}
	
	private static void addUser() throws InterruptedException {
		wd.findElement(By.cssSelector("i[class='oxd-icon bi-plus oxd-button-icon']")).click();
		System.out.println("Add Admin user: " + wd.getTitle());
		
		WebElement userRole = wd.findElement(By.xpath("//form/div[1]/div/div[1]/div/div[2]/div/div/div[2]/i"));
		selectDropdown(userRole);
		
		WebElement sbox=wd.findElement(By.xpath("//form/div[1]/div/div[2]/div/div[2]/div/div/input"));
		sbox.sendKeys("P");
		Thread.sleep(1000);
		sbox.sendKeys(Keys.DOWN);
		Thread.sleep(1000);
		sbox.sendKeys(Keys.ENTER);
		
		WebElement Username=wd.findElement(By.xpath("//form/div[1]/div/div[4]/div/div[2]/input"));
		fillBox(Username, "Anything");
		
		WebElement status = wd.findElement(By.xpath("//form/div[1]/div/div[3]/div/div[2]/div/div/div[2]/i"));
		selectDropdown(status);
		
		WebElement Password=wd.findElement(By.xpath("//form/div[2]/div/div[1]/div/div[2]/input"));
		fillBox(Password, "Password@12");
		
		WebElement Confirm_Password=wd.findElement(By.xpath("//form/div[2]/div/div[2]/div/div[2]/input"));
		fillBox(Confirm_Password, "Password@12");
		
		 
//		wd.findElement(By.xpath("//form/div[3]/button[@type='button']")).click();
		
		
		Thread.sleep(2000);
		
		
		
	}

	
	public static void getElementsfromTable() {
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
		for (int i = 1; i <= rows; i++) {
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
	}

	private static void performAction() throws InterruptedException {
//		deleteUser();
		editUser();

	}

	private static void editUser() {
//		List<WebElement> rowElements = wd.findElements(By.xpath("//div[@role='table']/div[2]/div"));
//		int rows = rowElements.size();
		for (int i=1;i<2;i++) {
			wd.findElement(By.xpath("//div[@class='oxd-table-body']/div["+i+"]//div[2]/following-sibling::div[last()]/div/button[2]")).click();
			System.out.println("click on edit button");
			editUserForm();
		
		}
	}

	private static void editUserForm() {
		we = wd.findElement(By.cssSelector("div>h6"));
		System.out.println("edit user page Caption:" + we.getText());
		
		
		
	}

	public static void deleteUser() throws InterruptedException {
//		List<WebElement> rowElements = wd.findElements(By.xpath("//div[@role='table']/div[2]/div"));
//		int rows = rowElements.size();
		for (int i = 1; i <= 5; i++) {
			String uName=wd.findElement(By.xpath("//div[@role='table']/div[2]/div[" + i + "]/div/div[2]")).getText();
			Thread.sleep(1000);
			if (uName == "Admin") {
				System.out.println(uName);
				System.out.println("Admin not deleted");				
			}
			else {
				System.out.println(uName);
				wd.findElement(By.xpath("//div[@class='oxd-table-body']/div["+i+"]//div[2]/following-sibling::div[last()]/div/button[1]")).click();
				System.out.println("click on delete button");
				Thread.sleep(2000);
				WebElement delete_yes=wd.findElement(By.xpath("//div[@role='document']/div[3]/button[2]"));
				Thread.sleep(1000);
				delete_yes.click();
				WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(3));
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div/h6")));
				System.out.println("after click on delete button");
				System.out.println("alert popup gone");
				
			}
			
		}
	}

	public static void checkResetForm() throws InterruptedException {

		List<WebElement> textboxes = wd
				.findElements(By.xpath("//div[2]/input[starts-with(@class,'oxd-input oxd-input')]"));
		textboxes.addAll(wd.findElements(By.xpath("//div[1]/input[starts-with(@placeholder,'Type for hints...')]")));
		for (WebElement textbox : textboxes) {
			textbox.clear();
			textbox.sendKeys(String.valueOf("enter"));
			Thread.sleep(1000);

		}
		wd.findElement(By.xpath("//div[@class='oxd-form-actions']/button[1]")).click();
		boolean flag = false;
		for (WebElement textbox : textboxes) {

			if (textbox.getAttribute("value").equals("0"))
				flag = true;
			else {
				flag = false;
				break;
			}
		}
		if (flag == true)
			System.out.println("All text boxes are reset to O-Functionality passed");
		else
			System.out.println("At least one text box is not reset to O-Functionality failed");

	}

	private static void closeBrowser() {
		wd.quit();
	}

}
