package webphone;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.logging.Level;
import java.util.regex.Pattern;

import static data.Data.agentChrome;
import static data.Data.webphoneUrl;
import static org.testng.Assert.assertTrue;
import static helpMethods.HelpMethods.handleLogoutWindow;

public class TwoLines {


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
        WebElement chatGroup = agentChrome.findElement(By.cssSelector("[data-label=\\!test_group5_5220]"));
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

    @Test(dependsOnMethods = "ssoLoginChrome")
    public void callOnTwoLines() throws InterruptedException, FindFailed {

        App cxphone = App.open("C:\\Program Files (x86)\\3CXPhone\\3CXPhone.exe");
        Screen screen = new Screen();
        org.sikuli.script.Pattern button_3CXAcceptCall = new org.sikuli.script.Pattern("C:\\SikuliImages\\button_3CXAcceptCall.png");

        WebElement phoneNumberField = agentChrome.findElement(By.cssSelector("#PhoneNumber"));
        phoneNumberField.sendKeys("94949");

        WebElement button_Call = agentChrome.findElement(By.cssSelector("#btn_call"));
        button_Call.click();
//fail with autoanswer
       /* WebDriverWait waitForRingingStatus = new WebDriverWait(agentChrome, 10);
        waitForRingingStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bRinging\\b.*")));
        //assertTrue(currentStatus.getText().equals(Pattern.compile(".*\\bRinging\\b.*")));*/
        answerCallOnClientSideLine1();
        screen.wait(button_3CXAcceptCall, 10);
        screen.click(button_3CXAcceptCall);

        WebDriverWait waitForInCallStatus = new WebDriverWait(agentChrome, 20);
        waitForInCallStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bIncall\\b.*")));
        //assertTrue(currentStatus.getTexSt().equals(Pattern.compile(".*\\bRinging\\b.*")));

        //switch to second line
        WebElement line2 = agentChrome.findElement(By.cssSelector("#btn_line_2"));
        line2.click();

        phoneNumberField = agentChrome.findElement(By.cssSelector("#PhoneNumber"));
        phoneNumberField.sendKeys("94948");
        WebDriverWait waitForCallButton = new WebDriverWait(agentChrome, 5);
        waitForCallButton.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#btn_call")));
        button_Call = agentChrome.findElement(By.cssSelector("#btn_call"));
        button_Call.click();
   /*     try{
            assertFalse(agentChrome.findElement(By.cssSelector("#btn_call")).isEnabled());
        } catch(AssertionError e){
            while(agentChrome.findElement(By.cssSelector("#btn_call")).isEnabled()&& !agentChrome.findElement(By.cssSelector("#btn_call")).isEnabled()){
                button_Call = agentChrome.findElement(By.cssSelector("#btn_call"));
                button_Call.click();
                Thread.sleep(200);
            }
        }
            finally{}*/


        WebDriverWait waitForOnHoldStatus = new WebDriverWait(agentChrome, 10);
        waitForOnHoldStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bOnhold\\b.*")));

        line2 = agentChrome.findElement(By.cssSelector("#btn_line_2"));
        System.out.println(line2.getText());
        assertTrue(line2.getText().equals("Ringing"));
        answerCallOnClientSideLine2();

        screen.wait(button_3CXAcceptCall, 10);
        screen.click(button_3CXAcceptCall);
        Thread.sleep(5000);
        org.sikuli.script.Pattern button_3CXHangupCall = new org.sikuli.script.Pattern("C:\\SikuliImages\\button_3CXHangupCall.png");
        screen.wait(button_3CXHangupCall, 10);
        screen.click(button_3CXHangupCall);

        org.sikuli.script.Pattern line_3CXLine1 = new org.sikuli.script.Pattern("C:\\SikuliImages\\line_3CXLine1.png");
        screen.wait(line_3CXLine1, 10);
        screen.click(line_3CXLine1);

        screen.click(button_3CXHangupCall);
        Thread.sleep(1000);
        org.sikuli.script.Pattern closePhoneWindow = new org.sikuli.script.Pattern("C:\\SikuliImages\\closePhoneWindow.png");
        screen.wait(closePhoneWindow, 10);
        screen.click(closePhoneWindow);
        //cxphone = App.open("C:\\Program Files (x86)\\3CXPhone\\3CXPhone.exe");
       // cxphone.close();
       /* waitForInCallStatus = new WebDriverWait(agentChrome, 10);
        waitForInCallStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bIncall\\b.*")));

        WebElement line1 = agentChrome.findElement(By.cssSelector("#btn_line_1"));
        line1.click();

        WebElement button_Hold = agentChrome.findElement(By.cssSelector("#btn_hold"));
        button_Hold.click();


        WebElement button_Hangup = agentChrome.findElement(By.cssSelector("#btn_hangup"));
        button_Hangup.click();


        line2 = agentChrome.findElement(By.cssSelector("#btn_line_2"));
        line2.click();

        WebDriverWait waitForButton_Hangup = new WebDriverWait(agentChrome, 5);
        waitForButton_Hangup.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#btn_hangup")));
        button_Hangup = agentChrome.findElement(By.cssSelector("#btn_hangup"));
        button_Hangup.click();*/
        Thread.sleep(1000);
        WebDriverWait waitForResultCode = new WebDriverWait(agentChrome, 5);
        waitForResultCode.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='Удачно']")));
        WebElement resultCode = agentChrome.findElement(By.xpath("//td[text()='Удачно']"));
        resultCode.click();

/*while(agentChrome.findElement(By.cssSelector("#btn_rslt"))!= null){
        WebElement button_Save = agentChrome.findElement(By.cssSelector("#btn_rslt"));
        button_Save.click();
        }*/

