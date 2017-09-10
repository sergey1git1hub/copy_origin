package ssoANDlogs;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
import static helpMethods.HelpMethods.lastFileModified;

public class Logsv2 {


    @Test(priority = 1)
    public void ssoLoginChrome() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");
        //for logging
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--enable-logging --v=1");
        DesiredCapabilities capsChrome = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        /*logPrefs.enable(LogType.BROWSER, Level.ALL);
        capsChrome.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);*/
        logPrefs.enable(LogType.BROWSER, Level.INFO);
        //for logging
        agentChrome = new ChromeDriver(options);
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
    }

    @Test(priority = 2)//(dependsOnMethods = "ssoLoginChrome")
    public void sendFeedback() {
        WebElement button_feedback = agentChrome.findElement(By.cssSelector("#btn_feedback"));
        button_feedback.click();
        WebElement notificationMessage = agentChrome.findElement(By.cssSelector("#notificationMessage1"));
        notificationMessage.sendKeys("TeTest feedback.");
        WebElement button_send = agentChrome.findElement(By.xpath("//form[@id = 'feedbackForm']//*[text()='Send']"));
        button_send.click();
    }

    @Test(priority = 3)//(dependsOnMethods = "sendFeedback")
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

    @Test(priority = 4)//(dependsOnMethods = "getChromeLogs")
    public void teardown() {
        agentChrome.quit();
    }


    @Test(priority = 5)//(dependsOnMethods = "teardown")
    public void getSystemLogs() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");
        DesiredCapabilities capsChrome = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        capsChrome.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
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
        WebDriverWait waitForButtonContinue = new WebDriverWait(agentChrome, 10);
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
        downloadButton.click();
        }

    @Test(priority = 6)
    public void moveLogs () throws InterruptedException {
        Thread.sleep(1000L);
        File source = lastFileModified("C:\\Users\\schubuk\\Downloads");
       //File source = new File("C:\\Users\\schubuk\\Downloads\\file1.txt");
        File dest = new File("C:\\Tutorial\\Idea sources\\CheckStatusAfterLoginIE1_4_3\\src\\test\\logs");
        try {
            FileUtils.copyFileToDirectory(source, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test(priority = 7)//(dependsOnMethods = "getChromeLogs")
    public void teardown2() {
        agentChrome.quit();
    }

}

