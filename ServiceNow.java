package week4.day2.Assignments;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

import java.util.List;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNow {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		//Step1: Load ServiceNow application URL https://dev101991.service-now.com/navpage.do
		driver.get("https://dev101991.service-now.com/navpage.do");
		
		//Step2: Enter username (Check for frame before entering the username)
		driver.switchTo().frame(0);
		driver.findElement(By.id("user_name")).sendKeys("admin");
		//Step3: Enter password 
		driver.findElement(By.id("user_password")).sendKeys("123ABC@123abc");
		//Step4: Click Login
		driver.findElement(By.id("sysverb_login")).click();
		//Step5: Search “	incident “ Filter Navigator
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//input[@id='filter']")).sendKeys("incident");
		//Step6: Click “All”
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	 driver.findElement(By.xpath("//ul[@id='collapseIddead1309c611228701e2bda7b4252474']/li[6]//a")).click();
		
		//Step7: Click New button
	 driver.switchTo().frame(0);
	 driver.findElement(By.xpath("//button[@id='sysverb_new']")).click();
	 driver.switchTo().defaultContent();
		//Step8: Select a value for Caller and Enter value for short_description
	 driver.switchTo().frame(0);
	 driver.findElement(By.id("lookup.incident.caller_id")).click();

	 List<String> listofwindows=new ArrayList<String>(driver.getWindowHandles());
	 String newwin=listofwindows.get(1);
	 driver.switchTo().window(newwin);
	 driver.findElement(By.xpath("//tbody[@class='list2_body']/tr[1]/td[3]/a")).click();
	 driver.switchTo().window(listofwindows.get(0));
	 driver.switchTo().frame(0);
	 driver.findElement(By.id("incident.short_description")).sendKeys("Sample Incident to test Frames and Windows");
	 
		//Step9: Read the incident number and save it a variable
	 String INCNum=driver.findElement(By.xpath("//input[@id='incident.number']")).getAttribute("value");
	 System.out.println(INCNum);
	 
//Step10: Click on Submit button
	 driver.findElement(By.xpath("(//button[contains(text(),'Submit')])[1]")).click();
		//Step 11: Search the same incident number in the next search screen as below
	 driver.switchTo().defaultContent();
	 driver.switchTo().frame(0);
	 WebElement search = driver.findElement(By.xpath("(//label[text()='Search'])[2]/following::input"));
	 search.sendKeys(INCNum, Keys.ENTER);
		//Step12: Verify the incident is created successful and take snapshot of the created incident.
	 String inclink = driver.findElement(By.xpath("//tbody[@class='list2_body']/tr/td[3]/a")).getText();
	 if(inclink.equals(INCNum))
{
	System.out.println("The incident is created successfully");
}
	 else
	 {
		 System.out.println("Check your code");
	 }
	 File source = driver.getScreenshotAs(OutputType.FILE);
	 File dest=new File("./src/main/resources/snaps/servicenowinc.jpg");
	 FileUtils.copyFile(source, dest);
	}

}
