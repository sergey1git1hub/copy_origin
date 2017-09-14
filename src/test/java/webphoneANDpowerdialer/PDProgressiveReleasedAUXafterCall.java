package webphoneANDpowerdialer;

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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

import static data.Data.*;
import static helpMethods.HelpMethods.handleLogoutWindow;

//import java.lang.Object;

public class PDProgressiveReleasedAUXafterCall {
    WebElement running;
    static WebElement button_Hangup;
    static Screen screen;

    @Test()
    public static void ssoLoginChrome() throws InterruptedException {

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
        Thread.sleep(1000);
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
        WebDriverWait waitForTrainingStatus = new WebDriverWait(agentChrome, 15);
        waitForTrainingStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bAvailable\\b.*")));
    }



    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        String userName = "GBWebPhoneTest";
        String password = "yt~k$tCW8%Gj";
        String url = "jdbc:sqlserver://172.21.7.225\\\\corporate;DatabaseName=GBWebPhoneTest;portNumber=1438";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection(url, userName, password);
        return conn;
    }


    public static void updateRecord(Connection con) throws SQLException {
        String query = "UPDATE GBWebPhoneTest.dbo.pd_5220copy SET phone_number_1 = '94949'," +
                " caller_id_1 = null, call_from_time_1 = null, call_till_time_1 = null," +
                " timezone_offset_1 = null, call_from_time = null, call_till_time = null," +
                " timezone_offset = null, start_date = null, answer_date = null, complete_date = null," +
                " called_number = null, telephony_result_code = null, user_result_code = null," +
                " rc_sequence = null, user_id = null, callback_number = null, callback_date = null," +
                " retry_number = null, retry_date = null, retry_total = null, retry_total_macro = null," +
                " ad_card_id = null, is_fetched = null, skill = null WHERE id = 374;";

       // String query = "UPDATE GBWebPhoneTest.dbo.pd_5220copy SET phone_number_1 = '94949' WHERE id = 374;";

        Statement stmt = con.createStatement();
        stmt.execute(query);
    }

    @Test()
    public void loginToPD() throws InterruptedException {
        agentPD = new ChromeDriver();
        agentPD.get(PDUrl);
        Assert.assertEquals(agentPD.getTitle(), "gbpowerdialer");
        Thread.sleep(1000);
        WebElement username = agentPD.findElement(By.cssSelector("#username"));
        username.sendKeys("81016");
        WebElement password = agentPD.findElement(By.cssSelector("#password"));
        password.sendKeys("1");
        WebElement button_SignIn = agentPD.findElement(By.cssSelector("#loginModal > div > div > form > div.modal-footer > button"));
        button_SignIn.click();
    }

    @Test
    public void runPDCampaign() throws InterruptedException {
       // Thread.sleep(3000);

/*        WebDriverWait waitForId = new WebDriverWait(agentPD, 2);
        waitForId.until(ExpectedConditions.elementToBeClickable(By.xpath("/*//*[text() = '257']")));*/
        WebElement columns = agentPD.findElement(By.xpath("//*[@id=\"campaignGrid\"]/div/div[1]"));
        columns.click();
        //Thread.sleep(2000);

        WebElement id = agentPD.findElement(By.xpath("//*[@id=\"menuitem-13\"]/button"));
        id.click();
        //Thread.sleep(3000);
        running = agentPD.findElement(By.xpath("//*[text() = '257']//parent::div//following-sibling::div[3]//div"));


        System.out.println(running.getText());
        if (!running.getText().equals("Running")) {
            WebElement campaignId = agentPD.findElement(By.xpath("//*[text() = '257']"));
            campaignId.click();
            WebElement icon_Enable = agentPD.findElement(By.cssSelector("#navbarCompainList > div > div:nth-child(7) > button"));
            icon_Enable.click();
            WebElement button_Enable = agentPD.findElement(By.cssSelector("#navbarCompainList > div > div.btn-group.open > ul > li:nth-child(1) > a"));
            button_Enable.click();
           // Thread.sleep(1000);
            WebElement button_Start = agentPD.findElement(By.cssSelector("#navbarCompainList > div > button.btn.btn-success.btn-sm.navbar-btn"));
            button_Start.click();
        }
        agentPD.quit();
    }


    @Test(dependsOnMethods = "ssoLoginChrome")
    public static void runSQLQuery() throws SQLException, ClassNotFoundException {
        updateRecord(getConnection());
    }


    @Test(dependsOnMethods = "runSQLQuery")
    public static void waitForCallOnClientSide2() throws FindFailed, InterruptedException {
        App cxphone = App.open("C:\\Program Files (x86)\\3CXPhone\\3CXPhone.exe");
        screen = new Screen();
        org.sikuli.script.Pattern button_3CXAcceptCall = new org.sikuli.script.Pattern("C:\\SikuliImages\\button_3CXAcceptCall.png");
        try{
            screen.wait(button_3CXAcceptCall, 10);
            screen.click(button_3CXAcceptCall);
           // Thread.sleep(1000);
            org.sikuli.script.Pattern closePhoneWindow = new org.sikuli.script.Pattern("C:\\SikuliImages\\closePhoneWindow.png");
            screen.wait(closePhoneWindow, 10);
            screen.click(closePhoneWindow);
           // Thread.sleep(1000);
        } catch(Exception e){}

    }

    @Test(dependsOnMethods = "waitForCallOnClientSide2")
    public static void receiveIncomingCallToAgent() throws InterruptedException {
        WebDriverWait waitForIncallStatus = new WebDriverWait(agentChrome, 5);
        waitForIncallStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bIncall\\b.*")));
        //Thread.sleep(1000);
        WebElement currentStatus = agentChrome.findElement(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"));
        Assert.assertTrue(currentStatus.getText().contains("Incall"));
       // Thread.sleep(2000);
    }

    @Test(dependsOnMethods = "receiveIncomingCallToAgent")
    public static void changeStatusToAUX() throws InterruptedException {
        WebElement currentStatus = agentChrome.findElement(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"));
        currentStatus.click();
        WebElement AUXStatus = agentChrome.findElement(By.xpath(
                "//*[contains(text(),'AUX') and not(contains(text(),'Доступен'))]"));
        AUXStatus.click();
       // Thread.sleep(3000);


    }

    @Test(dependsOnMethods = "changeStatusToAUX")
    public static void agentHangup() throws InterruptedException, FindFailed {

        button_Hangup = agentChrome.findElement(By.cssSelector("#btn_hangup"));
        button_Hangup.click();
        //Thread.sleep(2000);
    }

    @Test(dependsOnMethods = "agentHangup")
    public static void setResultCodeAndCheckAUXStatus() throws InterruptedException, FindFailed {
        WebDriverWait waitForResultCode = new WebDriverWait(agentChrome, 5);
        waitForResultCode.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='Удачно']")));
        WebElement resultCode = agentChrome.findElement(By.xpath("//td[text()='Удачно']"));
        resultCode.click();
        Thread.sleep(1000); //necessary
        WebElement button_Save = agentChrome.findElement(By.cssSelector("#btn_rslt > span.ui-button-text.ui-c"));
        button_Save.click();
        //Thread.sleep(2000);
        WebDriverWait waitForAvailableStatus = new WebDriverWait(agentChrome, 10);
        waitForAvailableStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bAUX\\b.*")));
/*        waitForAvailableStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\AUX\\b.*")));*/
       // Thread.sleep(3000);
    }
    @Test(dependsOnMethods = "setResultCodeAndCheckAUXStatus") //sikuli wait for call 8 sec
    public static void waitForCallOnClientSide() throws FindFailed, InterruptedException {
        App cxphone = App.open("C:\\Program Files (x86)\\3CXPhone\\3CXPhone.exe");
        screen = new Screen();
        org.sikuli.script.Pattern button_3CXAcceptCall = new org.sikuli.script.Pattern("C:\\SikuliImages\\button_3CXAcceptCall.png");
        try{
            screen.wait(button_3CXAcceptCall, 8);
            screen.click(button_3CXAcceptCall);
           // Thread.sleep(1000);
            org.sikuli.script.Pattern closePhoneWindow = new org.sikuli.script.Pattern("C:\\SikuliImages\\closePhoneWindow.png");
            screen.wait(closePhoneWindow, 10);
            screen.click(closePhoneWindow);
           // Thread.sleep(1000);
        } catch(Exception e){}
    }

    @Test(dependsOnMethods = "waitForCallOnClientSide") //agent wait for call 2 sec
    public static void noIncomingCallToAgent() throws InterruptedException, FindFailed {
        try{
            WebDriverWait waitForIncallStatus = new WebDriverWait(agentChrome, 2);
            waitForIncallStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                    "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bIncall\\b.*")));
        } catch(Exception e){}
        //Thread.sleep(1000);
        WebElement currentStatus = agentChrome.findElement(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"));
        Assert.assertFalse(currentStatus.getText().contains("Incall"));
        org.sikuli.script.Pattern closePhoneWindow = new org.sikuli.script.Pattern("C:\\SikuliImages\\closePhoneWindow.png");
        screen.wait(closePhoneWindow, 10);
        screen.click(closePhoneWindow);
    }

    @AfterClass
    public void teardown() {
        agentChrome.quit();
    }
}