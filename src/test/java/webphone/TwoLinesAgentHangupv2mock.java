package webphone;

import methods.MethodsTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

import static data.Data.agentChrome;
import static methods.Methods.driver;
import static org.testng.Assert.assertTrue;

public class TwoLinesAgentHangupv2mock {
    static App cxphone;
    static Screen screen;
    static org.sikuli.script.Pattern button_3CXAcceptCall;
    static org.sikuli.script.Pattern closePhoneWindow;
    org.sikuli.script.Pattern button_3CXHangupCall;


    static WebElement phoneNumberField;
    static WebElement button_Call;
    static WebElement line1;
    static WebElement line2;
    static WebDriverWait waitForInCallStatus;
    static WebElement button_Hangup;

    @Test
    public static void ssoLoginChrome() {
    }

    @Test
    public static void IELogin() throws InterruptedException {
        MethodsTest.IELogin();
    }

    @Test(dependsOnMethods = "IELogin")
    public static void callOnFirstLine() throws InterruptedException, FindFailed {
       /* agentChrome = driver;
        cxphone = App.open("C:\\Program Files (x86)\\3CXPhone\\3CXPhone.exe");
        screen = new Screen();
        button_3CXAcceptCall = new org.sikuli.script.Pattern("C:\\SikuliImages\\button_3CXAcceptCall.png");
        phoneNumberField = agentChrome.findElement(By.cssSelector("#PhoneNumber"));
        phoneNumberField.sendKeys("94949");
        //Thread.sleep(1000);
        button_Call = agentChrome.findElement(By.cssSelector("#btn_call"));
        button_Call.click();
        Thread.sleep(1000);
        answerCallOnClientSideLine1();*/

        agentChrome = driver;
        cxphone = App.open("C:\\Program Files (x86)\\3CXPhone\\3CXPhone.exe");
        screen = new Screen();
        button_3CXAcceptCall = new org.sikuli.script.Pattern("C:\\SikuliImages\\button_3CXAcceptCall.png");
        Thread.sleep(5000);
        phoneNumberField = agentChrome.findElement(By.cssSelector("#PhoneNumber"));
        phoneNumberField.sendKeys("94949");
        // Thread.sleep(1000);
        button_Call = agentChrome.findElement(By.cssSelector("#btn_call"));
        WebDriverWait waitForCallButton = new WebDriverWait(agentChrome, 10);
        waitForCallButton.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#btn_call")));
        button_Call.click();
        // Thread.sleep(1000);
        answerCallOnClientSideLine1();
        screen.wait(button_3CXAcceptCall, 10);
        screen.click(button_3CXAcceptCall);
        Thread.sleep(2000);
        closePhoneWindow = new org.sikuli.script.Pattern("C:\\SikuliImages\\closePhoneWindow.png");
        screen.wait(closePhoneWindow, 10);
        screen.click(closePhoneWindow);
        waitForInCallStatus = new WebDriverWait(agentChrome, 20);
        waitForInCallStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bIncall\\b.*")));
        Thread.sleep(2000);
    }

    @Test(dependsOnMethods = "callOnFirstLine")
    public static void callOnSecondLine() throws InterruptedException, FindFailed {
        line2 = agentChrome.findElement(By.cssSelector("#btn_line_2"));
        /*line2.click();*/
        try {
            if (driver instanceof JavascriptExecutor) {
                ((JavascriptExecutor) driver)
                        .executeScript("wp_common.wp_ChangeLine(2); log(event);");
            }
        } catch(Exception e){}
        WebDriverWait waitForPhoneNumber = new WebDriverWait(agentChrome, 5);
        waitForPhoneNumber.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#PhoneNumber")));
        phoneNumberField = agentChrome.findElement(By.cssSelector("#PhoneNumber"));
        phoneNumberField.sendKeys("94948");
        Thread.sleep(1000);
        WebDriverWait waitForCallButton = new WebDriverWait(agentChrome, 5);
        waitForCallButton.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#btn_call")));
        button_Call = agentChrome.findElement(By.cssSelector("#btn_call"));
        button_Call.click();
        Thread.sleep(1000);
        WebDriverWait waitForOnHoldStatus = new WebDriverWait(agentChrome, 10);
        waitForOnHoldStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bOnhold\\b.*")));
        WebDriverWait waitForLineRinging = new WebDriverWait(agentChrome, 5);
        waitForLineRinging.until(ExpectedConditions.textMatches(By.cssSelector(
                "#btn_line_2"), Pattern.compile(".*\\bRinging\\b.*")));
        line2 = agentChrome.findElement(By.cssSelector("#btn_line_2"));
        System.out.println(line2.getText());
        assertTrue(line2.getText().equals("Ringing"));
        answerCallOnClientSideLine2();
        Thread.sleep(500);
        screen.wait(button_3CXAcceptCall, 10);
        screen.click(button_3CXAcceptCall);
        screen.wait(closePhoneWindow, 10);
        screen.click(closePhoneWindow);
        Thread.sleep(1000);
        waitForInCallStatus = new WebDriverWait(agentChrome, 10);
        waitForInCallStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bIncall\\b.*")));
        Thread.sleep(1000);
    }

