package testingGrid;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

import java.awt.AWTException;
import java.awt.Robot;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import utilityScripts.Log4j2;

public class TestFlipkart extends TestBase{
	
	Actions mouse_actions = null;

	@Test(priority = 1)
	public void TestLaunchFlipkart() {
		wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		wd.get("http://www.flipkart.com/");
		wd.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		Assert.assertTrue(wd.getTitle().startsWith("Online Shopping Site"));
	}

	@Test(priority = 2)
	public void TestLogin() {
		try {
			WebElement we = wd.findElement(By.cssSelector("._2KpZ6l._2doB4z"));
			we.click();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			WebElement logo = wd.findElement(By.xpath("//img[@title='Flipkart']"));
			Assert.assertTrue(logo.isDisplayed());
		}
	}

	@Test(priority = 3)
	public void TestMainMenu() throws InterruptedException {
		Robot r;
		try {
			r = new Robot();
			r.mouseMove(0, 0);
		} catch (AWTException e) {
			e.printStackTrace();
		}
		Thread.sleep(2000);
		mouse_actions = new Actions(wd);
		WebElement menu = wd.findElement(By.xpath("//div[contains(text(),'Toys & More')]"));
		mouse_actions.moveToElement(menu).perform();
		System.out.println("Mouse hover: " + menu.getText());
		WebElement sportsMenu = wd.findElement(By.xpath("//a[text()='Sports & Fitness']"));
		WebElement booksMenu = wd.findElement(with(By.linkText("Books")).below(sportsMenu));
		mouse_actions.moveToElement(booksMenu).perform();
		System.out.println("Mouse hover: " + booksMenu.getText());
		Thread.sleep(2000);
		WebElement subMenu = wd.findElement(By.xpath("//a[contains(text(),'Literature & Fiction')]"));
		System.out.println("Mouse click: " + subMenu.getText());
		mouse_actions.click(subMenu).perform();
		Thread.sleep(2000);
		Assert.assertTrue(wd.getTitle().contains("Fiction"));
	}

	@Test(priority = 4, enabled = false) 
	public void TestSubMenu() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(10));
		WebElement ele = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Literature & Fiction']")));
		Thread.sleep(2000);
		System.out.println("Sub menu '" + ele.getText() + "' located @" + ele.getLocation());
		Thread.sleep(2000);
		mouse_actions.moveToElement(ele).click(ele).perform();
		wd.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		Thread.sleep(2000);
		WebElement expected = wd.findElement(By.cssSelector("._3vKRL2"));
		Assert.assertEquals(expected.getText(), "Literature and Fiction");
	}

	@Test(priority = 5, enabled = true)
	public void TestFirstRow() {
		wd.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		List<WebElement> firstRowbooks = wd.findElements(
				By.xpath("//div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div//div[1]/div[1]/div[1]/a"));
		for (WebElement FRB : firstRowbooks) {
			Log4j2.info(FRB.getText());
		}
		System.out.println("Found " + firstRowbooks.size() + " books");
		List<WebElement> firstRowDiscounts = wd
				.findElements(By.xpath("//div[2]/div[1]/div[2]//descendant::div[3]/span"));
		for (WebElement FRD : firstRowDiscounts) {
			Log4j2.info(FRD.getText());
		}
		System.out.println("Found " + firstRowDiscounts.size() + " discounted items");

		for (int i =0; i <firstRowbooks.size(); i++) {
			System.out.print(firstRowbooks.get(i).getText() + " - ");
			if (i < firstRowDiscounts.size()) {
				System.out.print(firstRowDiscounts.get(i).getText());
			} else {
				System.out.print("No Discount");
			}
			System.out.println();
		}
		Assert.assertEquals(firstRowbooks.size(), 6);
	}

}
