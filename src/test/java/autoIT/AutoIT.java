package autoIT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by schubuk on 28.08.2017.
 */
public class AutoIT {
    @Test
    public void fileUploadTest() throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");
        WebDriver driver=new ChromeDriver();
       // driver.get("http://demo.guru99.com/selenium/autoit.html");
        driver.get("https://www.guru99.com/become-an-instructor.html");
       // driver.findElement(By.id("postjob")).click();
        /*Thread.sleep(3000);
        driver.findElement(By.cssSelector("#input_1")).sendKeys("Gaurav");
        driver.findElement(By.cssSelector("#input_2")).sendKeys("test.test@gmail.com");

        driver.findElement(By.id("input_4")).click();
        // below line execute the AutoIT script .*/
        Thread.sleep(5000);
        Runtime.getRuntime().exec("C:\\AutoItScripts\\UploadFile.exe");
     /*   driver.findElement(By.id("input_6")).sendKeys("AutoIT in Selenium");
        driver.findElement(By.id("input_2")).click();*/
        driver.close();

    }
}
