package rerunTests.inAnnotation;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by SChubuk on 14.09.2017.
 */
public class Test1 {
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void Test1(){
        Assert.assertEquals(false, true);
    }

    @Test
    public void Test2(){
        Assert.assertEquals(false, true);
    }
}
