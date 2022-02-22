package week4.day2.Assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FramesExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
WebDriverManager.chromedriver().setup();
ChromeDriver driver=new ChromeDriver();
driver.get("https://chercher.tech/practice/frames-example-selenium-webdriver");
driver.manage().window().maximize();

driver.switchTo().frame("frame1");
driver.findElement(By.xpath("//b[@id='topic']/following-sibling::input")).sendKeys("lakshmi");
//driver.switchTo().defaultContent();
driver.switchTo().frame("frame3");
driver.findElement(By.xpath("//b[@style='color:green']/following-sibling::input")).click();
driver.switchTo().defaultContent();
driver.switchTo().frame(1);
Select s=new Select(driver.findElement(By.xpath("//b[text()='Animals :']/following::select")));
s.selectByValue("babycat");
	}

}