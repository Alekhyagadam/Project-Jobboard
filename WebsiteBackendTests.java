package Jobboard;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WebsiteBackendTests {
	WebDriver driver;
	@BeforeTest
    public void beforeMethod() {
        driver = new ChromeDriver();      
        driver.get("https://alchemy.hguy.co/jobs/wp-admin");
        driver.manage().window().maximize();
        driver.findElement(By.id("user_login")).sendKeys("root");
  	  driver.findElement(By.id("user_pass")).sendKeys("pa$$w0rd");
  	  driver.findElement(By.id("wp-submit")).click();
  	  driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

    }
  @Test
  public void login() {
	  
	 
	   Actions actions = new Actions(driver);
    WebElement list  = driver.findElement(By.id("wp-admin-bar-my-account"));
	actions.moveToElement(list).perform();
	   String checklogout  = driver.findElement(By.id("wp-admin-bar-logout")).getText();   
       Assert.assertEquals("Log Out", checklogout);

	
  }
  
  @Test
  public void publish() 
  {
	  	  driver.findElement(By.xpath("//*[@id=\"menu-posts-job_listing\"]/a/div[3]")).click();
	  driver.findElement(By.xpath("//*[@id=\"wpbody-content\"]/div[4]/a")).click();	 	  
	  driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div/div/div/div/div[1]/button")).click();
	  driver.findElement(By.id("post-title-0")).sendKeys("QA1");
	 driver.findElement(By.xpath("//*[@id=\"editor\"]/div/div/div[1]/div/div[1]/div/div[2]/button[2]")).click();
	 driver.findElement(By.xpath("//*[@id=\"editor\"]/div/div/div[1]/div/div[2]/div[3]/div/div/div/div[1]/div/button")).click();
	 driver.findElement(By.xpath("//*[@id=\"editor\"]/div/div/div[1]/div/div[2]/div[3]/div/div/div/div[2]/div/div[2]/div[2]/a")).click();
	 WebDriverWait wait = new WebDriverWait(driver,30);
	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Apply for job']"))).getText();
	 System.out.println("Job published");



	  
  }
  @AfterTest
  public void afterMethod() {
      //Close the browser
      driver.quit();
  }

}
