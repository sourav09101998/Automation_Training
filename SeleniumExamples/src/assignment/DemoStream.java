package assignment;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utilityScripts.BrowserSetup;
import utilityScripts.BrowserSetup.Browser;
import utilityScripts.Log4j2;

public class DemoStream {
		
	private BrowserSetup b;
	private WebDriver wd ;
	
	@BeforeClass
	public void beforeClass() throws Exception {
		b = new BrowserSetup(Browser.CHROME, false);

	}

	@AfterClass
	public void afterClass() {
		b.closeBrowser();

	}

	@Test( priority = 1 )
	public void TestToOpenURL() {
		wd = b.invokeBrowser("https://rahulshettyacademy.com/seleniumPractise/#/offers");
		Assert.assertEquals(wd.getTitle(), "GreenKart - veg and fruits kart");

	}
	@Test(priority = 2,enabled = true )
	public void TestSort(){
		
		//click on name column
		wd.findElement(By.xpath("//tr/th[1]")).click();

		//capture all webelements into list 
		List<WebElement> elementsList = wd.findElements(By.xpath("//tr/td[1]"));

		//capture text of all webelements into new(original) list 
		List<String> originalList = elementsList.stream()
												.map(s -> s.getText())
												.collect(Collectors.toList());

		//sort on the original list of step 3 -> sorted list
		List<String> sortedList = originalList.stream().sorted().collect(Collectors.toList());
		
		//compare original list vs sorted list
		Assert.assertTrue(originalList.equals(sortedList));

	}
	
	@Test(priority = 3,enabled = true)
	public void TestFilter() {
		List<WebElement> elementsList = wd.findElements(By.xpath("//tr/td[1]"));

		List<String> priceList = elementsList.stream()
											.filter(s->s.getText().contains("Apple"))
											.map(s->getPrice(s))
											.collect(Collectors.toList());
		priceList.forEach(a -> Log4j2.info(a));
		Assert.assertEquals(priceList.get(0),"67");
	}
	
	
	@Test(priority = 4,enabled = true)
	public void TestPagination() {
		List<String> price;
		do {
			List<WebElement> row =wd.findElements(By.xpath("//tr/td[1]"));
			price=row.stream()
					.filter(s->s.getText().contains("Pineapple"))
					.map(s -> getPrice(s)).collect(Collectors.toList());
			price.forEach(a->Log4j2.info(a));
			if(price.size()<1) {
				wd.findElement(By.cssSelector("[aria-label='Next']")).click();
			}
		}while(price.size()<1);
		Assert.assertEquals(price.get(0),"44");
	}
	
	@Test(priority = 5,enabled = true)
	public void TestSearchFilter() {
		wd.findElement(By.id("search-field")).sendKeys("Rice");
		List<WebElement> resultList = wd.findElements(By.xpath("//tr/td[1]"));

		List<WebElement> filteredList = resultList.stream()
											.filter(s->s.getText().contains("Rice"))
											.collect(Collectors.toList());
		
		Assert.assertEquals(filteredList.size(),resultList.size());
	}
	
	
	private static String getPrice(WebElement s) {
		String value= s.findElement(By.xpath("following-sibling::td[1]")).getText();
		return value;
	}
	

}
