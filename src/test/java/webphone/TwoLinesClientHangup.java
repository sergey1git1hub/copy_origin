package webphone;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

import static data.Data.agentChrome;
import static data.Data.webphoneUrl;
import static org.testng.Assert.assertTrue;
import static helpMethods.HelpMethods.handleLogoutWindow;

public class TwoLinesClientHangup {
    App cxphone;
    Screen screen;
    org.sikuli.script.Pattern button_3CXAcceptCall;

    WebElement phoneNumberField;
    WebElement button_Call;
    WebElement line2;


    @Test(priority = 1)
    public void ssoLoginChrome() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");
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

        cxphone = App.open("C:\\Program Files (x86)\\3CXPhone\\3CXPhone.exe");
        screen = new Screen();
        org.sikuli.script.Pattern button_3CXAcceptCall = new org.sikuli.script.Pattern("C:\\SikuliImages\\button_3CXAcceptCall.png");
        phoneNumberField = agentChrome.findElement(By.cssSelector("#PhoneNumber"));
        phoneNumberField.sendKeys("94949");
        button_Call = agentChrome.findElement(By.cssSelector("#btn_call"));
        button_Call.click();
        answerCallOnClientSideLine1();
        screen.wait(button_3CXAcceptCall, 10);
        screen.click(button_3CXAcceptCall);
        WebDriverWait waitForInCallStatus = new WebDriverWait(agentChrome, 20);
        waitForInCallStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bIncall\\b.*")));

        line2 = agentChrome.findElement(By.cssSelector("#btn_line_2"));
        line2.click();
        WebElement phoneNumberField = agentChrome.findElement(By.cssSelector("#PhoneNumber"));
        phoneNumberField.sendKeys("94948");
        WebDriverWait waitForCallButton = new WebDriverWait(agentChrome, 5);
        waitForCallButton.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#btn_call")));
        button_Call = agentChrome.findElement(By.cssSelector("#btn_call"));
        button_Call.click();

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
        Thread.sleep(1000);

        WebDriverWait waitForResultCode = new WebDriverWait(agentChrome, 5);
        waitForResultCode.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='Удачно']")));
        WebElement resultCode = agentChrome.findElement(By.xpath("//td[text()='Удачно']"));
        resultCode.click();
        Thread.sleep(1000);
        WebElement button_Save = agentChrome.findElement(By.cssSelector("#btn_rslt > span.ui-button-text.ui-c"));
        button_Save.click();
        WebDriverWait waitForAvailableStatus = new WebDriverWait(agentChrome, 10);
        waitForAvailableStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bAvailable\\b.*")));
    }

    public void answerCallOnClientSideLine1() {

    }

    public void answerCallOnClientSideLine2() {
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
