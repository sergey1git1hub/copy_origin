package webphone;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.regex.Pattern;
import static data.Data.*;
import static org.testng.Assert.assertEquals;
import static helpMethods.HelpMethods.handleLogoutWindow;

/**
 * Created by SChubuk on 02.08.2017.
 */
public class SSOTestWithCrutches {

    int repeatCounter = 3;

    @Test(priority = 1)
    public WebDriver ssoLoginIE(WebDriver agentIE) throws InterruptedException {

        //Login with SSO in IE
        System.setProperty("webdriver.ie.driver", "C:/iedriver/IEDriverServer.exe");

       /********************************************/










       /********************************************/
        DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
        ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
                true);
        //for logs
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        ieCapabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        //for logs
        agentIE = new InternetExplorerDriver(ieCapabilities);
        agentIE.get(webphoneUrl);
        WebDriverWait waitForTitle = new WebDriverWait(agentIE, 10);
        waitForTitle.until(ExpectedConditions.titleIs("gbwebphone"));
        assertEquals(agentIE.getTitle(), "gbwebphone");

        WebElement button_SSO_IE = agentIE.findElement(By.cssSelector("#ssoButton > span"));
        button_SSO_IE.click();
        try {
            WebDriverWait waitForButtonContinue = new WebDriverWait(agentIE, 5);
            waitForButtonContinue.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#btn_continue > span.ui-button-text.ui-c")));
        } catch (TimeoutException e) {
            agentIE = handleLogoutWindow(agentIE);
        }
        WebDriverWait waitForGroupList = new WebDriverWait(agentIE, 5);
        waitForGroupList.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#group_input_label")));

        WebElement groupList = agentIE.findElement(By.cssSelector("#group_input_label"));
        groupList.click();
        WebElement chatGroup = agentIE.findElement(By.cssSelector("[data-label=test_alex]"));
        chatGroup.click();
        WebElement btnContinue = agentIE.findElement(By.cssSelector("#btn_continue > span.ui-button-text.ui-c"));
        btnContinue.click();

        //verification that page has been loaded
  /*      WebElement currentStatus = agentIE.findElement(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"));
        for (int i = 0; i < 5; i++) {
            if (currentStatus.getText().contains("Select")) {
                Thread.sleep(5000);
            } else break;
        }*/
//        Assert.assertFalse(currentStatus.getText().contains("Select"), "Test failed because of status.");
        WebElement currentStatus = agentIE.findElement(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"));
        WebDriverWait waitForAvailableStatus = new WebDriverWait(agentIE, 15);
/*        waitForAvailableStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bAvailable\\b.*|.*\\bIncoming\\b.*")));*/
        waitForAvailableStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bДоступен\\b.*|.*\\bIncoming\\b.*")));
        //System.out.println(currentStatus.getText());
        System.out.println("IE login: 1.");
        return agentIE;
    }

    @Test(priority = 2)
    public WebDriver ssoLoginChrome(WebDriver agentChrome) throws InterruptedException {

        //Login with SSO in Chrome
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");
        //for logging
        DesiredCapabilities capsChrome = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);

        capsChrome.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        //for logging
        agentChrome = new ChromeDriver();
        agentChrome.get(webphoneUrl);
        assertEquals(agentChrome.getTitle(), "gbwebphone");
        WebDriverWait waitForTitle = new WebDriverWait(agentChrome, 10);
        waitForTitle.until(ExpectedConditions.titleIs("gbwebphone"));
        assertEquals(agentChrome.getTitle(), "gbwebphone");
        WebElement button_SSO_IE = agentChrome.findElement(By.cssSelector("#ssoButton > span"));
        String winHandleBefore = agentChrome.getWindowHandle();
        button_SSO_IE.click();
        for (String winHandle : agentChrome.getWindowHandles()) {
            agentChrome.switchTo().window(winHandle);
        }
        WebElement ssoUsername = agentChrome.findElement(By.cssSelector("#username"));
        ssoUsername.sendKeys("81016");
        WebElement ssoPassword = agentChrome.findElement(By.cssSelector("#password"));
        ssoPassword.sendKeys("1");
        WebElement ssoRememberMe = agentChrome.findElement(By.cssSelector("#remember-me"));
        ssoRememberMe.click();
        WebElement button_sso_Login = agentChrome.findElement(By.cssSelector("#login_button"));
        button_sso_Login.click();
        agentChrome.switchTo().window(winHandleBefore);
        agentChrome = handleLogoutWindow(agentChrome);


/**************************************************************/

        WebDriverWait waitForButtonContinue = new WebDriverWait(agentChrome, 5);
        waitForButtonContinue.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#btn_continue > span.ui-button-text.ui-c")));

