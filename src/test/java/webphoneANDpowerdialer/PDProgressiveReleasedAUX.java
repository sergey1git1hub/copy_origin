package webphoneANDpowerdialer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.regex.Pattern;

import static data.Data.agentChrome;
import static data.Data.webphoneUrl;
import static helpMethods.HelpMethods.handleLogoutWindow;

//import java.lang.Object;

public class PDProgressiveReleasedAUX {



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
        WebElement chatGroup = agentChrome.findElement(By.cssSelector("[data-label=\\!test_group5_5220]"));
        chatGroup.click();
        WebElement btnContinue = agentChrome.findElement(By.cssSelector("#btn_continue > span.ui-button-text.ui-c"));
        btnContinue.click();
        WebElement currentStatus = agentChrome.findElement(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"));
        WebDriverWait waitForTrainingStatus = new WebDriverWait(agentChrome, 15);
        waitForTrainingStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bAvailable\\b.*")));
        System.out.println("Chrome login: 1.");
    }


    @Test()
    public void changeStatusToAUX(){
        WebElement currentStatus = agentChrome.findElement(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"));
        currentStatus.click();
      /*  WebElement AUXStatus = agentChrome.findElement(By.xpath(
                "/*//*[contains(text(),'AUX') AND not(contains(text(),'Доступен'))])"));*/
        WebElement AUXStatus = agentChrome.findElement(By.xpath(
                "//*[contains(text(),'AUX') and not(contains(text(),'Доступен'))]"));
        AUXStatus.click();
        WebDriverWait waitForAUXStatus = new WebDriverWait(agentChrome, 5);
        waitForAUXStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bAUX\\b.*")));
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
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
                " ad_card_id = null, is_fetched = null, skill = null WHERE id = 373;";

        Statement stmt = con.createStatement();
        stmt.execute(query);
    }

    @Test
    public void runSQLQuery() throws SQLException, ClassNotFoundException {
        updateRecord(getConnection());
    }


    public void answerCallOnClientSide() {
    }


    @Test
    public void controlFlow() throws InterruptedException, SQLException, ClassNotFoundException {
        ssoLoginChrome();
        changeStatusToAUX();
        runSQLQuery();
    }

    @AfterClass
    public void teardown(){
        agentChrome.quit();
    }
}