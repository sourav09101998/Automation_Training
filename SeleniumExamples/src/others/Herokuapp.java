package others;


import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utilityScripts.BrowserSetup;
import utilityScripts.BrowserSetup.Browser;

public class Herokuapp {
	public static void main(String[] args) throws Exception {
		WebDriver wd;
		BrowserSetup b;
		b = new BrowserSetup(Browser.CHROME, false); 
		wd = b.invokeBrowser("https://the-internet.herokuapp.com/tables");
		Sort(wd);
		getPrice(wd);
		b.closeBrowser();
		System.out.println("Browser closed");

	}
	
	private static void Sort(WebDriver wd) {

		wd.findElement(By.xpath("//table[2]/thead/tr/th[1]")).click();
		List<WebElement> listlastname = wd.findElements(By.xpath("//table[2]/tbody/tr/td[1]")); 
		List<String> list1 = listlastname.stream().map(element->element.getText()).collect(Collectors.toList());
		list1.forEach(System.out::println);
		System.out.println();
		List<String> list2 = list1.stream().sorted().collect(Collectors.toList());
		list2.forEach(System.out::println);
		boolean bsorted = list1.equals(list2);
		if (bsorted)
			System.out.println("Sorted");
		else
			System.out.println("\nNot Sorted");
	}
	private static void getPrice(WebDriver wd) {

		List<WebElement> listlastname = wd.findElements(By.xpath("//table[2]/tbody/tr/td[4]"));
		List<String> list1 = listlastname.stream().map(element->element.getText()).collect(Collectors.toList());
		list1.forEach(System.out::println);
		System.out.println();
		list1.stream().filter(due->due.contains("$50")).collect(Collectors.toList()).forEach(System.out::println);

		System.out.println();
		listlastname.stream()
		.filter(element->element.getText().contains("$51"))
		.map(element->element.findElement(By.xpath("preceding-sibling::*[last()]")))
		.map(element->element.getText())
		.collect(Collectors.toList())
		.forEach(System.out::println);
		
		

		}
}