        WebDriverWait waitForGroupList = new WebDriverWait(agentChrome, 10);
        waitForGroupList.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#group_input_label")));

        WebElement groupList = agentChrome.findElement(By.cssSelector("#group_input_label"));
        groupList.click();
        WebElement chatGroup = agentChrome.findElement(By.cssSelector("[data-label=test_alex]"));
        chatGroup.click();
        WebElement btnContinue = agentChrome.findElement(By.cssSelector("#btn_continue > span.ui-button-text.ui-c"));
        btnContinue.click();

        //verification that page has been loaded
 /*       WebDriverWait waitForSupervisorTab = new WebDriverWait(agentChrome, 10);
       waitForSupervisorTab.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#tabView > ul > li:nth-child(1)")));
        WebElement tabSupervisor = agentChrome.findElement(By.cssSelector("#tabView > ul > li:nth-child(1)"));
        tabSupervisor.click();*/

      /*  WebElement currentStatus = agentChrome.findElement(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"));
        for(int i=0; i<5; i++){
            if(currentStatus.getText().contains("Select status")){
                Thread.sleep(5000);
            } else break;
        }
        Assert.assertFalse(currentStatus.getText().contains("Select status"), "Test failed because of status.");
        */
        WebElement currentStatus = agentChrome.findElement(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"));
        WebDriverWait waitForAvailableStatus = new WebDriverWait(agentChrome, 15);
/*        waitForAvailableStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bAvailable\\b.*|.*\\bIncoming\\b.*")));*/
        waitForAvailableStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bДоступен\\b.*|.*\\bIncoming\\b.*|.*\\bAvailable\\b.*")));
        //System.out.println(currentStatus.getText());
        System.out.println("Chrome login: 1.");
        return agentChrome;
    }

