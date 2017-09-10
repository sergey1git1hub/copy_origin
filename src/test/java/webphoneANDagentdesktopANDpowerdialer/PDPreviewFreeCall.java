package webphoneANDagentdesktopANDpowerdialer;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.regex.Pattern;

import static data.Data.*;

import static java.lang.Thread.sleep;
import static helpMethods.HelpMethods.handleLogoutWindow;

//import java.lang.Object;

public class PDPreviewFreeCall {
    String phoneNumber = "94949";

    @Test()
    public void ssoLoginChrome() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");
        //for logging
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--enable-logging --v=1");
        DesiredCapabilities capsChrome = DesiredCapabilities.chrome();
        capsChrome.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
        LoggingPreferences logPrefs = new LoggingPreferences();
        /*logPrefs.enable(LogType.BROWSER, Level.ALL);
        capsChrome.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);*/
        logPrefs.enable(LogType.BROWSER, Level.INFO);
        //for logging
        agentChrome = new ChromeDriver(capsChrome);
 /*  How to pass capabilities and options at the same time?
        agentChrome = new ChromeDriver(options);

        and log preferences desirably*/

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
        WebElement chatGroup = agentChrome.findElement(By.cssSelector("[data-label=pasha_G_5_copy_preview]"));
        chatGroup.click();
        WebElement btnContinue = agentChrome.findElement(By.cssSelector("#btn_continue > span.ui-button-text.ui-c"));
        btnContinue.click();
        WebElement currentStatus = agentChrome.findElement(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"));
        WebDriverWait waitForTrainingStatus = new WebDriverWait(agentChrome, 15);
        waitForTrainingStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bТренинг\\b.*")));
        System.out.println("Chrome login: 1.");
    }

