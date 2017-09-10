package ssoANDlogs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.regex.Pattern;

import static data.Data.agentChrome;
import static data.Data.webphoneUrl;
import static helpMethods.HelpMethods.handleLogoutWindow;

/**
 * Created by SChubuk on 14.08.2017.
 */
public class Logs {


    @Test
    public void ssoLoginChrome() throws InterruptedException {

        //Login with SSO in Chrome
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");
        //System.setProperty("webdriver.chrome.verboseLogging", "true");
        //for logging
        DesiredCapabilities capsChrome = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);

        capsChrome.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        //for logging
        agentChrome = new ChromeDriver();
        agentChrome.get(webphoneUrl);
        Assert.assertEquals(agentChrome.getTitle(), "gbwebphone");
        WebDriverWait waitForTitle = new WebDriverWait(agentChrome, 10);
        waitForTitle.until(ExpectedConditions.titleIs("gbwebphone"));
        Assert.assertEquals(agentChrome.getTitle(), "gbwebphone");
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
    }

    /*
        @Test
        public void ssoLoginIE() throws InterruptedException {

            //Login with SSO in Chrome
            System.setProperty("webdriver.ie.driver", "C:/iedriver/IEDriverServer.exe");
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
        }
    */
    @Test(dependsOnMethods = "ssoLoginChrome")
    public void sendFeedback() {
        WebElement button_feedback = agentChrome.findElement(By.cssSelector("#btn_feedback"));
        button_feedback.click();
        WebElement notificationMessage = agentChrome.findElement(By.cssSelector("#notificationMessage1"));
        notificationMessage.sendKeys("Test feedback.");
        WebElement button_send = agentChrome.findElement(By.xpath("//form[@id = 'feedbackForm']//*[text()='Send']"));
        button_send.click();
    }

    //tr[@class='ui-widget-content']//td[@role ='gridcell']//*[text()='Send']
    @Test(dependsOnMethods = "sendFeedback")
    public void getChromeLogs() throws IOException {
        LogEntries logEntries = agentChrome.manage().logs().get("browser");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        String sDate = sdf.format(date);
        File driverLog = new File("C:\\Tutorial\\Idea sources\\CheckStatusAfterLoginIE1_4_3\\src\\test\\logs\\" +
                "test" + "  " +
                sDate + ".log");
        FileWriter out = new FileWriter(driverLog);
        for (LogEntry logEntry : logEntries.getAll()) {
            out.write(logEntry.toString() + "\n");
        }
        System.out.println("Logs saved to file.");
        out.close();

    }

    @Test(dependsOnMethods = {"getChromeLogs", "sendFeedback"})
    public void teardown() {
        agentChrome.quit();
    }


    @Test
    public void getSystemLogs() throws InterruptedException {

        //Login with SSO in Chrome
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");
        //System.setProperty("webdriver.chrome.verboseLogging", "true");
        //for logging
        DesiredCapabilities capsChrome = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);

        capsChrome.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        //for logging
        agentChrome = new ChromeDriver();
        agentChrome.get(webphoneUrl);
        Assert.assertEquals(agentChrome.getTitle(), "gbwebphone");
        WebDriverWait waitForTitle = new WebDriverWait(agentChrome, 10);
        waitForTitle.until(ExpectedConditions.titleIs("gbwebphone"));
        Assert.assertEquals(agentChrome.getTitle(), "gbwebphone");
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


        WebElement btnAdminPanel = agentChrome.findElement(By.cssSelector("#btn_admin_panel"));
        btnAdminPanel.click();

        WebElement menuOther = agentChrome.findElement(By.cssSelector("div.ui-slidemenu-content > ul > li.ui-widget.ui-menuitem.ui-corner-all.ui-menu-parent.otherClass > a"));
        menuOther.click();
        WebElement feedbackList = agentChrome.findElement(By.xpath("//*[@href = '/gbwebphone/admin/feedbackList.jsf']"));
        WebDriverWait wait = new WebDriverWait(agentChrome, 1);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@href = '/gbwebphone/admin/feedbackList.jsf']")));
        feedbackList.click();

        WebElement firstLogRecord = agentChrome.findElement(By.cssSelector("#feedbackDataForm\\3a feedbackDataTable_data > tr:nth-child(1) > td:nth-child(1)"));
        firstLogRecord.click();
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

    }





/*    @AfterClass
    public void getIELogs() throws IOException {
        LogEntries logEntries = agentIE.manage().logs().get("browser");
        Date date = new Date();
        File driverLog = new File("C:\\Tutorial\\Idea sources\\ssoTestWithCrutches\\src\\test\\logs\\"+
                "agentIE" + "  " +
                date.toString() + ".log");
        FileWriter out = new FileWriter(driverLog);
        for (LogEntry logEntry : logEntries.getAll()) {
            out.write(logEntry.toString() + "\n");
        }
        out.close();
    }*/
}

