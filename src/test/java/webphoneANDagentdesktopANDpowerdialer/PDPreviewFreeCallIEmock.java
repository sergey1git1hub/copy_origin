package webphoneANDagentdesktopANDpowerdialer;

import methods.MethodsTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

import static data.Data.agentChrome;
import static methods.Methods.driver;
import static org.openqa.selenium.By.cssSelector;

//import java.lang.Object;

public class PDPreviewFreeCallIEmock {
    static String phoneNumber = "94949";
    static String webphoneUrl = "http://172.21.24.109/gbwebphone/";

    WebElement button_nextForm;
    @Test
    public static void ssoLoginChrome() {
    }


    @Test
    public static void IELoginAD() throws InterruptedException {
        MethodsTest.IELoginAD();
    }


    /*@Test()
    public static void ssoLoginChrome() throws InterruptedException {


        System.setProperty("webdriver.ie.driver", "C:/iedriver/IEDriverServer.exe");
        DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
        ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
                true);
        agentChrome = new InternetExplorerDriver(ieCapabilities);
        agentChrome.get(webphoneUrl);
        WebDriverWait waitForTitle = new WebDriverWait(agentChrome, 10);
        waitForTitle.until(ExpectedConditions.titleIs("gbwebphone"));
        Assert.assertEquals(agentChrome.getTitle(), "gbwebphone");
        WebElement language = agentChrome.findElement(By.cssSelector("#lang_input_label"));
        language.click();
        WebElement language_en = agentChrome.findElement(By.xpath("//li[text() = 'English']"));
        language_en.click();
        WebElement button_SSO_IE = agentChrome.findElement(By.cssSelector("#ssoButton > span"));
        String winHandleBefore = agentChrome.getWindowHandle();
        button_SSO_IE.click();
        //delete if problems

        *//*try {
            for (String winHandle : agentChrome.getWindowHandles()) {
                agentChrome.switchTo().window(winHandle);
            }
            WebDriverWait waitForSSOusername = new WebDriverWait(agentChrome, 1);
            waitForSSOusername.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#username")));
            WebElement ssoUsername = agentChrome.findElement(By.cssSelector("#username"));
            ssoUsername.sendKeys("81016");
            WebElement ssoPassword = agentChrome.findElement(By.cssSelector("#password"));
            ssoPassword.sendKeys("1");
            WebElement ssoRememberMe = agentChrome.findElement(By.cssSelector("#remember-me"));
            ssoRememberMe.click();
            WebElement button_sso_Login = agentChrome.findElement(By.cssSelector("#login_button"));
            button_sso_Login.click();
            agentChrome.switchTo().window(winHandleBefore);
        } catch (Exception e) {
        }*//*
        //delete if problems
        try {
            WebDriverWait waitForLogoutWindow = new WebDriverWait(agentChrome, 5);
            waitForLogoutWindow.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("#userLogoutForm\\3a btn_userlogout_yes > span.ui-button-text.ui-c")));
            WebElement button_Yes = agentChrome.findElement(By.cssSelector("#userLogoutForm\\3a btn_userlogout_yes > span.ui-button-text.ui-c"));
            button_Yes.click();
        } catch (ElementNotVisibleException e) {
            System.out.println(e);

        } catch (TimeoutException e) {
        }

        WebDriverWait waitForButtonContinue = new WebDriverWait(agentChrome, 10);
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
*/
 /*   @Test(dependsOnMethods = "IELoginAD")
    public static void changeStatusToAvailable() {
        agentChrome = driver;
        WebElement currentStatus = agentChrome.findElement(cssSelector(
                "#statusButton > span.ui-button-text.ui-c"));
        currentStatus.click();
        WebElement availableStatus = agentChrome.findElement(By.xpath(
                "/*//*[contains(text(),'Available')]"));
        availableStatus.click();
        WebDriverWait waitForAvailableStatus = new WebDriverWait(agentChrome, 15);
        waitForAvailableStatus.until(ExpectedConditions.textMatches(cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bAvailable\\b.*")));
    }*/

    @Test(dependsOnMethods = "IELoginAD")
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
        agentChrome = driver;
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
    public static void switchToADTab() throws FindFailed {
        try{
        WebElement adTab = agentChrome.findElement(By.xpath("//a[@href = '#tabView:tab123']"));
        adTab.click();
        }catch(Exception e){
        Screen screen = new Screen();
        org.sikuli.script.Pattern ADTab = new org.sikuli.script.Pattern("C:\\SikuliImages\\ADTab.png");
        screen.wait(ADTab, 10);
        screen.click(ADTab);
        }
    }


