package week4.day2.Assignments;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ActionAssignments {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Assignment 5:  (Actions)
//		=============
//
//		https://jqueryui.com/draggable
//		https://jqueryui.com/droppable
//		https://jqueryui.com/resizable
//		https://jqueryui.com/selectable
//		https://jqueryui.com/sortable
WebDriverManager.chromedriver().setup();
ChromeDriver driver=new ChromeDriver();
driver.get("https://jqueryui.com/draggable/");
driver.manage().window().maximize();
//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
driver.switchTo().frame(0);
WebElement sourcedrag = driver.findElement(By.xpath("//div[@id='draggable']"));
Actions builder=new Actions(driver);
builder.dragAndDropBy(sourcedrag, 100,90).build().perform();
driver.switchTo().defaultContent();

//Droppable
driver.findElement(By.xpath("//h3[text()='Interactions']/following::li[2]/a")).click();
driver.switchTo().frame(0);
WebElement source = driver.findElement(By.id("draggable"));
WebElement target = driver.findElement(By.id("droppable"));
builder.dragAndDrop(source, target).build().perform();
driver.switchTo().defaultContent();

//resizable
driver.findElement(By.xpath("//h3[text()='Interactions']/following::li[3]")).click();
driver.switchTo().frame(0);
WebElement ele = driver.findElement(By.xpath("//*[@class='ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se']"));
//Point location = ele.getLocation();
//int x = location.getX();
//int y = location.getY();
builder.clickAndHold(ele).moveByOffset(-100,-10).release().perform();
Point location = ele.getLocation();
int x = location.getX();
System.out.println(x);
int y = location.getY();
System.out.println(y);
driver.switchTo().defaultContent();

//Selectable
driver.findElement(By.xpath("//h3[text()='Interactions']/following::li[4]")).click();
builder.clickAndHold(driver.findElement(By.cssSelector("div[id='container']"))).moveByOffset(0, 100).release().build().perform();
driver.switchTo().frame(0);
WebElement item1 = driver.findElement(By.xpath("//ol[@id='selectable']/li[1]"));
WebElement item2 = driver.findElement(By.xpath("//ol[@id='selectable']/li[2]"));
WebElement item3 = driver.findElement(By.xpath("//ol[@id='selectable']/li[3]"));
WebElement item4 = driver.findElement(By.xpath("//ol[@id='selectable']/li[4]"));
WebElement item5 = driver.findElement(By.xpath("//ol[@id='selectable']/li[5]"));
WebElement item6 = driver.findElement(By.xpath("//ol[@id='selectable']/li[6]"));
WebElement item7 = driver.findElement(By.xpath("//ol[@id='selectable']/li[7]"));
builder.keyDown(Keys.CONTROL).click(item1).click(item3).click(item4).keyUp(Keys.CONTROL).perform();
//Unable to select the items 5,6,7 as it is not visible only on srolling down it is visible - so How to scroll down - need Help here 
driver.switchTo().defaultContent();

//Sortable
driver.findElement(By.xpath("//h3[text()='Interactions']/following::li[5]")).click();
driver.switchTo().frame(0);
WebElement sortitem4 = driver.findElement(By.xpath("//ul[@id='sortable']/li[4]/span"));
WebElement sortitem1 = driver.findElement(By.xpath("//ul[@id='sortable']/li[1]/span"));
builder.clickAndHold(sortitem4).moveToElement(sortitem1).build().perform();





	}

}
