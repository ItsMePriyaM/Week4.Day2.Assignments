package week4.day2.Assignments;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

//http://leafground.com/pages/frame.html
//1.Take the the screenshot of the click me button of first frame
//2.Find the number of frames
//  - find the Elements by tagname - iframe
//  - Store it in a list
//  - Get the size of the list. (This gives the count of the frames visible to the main page)

public class LeafgroundFrames {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
WebDriverManager.chromedriver().setup();
ChromeDriver driver=new ChromeDriver();
driver.get("http://leafground.com/pages/frame.html");
driver.manage().window().maximize();
driver.switchTo().frame(0);
driver.findElement(By.xpath("//button[@id='Click']")).click();

File source1 = driver.getScreenshotAs(OutputType.FILE);
File dest1=new File("./src/main/resources/snaps/framesleaf.jpg");
FileUtils.copyFile(source1,dest1);

driver.switchTo().defaultContent();
driver.switchTo().frame(1);
driver.switchTo().frame("frame2");
driver.findElement(By.id("Click1")).click();

driver.switchTo().defaultContent();
//Find number of frames
List<WebElement> listofframes = driver.findElements(By.tagName("iframe"));

int count=listofframes.size();

driver.switchTo().frame(0);
List<WebElement> noofinnerframes = driver.findElements(By.tagName("iframe"));
if(noofinnerframes.size()>0)
{
	count=count+noofinnerframes.size();
}
driver.switchTo().defaultContent();
driver.switchTo().frame(1);
List<WebElement> noofinnerframes2 = driver.findElements(By.tagName("iframe"));
if(noofinnerframes2.size()>0)
{
	count=count+noofinnerframes2.size();
}
driver.switchTo().defaultContent();
driver.switchTo().frame(2);
List<WebElement> noofinnerframes3 = driver.findElements(By.tagName("iframe"));
if(noofinnerframes3.size()>0)
{
	count=count+noofinnerframes3.size();
}
System.out.println("The Number of frames in this page is :"+count);

	}

}
