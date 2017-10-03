package webphoneANDpowerdialer;

import methods.MethodsTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
import static methods.Methods.driver;


//import java.lang.Object;

public class PDProgressiveReleasedAUXagentHangupmock {
    static WebElement running;
    static WebElement button_Hangup;

 /*   @Test
    public static void ssoLoginChrome() {
    }*/

    @Test
    public static void IELogin() throws InterruptedException {
        MethodsTest.IELogin();
    }

    @Test(dependsOnMethods = "IELogin")
    public static void changeStatusToAUX() {
        agentChrome = driver;
        WebElement currentStatus = agentChrome.findElement(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"));
        currentStatus.click();
        WebElement AUXStatus = agentChrome.findElement(By.xpath(
                "//*[contains(text(),'AUX') and not(contains(text(),'Доступен'))]"));
        AUXStatus.click();
        WebDriverWait waitForAUXStatus = new WebDriverWait(agentChrome, 5);
        waitForAUXStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bAUX\\b.*")));
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
        String query = "INSERT INTO GBWebPhoneTest.dbo.pd_5220copy (phone_number_1, caller_id_1, " +
                "call_from_time_1, call_till_time_1, timezone_offset_1, call_from_time, call_till_time," +
                " timezone_offset, start_date, answer_date, complete_date, called_number, telephony_result_code," +
                " user_result_code, rc_sequence, user_id, callback_number, callback_date, retry_number," +
                " retry_date, retry_total, retry_total_macro, ad_card_id, is_fetched, skill) VALUES" +
                " ('94949', null, null, null, null, null, null, null, null, null, null," +
                " null, null, null, null, null, null, null, null, null, null, null, null, null, null);";

       // String query = "UPDATE GBWebPhoneTest.dbo.pd_5220copy SET phone_number_1 = '94949' WHERE id = 374;";

        Statement stmt = con.createStatement();
        stmt.execute(query);
    }
    @Test()
    public static void loginToPD() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");
        agentPD = new ChromeDriver();
        agentPD.get(PDUrl);
        Thread.sleep(1000);
        System.out.println(agentPD.getTitle());
 /*       if(agentPD.getTitle() == "Доступ запрещён!"){
            System.out.println("Inside if");
            agentPD.navigate().refresh();
        }*/
        agentPD.navigate().refresh();
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
    public static void runPDCampaign() throws InterruptedException {
        Thread.sleep(3000);

/*        WebDriverWait waitForId = new WebDriverWait(agentPD, 2);
        waitForId.until(ExpectedConditions.elementToBeClickable(By.xpath("/*//*[text() = '257']")));*/
        WebElement columns = agentPD.findElement(By.xpath("//*[@id=\"campaignGrid\"]/div/div[1]"));
        columns.click();
        Thread.sleep(2000);

        WebElement id = agentPD.findElement(By.xpath("//*[@id=\"menuitem-13\"]/button"));
        id.click();
        Thread.sleep(3000);
        running = agentPD.findElement(By.xpath("//*[text() = '257']//parent::div//following-sibling::div[3]//div"));


        System.out.println(running.getText());
        if (!running.getText().equals("Running")) {
            WebElement campaignId = agentPD.findElement(By.xpath("//*[text() = '257']"));
            campaignId.click();
            WebElement icon_Enable = agentPD.findElement(By.cssSelector("#navbarCompainList > div > div:nth-child(7) > button"));
            icon_Enable.click();
            WebElement button_Enable = agentPD.findElement(By.cssSelector("#navbarCompainList > div > div.btn-group.open > ul > li:nth-child(1) > a"));
            button_Enable.click();
            Thread.sleep(1000);
            WebElement button_Start = agentPD.findElement(By.cssSelector("#navbarCompainList > div > button.btn.btn-success.btn-sm.navbar-btn"));
            button_Start.click();
        }
        agentPD.quit();
    }


    @Test(dependsOnMethods = "changeStatusToAUX")
    public static void runSQLQuery() throws SQLException, ClassNotFoundException {
        updateRecord(getConnection());
    }

    @Test(dependsOnMethods = "runSQLQuery") //sikuli wait for call 8 sec
    public static void waitForCallOnClientSide() throws FindFailed, InterruptedException {
        App cxphone = App.open("C:\\Program Files (x86)\\3CXPhone\\3CXPhone.exe");
        Screen screen = new Screen();
        org.sikuli.script.Pattern button_3CXAcceptCall = new org.sikuli.script.Pattern("C:\\SikuliImages\\button_3CXAcceptCall.png");
        try{
            screen.wait(button_3CXAcceptCall, 8);
            screen.click(button_3CXAcceptCall);
            Thread.sleep(1000);
            org.sikuli.script.Pattern closePhoneWindow = new org.sikuli.script.Pattern("C:\\SikuliImages\\closePhoneWindow.png");
            screen.wait(closePhoneWindow, 10);
            screen.click(closePhoneWindow);
            Thread.sleep(1000);
        } catch(Exception e){}

    }

    @Test(dependsOnMethods = "waitForCallOnClientSide") //agent wait for call 2 sec
    public static void noIncomingCallToAgent() throws InterruptedException {
        try{
            WebDriverWait waitForIncallStatus = new WebDriverWait(agentChrome, 2);
            waitForIncallStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                    "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bIncall\\b.*")));
        } catch(Exception e){}
        //Thread.sleep(1000);
        WebElement currentStatus = agentChrome.findElement(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"));
        Assert.assertFalse(currentStatus.getText().contains("Incall"));
    }

    @Test(dependsOnMethods = "noIncomingCallToAgent")
    public static void changeStatusToAvailable() throws InterruptedException, FindFailed {
     /*   WebElement currentStatus = agentChrome.findElement(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"));
        Thread.sleep(200);
        currentStatus.click();

        String keysPressed =  Keys.chord(Keys.CONTROL, Keys.ENTER);*/
       /* currentStatus.sendKeys(keysPressed) ;*/

       /* WebElement availableStatus = agentChrome.findElement(By.xpath(
                "/*//*[contains(text(),'Available')]"));*/
        /*availableStatus.click();*/
       /* availableStatus.sendKeys(keysPressed);*/
   /*     try {
            if (driver instanceof JavascriptExecutor) {
                ((JavascriptExecutor) driver)
                        .executeScript("checkSelectedStatus();PrimeFaces.ab({source:'statusButton'});return false;");
            }
        } catch(Exception e){}*/

        Screen screen = new Screen();
        org.sikuli.script.Pattern currentStatus = new org.sikuli.script.Pattern("C:\\SikuliImages\\currentStatus.png");
            screen.wait(currentStatus, 10);
            screen.click(currentStatus);
            Thread.sleep(1000);
            org.sikuli.script.Pattern availableStatus = new org.sikuli.script.Pattern("C:\\SikuliImages\\availableStatus.png");
            screen.wait(availableStatus, 10);
            screen.click(availableStatus);
            Thread.sleep(3000);
        WebDriverWait waitForAvailableStatus = new WebDriverWait(agentChrome, 5);
        waitForAvailableStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bAvailable\\b.*")));
    }

    @Test(dependsOnMethods = "changeStatusToAvailable")
    public static void waitForCallOnClientSide2() throws FindFailed, InterruptedException {
        App cxphone = App.open("C:\\Program Files (x86)\\3CXPhone\\3CXPhone.exe");
        Screen screen = new Screen();
        org.sikuli.script.Pattern button_3CXAcceptCall = new org.sikuli.script.Pattern("C:\\SikuliImages\\button_3CXAcceptCall.png");
        try{
            screen.wait(button_3CXAcceptCall, 50);
            screen.click(button_3CXAcceptCall);
            Thread.sleep(1000);
            org.sikuli.script.Pattern closePhoneWindow = new org.sikuli.script.Pattern("C:\\SikuliImages\\closePhoneWindow.png");
            screen.wait(closePhoneWindow, 10);
            screen.click(closePhoneWindow);
            Thread.sleep(3000);
        } catch(Exception e){
            PDProgressiveReleasedAUXagentHangupmock.loginToPD();
            PDProgressiveReleasedAUXagentHangupmock.runPDCampaign();
            PDProgressiveReleasedAUXagentHangupmock.changeStatusToAUX();
            PDProgressiveReleasedAUXagentHangupmock.waitForCallOnClientSide();
            PDProgressiveReleasedAUXagentHangupmock.noIncomingCallToAgent();
            PDProgressiveReleasedAUXagentHangupmock.changeStatusToAvailable();
            screen.wait(button_3CXAcceptCall, 10);
            screen.click(button_3CXAcceptCall);
            Thread.sleep(1000);
            org.sikuli.script.Pattern closePhoneWindow = new org.sikuli.script.Pattern("C:\\SikuliImages\\closePhoneWindow.png");
            screen.wait(closePhoneWindow, 10);
            screen.click(closePhoneWindow);
            Thread.sleep(3000);
        }

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
        Thread.sleep(2000);
    }

    @Test(dependsOnMethods = "receiveIncomingCallToAgent")
    public static void agentHangup() throws InterruptedException, FindFailed {
        Screen screen = new Screen();
        org.sikuli.script.Pattern button_Hangup = new org.sikuli.script.Pattern("C:\\SikuliImages\\button_Hangup.png");
        screen.wait(button_Hangup, 10);
        screen.click(button_Hangup);
        /*button_Hangup = agentChrome.findElement(By.cssSelector("#btn_hangup"));
        button_Hangup.click();*/
        Thread.sleep(1000);
    }

  /*  @Test(dependsOnMethods = "agentHangup")
    public static void setResultCodeAndCheckAvailableStatus() throws InterruptedException, FindFailed {
        WebDriverWait waitForResultCode = new WebDriverWait(agentChrome, 10);
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
*//*        waitForAvailableStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bAvailable\\b.*")));*//*
        Thread.sleep(2000);
    }*/

    @Test(dependsOnMethods = "agentHangup")
    public static void setResultCodeAndCheckAvailableStatus() throws InterruptedException, FindFailed {
        Screen screen = new Screen();
        org.sikuli.script.Pattern resultCodeUdachno = new org.sikuli.script.Pattern("C:\\SikuliImages\\resultCodeUdachno.png");
        screen.wait(resultCodeUdachno, 10);
        screen.click(resultCodeUdachno);
        /*WebDriverWait waitForResultCode = new WebDriverWait(agentChrome, 5);
        waitForResultCode.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='Удачно']")));
        WebElement resultCode = agentChrome.findElement(By.xpath("//td[text()='Удачно']"));
        resultCode.click();*/
        Thread.sleep(1000);
        WebElement button_Save = agentChrome.findElement(By.cssSelector("#btn_rslt > span.ui-button-text.ui-c"));
        button_Save.click();
        Thread.sleep(1000);
        WebDriverWait waitForAvailableStatus = new WebDriverWait(agentChrome, 10);
        waitForAvailableStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bAvailable\\b.*|.*\\bOnhold\\b.*")));
/*        waitForAvailableStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bAvailable\\b.*")));*/
        Thread.sleep(2000);
        //line1 = agentChrome.findElement(By.cssSelector("#btn_line_1"));
        /*line1.click();*/
        try {
            if (driver instanceof JavascriptExecutor) {
                ((JavascriptExecutor) driver)
                        .executeScript("wp_common.wp_ChangeLine(1); log(event);");
            }
        } catch(Exception e){}
    }


    /* @Test
    public void runSQLQuery() throws SQLException, ClassNotFoundException {
        updateRecord(getConnection());
    }
*/
/*    @Test(dependsOnMethods = "runSQLQuery")
    public void answerCallOnClientSide() throws FindFailed, InterruptedException {
        Thread.sleep(2000);
        App cxphone = App.open("C:\\Program Files (x86)\\3CXPhone\\3CXPhone.exe");
        Screen screen = new Screen();
        org.sikuli.script.Pattern button_3CXAcceptCall = new org.sikuli.script.Pattern("C:\\SikuliImages\\button_3CXAcceptCall.png");
        try{
        screen.wait(button_3CXAcceptCall, 10);
        } catch(Exception e){
            loginToPD();
            runPDCampaign();
            screen.wait(button_3CXAcceptCall, 10);
        }

        screen.click(button_3CXAcceptCall);
        Thread.sleep(1000);
        org.sikuli.script.Pattern closePhoneWindow = new org.sikuli.script.Pattern("C:\\SikuliImages\\closePhoneWindow.png");
        screen.wait(closePhoneWindow, 10);
        screen.click(closePhoneWindow);
        Thread.sleep(3000);
    }
    */
    @AfterClass
    public void teardown() {
        agentChrome.quit();
    }
}