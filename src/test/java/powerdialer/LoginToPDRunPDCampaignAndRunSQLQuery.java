package powerdialer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

import static data.Data.PDUrl;
import static data.Data.agentPD;

public class LoginToPDRunPDCampaignAndRunSQLQuery {

    ChromeOptions options;

    @BeforeClass
    public void setupDriver() {
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");
        options = new ChromeOptions();
        options.addArguments("--enable-logging --v=1");
        DesiredCapabilities capsChrome = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.INFO);
    }

    @Test()
    public void loginToPD() throws InterruptedException {
        agentPD = new ChromeDriver(options);
        agentPD.get(PDUrl);
        Assert.assertEquals(agentPD.getTitle(), "gbpowerdialer");
        Thread.sleep(1000);
/*        WebDriverWait waitForUsername = new WebDriverWait(agentPD, 2);
        waitForUsername.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#username")));*/
        WebElement username = agentPD.findElement(By.cssSelector("#username"));
        username.sendKeys("81016");
        WebElement password = agentPD.findElement(By.cssSelector("#password"));
        password.sendKeys("1");
        WebElement button_SignIn = agentPD.findElement(By.cssSelector("#loginModal > div > div > form > div.modal-footer > button"));
        button_SignIn.click();
    }

    @Test
    public void runPDCampaign() throws InterruptedException {
        Thread.sleep(3000);
        /*WebDriverWait waitForButtonEnable = new WebDriverWait(agentPD, 10);
        waitForButtonEnable.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/*//*[text() = '5230c']//parent::div//following-sibling::div[2]//div")));*/
        WebElement running = agentPD.findElement(By.xpath("//*[text() = '5230c']//parent::div//following-sibling::div[2]//div"));
        System.out.println(running.getText());
        if (!running.getText().equals("Running")) {
            WebElement campaignName = agentPD.findElement(By.xpath("//*[text() = '5230c']"));
            campaignName.click();
            WebElement icon_Enable = agentPD.findElement(By.cssSelector("#navbarCompainList > div > div:nth-child(7) > button"));
            icon_Enable.click();
            WebElement button_Enable = agentPD.findElement(By.cssSelector("#navbarCompainList > div > div.btn-group.open > ul > li:nth-child(1) > a"));
            button_Enable.click();
            Thread.sleep(1000);
            WebElement button_Start = agentPD.findElement(By.cssSelector("#navbarCompainList > div > button.btn.btn-success.btn-sm.navbar-btn"));
            button_Start.click();
        }
    }

    @Test
    public void controlFlow() throws InterruptedException, SQLException, ClassNotFoundException {
        loginToPD();
        runPDCampaign();
        runSQLQuery();
    }

    @Test
    public void loginToBDWithSSO() {
        agentPD = new ChromeDriver(options);
        agentPD.get(PDUrl);
        Assert.assertEquals(agentPD.getTitle(), "gbpowerdialer");
        WebDriverWait waitForButtonSSO = new WebDriverWait(agentPD, 2);
        // waitForUsername.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#ssoButton")));
        waitForButtonSSO.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#ssoButton")));
        WebElement button_SSO = agentPD.findElement(By.cssSelector("#ssoButton"));
        button_SSO.click();
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
        String query = "UPDATE GBWebPhoneTest.dbo.pd_5009_3 SET phone_number_1 = '94636'," +
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
        updateRecord(getConnection());
    }

    @AfterClass
    public void teardown(){
        agentPD.quit();
    }
}