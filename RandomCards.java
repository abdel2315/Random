package Selenium;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import verify.*;

public class RandomCards {
	
	protected static WebDriver driver;
	
	@BeforeClass
	public static void setup(){
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notification");
		options.addArguments("start-maximized");
		
		System.setProperty("webdriver.chrome.driver","C:\\webdriver\\chromedriver.exe");
		
		driver = new ChromeDriver(options);
		
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
		
		driver.navigate().to("https://www.random.org/playing-cards/");
								
	}

	@Test
	public void test() throws InterruptedException {
		
		WebElement nbreCarte = driver.findElement(By.xpath("//input[@name='cards']"));
		nbreCarte.clear();
		nbreCarte.sendKeys("5");
		Thread.sleep(1000);
		
		WebElement resTxt = driver.findElement(By.xpath("//input[@name='text']"));
		resTxt.click();;
		Thread.sleep(1000);
		
		WebElement Lancer = driver.findElement(By.xpath("//p[6]//input[1]"));
		Lancer.click();
		Thread.sleep(1000);
		
		assertTrue(Verify.verifyTextPresent(driver, "Here are your 5 cards, drawn from a total of 52:", true));
		
		WebElement retour = driver.findElement(By.xpath("//body//input[23]"));
		retour.click();
		
	}
	
	@Test
	public void test1() throws InterruptedException {
		
		WebElement nbreCarte = driver.findElement(By.xpath("//input[@name='cards']"));
		nbreCarte.clear();
		nbreCarte.sendKeys("1");
		Thread.sleep(1000);
		
		new Select(driver.findElement(By.xpath("//select[@name='decks']"))).selectByVisibleText("2");
		
		WebElement Lancer = driver.findElement(By.xpath("//p[6]//input[1]"));
		Lancer.click();
		Thread.sleep(1000);
	
	}

	@AfterClass
	public static void tearDown(){
		
		driver.manage().deleteAllCookies();
		driver.close();
	}
}
