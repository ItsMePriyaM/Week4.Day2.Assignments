package week4.day2.Assignments;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
WebDriverManager.chromedriver().setup();
ChromeDriver driver=new ChromeDriver();

//1) Go to https://www.nykaa.com/
driver.get("https://www.nykaa.com/");
driver.manage().window().maximize();

//2) Mouseover on Brands and Search L'Oreal Paris
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
Actions builder=new Actions(driver);
builder.moveToElement(driver.findElement(By.partialLinkText("BRANDS"))).perform(); 

//3) Click L'Oreal Paris
builder.moveToElement(driver.findElement(By.xpath("//div[@id='scroller-container']/div[7]/a[1]"))).click().perform();

//4) Check the title contains L'Oreal Paris(Hint-GetTitle)
String title = driver.getTitle();
System.out.println(title);
if(title.contains("L'Oreal Paris"))
{
	System.out.println("You are into Loreal Paris Brand");
}
else
{
	System.out.println("You are yet to select Loreal Paris");
}

//5) Click sort By and select customer top rated
driver.findElement(By.xpath("//div[@id='filter-sort']/div/div/button")).click();
driver.findElement(By.xpath("//span[text()='customer top rated']/following::div[1]")).click();

//6) Click Category and click Hair->Click haircare->Shampoo
driver.findElement(By.xpath("//span[text()='Category']/following::div[1]")).click();
WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Hair']")));
driver.findElement(By.xpath("//span[text()='Hair']//following-sibling::span")).click();
driver.findElement(By.xpath("//span[text()='Hair Care']/following-sibling::span")).click();
driver.findElement(By.xpath("//div[@class='control-value']/following::div[1]")).click();

//7) Click->Concern->Color Protection
driver.findElement(By.xpath("//span[text()='Concern']/following-sibling::div")).click();
driver.findElement(By.xpath("//span[text()='Color Protection']/following::div[1]")).click();

//8)check whether the Filter is applied with Shampoo
List<WebElement> filterElements = driver.findElements(By.xpath("//div[@id='filters-listing']/div[1]//span"));
for(int i=0;i<filterElements.size();i++)
{
	String filters=filterElements.get(i).getText();
	if(filters.contains("Shampoo"))
	{
	System.out.println("Yes the Shampoo filter is applied");
	}
}
//9) Click on L'Oreal Paris Colour Protect Shampoo

List<WebElement> listofitems = driver.findElements(By.xpath("//div[@id='product-list-wrap']/div"));
for(int i=0;i<listofitems.size();i++)
{
	WebElement eachElement = listofitems.get(i);
	WebElement eachname = eachElement.findElement(By.tagName("a"));
	String itemname = eachname.getText();
	if(itemname.contains("L'Oreal Paris Colour Protect Shampoo"))
	{
		eachname.click();
		break;
	}
}

//10) GO to the new window and select size as 175ml
Set<String> allwindows = driver.getWindowHandles();
List<String> allwinList=new ArrayList<String>(allwindows);
driver.switchTo().window(allwinList.get(1));
Select Options=new Select(driver.findElement(By.xpath("//Select[@title='SIZE']")));
Options.selectByVisibleText("75ml");
Options.selectByVisibleText("175ml");
System.out.println("175 ml is clicked");

//11) Print the MRP of the product
String mrp = driver.findElement(By.xpath("(//span[contains(text(),'MRP')])[2]/following::span[1]")).getText();
System.out.println(mrp);

//12) Click on ADD to BAG
driver.findElement(By.xpath("(//span[text()='ADD TO BAG'])[1]")).click();

//13) Go to Shopping Bag 
driver.findElement(By.xpath("//div[@class='css-0 e1ewpqpu1']/button")).click();

//14) Print the Grand Total amount
driver.switchTo().frame(0);
String total = driver.findElement(By.xpath("(//span[text()='Grand Total'])[1]/following::div[1]")).getText();
System.out.println(total);
String totalnum = total.replaceAll("[^a-zA-Z0-9]", "");
//System.out.println(totalnum);
int grandtotal = Integer.parseInt(totalnum);
System.out.println("The Grandtotal : "+grandtotal);

//15) Click Proceed
driver.findElement(By.xpath("//button[@class='btn full fill no-radius proceed ']")).click();

//16) Click on Continue as Guest
driver.findElement(By.xpath("//button[text()='CONTINUE AS GUEST']")).click();

//17) Check if this grand total is the same in step 14
String finaltotal = driver.findElement(By.xpath("//div[text()='Grand Total']/following-sibling::div")).getText();
int finalgrandtotal=Integer.parseInt(finaltotal.replaceAll("[^a-zA-Z0-9]", ""));
if(grandtotal==finalgrandtotal)
{
	System.out.println("The grandtotal is correct");
}
else
{
	System.out.println("The grandtotal is incorrect");
}

//18) Close all windows
driver.quit();
	}

}

//span[text()='Hair']//following-sibling::span - Hair Xpath
