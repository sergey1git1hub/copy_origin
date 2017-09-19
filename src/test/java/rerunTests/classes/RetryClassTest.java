package rerunTests.classes;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by SChubuk on 14.09.2017.
 */
public class RetryClassTest {
    @Test
    public void Test1() {
        Assert.assertTrue(true);
    }

    @Test(dependsOnMethods = "Test1")
    public void Test2() {
        Assert.assertTrue(false);
    }

    @Test(dependsOnMethods = "Test2")
    public void Test3() {
        Assert.assertTrue(true);
    }

}
