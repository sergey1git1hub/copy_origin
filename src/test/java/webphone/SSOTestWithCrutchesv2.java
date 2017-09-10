package webphone;

import org.apache.commons.io.FileUtils;
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
import org.testng.Assert;
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
import static helpMethods.HelpMethods.lastFileModified;

/**
 * Created by SChubuk on 02.08.2017.
 */
public class SSOTestWithCrutchesv2 {

    int repeatCounter = 3;

    @Test(priority = 1)
    public WebDriver ssoLoginIE(WebDriver agentIE) throws InterruptedException {

        System.setProperty("webdriver.ie.driver", "C:/iedriver/IEDriverServer.exe");
        DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
        ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
                true);
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        ieCapabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
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
        WebElement currentStatus = agentIE.findElement(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"));
        WebDriverWait waitForAvailableStatus = new WebDriverWait(agentIE, 15);
        waitForAvailableStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bДоступен\\b.*|.*\\bIncoming\\b.*")));
        System.out.println("IE login: 1.");
        return agentIE;
    }

    /*
    * LoginToWebphnoneViaSSO method accepts Webdriver instance with all required capabilities and preferences, username,
    * password, group and initial status expected after login.
    * Accepts an array of parameters username, password, group, initialStatus
    *
    *
    * */

    @Test(priority = 2)
    public WebDriver ssoLoginChrome(WebDriver agentChrome) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");
        DesiredCapabilities capsChrome = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        capsChrome.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
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
        WebElement currentStatus = agentChrome.findElement(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"));
        WebDriverWait waitForAvailableStatus = new WebDriverWait(agentChrome, 15);
        waitForAvailableStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bДоступен\\b.*|.*\\bIncoming\\b.*|.*\\bAvailable\\b.*")));
        System.out.println("Chrome login: 1.");
        return agentChrome;
    }

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
        WebElement currentStatus = agentIE.findElement(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"));
        WebDriverWait waitForAvailableStatus = new WebDriverWait(agentIE, 15);
        waitForAvailableStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bДоступен\\b.*|.*\\bIncoming\\b.*")));
        return agentIE;
    }

    public WebDriver loopChrome(WebDriver agentChrome) throws InterruptedException {
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
        WebElement currentStatus = agentChrome.findElement(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"));
        WebDriverWait waitForAvailableStatus = new WebDriverWait(agentChrome, 15);
        waitForAvailableStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bДоступен\\b.*|.*\\bIncoming\\b.*|.*\\bAvailable\\b.*")));
        return agentChrome;
    }


    @Test(priority = 3)
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

    @Test(priority = 4)
    public void getChromeLogs() throws IOException {
        LogEntries logEntries = agentChrome.manage().logs().get("browser");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        String sDate = sdf.format(date);
        File driverLog = new File("C:\\Tutorial\\Idea sources\\CheckStatusAfterLoginIE1_4_3\\src\\test\\logs\\" +
                "agentChrome" + "  " +
                sDate + ".log");
        FileWriter out = new FileWriter(driverLog);
        for (LogEntry logEntry : logEntries.getAll()) {
            out.write(logEntry.toString() + "\n");
        }
        System.out.println("Logs saved to file.");
        out.close();
    }

    @Test(priority = 5)//(dependsOnMethods = "ssoLoginChrome")
    public void sendChromeFeedback() throws InterruptedException {
        WebElement button_feedback = agentChrome.findElement(By.cssSelector("#btn_feedback"));
        button_feedback.click();
        WebElement notificationMessage = agentChrome.findElement(By.cssSelector("#notificationMessage1"));
        notificationMessage.sendKeys("Test feedback.");
        WebDriverWait waitForButtonSend = new WebDriverWait(agentChrome, 5);
        waitForButtonSend.until(ExpectedConditions.elementToBeClickable(By.xpath("//form[@id = 'feedbackForm']//*[text()='Send']")));
        WebElement button_send = agentChrome.findElement(By.xpath("//form[@id = 'feedbackForm']//*[text()='Send']"));
        Thread.sleep(1000);
        button_send.click();
    }


    @Test(priority = 6)//(dependsOnMethods = "teardown")
    public void getSystemChromeLogs() throws InterruptedException {

 /*       System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");
        DesiredCapabilities capsChrome = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        capsChrome.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        agentChrome = new ChromeDriver();*/
        agentChrome.get(webphoneUrl);
        /*Assert.assertEquals(agentChrome.getTitle(), "gbwebphone");*/
        WebDriverWait waitForTitle = new WebDriverWait(agentChrome, 10);
        waitForTitle.until(ExpectedConditions.titleIs("gbwebphone"));
        Assert.assertEquals(agentChrome.getTitle(), "gbwebphone");
        WebElement button_SSO_Chrome = agentChrome.findElement(By.cssSelector("#ssoButton > span"));
        String winHandleBefore = agentChrome.getWindowHandle();
        button_SSO_Chrome.click();
       /* for (String winHandle : agentChrome.getWindowHandles()) {
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


        agentChrome = handleLogoutWindow(agentChrome);*/
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
        WebDriverWait waitForDownloadButton = new WebDriverWait(agentChrome, 5);
        waitForDownloadButton.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Export']")));
        WebElement downloadButton = agentChrome.findElement(By.xpath("//*[text()='Export']"));
        Thread.sleep(10000);
        downloadButton.click();
    }

    @Test(priority = 7)
    public void moveChromeLogs() {
        File source = lastFileModified("C:\\Users\\schubuk\\Downloads");
        //File source = new File("C:\\Users\\schubuk\\Downloads\\file1.txt");
        File dest = new File("C:\\Tutorial\\Idea sources\\CheckStatusAfterLoginIE1_4_3\\src\\test\\logs");
        try {
            FileUtils.copyFileToDirectory(source, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterClass()
    public void teardown() {
        agentIE.quit();
        agentChrome.quit();
        System.out.println("Teardown.");
    }
}
