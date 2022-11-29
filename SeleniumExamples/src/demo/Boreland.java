package demo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import utilityScripts.BrowserSetup;
import utilityScripts.BrowserSetup.Browser;

public class Boreland {
	private static WebDriver wd;
	private static WebElement we;

	public static void main(String[] args) throws Exception {
		BrowserSetup browserSetup = new BrowserSetup(Browser.CHROME, false);
		wd = browserSetup.invokeBrowser("https://demo.borland.com/gmopost/");

		readExcel();
		browserSetup.closeBrowser();
	}

	private static void navigateToGMOSite() {

		System.out.println("Navigated to: " + wd.getTitle());
		we = wd.findElement(By.cssSelector("h1>font"));
		System.out.println("Home page Caption:" + we.getText());
		otherPagesinHomePage();

	}

	private static void otherPagesinHomePage() {
		wd.findElement(By.cssSelector("[name='bAbout']")).click();
		wd.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		System.out.println("Navigated to:" + wd.getTitle());
		wd.navigate().back();

		wd.findElement(By.cssSelector("[name='bBrowserTest']")).click();
		wd.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		System.out.println("Navigated to:" + wd.getTitle());
		wd.navigate().back();

		System.out.println("Navigated back to:" + wd.getTitle());
	}

	private static void catalogPage() {
		we = wd.findElement(By.xpath("//input[@value='Enter GMO OnLine']"));
		we.click();
		wd.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		System.out.println("Navigated to: " + wd.getTitle());
		checkBrokenLinksOfItems();
//		getElementsfromTable();
//		getElementsfromTableStream();
//		enterOrderQuantity();
		WebElement PlaceorderButton = wd.findElement(By.name("bSubmit"));
		PlaceorderButton.click();
		while (isAlertPresent()) {
			wd.switchTo().alert().accept();
			enterOrderQuantity();
			PlaceorderButton.click();
		}
	}

	private static boolean isAlertPresent() {
		try {
			wd.switchTo().alert();
			return true;
		} catch (Exception e) {
			return false;

		}

	}

	private static void checkBrokenLinksOfItems() {

		List<WebElement> lstitems = wd.findElements(By.cssSelector("a>strong"));

		lstitems.stream().map(x -> x.getText()).forEach(System.out::println);
		int i = 0;
		while (i < lstitems.size()) {
			String itemName = lstitems.get(i).getText();
			System.out.println("Click on: " + itemName);
			lstitems.get(i).click();
			String productName = wd.findElement(By.xpath("//h2[" + (i + 1) + "]/a")).getText();
			System.out.println("productName:" + productName);
			wd.navigate().back();
			i++;
			lstitems = wd.findElements(By.cssSelector("a>strong"));
		}

	}

	private static void placeOrderPage() {
//		we = wd.findElement(By.name("QTY_TENTS"));
//		we.clear();
//		we.sendKeys("2");
		wd.findElement(By.name("bSubmit")).click();
		wd.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		System.out.println("Navigated to: " + wd.getTitle());
	}

	private static void billingPage(String[] billAddress) {
		System.out.println("Navigated to: " + wd.getTitle());

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
		wd.findElement(By.name("CardDate")).sendKeys("12/25");
		wd.findElement(By.cssSelector("[name='shipSameAsBill']")).click();
		wd.findElement(By.cssSelector("[value='Place The Order']")).click();
		wd.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
	}

	public static String selectCardType() {

		WebElement cardType = wd.findElement(By.name("CardType"));
		Select selectCard = new Select(cardType);
		List<WebElement> options = selectCard.getOptions();
		int elementSize = options.size();
		int randVal = (int) (Math.random() * elementSize);
		selectCard.selectByIndex(randVal);

//		OR
//		wd.findElement(By.cssSelector("select[name=" + card Type+">option:nth-child(" randVal + "'")).click();

		String valueSelected = cardType.getAttribute("value");
		System.out.println("Card type selected:" + valueSelected);
		return valueSelected;

	}

	private static void receiptPage() {

		System.out.println("Navigated to: " + wd.getTitle());
		wd.findElement(By.name("bSubmit")).click();
		wd.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		System.out.println("Navigated back to : " + wd.getTitle());

	}

