package others;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utilityScripts.BrowserSetup;
import utilityScripts.BrowserSetup.Browser;

public class datatablesNet {

	public static void main(String[] args) throws Exception {
		WebDriver wd;
		BrowserSetup b = new BrowserSetup(Browser.CHROME, false);
		wd = b.invokeBrowser("https://datatables.net/");
		pagination(wd);
		sort(wd);
		b.closeBrowser();
		System.out.println("browser closed");

	}

	private static void pagination(WebDriver wd) throws InterruptedException {
		WebElement paginate = wd.findElement(By.cssSelector("#example_paginate"));
		WebElement nextpage = wd.findElement(By.cssSelector("#example_next"));
		WebElement lastpage;
		Integer lastpageno;
		do {
			lastpage = paginate
					.findElement(By.xpath("//a[@id='example_next']/preceding-sibling::span/child::*[last()]"));
			lastpageno = Integer.valueOf(lastpage.getAttribute("data-dt-idx"));
			System.out.println("lastpageno:" + lastpageno);
			nextpage.click();
			Thread.sleep(2000);
			nextpage = paginate.findElement(By.cssSelector("#example_next"));

		} while (!nextpage.getAttribute("class").contains("disabled"));

		WebElement prevpage = paginate.findElement(By.cssSelector("#example_previous"));
		do {
			prevpage.click();
			Thread.sleep(2000);
			prevpage = paginate.findElement(By.cssSelector("#example_previous"));
		} while (!prevpage.getAttribute("class").contains("disabled"));
		List<WebElement> pagenos = paginate.findElements(By.cssSelector("span>a"));
		int pagecount = 0;
		while (pagecount < lastpageno) {
			pagenos.get(pagecount).click();
			Thread.sleep(2000);
			pagenos = paginate.findElements(By.cssSelector("span>a"));
			pagecount++;
		}

	}

	private static void sort(WebDriver wd) {
		wd.findElement(By.xpath("//table[@id='example']//tr/th[text()='Name']")).click();
		List<WebElement> listlastname = wd.findElements(By.xpath("//table[@id='example']/tbody/tr/td[1]"));
		List<String> list1 = listlastname.stream().map(element -> element.getText()).collect(Collectors.toList());
		list1.forEach(System.out::println);
		System.out.println();
		List<String> list2 = list1.stream().sorted().collect(Collectors.toList());
		list2.forEach(System.out::println);
		boolean bsorted = list1.equals(list2);
		if (bsorted)
			System.out.println("sorted");
		else
			System.out.println("\n not sorted");

	}

}