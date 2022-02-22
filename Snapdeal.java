package week4.day2.Assignments;



import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


import io.github.bonigarcia.wdm.WebDriverManager;

public class Snapdeal {

	public static void main(String[] args) throws InterruptedException, IOException  {
		// TODO Auto-generated method stub
//		1. Launch https://www.snapdeal.com/
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://www.snapdeal.com/");
		
//			2. Go to Mens Fashion
		driver.findElement(By.id("pushDenied")).click();
		driver.findElement(By.xpath("//div[text()='Top Categories']/following::span[2]")).click();
	
//			3. Go to Sports Shoes
		 driver.findElement(By.xpath("(//span[text()='Sports Shoes'])[1]")).click();
	
//			4. Get the count of the sports shoes
		//div[@id='products']/section/div
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 WebElement noofshoes = driver.findElement(By.xpath("//span[@class='category-name category-count']"));
		 String countofsportsshoes=noofshoes.getText().replaceAll("[^0-9]", "");
		 System.out.println("Count of shoes is :"+countofsportsshoes);
		 
//			5. Click Training shoes
		 driver.findElement(By.xpath("//div[@class='child-cat-name selected']//following::ul[1]/li[1]")).click();
//			6. Sort by Low to High
		Thread.sleep(5000);
		 List<WebElement> allshoeprice = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
		 System.out.println(allshoeprice.size());
		 List<Integer> pricelist=new ArrayList<Integer>();
		 for(int i=0;i<allshoeprice.size();i++)
		 {
			 String replace = allshoeprice.get(i).getText().replaceAll("[^0-9]", "");
             int parseInt = Integer.parseInt(replace);
			 pricelist.add(parseInt);
			 
		 }
		 Collections.sort(pricelist);
		 System.out.println(pricelist);
		 driver.findElement(By.xpath("//span[@class='sort-label']/following-sibling::i")).click();
		 driver.findElement(By.xpath("//ul[@class='sort-value']/li[text()[normalize-space()='Price Low To High']]")).click();
		 
//			7. Check if the items displayed are sorted correctly
		 //To check this I have to get the list of all products but on first load of the page we are getting only 20 products but actual products is 53 which loads
		 //as we scroll the page. So need to know how to get all 53 items - Here the below code extracts only 20 items - So need assistance here
		 List<WebElement> sortedprice = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
		 System.out.println(sortedprice.size());
		 List<Integer> aftersortprice=new ArrayList<Integer>();
		 for(int i=0;i<allshoeprice.size();i++)
		 {
			 String sortedamount = sortedprice.get(i).getText().replaceAll("[^0-9]", "");
             int sortedint = Integer.parseInt(sortedamount);
             aftersortprice.add(sortedint);
			 
		 }
		 System.out.println(aftersortprice);
//			8.Select the price range (900-1200)
		 WebElement priceFilterstart = driver.findElement(By.xpath("(//div[@class='price-text-box'])[1]/input"));
		 priceFilterstart.clear();
		 priceFilterstart.sendKeys("900");
		 WebElement priceFilterend = driver.findElement(By.xpath("(//div[@class='price-text-box'])[2]/input"));
		 priceFilterend.clear();
		 priceFilterend.sendKeys("1200");
		driver.findElement(By.xpath("//div[text()[normalize-space()='GO']]")).click();
		 
//			9.Filter with color Navy 
		Thread.sleep(2000);
		WebElement colourfilter = driver.findElement(By.xpath("//input[@value='Navy']/following-sibling::label"));
		
		colourfilter.click();
//			10 verify the all applied filters 
		Thread.sleep(2000);
		List<WebElement> appliedfilters = driver.findElements(By.xpath("(//div[@class='filters'])[1]/div/a"));
		for(int i=0;i<appliedfilters.size();i++)
		{
			System.out.println("The Applied Filters is :"+appliedfilters.get(i).getText());
		}
		
//			11. Mouse Hover on first resulting Training shoes
		Actions action=new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//div[@id='products']/section/div[1]"))).build().perform();

//			12. click QuickView button
		driver.findElement(By.xpath("(//div[text()[normalize-space()='Quick View']])[1]")).click();
		
//			13. Print the cost and the discount percentage
		String cost = driver.findElement(By.xpath("//div[@class='product-price pdp-e-i-PAY-l clearfix']/span[1]")).getText().replaceAll("[^0-9]", "");
		System.out.println("The Cost of the shoes is : "+cost);
		String discount = driver.findElement(By.xpath("//div[@class='product-price pdp-e-i-PAY-l clearfix']/span[2]")).getText();
		System.out.println("The Discount applied is : "+discount);
		
//			14. Take the snapshot of the shoes.
		File source = driver.getScreenshotAs(OutputType.FILE);
		File dest=new File("./src/main/resources/snaps/snapdeal.jpg");
		FileUtils.copyFile(source, dest);
		
//			15. Close the current window
	    driver.findElement(By.xpath("(//i[@class='sd-icon sd-icon-delete-sign'])[3]")).click();
	
//			16. Close the main window
		driver.close();
	}

}
