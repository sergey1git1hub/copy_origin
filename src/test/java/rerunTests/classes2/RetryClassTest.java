package rerunTests.classes2;

import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Created by SChubuk on 14.09.2017.
 */
@Test(retryAnalyzer = RetryAnalyzerImpl.class)
public class RetryClassTest {
    static int k = 0;
        @Test(retryAnalyzer = RetryAnalyzerImpl.class)
        public void Test1() {
            Assert.assertTrue(true);
            System.out.println("Something");
        }

        @Test(dependsOnMethods = "Test1", retryAnalyzer = RetryAnalyzerImpl.class)
        public void Test2() {
            if(k!=2){
                k++;
            Assert.assertTrue(false);
            } else {
                Assert.assertTrue(true);
            }
        }

        @Test(dependsOnMethods = "Test2", retryAnalyzer = RetryAnalyzerImpl.class)
        public void Test3() {
            Assert.assertTrue(true);
        }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        IRetryAnalyzer retry = result.getMethod().getRetryAnalyzer();
        if (retry == null) {
            return;
        }
        result.getTestContext().getFailedTests().removeResult(result.getMethod());
        result.getTestContext().getSkippedTests().removeResult(result.getMethod());
    }
    }

