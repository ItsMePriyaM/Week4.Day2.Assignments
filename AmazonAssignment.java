package week4.day2.Assignments;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hpsf.Array;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonAssignment {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
//		Amazon:
//			1.Load the uRL https://www.amazon.in/
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
//			2.search as oneplus 9 pro 
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro",Keys.ENTER);
//			3.Get the price of the first product
		WebElement price = driver.findElement(By.xpath("(//span[@class='a-price-symbol'])[1]/following-sibling::span"));
         String pricetotal = price.getText();
		System.out.println("The Price of first product is : "+price.getText());
		
//			4. Print the number of customer ratings for the first displayed product
		 String rating = driver.findElement(By.xpath("(//div[@class='a-row a-size-small'])[1]/span[1]")).getAttribute("aria-label");
		 String custrating = driver.findElement(By.xpath("(//div[@class='a-row a-size-small'])[1]/span[2]")).getAttribute("aria-label");
		System.out.println("The Rating is : "+rating);
		System.out.println("The Customer rating is : "+custrating);
		
//			5. click on the stars 
		driver.findElement(By.xpath("(//a[@role='button'])[2]/i[1]")).click();
		
//			6. Get the percentage of ratings for the 5 star.
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement star5 = driver.findElement(By.xpath("//table[@id='histogramTable']//tr[1]/td[3]"));
		//System.out.println(driver.findElement(By.xpath("(//a[@title='5 stars represent 65% of rating'])[3]")).getText());
		System.out.println("The 5 star % is :"+star5.getText());
		
//			7. Click the first text link of the first image
		driver.findElement(By.xpath("(//h2[@class='a-size-mini a-spacing-none a-color-base s-line-clamp-2'])[1]/a[1]")).click();
//			8. Take a screen shot of the product displayed
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> wins=new ArrayList<String>(windowHandles);
		driver.switchTo().window(wins.get(1));
		File source = driver.getScreenshotAs(OutputType.FILE);
		File dest=new File("./src/main/resources/snaps/amazon.jpg");
		FileUtils.copyFile(source, dest);
		
//			9. Click 'Add to Cart' button
		driver.findElement(By.id("add-to-cart-button")).click();
//			10. Get the cart subtotal and verify if it is correct.
		
		driver.findElement(By.id("attach-close_sideSheet-link")).click();
		Thread.sleep(2000);
		WebElement carticon=driver.findElement(By.id("nav-cart-count"));
		carticon.click();
		WebElement sub = driver.findElement(By.xpath("(//span[contains(@class,'a-color-price sc-price-container')]//span)[3]"));
		String pricestring = sub.getText();
String total= pricestring.replaceAll(".00", "");
String subtotal = total.replaceAll(" ", "");

if(pricetotal.equals(subtotal))
{
	System.out.println("The subtotal is as expected :"+subtotal);
}
else
{
	System.out.println("The subtotal is not as expected");
}
	
	}

}
