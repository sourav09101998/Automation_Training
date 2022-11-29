package testingGrid;

import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class TestHerokuapp  extends TestBase{

	
	@Test(priority = 0)
	public void TestLaunchFrames() {
		wd.get("https://the-internet.herokuapp.com/tables");
		wd.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		Assert.assertEquals(wd.getTitle(), "The Internet");
		
	}
	@Test(priority = 1)
	public void SortTest() {
		wd.findElement(By.xpath("//table[1]/thead/tr/th[1]")).click();
		Assert.assertEquals(wd.findElement(By.xpath("//table[1]/thead/tr/th[1]")).getText(), "Last Name");
		List<WebElement> lastname = wd.findElements(By.xpath("//table[1]/tbody/tr/td[1]")); 
		List<String> lastname1 = lastname.stream().map(element->element.getText()).collect(Collectors.toList());
		lastname1.forEach(System.out::println);
		List<String> lastname2 = lastname1.stream().sorted().collect(Collectors.toList());
		lastname2.forEach(System.out::println);
		Assert.assertEquals(lastname1,lastname2);
		
	}

	@Test(priority = 2)
	public void getPriceTest() {
		
		List<WebElement> listlastname = wd.findElements(By.xpath("//table[2]/tbody/tr/td[4]"));
		List<String> list1 = listlastname.stream().map(element->element.getText()).collect(Collectors.toList());
		list1.forEach(System.out::println);
		System.out.println();
		list1.stream().filter(due->due.contains("$50")).collect(Collectors.toList()).forEach(System.out::println);

		System.out.println();

		List<String> expectation = new ArrayList<>();
	    expectation.add("Bach");
	    
		List<String> result = listlastname.stream()
		.filter(element->element.getText().contains("$51"))
		.map(element->element.findElement(By.xpath("preceding-sibling::*[last()]")))
		.map(element->element.getText())
		.collect(Collectors.toList());
		Assert.assertEquals(result,expectation);
	}
}