        Thread.sleep(1000);
        WebElement button_Save = agentChrome.findElement(By.cssSelector("#btn_rslt > span.ui-button-text.ui-c"));
        button_Save.click();

        WebDriverWait waitForAvailableStatus = new WebDriverWait(agentChrome, 10);
        waitForAvailableStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bAvailable\\b.*")));
        // timer here assertTrue(line2.getText().equals("Incall"));

        //Onhold
        //Ringing

    }

    @AfterClass
    public void teardown(){
        agentChrome.quit();
    }
    /*
    * 1.Login to webphone 2.0.0
    * 2.Call 94636 on the first line
    * 3.Answer call on client side
    * 4.Switch to the second line
    * 5.Call 94626 on the second line
    * 6.Answer call on cilent side
    * 7.Switch to the first line
    * 8.Unhold the first line
    * 9.Hangup the first line on agent side
    * 10.Hangup on the second line on agent side
    * 11.Choose result code "Удачно"
    * 12.Click save button
    * 13.Verify available status
    * */

    /*   @Test(priority = 2)//(dependsOnMethods = "ssoLoginChrome")
       public void sendFeedback() {
           WebElement button_feedback = agentChrome.findElement(By.cssSelector("#btn_feedback"));
           button_feedback.click();
           WebElement notificationMessage = agentChrome.findElement(By.cssSelector("#notificationMessage1"));
           notificationMessage.sendKeys("TeTest feedback.");
           WebElement button_send = agentChrome.findElement(By.xpath("//form[@id = 'feedbackForm']/*//*[text()='Send']"));
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
        WebElement feedbackList = agentChrome.findElement(By.xpath("/*//*[@href = '/gbwebphone/admin/feedbackList.jsf']"));
        WebDriverWait wait = new WebDriverWait(agentChrome, 1);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/*//*[@href = '/gbwebphone/admin/feedbackList.jsf']")));
        feedbackList.click();
        WebElement firstLogRecord = agentChrome.findElement(By.cssSelector("#feedbackDataForm\\3a feedbackDataTable_data > tr:nth-child(1) > td:nth-child(1)"));
        firstLogRecord.click();
        WebDriverWait waitForDownloadButton = new WebDriverWait(agentChrome, 5);
        waitForDownloadButton.until(ExpectedConditions.elementToBeClickable(By.xpath("/*//*[text()='Export']")));
        WebElement downloadButton = agentChrome.findElement(By.xpath("/*//*[text()='Export']"));
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
*/
    public void answerCallOnClientSideLine1() {

    }

    public void answerCallOnClientSideLine2() {
    }
}

