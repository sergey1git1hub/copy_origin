package rerunTests.classes;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Created by SChubuk on 15.09.2017.
 */
public class OnTestFailureClass implements IRetryAnalyzer {
    private int retryCount = 0;
    private int maxRetryCount = 3;
    public boolean retry(ITestResult result) {
        if(retryCount < maxRetryCount)
        {
            retryCount++;
            return true;
        }
        return false;
    }

}
