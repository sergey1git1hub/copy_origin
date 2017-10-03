package webphoneANDagentdesktopANDpowerdialer;

import methods.MethodsTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

import static data.Data.agentChrome;
import static methods.Methods.driver;

//import java.lang.Object;

public class PDPreviewFreeAUXsubtestsmock /*implements IRetryAnalyzer*/ {
    private int retryCount = 0;
    private int maxRetryCount = 1;
    static String phoneNumber = "94949";
    static String webphoneUrl = "http://172.21.24.109/gbwebphone/";

   /* public boolean retry(ITestResult result) {

        if (retryCount < maxRetryCount) {
            retryCount++;
            return true;
        }
        return false;
    }
*/
    WebElement button_nextForm;
    @Test(/*retryAnalyzer = PDPreviewFreeAUXsubtestsmock.class*/)
    public static void ssoLoginChrome() {
    }


    @Test(/*retryAnalyzer = PDPreviewFreeAUXsubtestsmock.class*/)
    public static void IELoginAD() throws InterruptedException {
        MethodsTest.IELoginAD();
    }

  /*  @Test()
    public static void ssoLoginChrome() throws InterruptedException {

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
        WebElement chatGroup = agentChrome.findElement(By.cssSelector("[data-label=pasha_G_5_copy_preview]"));
        chatGroup.click();
        WebElement btnContinue = agentChrome.findElement(By.cssSelector("#btn_continue > span.ui-button-text.ui-c"));
        btnContinue.click();
        WebElement currentStatus = agentChrome.findElement(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"));
        WebDriverWait waitForAvailableStatus = new WebDriverWait(agentChrome, 15);
        waitForAvailableStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bТренинг\\b.*")));
    }
*/

    @Test(dependsOnMethods = "IELoginAD")
    public static void changeStatusToAUX() throws InterruptedException, FindFailed {

        agentChrome = driver;
        Screen screen = new Screen();
        org.sikuli.script.Pattern currentStatus = new org.sikuli.script.Pattern("C:\\SikuliImages\\currentStatus.png");
        screen.wait(currentStatus, 10);
        //Thread.sleep(2000);
        screen.click(currentStatus);
        Thread.sleep(1000);
        org.sikuli.script.Pattern auxStatus = new org.sikuli.script.Pattern("C:\\SikuliImages\\auxStatus.png");
        screen.wait(auxStatus, 10);
        screen.click(auxStatus);
        Thread.sleep(1000);
        WebDriverWait waitForAuxStatus = new WebDriverWait(agentChrome, 5);
        waitForAuxStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bAUX\\b.*")));
    }
   /* @Test(dependsOnMethods = "IELoginAD"*//*, retryAnalyzer = PDPreviewFreeAUXsubtestsmock.class*//*)
    public static void changeStatusToAUX() throws InterruptedException {
        agentChrome = driver;
        WebElement currentStatus = agentChrome.findElement(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"));
        currentStatus.click();
        Thread.sleep(2000);

        WebElement AUXStatus = agentChrome.findElement(By.xpath(
                "/*//*[contains(text(),'AUX')]"));
        AUXStatus.click();
        WebDriverWait waitForAUXStatus = new WebDriverWait(agentChrome, 5);
        waitForAUXStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bAUX\\b.*")));
    }*/

    @Test(dependsOnMethods = "changeStatusToAUX"/*, retryAnalyzer = PDPreviewFreeAUXsubtestsmock.class*/)
    public static void switchToADTab() {
        WebElement adTab = agentChrome.findElement(By.xpath("//a[@href = '#tabView:tab123']"));
        adTab.click();
    }


    @Test(/*retryAnalyzer = PDPreviewFreeAUXsubtestsmock.class*/)
    public void testCRMTab() throws InterruptedException {
        WebElement adTab = agentChrome.findElement(By.xpath("//a[@href = '#tabView:tab123']"));
        adTab.click();
        agentChrome.switchTo().frame(agentChrome.findElement(By.id("TAB_123")));
        WebElement administrationMenu = agentChrome.findElement(By.xpath("/*//*[text()='Administration']"));
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

    @Test(dependsOnMethods = "switchToADTab"/*, retryAnalyzer = PDPreviewFreeAUXsubtestsmock.class*/)
    public static void runSQLQuery() throws SQLException, ClassNotFoundException {
        updateRecord(getConnection(), phoneNumber);
    }

/*    @Test(dependsOnMethods = "runSQLQuery")
    public void noIncomingCall() throws InterruptedException {
        Thread.sleep(2000);
        WebDriverWait waitForButtonAccept = new WebDriverWait(agentChrome, 10);
        waitForButtonAccept.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#btn_preview_accept")));
        //WebElement button_Accept = agentChrome.findElement(By.cssSelector("#btn_preview_accept"));
        //button_Accept.click();
    }*/

    @Test(dependsOnMethods = "runSQLQuery"/*, retryAnalyzer = PDPreviewFreeAUXsubtestsmock.class*/)
    public static void noIncomingCall() throws InterruptedException {
        try{
        WebDriverWait waitForPreviewStatus = new WebDriverWait(agentChrome, 5);
        waitForPreviewStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bPreview\\b.*")));
        } catch(Exception e){}
        //Thread.sleep(1000);
        WebElement currentStatus = agentChrome.findElement(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"));
        Assert.assertFalse(currentStatus.getText().contains("Preview"));
    }

    @Test(dependsOnMethods = "noIncomingCall"/*, retryAnalyzer = PDPreviewFreeAUXsubtestsmock.class*/)
    public static void changeStatusToAvailable() {
        WebElement currentStatus = agentChrome.findElement(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"));
        currentStatus.click();
        WebElement availableStatus = agentChrome.findElement(By.xpath(
                "//*[contains(text(),'Available')]"));
        availableStatus.click();
        WebDriverWait waitForAvailableStatus = new WebDriverWait(agentChrome, 5);
        waitForAvailableStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bAvailable\\b.*")));
    }

    @Test(dependsOnMethods = "changeStatusToAvailable"/*, retryAnalyzer = PDPreviewFreeAUXsubtestsmock.class*/)
    public static void receiveIncomingCall() throws InterruptedException {
        WebDriverWait waitForPreviewStatus = new WebDriverWait(agentChrome, 50);
        waitForPreviewStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bPreview\\b.*")));
        //Thread.sleep(1000);
        WebElement currentStatus = agentChrome.findElement(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"));
        Assert.assertTrue(currentStatus.getText().contains("Preview"));
    }

    @AfterClass
    public void teardown() {
        agentChrome.quit();
    }

}