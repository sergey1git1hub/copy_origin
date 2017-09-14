package rerunTests.inAnnotation;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
/**
 * Created by SChubuk on 14.09.2017.
 */
public class RetryAnalyzer implements IRetryAnalyzer{
    int counter = 0;
    int retryLimit = 4;

    @Override
    public boolean retry(ITestResult result){
        if(counter < retryLimit){
            counter++;
            return true;
        }
    return false;
    }
}
