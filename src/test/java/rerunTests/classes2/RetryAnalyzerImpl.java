package rerunTests.classes2;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;


/**
 * Created by SChubuk on 15.09.2017.
 */
public class RetryAnalyzerImpl implements IRetryAnalyzer {
    private int count = 0;
    private static int maxTry = 5;

    @Override  //if it fails testng will mark test as failed
    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {                      //Check if test not succeed
            if (count < maxTry) {                            //Check if maxtry count is reached
                count++;                                     //Increase the maxTry count by 1
                iTestResult.setStatus(ITestResult.FAILURE);  //Mark test as failed
                return true;                                 //Tells TestNG to re-run the test
            } else {
                iTestResult.setStatus(ITestResult.FAILURE);  //If maxCount reached,test marked as failed
            }
        } else {
            iTestResult.setStatus(ITestResult.SUCCESS);      //If test passes, TestNG marks it as passed
        }
        return false;
    }

}