    @Test()
    public void changeStatusToAvailable() {
        WebElement currentStatus = agentChrome.findElement(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"));
        currentStatus.click();
        WebElement availableStatus = agentChrome.findElement(By.xpath(
                "//*[contains(text(),'Available')]"));
        availableStatus.click();
        WebDriverWait waitForAvailableStatus = new WebDriverWait(agentChrome, 15);
        waitForAvailableStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bAvailable\\b.*")));
    }

    public void switchToADTab() {
        WebElement adTab = agentChrome.findElement(By.xpath("//a[@href = '#tabView:tab123']"));
        adTab.click();
/*        WebElement welcome = agentChrome.findElement(By.cssSelector("#main > h1"));
        assertTrue(welcome.getText().equals("Welcome!"));*/
    }

/*    @Test
    public void testCRMTab() throws InterruptedException {
        String winHandleBefore = agentChrome.getWindowHandle();
        switchToADTab();
        Thread.sleep(1000);
        for (String winHandle : agentChrome.getWindowHandles()) {
            agentChrome.switchTo().window(winHandle);
        }
        WebElement administrationMenu = agentChrome.findElement(By.xpath("/*//*[text()='Administration']"));
        administrationMenu.click();
        agentChrome.switchTo().window(winHandleBefore);
        Thread.sleep(1000);

    }*/

    @Test
    public void testCRMTab() throws InterruptedException {
        WebElement adTab = agentChrome.findElement(By.xpath("//a[@href = '#tabView:tab123']"));
        adTab.click();
        agentChrome.switchTo().frame(agentChrome.findElement(By.id("TAB_123")));
        WebElement administrationMenu = agentChrome.findElement(By.xpath("/*//*[text()='Administration']"));
        administrationMenu.click();
        agentChrome.switchTo().defaultContent();

    }


    public Connection getConnection() throws SQLException, ClassNotFoundException {
        String userName = "GBWebPhoneTest";
        String password = "yt~k$tCW8%Gj";
        String url = "jdbc:sqlserver://172.21.7.225\\\\corporate;DatabaseName=GBWebPhoneTest;portNumber=1438";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection(url, userName, password);
        return conn;
    }

    public static void updateRecord(Connection con, String phoneNumber) throws SQLException {
        String query = "UPDATE GBWebPhoneTest.dbo.pd_5009_3 SET phone_number_1 = '" + phoneNumber + "'," +
                " caller_id_1 = null, call_from_time_1 = null, call_till_time_1 = null," +
                " timezone_offset_1 = null, call_from_time = null, call_till_time = null," +
                " timezone_offset = null, start_date = null, answer_date = null," +
                " complete_date = null, called_number = null, telephony_result_code = null," +
                " user_result_code = null, rc_sequence = null, user_id = null, " +
                "callback_number = null, callback_date = null, retry_number = null, " +
                "retry_date = null, retry_total = null, retry_total_macro = null, ad_card_id = null," +
                " is_fetched = null, caller_id_4 = null, phone_number_4 = null," +
                " call_from_time_4 = null, call_till_time_4 = null, timezone_offset_4 = null" +
                " WHERE id = 124;";

        Statement stmt = con.createStatement();
        stmt.execute(query);
    }

    @Test
    public void runSQLQuery() throws SQLException, ClassNotFoundException {
        updateRecord(getConnection(), phoneNumber);
    }
    @Test
    public void answerCallOnClientSide() throws FindFailed, InterruptedException {
        Thread.sleep(2000);
        App cxphone = App.open("C:\\Program Files (x86)\\3CXPhone\\3CXPhone.exe");
        Screen screen = new Screen();
        org.sikuli.script.Pattern button_3CXAcceptCall = new org.sikuli.script.Pattern("C:\\SikuliImages\\button_3CXAcceptCall.png");
        screen.wait(button_3CXAcceptCall, 10);
        screen.click(button_3CXAcceptCall);
        Thread.sleep(1000);
        org.sikuli.script.Pattern closePhoneWindow = new org.sikuli.script.Pattern("C:\\SikuliImages\\closePhoneWindow.png");
        screen.wait(closePhoneWindow, 10);
        screen.click(closePhoneWindow);
    }

    @Test(priority = 5)
    public void receivePDCall() throws InterruptedException, FindFailed {
        Thread.sleep(2000);
        WebDriverWait waitForButtonAccept = new WebDriverWait(agentChrome, 10);
        waitForButtonAccept.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#btn_preview_accept")));
        WebElement button_Accept = agentChrome.findElement(By.cssSelector("#btn_preview_accept"));
        button_Accept.click();

        answerCallOnClientSide();
        WebDriverWait waitForIncallStatus = new WebDriverWait(agentChrome, 5);
        waitForIncallStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bIncall\\b.*")));

        sleep(3000);
        int size = agentChrome.findElements(By.tagName("iframe")).size();
        System.out.println(size);
        agentChrome.switchTo().frame("TAB_123");
    /*   // WebElement nextForm = agentChrome.findElement(By.xpath("/*//*[@id=\"viewerForm802\"]/table/tbody/tr[2]/td[2]"));
        WebElement nextForm = agentChrome.findElement(By.xpath("/*//*[@id=\"viewerForm802\"]/table/tbody/tr[2]/td[2]"));
        JavaScriptExecutor executor = (JavaScriptExecutor)agentChrome;*/

        WebElement button_nextForm = agentChrome.findElement(By.xpath("//*[@id=\"viewerForm802\"]/table/tbody/tr[2]/td[2]"));
        JavascriptExecutor executor = (JavascriptExecutor) agentChrome;
        executor.executeScript("arguments[0].click();", button_nextForm);

        Thread.sleep(1000);
        button_nextForm.click();
        /*nextForm.click();*/
        new Actions(agentChrome).moveToElement(button_nextForm).click().perform();
        Actions action = new Actions(agentChrome);
        action.clickAndHold(button_nextForm).perform();
        sleep(1);
        action.release().perform();

        WebElement link = agentChrome.findElement(By.xpath("//*[@id=\"viewerForm802\"]/table/tbody/tr[2]/td[2]"));
        String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover'," +
                "true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject)" +
                "{ arguments[0].fireEvent('onmouseover');}";

        String onClickScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('click'," +
                "true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject)" +
                "{ arguments[0].fireEvent('onclick');}";
        JavascriptExecutor js = (JavascriptExecutor) agentChrome;
        js.executeScript(mouseOverScript, link);
        js.executeScript(onClickScript, link);
        // button_nextForm.sendKeys();
        agentChrome.switchTo().defaultContent();
        Thread.sleep(2000);
        WebElement button_Hangup = agentChrome.findElement(By.cssSelector("#btn_hangup"));
        button_Hangup.click();
        agentChrome.switchTo().frame("TAB_123");
        Thread.sleep(1000);
        WebDriverWait waitForResultCode = new WebDriverWait(agentChrome, 5);
        waitForResultCode.until(ExpectedConditions.elementToBeClickable(By.xpath("/*//*[@id = 'resultCodeText']")));
        Thread.sleep(1000);
        WebElement resultCodeList = agentChrome.findElement(By.xpath("/*//*[@id = 'resultCodeText']"));
        resultCodeList.click();
        WebElement resultCodeChoice = agentChrome.findElement(By.xpath("/*//*[@id = 'resultCode598']"));
        resultCodeChoice.click();

        try {
            Thread.sleep(1000);
            button_nextForm = agentChrome.findElement(By.xpath("//*[@id=\"viewerForm802\"]/table/tbody/tr[2]/td[2]"));
            JavascriptExecutor executor2 = (JavascriptExecutor) agentChrome;
            executor.executeScript("switchForm(802,false);", button_nextForm);
        } catch (InvalidSelectorException e) {
            WebElement button_WarningConfirmation = agentChrome.findElement(By.xpath("#maButtonOkey"));
            button_WarningConfirmation.click();
            WebElement date = agentChrome.findElement(By.cssSelector("input[name = cardValues\\[0\\]\\.value] "));
            date.sendKeys("2017-10-25");
            button_nextForm = agentChrome.findElement(By.xpath("//*[@id=\"viewerForm802\"]/table/tbody/tr[2]/td[2]"));
            JavascriptExecutor executor2 = (JavascriptExecutor) agentChrome;
            executor.executeScript("switchForm(802,false);", button_nextForm);
        }
        Thread.sleep(1000);
        WebElement button_Save = agentChrome.findElement(By.cssSelector("#submitButton"));
        button_Save.click();
        //  agentChrome.switchTo().frame("TAB_123");
        // button_nextForm.submit();
      /*  button_nextForm.sendKeys(Keys.ENTER);
        WebElement button_Save = agentChrome.findElement(By.cssSelector("#submitButton"));
        button_Save.click();
        WebDriverWait waitForResultCode = new WebDriverWait(agentChrome, 5);
        waitForResultCode.until(ExpectedConditions.elementToBeClickable(By.xpath("/*//*[@id = 'resultCodeText']")));
        WebElement resultCodeList = agentChrome.findElement(By.xpath("/*//*[@id = 'resultCodeText']"));
        resultCodeList.click();
        WebElement resultCodeChoice = agentChrome.findElement(By.xpath("/*//*[@id = 'resultCode598']"));
        resultCodeChoice.click();
        WebElement button_NextForm = agentChrome.findElement(By.cssSelector("#viewerForm802 > table > tbody > tr:nth-child(2) > td:nth-child(2) > input"));
        button_NextForm.click();
*/


    }


    @Test
    public void controlFlow() throws InterruptedException, SQLException, ClassNotFoundException, FindFailed {
        ssoLoginChrome();
        changeStatusToAvailable();
        switchToADTab();
        //testCRMTab();
        runSQLQuery();
        receivePDCall();
    }

    @AfterClass
    public void teardown() {
        agentChrome.quit();
    }

}