    @Test
    public void testCRMTab() throws InterruptedException {
        WebElement adTab = agentChrome.findElement(By.xpath("//a[@href = '#tabView:tab123']"));
        adTab.click();
        agentChrome.switchTo().frame(agentChrome.findElement(By.id("TAB_123")));
        WebElement administrationMenu = agentChrome.findElement(By.xpath("//*[text()='Administration']"));
        administrationMenu.click();
        agentChrome.switchTo().defaultContent();

    }


    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        String userName = "GBWebPhoneTest";
        String password = "yt~k$tCW8%Gj";
        String url = "jdbc:sqlserver://172.21.7.225\\\\corporate;DatabaseName=GBWebPhoneTest;portNumber=1438";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection(url, userName, password);
        return conn;
    }

    public static void updateRecord(Connection con, String phoneNumber) throws SQLException {
   /*     String query = "UPDATE GBWebPhoneTest.dbo.pd_5009_3 SET phone_number_1 = '" + phoneNumber + "'," +
                " caller_id_1 = null, call_from_time_1 = null, call_till_time_1 = null," +
                " timezone_offset_1 = null, call_from_time = null, call_till_time = null," +
                " timezone_offset = null, start_date = null, answer_date = null," +
                " complete_date = null, called_number = null, telephony_result_code = null," +
                " user_result_code = null, rc_sequence = null, user_id = null, " +
                "callback_number = null, callback_date = null, retry_number = null, " +
                "retry_date = null, retry_total = null, retry_total_macro = null, ad_card_id = null," +
                " is_fetched = null, caller_id_4 = null, phone_number_4 = null," +
                " call_from_time_4 = null, call_till_time_4 = null, timezone_offset_4 = null" +
                " WHERE id = 135;";*/

        String query = "INSERT INTO GBWebPhoneTest.dbo.pd_5009_3 (phone_number_1," +
                " caller_id_1, call_from_time_1, call_till_time_1, timezone_offset_1, call_from_time," +
                " call_till_time, timezone_offset, start_date, answer_date, complete_date, called_number, telephony_result_code," +
                " user_result_code, rc_sequence, user_id, callback_number, callback_date, retry_number, retry_date," +
                " retry_total, retry_total_macro, ad_card_id, is_fetched, caller_id_4, phone_number_4, call_from_time_4," +
                " call_till_time_4, timezone_offset_4) VALUES ('94949', null, null, null, null, null, null, null," +
                " null, null, null, 'null', null, null, null, null, null, null, null," +
                " null, null, null, null, null, null, null, null, null, null);";

        Statement stmt = con.createStatement();
        stmt.execute(query);
    }

    @Test(dependsOnMethods = "switchToADTab")
    public static void runSQLQuery() throws SQLException, ClassNotFoundException {
        updateRecord(getConnection(), phoneNumber);
    }


    @Test(dependsOnMethods = "runSQLQuery")
    public static void agentAcceptCall() throws InterruptedException, FindFailed {
     /*   try{
        Thread.sleep(2000);
        WebElement mlCampaign =  agentChrome.findElement(By.cssSelector("[value='MLytvynovTest']"));
        mlCampaign.click();
        } catch(Exception e){}*/

        /*WebDriverWait waitForCardSelection = new WebDriverWait(agentChrome, 20);
        waitForCardSelection.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[value='MLytvynovTest']")));
        WebElement mlCampaign =  agentChrome.findElement(By.cssSelector("[value='MLytvynovTest']"));
        mlCampaign.click();*/

        Screen screen = new Screen();
        org.sikuli.script.Pattern mltest = new org.sikuli.script.Pattern("C:\\SikuliImages\\mltest.png");
        screen.wait(mltest, 30);
        screen.click(mltest);

        org.sikuli.script.Pattern button_OK = new org.sikuli.script.Pattern("C:\\SikuliImages\\button_OK.png");
        screen.wait(button_OK, 10);
        screen.click(button_OK);

        try{
        WebDriverWait waitForButtonAccept = new WebDriverWait(agentChrome, 20);
        waitForButtonAccept.until(ExpectedConditions.elementToBeClickable(cssSelector("#btn_preview_accept")));
        WebElement button_Accept = agentChrome.findElement(cssSelector("#btn_preview_accept"));
        button_Accept.click();
        } catch(Exception e){
            webphoneANDpowerdialer.PDProgressiveReleasedAUXagentHangup.loginToPD();
            webphoneANDpowerdialer.PDProgressiveReleasedAUXagentHangup.runPDCampaign();
            WebDriverWait waitForButtonAccept = new WebDriverWait(agentChrome, 10);
            waitForButtonAccept.until(ExpectedConditions.elementToBeClickable(cssSelector("#btn_preview_accept")));
            WebElement button_Accept = agentChrome.findElement(cssSelector("#btn_preview_accept"));
            button_Accept.click();
        }
    }



    @Test(dependsOnMethods = "agentAcceptCall")
    public static void answerCallOnClientSide() throws FindFailed, InterruptedException {
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

    @Test(dependsOnMethods = "answerCallOnClientSide")
    public static void saveCRMCard() throws InterruptedException, FindFailed {
        Thread.sleep(2000);
        WebDriverWait waitForIncallStatus = new WebDriverWait(agentChrome, 5);
        waitForIncallStatus.until(ExpectedConditions.textMatches(cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bIncall\\b.*")));

        Screen screen = new Screen();
        org.sikuli.script.Pattern button_nextForm = new org.sikuli.script.Pattern("C:\\SikuliImages\\button_nextForm.png");
        screen.wait(button_nextForm, 10);
        screen.click(button_nextForm);

        org.sikuli.script.Pattern button_save = new org.sikuli.script.Pattern("C:\\SikuliImages\\button_save.png");
        screen.wait(button_save, 10);
        Thread.sleep(5000);
        screen.click(button_save);

        org.sikuli.script.Pattern selectResultCode = new org.sikuli.script.Pattern("C:\\SikuliImages\\selectResultCode.png");
        screen.wait(selectResultCode, 10);
        screen.click(selectResultCode);

        org.sikuli.script.Pattern callLater = new org.sikuli.script.Pattern("C:\\SikuliImages\\callLater.png");
        screen.wait(callLater, 10);
        screen.click(callLater);
        Thread.sleep(2000);
        screen.wait(button_save, 10);
        screen.click(button_save);
    }

    @Test(dependsOnMethods = "saveCRMCard")
    public static void checkAvailableStatus() throws InterruptedException {
        WebDriverWait waitForAvailableStatus = new WebDriverWait(agentChrome, 7);
        waitForAvailableStatus.until(ExpectedConditions.textMatches(cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bAvailable\\b.*")));

    }


    @AfterClass
    public void teardown() {
        agentChrome.quit();
    }

}