    @Test
    public WebDriver loopIE(WebDriver agentIE) throws InterruptedException {
        try {
            WebDriverWait waitForButton_SSO_IE = new WebDriverWait(agentIE, 10);
            waitForButton_SSO_IE.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("#ssoButton > span")));
            WebElement button_SSO_IE = agentIE.findElement(By.cssSelector("#ssoButton > span"));
            button_SSO_IE.click();
            WebDriverWait waitForButton_SSO_IE2 = new WebDriverWait(agentIE, 10);
            waitForButton_SSO_IE.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("#ssoButton > span")));
            WebElement button_SSO_IE2 = agentIE.findElement(By.cssSelector("#ssoButton > span"));
            button_SSO_IE.click();
        } catch (NoSuchElementException e) {
            WebElement button_logout = agentIE.findElement(By.cssSelector("#btn_power"));
            button_logout.click();
        } catch (ElementNotVisibleException e) {
            WebElement button_logout = agentIE.findElement(By.cssSelector("#btn_power"));
            button_logout.click();
        } finally {
            Thread.sleep(2000L);
        }

        agentIE = handleLogoutWindow(agentIE);

        WebDriverWait waitForGroupList = new WebDriverWait(agentIE, 10);
        waitForGroupList.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#group_input_label")));

        WebElement groupList = agentIE.findElement(By.cssSelector("#group_input_label"));
        groupList.click();
        WebElement chatGroup = agentIE.findElement(By.cssSelector("[data-label=test_alex]"));
        chatGroup.click(); //rebuild project
        WebDriverWait waitForButtonContinue = new WebDriverWait(agentIE, 10);
        waitForButtonContinue.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("#btn_continue > span.ui-button-text.ui-c")));
        WebElement btnContinue = agentIE.findElement(By.cssSelector("#btn_continue > span.ui-button-text.ui-c"));
        btnContinue.click();

        //verification that page has been loaded
 /*       WebElement currentStatus = agentIE.findElement(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"));
        for (int i = 0; i < 5; i++) {
            if (currentStatus.getText().contains("Select")) {
                Thread.sleep(5000);
            } else break;
        }*/
        // Assert.assertFalse(currentStatus.getText().contains("Select"), "Test failed because of status.");

        WebElement currentStatus = agentIE.findElement(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"));
        WebDriverWait waitForAvailableStatus = new WebDriverWait(agentIE, 15);
/*        waitForAvailableStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bAvailable\\b.*|.*\\bIncoming\\b.*")));*/
        waitForAvailableStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bДоступен\\b.*|.*\\bIncoming\\b.*")));
        //System.out.println(currentStatus.getText());

        return agentIE;

    }

    @Test
    public WebDriver loopChrome(WebDriver agentChrome) throws InterruptedException {
        // Thread.sleep(10000);
        try {
            WebDriverWait waitForButton_SSO_Chrome = new WebDriverWait(agentChrome, 10);
            waitForButton_SSO_Chrome.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("#ssoButton > span")));
            WebElement button_SSO_Chrome = agentChrome.findElement(By.cssSelector("#ssoButton > span"));
            button_SSO_Chrome.click();
        } catch (NoSuchElementException e) {
            WebDriverWait waitForButton_logout = new WebDriverWait(agentChrome, 10);
            waitForButton_logout.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("#btn_power")));
            WebElement button_logout = agentChrome.findElement(By.cssSelector("#btn_power"));
            button_logout.click();
        } catch (TimeoutException e) {
        }
        try {
            WebDriverWait waitForButton_SSO_Chrome = new WebDriverWait(agentChrome, 10);
            waitForButton_SSO_Chrome.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("#ssoButton > span")));
            WebElement button_SSO_Chrome = agentChrome.findElement(By.cssSelector("#ssoButton > span"));
            button_SSO_Chrome.click();
            agentChrome = handleLogoutWindow(agentChrome);
            WebDriverWait waitForGroupList = new WebDriverWait(agentChrome, 10);
            waitForGroupList.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#group_input_label")));
        } catch (TimeoutException e) {
            WebElement button_SSO_Chrome = agentChrome.findElement(By.cssSelector("#ssoButton > span"));
            button_SSO_Chrome.click();
            WebDriverWait waitForGroupList = new WebDriverWait(agentChrome, 10);
            waitForGroupList.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#group_input_label")));
        }

        WebElement groupList = agentChrome.findElement(By.cssSelector("#group_input_label"));
        groupList.click();
        WebElement chatGroup = agentChrome.findElement(By.cssSelector("[data-label=test_alex]"));
        chatGroup.click();
        WebElement btnContinue = agentChrome.findElement(By.cssSelector("#btn_continue > span.ui-button-text.ui-c"));
        btnContinue.click();

        //verification that page has been loaded
 /*           WebDriverWait waitForSupervisorTab = new WebDriverWait(agentChrome, 10);
            waitForSupervisorTab.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#tabView > ul > li:nth-child(1)")));
            WebElement tabSupervisor = agentChrome.findElement(By.cssSelector("#tabView > ul > li:nth-child(1)"));
            tabSupervisor.click();
*/
      /*  WebElement currentStatus = agentChrome.findElement(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"));
        for(int i=0; i<5; i++){
            if(currentStatus.getText().contains("Select status")){
                Thread.sleep(5000);
            } else break;
        }
        Assert.assertFalse(currentStatus.getText().contains("Select status"), "Test failed because of status.");
        */
        WebElement currentStatus = agentChrome.findElement(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"));
        WebDriverWait waitForAvailableStatus = new WebDriverWait(agentChrome, 15);
/*        waitForAvailableStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bAvailable\\b.*|.*\\bIncoming\\b.*")));*/
        waitForAvailableStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bДоступен\\b.*|.*\\bIncoming\\b.*|.*\\bAvailable\\b.*")));
        //System.out.println(currentStatus.getText());

        return agentChrome;
    }



    @Test
    public void ssoLoop() throws InterruptedException {
        agentIE = ssoLoginIE(agentIE);
        agentChrome = ssoLoginChrome(agentChrome);
        for (int i = 0; i < repeatCounter - 1; ++i) { //It will run this like a functions, not like a test.
            agentIE = loopIE(agentIE);
            System.out.println("IE login: " + repeatCounter + ".");
            agentChrome = loopChrome(agentChrome);
            System.out.println("Chrome login: " + repeatCounter + ".");
        }
    }

    @AfterClass(dependsOnMethods = {"getChromeLogs"})
    public void teardown() {
        agentIE.quit();
        agentChrome.quit();
        System.out.println("Teardown.");
    }

/* NOT IMPLEMENTED FOR IE
@AfterClass
    public void getIELogs() throws IOException {
        LogEntries logEntries = agentIE.manage().logs().get("browser");
        File driverLog = new File("C:\\Tutorial\\Idea sources\\ssoTestWithCrutches\\src\\test\\logs\\" + "agentIE.log");
        FileWriter out = new FileWriter(driverLog);
        for (LogEntry logEntry : logEntries.getAll()) {
            out.write(logEntry.toString() + "\n");
        }
        out.close();
    }*/
    @AfterClass
    public void getChromeLogs() throws IOException {
        LogEntries logEntries = agentChrome.manage().logs().get("browser");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        String sDate= sdf.format(date);
        File driverLog = new File("C:\\Tutorial\\Idea sources\\CheckStatusAfterLoginIE1_4_3\\src\\test\\logs\\"+
                "agentChrome" + "  " +
                sDate + ".log");
        FileWriter out = new FileWriter(driverLog);
        for (LogEntry logEntry : logEntries.getAll()) {
            out.write(logEntry.toString() + "\n");
        }
        System.out.println("Logs saved to file.");
        out.close();
    }
}
