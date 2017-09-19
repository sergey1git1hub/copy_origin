package rerunTests.classes2copy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by SChubuk on 14.09.2017.
 */
@Test(/*retryAnalyzer = RetryAnalyzerImpl.class*/)
public class RetryClassTest {
    static int k = 0;
    WebDriver driver;
    @BeforeClass
    public void browserSetup(){
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");
    }

        @Test(/*retryAnalyzer = RetryAnalyzerImpl.class*/)
        public void Test1() throws InterruptedException {
            driver = new ChromeDriver();
            driver.get("http://www.google.com");
            Thread.sleep(1000);
            System.out.println(driver.getTitle());
            Assert.assertTrue(driver.getTitle().equals("Google"));
        }

        @Test(dependsOnMethods = "Test1"/*, retryAnalyzer = RetryAnalyzerImpl.class*/)
        public void Test2() throws InterruptedException {
           /* if(k!=2){
                k++;
            Assert.assertTrue(false);
            } else {
                Assert.assertTrue(true);
            }*/
            /*Assert.assertTrue(true);*/
            driver = new ChromeDriver();
            driver.get("http://www.google.com");
            Thread.sleep(1000);

            Assert.assertTrue(driver.getTitle().equals("Google"));
        }

        @Test(dependsOnMethods = "Test2"/*, retryAnalyzer = RetryAnalyzerImpl.class*/)
        public void Test3() throws InterruptedException {
           /* Assert.assertTrue(true);
            System.out.println("Inside test3.");*/
            driver = new ChromeDriver();
            driver.get("http://www.google.com");
            Thread.sleep(1000);

            Assert.assertTrue(driver.getTitle().equals("Google"));
        }

 /*   @AfterMethod
    public void afterMethod(ITestResult result) {
        IRetryAnalyzer retry = result.getMethod().getRetryAnalyzer();
        if (retry == null) {
            return;
        }
        result.getTestContext().getFailedTests().removeResult(result.getMethod());
        result.getTestContext().getSkippedTests().removeResult(result.getMethod());
    }*/
    }