	public static void getElementsfromTable() {
		String rows_Locator_xpath = "//table/tbody/tr[2]/td/div/center/table/tbody/tr";
		String cols_Locator_xpath = "//table/tbody/tr[2]/td/div/center/table/tbody/tr[1]/td";
		List<WebElement> rowElements = wd.findElements(By.xpath(rows_Locator_xpath));
		List<WebElement> colElements = wd.findElements(By.xpath(cols_Locator_xpath));
		int rows = rowElements.size();
		int columns = colElements.size();
		System.out.println("rows:" + rows + ", cols:" + columns);

		System.out.println("-------");
		for (int i = 1; i <= rows; i++) {
			for (int j = 1; j <= columns; j++) {
				WebElement col_element = wd.findElement(
						By.xpath("//table/tbody/tr[2]/td/div/center/table/tbody/tr[" + i + "]/td[" + j + "]"));
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
	public static void checkResetForm() {

		List<WebElement> textboxes = wd.findElements(By.cssSelector("h1>input"));
		for (WebElement textbox : textboxes) {
			textbox.clear();
			textbox.sendKeys(String.valueOf((int) (Math.random() * 9)));
		}
		wd.findElement(By.name("bReset")).click();
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

	public static void readExcel() throws IOException {

		String dbURL = "jdbc:mysql://localhost:3306/whatacart";
		String username = "root";
		String password = "root";
		Connection conn = null;
		Statement sqlStmt = null;
		try {
			conn = DriverManager.getConnection(dbURL, username, password);
			System.out.println("DB Connected");
			String sqlQuery = "SELECT * from gmobilladdress";
			sqlStmt = conn.createStatement();
			ResultSet rs = sqlStmt.executeQuery(sqlQuery);
			ResultSetMetaData rsmeta = rs.getMetaData();
			int cols = rsmeta.getColumnCount();
			String[] billAddress = new String[cols];
			System.out.println("#cols=" + cols); // print the col names with its type
			for (int i = 1; i <= cols; i++) {
				System.out.println(i + "." + rsmeta.getColumnName(i) + "\t\t" + rsmeta.getColumnTypeName(i));
			}
			while (rs.next()) {
				billAddress[0] = rs.getString("billName");
				billAddress[1] = rs.getString("billAddress");
				billAddress[2] = rs.getString("billCity");
				billAddress[3] = rs.getString("billState");
				billAddress[4] = rs.getString("billZipCode");
				billAddress[5] = rs.getString("billPhone");
				billAddress[6] = rs.getString("billEmail");

				System.out.println(Arrays.toString(billAddress));
				navigateToGMOSite();
				catalogPage();
				placeOrderPage();
				billingPage(billAddress);
				receiptPage();
			}
			sqlStmt.close();
			conn.close();
		} catch (java.sql.SQLRecoverableException e) {
			System.out.println("SQLRecoverable Exption-10 Error: The Network Adapter could not establish t");
		} catch (java.sql.SQLException e) {
			System.out.println("SQLException-DB not Connected");
		}
	}

//		String[] billAddress = new String[7];
//		try {
//			XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("C:\\Selenium\\CoreFiles\\gmoBill.xlsx"));
//			int sheetIndex = 0;
//			XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
//			for (Row tempRow : sheet) {
//				int i = 0;
//				System.out.println(tempRow.getRowNum());
//				if (tempRow.getRowNum() >= 0) {
//					for (Cell tempCell : tempRow) {
//						if (tempCell.getCellType() == CellType.STRING)
//							billAddress[i] = tempCell.getStringCellValue();
//						if (tempCell.getCellType() == CellType.NUMERIC) {
//							billAddress[i] = String.valueOf((int) tempCell.getNumericCellValue());
//						}
//
//						i++;
//					}
//					System.out.println(Arrays.toString(billAddress));
//					navigateToGMOSite();
//					catalogPage();
//					placeOrderPage();
//					billingPage(billAddress);
//					receiptPage();
//				}
//			}
//		} catch (FileNotFoundException fnfe) {
//			fnfe.printStackTrace();
//		} catch (IOException ioe) {
//			ioe.printStackTrace();
//		}
//	}

	public static void getElementsfromTableStream() {
		System.out.println();
		List<WebElement> items = wd.findElements(By.xpath("//table/tbody/tr[2]/td/div/center/table/tbody/tr/td"));
		items.stream().map(item -> item.getText().replaceAll("\\s", " ")).filter(item -> !item.isEmpty())
				.forEach(name -> System.out.print(name + ", "));

		System.out.println();
		List<WebElement> orderQty = wd.findElements(By.xpath("//form/table/tbody/tr[2]//table/tbody/tr/td/h1/input"));
		orderQty.stream().peek(item -> item.clear())
				.peek(item -> item.sendKeys(String.valueOf((int) (Math.random() * 4))))
				.map(item -> item.getAttribute("name") + "=" + item.getAttribute("value"))
				.forEach(name -> System.out.print(name + ", "));
	}

	private static void enterOrderQuantity() {

		// input the quantity in the Order Quantity column - Random selection of Items
		// store the Item Name and price for the quantity entered.

		List<WebElement> QuantityFields = wd.findElements(By.xpath("//input[@type='text']"));
		int NoOfQuantityFields = QuantityFields.size();
		System.out.println("Number of rows in the catalog table: " + NoOfQuantityFields);
		int randVal = (int) (Math.random() * NoOfQuantityFields);
		System.out.println("random value: " + randVal);
		String qty = String.valueOf(randVal);
		enterData(QuantityFields.get(randVal), qty);
		System.out.println("Item selected: " + QuantityFields.get(randVal).getAttribute("name") + "="

				+ QuantityFields.get(randVal).getAttribute("value"));

	}

	private static void enterData(WebElement we, String qty) {
		we.clear();
		we.sendKeys(qty);

	}
}
