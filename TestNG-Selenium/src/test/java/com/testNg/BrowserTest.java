package com.testNg;


import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class BrowserTest {

	String actualUrl = "http://automationpractice.com/index.php";
	WebDriver driver=null;
	JavascriptExecutor js= null;

	//Method to launch the browser and open the url="http://automationpractice.com/index.php"
	
	@BeforeClass
	public void launchBrowser() {
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\Adminstrator\\Desktop\\New folder\\geckodriver.exe");	
	driver = new FirefoxDriver();
	js=(JavascriptExecutor) driver;
	System.out.println("Launching the firefox browser");
	driver.get(actualUrl);
	System.out.println("Opening the url:"+actualUrl);
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	}
	
	
	//Method to fill the registeration form and create an account
	
	@Test(priority=1)
	public void registerationForm() throws InterruptedException {
		System.out.println("Clicking on Sign in");
		driver.findElement(By.className("login")).sendKeys(Keys.ENTER);
		System.out.println("Entering Email-id for registeration");
		driver.findElement(By.name("email_create")).sendKeys("registertrial1234@gmail.com");
		Thread.sleep(5000);
		driver.findElement(By.name("SubmitCreate")).sendKeys(Keys.ENTER);
		System.out.println("Clicking on the Create account button");
		WebElement female=driver.findElement(By.id("id_gender2"));
		female.click();
		Thread.sleep(2000);
		driver.findElement(By.name("customer_firstname")).sendKeys("Vennis");
		Thread.sleep(2000);
		driver.findElement(By.name("customer_lastname")).sendKeys("Shah");
		Thread.sleep(2000);
		driver.findElement(By.name("passwd")).sendKeys("testtrial1234");
		Thread.sleep(2000);
		Select days=new Select(driver.findElement(By.name("days")));
		days.selectByValue("31");
		Select months=new Select(driver.findElement(By.name("months")));
		months.selectByValue("10");
		Select years=new Select(driver.findElement(By.name("years")));
		years.selectByValue("1991");
		Thread.sleep(2000);
		WebElement check=driver.findElement(By.id("optin"));
		check.click();
		Thread.sleep(2000);
		driver.findElement(By.name("company")).sendKeys("ABC");
		Thread.sleep(2000);
		driver.findElement(By.name("address1")).sendKeys("X Street,ABC pvt. ltd.");
		Thread.sleep(2000);
		driver.findElement(By.name("address2")).sendKeys("Y apartment,2nd Floor");
		Thread.sleep(2000);
		driver.findElement(By.name("city")).sendKeys("New York");
		Thread.sleep(2000);
		Select state=new Select(driver.findElement(By.name("id_state")));
		state.selectByVisibleText("New York");
		Thread.sleep(2000);
		driver.findElement(By.name("postcode")).sendKeys("00000");
		Thread.sleep(2000);
		Select country=new Select(driver.findElement(By.name("id_country")));
		country.selectByVisibleText("United States");
		Thread.sleep(2000);
		driver.findElement(By.name("phone_mobile")).sendKeys("9876543210");
		Thread.sleep(2000);
		driver.findElement(By.name("alias")).sendKeys("Add");
		driver.findElement(By.name("submitAccount")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		System.out.println("Filled in the registeration form and click on submit account");
		driver.findElement(By.className("logout")).sendKeys(Keys.ENTER);
		System.out.println("Logged out of the account");
		Thread.sleep(5000);
		driver.navigate().to(actualUrl);
			
	}
	
	
	//Method to login into an existing account
	
	
	@Test(priority=2)
	public void login() throws InterruptedException {
		driver.findElement(By.className("login")).sendKeys(Keys.ENTER);
		try {
		driver.findElement(By.id("email")).sendKeys("registertrial1234@gmail.com");
		Thread.sleep(2000);
		driver.findElement(By.id("passwd")).sendKeys("testtrial1234");
		Thread.sleep(2000);
		driver.findElement(By.name("SubmitLogin")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		}catch (Exception e) {
			System.out.println("Invalid login credentials");
			throw(e);
		}
		driver.navigate().to(actualUrl);
	}
	
	//Method to show search bar functionality
	
	
	@Test(priority=3)
	public void searchBar() throws InterruptedException {
	System.out.println("Entering text in the search bar");
	driver.findElement(By.id("search_query_top")).sendKeys("Dress");
	Thread.sleep(2000);
	System.out.println("Hitting the button for search");
	driver.findElement(By.name("submit_search")).sendKeys(Keys.ENTER);
	Thread.sleep(2000);
	System.out.println("Inside search result");
	js.executeScript("window.scrollBy(0,500)");
	Thread.sleep(2000);
	driver.navigate().to(actualUrl);
	
	}
	
	//Method to add an item to cart and then confirm the order
	
	
	@Test(priority=4)
	public void addToCart() throws InterruptedException{
		
		
		driver.findElement(By.cssSelector("img[title=\"Blouse\"]")).click();	
		Thread.sleep(2000);
		driver.findElement(By.name("Submit")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		System.out.println("Added to cart");
		driver.navigate().to(actualUrl);
		driver.findElement(By.cssSelector("a[title=\"View my shopping cart\"]")).click();
		Thread.sleep(3000);
		js.executeScript("window.scrollBy(0,1000)");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a/span[text()='Proceed to checkout']")).click();
		Thread.sleep(3000);
		driver.findElement(By.name("processAddress")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		WebElement checkb=driver.findElement(By.id("cgv"));
		checkb.click();
		Thread.sleep(3000);
		js.executeScript("window.scrollBy(0,1000)");
		driver.findElement(By.xpath("//button/span[text()='\n" + 
				"								Proceed to checkout\n" + 
				"								']")).click();
		Thread.sleep(3000);
		js.executeScript("window.scrollBy(0,1000)");
		driver.findElement(By.xpath("//a/span[text()='(order processing will be longer)']")).click();
		Thread.sleep(3000);
		js.executeScript("window.scrollBy(0,1000)");
		driver.findElement(By.xpath("//button/span[text()='I confirm my order']")).click();
		Thread.sleep(3000);
		driver.navigate().to(actualUrl);
		Thread.sleep(3000);
	}
	
	//Method to close the browser
	
	@AfterClass
	public void closeBrowser(){
		driver.close();
		
	}
}
 
  


