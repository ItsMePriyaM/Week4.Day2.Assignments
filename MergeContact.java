package week4.day2.Assignments;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		 //Pseudo Code
		  
		 // 1. Launch URL "http://leaftaps.com/opentaps/control/login"
		driver.get("http://leaftaps.com/opentaps/control/login");
		 
		 // 2. Enter UserName and Password Using Id Locator
		driver.manage().window().maximize();
		
		driver.findElement(By.id("username")).sendKeys("democsr");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		 // 3. Click on Login Button using Class Locator
		driver.findElement(By.className("decorativeSubmit")).click();
		 // 4. Click on CRM/SFA Link
		driver.findElement(By.partialLinkText("CRM/SFA")).click();
		 // 5. Click on contacts Button
		 	driver.findElement(By.partialLinkText("Contacts")).click();
		 // 6. Click on Merge Contacts using Xpath Locator
		 driver.findElement(By.xpath("//ul[@class='shortcuts']/li[4]/a")).click();
		 // 7. Click on Widget of From Contact
		 driver.findElement(By.xpath("(//img[@alt='Lookup'])[1]")).click();
		 
		 // 8. Click on First Resulting Contact
		 Set<String> windowHandles = driver.getWindowHandles();
			List<String> listwindows=new ArrayList<String>(windowHandles);
			String newwin=listwindows.get(1);
			driver.switchTo().window(newwin);
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			driver.findElement(By.xpath("(//table[@class='x-grid3-row-table'])[1]//tr[1]/td[1]/div/a")).click();
		 // 9. Click on Widget of To Contact
		 driver.switchTo().window(listwindows.get(0));
		 driver.findElement(By.xpath("(//img[@alt='Lookup'])[2]")).click();
		 // 10. Click on Second Resulting Contact
		 Set<String> windowHandles2 = driver.getWindowHandles();
			List<String> listwindows2=new ArrayList<String>(windowHandles2);
			String newwin2=listwindows2.get(1);
			driver.switchTo().window(newwin2);
		 driver.findElement(By.xpath("(//table[@class='x-grid3-row-table'])[2]//tr[1]/td[4]/div/a")).click();
		 // 11. Click on Merge button using Xpath Locator
		 driver.switchTo().window(listwindows2.get(0));
		 driver.findElement(By.xpath("//table[@class='twoColumnForm']//tr[4]/td[2]/a")).click();
		 
		 // 12. Accept the Alert
		 Alert alert = driver.switchTo().alert();
		 alert.accept();
		 // 13. Verify the title of the page
		
		 System.out.println(driver.getCurrentUrl());
		
	}

}


