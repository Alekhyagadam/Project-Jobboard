package Jobboard;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import org.testng.annotations.AfterTest;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WebsiteFrontendTests {
	  WebDriver driver;

	    
	    @BeforeTest
	    public void beforeMethod() {
	        driver = new ChromeDriver();      
	        driver.get("https://alchemy.hguy.co/jobs");
	        driver.manage().window().maximize();
	    	  driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

	    }
     @Test(priority=0)	    
	    public void verifyTitle() {
	        String title = driver.getTitle();
		     System.out.println("Page title is: " + title);
	         Assert.assertEquals("Alchemy Jobs – Job Board Application", title);
	            	        
	    }
     
     @Test(priority=1)
     public void verifyHeading() {
   	  
   	  String heading = driver.findElement(By.xpath("//*[@id=\"post-16\"]/header/h1")).getText();
   	  System.out.println("Heading of webpage is:" +heading);
         Assert.assertEquals("Welcome to Alchemy Jobs", heading);

     }
     
     @Test(priority=2)
     public void verifyImage() {
   	  
   	  WebElement img = driver.findElement(By.cssSelector("#post-16 > header > div > img"));
   	  
   	  String image = img.getAttribute("src");
   	  
   	  System.out.println("The image url is :" +image);
   	  
   	 
   	  
     }
     @Test(priority=3)
     public void verifySecondHeading() {

   	  
   	  String heading2 = driver.findElement(By.xpath("//*[@id=\"post-16\"]/div/h2")).getText();
   	  System.out.println("Second Heading of webpage is:" +heading2);
         Assert.assertEquals("Quia quis non", heading2);

     }
     
     @Test(priority=4)
     public void navitePage() throws InterruptedException {
   	  
   	  WebElement Navigationbar = driver.findElement(By.id("primary-menu"));
   	 boolean text =  Navigationbar.getText().contains("Jobs");  
   	  System.out.println("FoundNavigation bar:" +text);
   	  WebElement jobs= driver.findElement(By.id("menu-item-24"));
   	  jobs.click();
   	  Thread.sleep(5000);
   	  String title = driver.findElement(By.xpath("//*[@id=\"post-7\"]/header/h1")).getText();
          Assert.assertEquals("Jobs", title);
         System.out.println("Title is verified:" +title);
   	  
   	  
     }     
     @Test(priority=5)
     public void searchJob() throws InterruptedException {
   	  WebElement jobs= driver.findElement(By.id("menu-item-24"));
   	  jobs.click();
   	  Thread.sleep(3000);
   	  driver.findElement(By.id("search_keywords")).sendKeys("java");
   	  driver.findElement(By.xpath("//*[@id=\"post-7\"]/div/div/form/div[1]/div[4]/input")).click();
   	  driver.findElement(By.xpath("//*[@id=\"post-7\"]/div/div/ul/li[1]/a/div[1]/h3")).click();
   	  driver.findElement(By.xpath("//input[@value='Apply for job']")).click();
   	  String email = driver.findElement(By.xpath("//a[@class='job_application_email']")).getAttribute("href");
   	  System.out.println("Email id is:" +email);
   	
     }
     
     @Test(priority=6)
     public void newJob() throws InterruptedException {
   	 WebElement postajob= driver.findElement(By.id("menu-item-26"));
       postajob.click();
       final String randomEmail = randomEmail();
       WebElement email = driver.findElement(By.id("create_account_email"));
       email.sendKeys(randomEmail);      
       driver.findElement(By.id("job_title")).sendKeys("QA");
       int s = driver.findElements(By.tagName("iframe")).size();
       System.out.println("Number of iframes" + s);
       driver.switchTo().frame("job_description_ifr");
      Thread.sleep(4000);
      driver.findElement(By.id("tinymce")).sendKeys("tttttt");
         driver.switchTo().defaultContent();
   	  driver.findElement(By.id("application")).sendKeys("https://alchemy.hguy.co/jobs/post-a-job/");
   	 driver.findElement(By.id("company_name")).sendKeys("IBM");
   	driver.findElement(By.xpath("//input[@type='submit'and @value='Preview']")).click();
   	   Thread.sleep(4000);

      driver.findElement(By.id("job_preview_submit_button")).click();
      
     String successmessage =  driver.findElement(By.xpath("//*[@id=\"post-5\"]/div")).getText();
          successmessage.contains("successfully") ;
    	   System.out.println("Job posted successfully s: " + successmessage);
     
     WebElement jobdashboard= driver.findElement(By.id("menu-item-25"));
     jobdashboard.click();
     
     String innerText = driver.findElement(By.xpath("//*[@id=\"job-manager-job-dashboard\"]/table/tbody/tr/td[1]")).getText(); 
     System.out.println("Table has text: " + innerText);
     Assert.assertEquals("QA", innerText);
   }
     private static String randomEmail() {
         return "random-" + UUID.randomUUID().toString() + "@example.com";
     }
   
	    @AfterTest
	    public void afterMethod() {
	        //Close the browser
	        driver.quit();
	    }
	 
	}