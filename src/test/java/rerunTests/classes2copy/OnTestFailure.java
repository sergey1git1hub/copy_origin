package rerunTests.classes2copy;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.List;

import static org.testng.ITestResult.SUCCESS;

/**
 * Created by SChubuk on 18.09.2017.
 */
public class OnTestFailure implements org.testng.ITestListener {
    public TestNG tng = new TestNG();
    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed!");

       /* XmlSuite suite = new XmlSuite();
        suite.setName("rerunFailedTestClasses");
        XmlTest test = new XmlTest(suite);
        test.setName(result.getTestName());
        List<XmlClass> classes = new ArrayList<XmlClass>();
        classes.add(result.getTestClass().getXmlClass());
        test.setXmlClasses(classes) ;
        List<XmlSuite> suites = new ArrayList<XmlSuite>();
        suites.add(suite);

        tng.setXmlSuites(suites);
        tng.run();*/

        XmlSuite suite = new XmlSuite();
        suite.setName("TmpSuite");

        XmlTest test = new XmlTest(suite);
        test.setName("TmpTest");
        List<XmlClass> classes = new ArrayList<XmlClass>();
        //classes.add(new XmlClass("rerunTests.classes2copy.RetryClassTest"));
        classes.add(result.getTestClass().getXmlClass());
        test.setXmlClasses(classes) ;


        //And then you can pass this XmlSuite to TestNG:

        List<XmlSuite> suites = new ArrayList<XmlSuite>();
        suites.add(suite);
        TestNG tng = new TestNG();
        tng.setXmlSuites(suites);
        tng.run();
        if(!tng.hasFailure())
        result.setStatus(SUCCESS);
        //tng.setHasSkip(false);
        //remove skipped and set success for skipped test too
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
