package rerunTests.inAnnotation;

import org.testng.ITestResult;

/**
 * Created by SChubuk on 14.09.2017.
 */

//org.testng.IRetryAnalyzer
public interface IRetryAnalyzer {
    public boolean retry(ITestResult result);
}
