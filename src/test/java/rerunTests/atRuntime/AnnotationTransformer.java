package rerunTests.atRuntime;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
import rerunTests.inAnnotation.RetryAnalyzer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by SChubuk on 14.09.2017.
 */
public class AnnotationTransformer implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod){
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }

}
