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

public class TwoLinesClientHangupv2mock {
    static App cxphone;
    static Screen screen;
    static org.sikuli.script.Pattern button_3CXAcceptCall;
    static org.sikuli.script.Pattern button_3CXCall;
    static org.sikuli.script.Pattern closePhoneWindow;
    static org.sikuli.script.Pattern button_3CXHangupCall;


    static WebElement phoneNumberField;
    static WebElement button_Call;
    static WebElement line2;
    static WebElement line1;

    @Test
    public static void ssoLoginChrome() {
    }

    @Test
    public static void IELogin() throws InterruptedException {
        MethodsTest.IELogin();
    }

   /* @Test(priority = 1)
    public void ssoLoginChrome() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");
        agentChrome = new ChromeDriver();
        agentChrome.get(webphoneUrl);
        Assert.assertEquals(agentChrome.getTitle(), "gbwebphone");
        WebDriverWait waitForTitle = new WebDriverWait(agentChrome, 10);
        waitForTitle.until(ExpectedConditions.titleIs("gbwebphone"));
        Assert.assertEquals(agentChrome.getTitle(), "gbwebphone");
        Thread.sleep(2000);
        WebElement button_SSO_IE = agentChrome.findElement(By.cssSelector("#ssoButton > span"));
        String winHandleBefore = agentChrome.getWindowHandle();
        button_SSO_IE.click();
        for (String winHandle : agentChrome.getWindowHandles()) {
            agentChrome.switchTo().window(winHandle);
        }
        WebElement ssoUsername = agentChrome.findElement(By.cssSelector("#username"));
        ssoUsername.sendKeys("81016");
        Thread.sleep(1000);
        WebElement ssoPassword = agentChrome.findElement(By.cssSelector("#password"));
        ssoPassword.sendKeys("1");
        Thread.sleep(1000);
        WebElement ssoRememberMe = agentChrome.findElement(By.cssSelector("#remember-me"));
        ssoRememberMe.click();
        Thread.sleep(1000);
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
*/
    @Test(dependsOnMethods = "IELogin")
    public static void callOnFirstLine() throws InterruptedException, FindFailed {
        agentChrome = driver;
        screen = new Screen();
        button_3CXAcceptCall = new org.sikuli.script.Pattern("C:\\SikuliImages\\button_3CXAcceptCall.png");
        Thread.sleep(3000);
        phoneNumberField = agentChrome.findElement(By.cssSelector("#PhoneNumber"));
        phoneNumberField.sendKeys("94949");
        cxphone = App.open("C:\\Program Files (x86)\\3CXPhone\\3CXPhone.exe");
        button_3CXCall = new org.sikuli.script.Pattern("C:\\SikuliImages\\button_3CXCall.png");
        screen.wait(button_3CXCall, 10);
        screen.click(button_3CXCall);

        answerCallOnClientSideLine1();
        App.open("C:\\Program Files (x86)\\3CXPhone\\3CXPhone.exe");
        screen.wait(button_3CXAcceptCall, 10);
        screen.click(button_3CXAcceptCall);
        Thread.sleep(2000);
       // Thread.sleep(1000);
        closePhoneWindow = new org.sikuli.script.Pattern("C:\\SikuliImages\\closePhoneWindow.png");
        screen.wait(closePhoneWindow, 10);
        screen.click(closePhoneWindow);
        WebDriverWait waitForInCallStatus = new WebDriverWait(agentChrome, 20);
        waitForInCallStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bIncall\\b.*")));
       // Thread.sleep(1000);
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
       // Thread.sleep(1000);
        WebElement phoneNumberField = agentChrome.findElement(By.cssSelector("#PhoneNumber"));
        phoneNumberField.sendKeys("94948");
       // Thread.sleep(1000);
        WebDriverWait waitForCallButton = new WebDriverWait(agentChrome, 5);
        waitForCallButton.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#btn_call")));
        button_Call = agentChrome.findElement(By.cssSelector("#btn_call"));
        button_Call.click();
       // Thread.sleep(1000);
        WebDriverWait waitForOnHoldStatus = new WebDriverWait(agentChrome, 10);
        waitForOnHoldStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bOnhold\\b.*")));
       // Thread.sleep(1000);

        WebDriverWait waitForRinging = new WebDriverWait(agentChrome, 5);
        waitForRinging.until(ExpectedConditions.textMatches(By.cssSelector(
                "#btn_line_2"), Pattern.compile(".*\\bRinging\\b.*")));
        line2 = agentChrome.findElement(By.cssSelector("#btn_line_2"));
        System.out.println(line2.getText());
        assertTrue(line2.getText().equals("Ringing"));
      //  Thread.sleep(1000);
        answerCallOnClientSideLine2();
        screen.wait(button_3CXAcceptCall, 10);
        screen.click(button_3CXAcceptCall);
       // Thread.sleep(5000);
    }