    @Test(dependsOnMethods = "callOnSecondLine")
    public static void agentHangupLine1() throws InterruptedException, FindFailed {
        line1 = agentChrome.findElement(By.cssSelector("#btn_line_1"));
        /*line1.click();*/
        try {
            if (driver instanceof JavascriptExecutor) {
                ((JavascriptExecutor) driver)
                        .executeScript("wp_common.wp_ChangeLine(1); log(event);");
            }
        } catch(Exception e){}
        Thread.sleep(1000);
        WebElement button_Hold = agentChrome.findElement(By.cssSelector("#btn_hold"));
        button_Hold.click();
        Thread.sleep(1000);
        button_Hangup = agentChrome.findElement(By.cssSelector("#btn_hangup"));
        WebDriverWait waitForButton_Hangup = new WebDriverWait(agentChrome, 5);
        waitForButton_Hangup.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#btn_hangup")));
        /*button_Hangup.click();*/
        Screen screen = new Screen();
        org.sikuli.script.Pattern button_Hangup = new org.sikuli.script.Pattern("C:\\SikuliImages\\button_Hangup.png");
        screen.wait(button_Hangup, 10);
        screen.click(button_Hangup);
        Thread.sleep(1000);
    }

    @Test(dependsOnMethods = "agentHangupLine1")
    public static void agentHangupLine2() throws InterruptedException, FindFailed {
        line2 = agentChrome.findElement(By.cssSelector("#btn_line_2"));
        //line2.click();
        try {
            if (driver instanceof JavascriptExecutor) {
                ((JavascriptExecutor) driver)
                        .executeScript("wp_common.wp_ChangeLine(2); log(event);");
            }
        } catch(Exception e){}
        Thread.sleep(1000);
        WebDriverWait waitForButton_Hangup = new WebDriverWait(agentChrome, 5);
        waitForButton_Hangup.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#btn_hangup")));
        button_Hangup = agentChrome.findElement(By.cssSelector("#btn_hangup"));
        /*button_Hangup.click();*/
        Screen screen = new Screen();
        org.sikuli.script.Pattern button_Hangup = new org.sikuli.script.Pattern("C:\\SikuliImages\\button_Hangup.png");
        screen.wait(button_Hangup, 10);
        screen.click(button_Hangup);
        Thread.sleep(1000);
    }

    @Test(dependsOnMethods = "agentHangupLine2")
    public static void setResultCodeAndCheckAvailableStatus() throws InterruptedException, FindFailed {
        WebDriverWait waitForResultCode = new WebDriverWait(agentChrome, 5);
        waitForResultCode.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='Удачно']")));
        WebElement resultCode = agentChrome.findElement(By.xpath("//td[text()='Удачно']"));
        resultCode.click();
        Thread.sleep(1000);
        WebElement button_Save = agentChrome.findElement(By.cssSelector("#btn_rslt > span.ui-button-text.ui-c"));
        button_Save.click();
        Thread.sleep(1000);
        WebDriverWait waitForAvailableStatus = new WebDriverWait(agentChrome, 10);
        waitForAvailableStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bAvailable\\b.*|.*\\bFinished\\b.*|.*\\bWrapup\\b.*")));
/*        waitForAvailableStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bAvailable\\b.*")));*/
        Thread.sleep(2000);
        line1 = agentChrome.findElement(By.cssSelector("#btn_line_1"));
        /*line1.click();*/
        try {
            if (driver instanceof JavascriptExecutor) {
                ((JavascriptExecutor) driver)
                        .executeScript("wp_common.wp_ChangeLine(1); log(event);");
            }
        } catch(Exception e){}
    }

    @AfterClass
    public void teardown() {
        agentChrome.quit();
    }

    public static void answerCallOnClientSideLine1() {

    }

    public static void answerCallOnClientSideLine2() {
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


}

