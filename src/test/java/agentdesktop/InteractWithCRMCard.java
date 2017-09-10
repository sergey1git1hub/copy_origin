package agentdesktop;

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

import java.sql.SQLException;
import java.util.logging.Level;

import static data.Data.CRMUrl;
import static data.Data.agentCRM;


public class InteractWithCRMCard {


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
    public void interactWithCRMCard() throws InterruptedException {
        agentCRM = new ChromeDriver(options);
        agentCRM.get(CRMUrl);
        Assert.assertEquals(agentCRM.getTitle(), "Login | Agent Desktop");
        Thread.sleep(1000);
        WebElement username = agentCRM.findElement(By.cssSelector("#j_username"));
        username.sendKeys("81016");
        WebElement password = agentCRM.findElement(By.cssSelector("#j_password"));
        password.sendKeys("1");
        WebElement rememberMe = agentCRM.findElement(By.cssSelector("#rememberMe"));
        rememberMe.click();
        WebElement button_Login = agentCRM.findElement(By.cssSelector("[name = login]"));
        button_Login.click();
        agentCRM.get(CRMUrl);
        Assert.assertEquals(agentCRM.getTitle(), "Card | Agent Desktop");
        WebDriverWait waitForResultCode = new WebDriverWait(agentCRM, 5);

        waitForResultCode.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id = 'resultCodeText']")));
        WebElement resultCodeList = agentCRM.findElement(By.xpath("//*[@id = 'resultCodeText']"));
        resultCodeList.click();
        WebElement resultCodeChoice = agentCRM.findElement(By.xpath("//*[@id = 'resultCode598']"));
        resultCodeChoice.click();
        WebElement button_NextForm = agentCRM.findElement(By.cssSelector("#viewerForm802 > table > tbody > tr:nth-child(2) > td:nth-child(2) > input"));
        button_NextForm.click();

        WebElement button_Save = agentCRM.findElement(By.cssSelector("#submitButton"));
        button_Save.click();
    }
    @Test
    public void controlFlow() throws InterruptedException, SQLException, ClassNotFoundException {
        interactWithCRMCard();
    }
    @AfterClass
    public void teardonw(){
        agentCRM.quit();
    }

}