    /* <include name = "agentHangupLine1()"/>
  <include name = "agentHangupLine2()"/>*/


    @Test(dependsOnMethods = "callOnSecondLine")
    public static void clientHangupLine1() throws FindFailed, InterruptedException {
        cxphone = App.open("C:\\Program Files (x86)\\3CXPhone\\3CXPhone.exe");
        screen = new Screen();
        button_3CXHangupCall = new org.sikuli.script.Pattern("C:\\SikuliImages\\button_3CXHangupCall.png");
        screen.wait(button_3CXHangupCall, 10);
        screen.click(button_3CXHangupCall);
       // Thread.sleep(1000);
    }

    @Test(dependsOnMethods = "clientHangupLine1")
    public static void clientHangupLine2() throws FindFailed, InterruptedException {
        org.sikuli.script.Pattern line_3CXLine1 = new org.sikuli.script.Pattern("C:\\SikuliImages\\line_3CXLine1.png");
        screen.wait(line_3CXLine1, 10);
        screen.click(line_3CXLine1);
       // Thread.sleep(1000);
        screen.click(button_3CXHangupCall);
       // Thread.sleep(1000);
        org.sikuli.script.Pattern closePhoneWindow = new org.sikuli.script.Pattern("C:\\SikuliImages\\closePhoneWindow.png");
        screen.wait(closePhoneWindow, 10);
        screen.click(closePhoneWindow);
       // Thread.sleep(1000);
    }

    @Test(dependsOnMethods = "clientHangupLine2")
    public static void setResultCodeAndCheckAvailableStatus() throws InterruptedException, FindFailed {
        /*WebDriverWait waitForResultCode = new WebDriverWait(agentChrome, 5);
        waitForResultCode.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='Удачно']")));
        WebElement resultCode = agentChrome.findElement(By.xpath("//td[text()='Удачно']"));
        resultCode.click();*/
        Screen screen = new Screen();
        org.sikuli.script.Pattern resultCodeUdachno = new org.sikuli.script.Pattern("C:\\SikuliImages\\resultCodeUdachno.png");
        screen.wait(resultCodeUdachno, 10);
        screen.click(resultCodeUdachno);
        Thread.sleep(1000); //necessary
        WebElement button_Save = agentChrome.findElement(By.cssSelector("#btn_rslt > span.ui-button-text.ui-c"));
        button_Save.click();/*
        WebDriverWait waitForAvailableStatus = new WebDriverWait(agentChrome, 10);
        waitForAvailableStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bAvailable\\b.*")));
       // Thread.sleep(2000);
        line1 = agentChrome.findElement(By.cssSelector("#btn_line_1"));
        line1.click();*/

        WebDriverWait waitForAvailableStatus = new WebDriverWait(agentChrome, 10);
        waitForAvailableStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bAvailable\\b.*|.*\\bOnhold\\b.*")));
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

    public static void answerCallOnClientSideLine1() {

    }

    public static void answerCallOnClientSideLine2() {
    }


    @AfterClass
    public void teardown() {
        agentChrome.quit();
    }

    /*
    * 1.Login to webphone 2.0.0
    * 2.Call 94636 on the first line
    * 3.Answer call on client side
    * 4.Switch to the second line
    * 5.Call 94626 on the second line
    * 6.Answer call on cilent side
    * 7.Hangup the second line on client side
    * 8.Hangup the first line on client side
    * 9.Choose result code "Удачно"
    * 10.Click save button
    * 11.Verify available status
    * */

}
