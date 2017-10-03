package methods;

import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;

import static methods.Methods.*;

/**
 * Created by SChubuk on 12.09.2017.
 */
public class MethodsTest {
    static String initialStatus;

    @Test
    public static void initializeVariablesChrome() {
        url = "http://172.21.7.239/gbwebphone/";
        browser = "chrome";
        method = "usual";
        username = "81016";
        password = "1";
        group = "\\!test_group5_5220";
        fast = false;
        initialStatus = "Available";
    }

    @Test(dependsOnMethods = "initializeVariables")
    public static void chromeLogin() throws InterruptedException {
        initializeVariablesChrome();
        loadPage();
        login();
        handlePluginWindow();
        checkStatus(initialStatus);
    }

    @Test
    public static void initializeVariablesIE() {
        url = "http://172.21.24.109/gbwebphone/";
        browser = "ie";
        method = "usual";
        username = "81016";
        password = "1";
        group = "\\!test_group5_5220";
        fast = false;
        initialStatus = "Available";
    }

    @Test(dependsOnMethods = "initializeVariables")
    public static void IELogin() throws InterruptedException {
        initializeVariablesIE();
        loadPage();
        login();
        handlePluginWindow();
        checkStatus(initialStatus);
    }

    @Test
    public static void initializeVariablesIEAD() throws UnsupportedEncodingException {
        url = "http://172.21.24.109/gbwebphone/";
        browser = "ie";
        method = "usual";
        username = "81016";
        password = "1";
        group = "pasha_G_5_copy_preview";
        fast = false;
        String myString = "Тренинг";
        byte bytes[] = myString.getBytes("ISO-8859-1");
        String value = new String(bytes, "UTF-8");
        initialStatus = value;
    }

    @Test(dependsOnMethods = "initializeVariablesIEAD")
    public static void IELoginAD() throws InterruptedException {
        initializeVariablesIEAD();
        loadPage();
        login();
        handlePluginWindow();
        checkStatus(initialStatus);
    }

    }
