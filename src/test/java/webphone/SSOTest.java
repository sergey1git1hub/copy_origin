package webphone;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static helpMethods.HelpMethods.handleLogoutWindow;
import static data.Data.*;
/**
 * Created by SChubuk on 02.08.2017.
 */
public class SSOTest {


    @Test(priority=1)
    public WebDriver ssoLoginIE(WebDriver agentIE) throws InterruptedException {

        //Login with SSO in IE
        System.setProperty("webdriver.ie.driver", "C:/iedriver/IEDriverServer.exe");
        DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
        ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
                true);
        agentIE = new InternetExplorerDriver(ieCapabilities);
        agentIE.get(webphoneUrl);
        WebDriverWait waitForTitle = new WebDriverWait(agentIE, 10);
        waitForTitle.until(ExpectedConditions.titleIs("gbwebphone"));
        assertEquals(agentIE.getTitle(), "gbwebphone");

        WebElement button_SSO_IE = agentIE.findElement(By.cssSelector("#ssoButton > span"));
        button_SSO_IE.click();

        WebDriverWait waitForButtonContinue = new WebDriverWait(agentIE, 5);
        waitForButtonContinue.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#btn_continue > span.ui-button-text.ui-c")));

        WebDriverWait waitForGroupList = new WebDriverWait(agentIE, 5);
        waitForGroupList.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#group_input_label")));

        WebElement groupList = agentIE.findElement(By.cssSelector("#group_input_label"));
        groupList.click();
        WebElement chatGroup = agentIE.findElement(By.cssSelector("[data-label=test_alex]"));
        chatGroup.click();
        WebElement btnContinue = agentIE.findElement(By.cssSelector("#btn_continue > span.ui-button-text.ui-c"));
        btnContinue.click();

        //verification that page has been loaded
        WebElement currentStatus = agentIE.findElement(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"));
        for(int i=0; i<5; i++){
            if(currentStatus.getText().contains("Select")){
                Thread.sleep(5000);
            } else break;
        }
        Assert.assertFalse(currentStatus.getText().contains("Select"), "Test failed because of status.");

        return agentIE;
    }

    @Test(priority=2)
    public WebDriver ssoLoginChrome(WebDriver agentChrome) throws InterruptedException {

        //Login with SSO in Chrome
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");
        agentChrome = new ChromeDriver();
        agentChrome.get(webphoneUrl);
        assertEquals(agentChrome.getTitle(), "gbwebphone");
        WebDriverWait waitForTitle = new WebDriverWait(agentChrome, 10);
        waitForTitle.until(ExpectedConditions.titleIs("gbwebphone"));
        assertEquals(agentChrome.getTitle(), "gbwebphone");
        WebElement button_SSO_IE = agentChrome.findElement(By.cssSelector("#ssoButton > span"));
        String winHandleBefore = agentChrome.getWindowHandle();
        button_SSO_IE.click();
        for(String winHandle : agentChrome.getWindowHandles()){
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


/**************************************************************/

        WebDriverWait waitForButtonContinue = new WebDriverWait(agentChrome, 5);
        waitForButtonContinue.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#btn_continue > span.ui-button-text.ui-c")));

        WebDriverWait waitForGroupList = new WebDriverWait(agentChrome, 10);
        waitForGroupList.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#group_input_label")));

        WebElement groupList = agentChrome.findElement(By.cssSelector("#group_input_label"));
        groupList.click();
        WebElement chatGroup = agentChrome.findElement(By.cssSelector("[data-label=test_alex]"));
        chatGroup.click();
        WebElement btnContinue = agentChrome.findElement(By.cssSelector("#btn_continue > span.ui-button-text.ui-c"));
        btnContinue.click();

        //verification that page has been loaded
 /*       WebDriverWait waitForSupervisorTab = new WebDriverWait(agentChrome, 10);
       waitForSupervisorTab.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#tabView > ul > li:nth-child(1)")));
        WebElement tabSupervisor = agentChrome.findElement(By.cssSelector("#tabView > ul > li:nth-child(1)"));
        tabSupervisor.click();*/

      /*  WebElement currentStatus = agentChrome.findElement(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"));
        for(int i=0; i<5; i++){
            if(currentStatus.getText().contains("Select status")){
                Thread.sleep(5000);
            } else break;
        }
        Assert.assertFalse(currentStatus.getText().contains("Select status"), "Test failed because of status.");
        */
        return agentChrome;
    }
    @Test
    public WebDriver loopIE (WebDriver agentIE) throws InterruptedException {
        Thread.sleep(20000);
        WebElement button_SSO_IE = agentIE.findElement(By.cssSelector("#ssoButton > span"));
        button_SSO_IE.click();
        button_SSO_IE.click();
        Thread.sleep(3000);
        agentIE = handleLogoutWindow(agentIE);
        WebDriverWait waitForButtonContinue = new WebDriverWait(agentIE, 5);
/**/        waitForButtonContinue.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#btn_continue > span.ui-button-text.ui-c")));

        WebDriverWait waitForGroupList = new WebDriverWait(agentIE, 5);
        waitForGroupList.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#group_input_label")));

        WebElement groupList = agentIE.findElement(By.cssSelector("#group_input_label"));
        groupList.click();
        WebElement chatGroup = agentIE.findElement(By.cssSelector("[data-label=test_alex]"));
        chatGroup.click();
        WebElement btnContinue = agentIE.findElement(By.cssSelector("#btn_continue > span.ui-button-text.ui-c"));
        btnContinue.click();

        //verification that page has been loaded
        WebElement currentStatus = agentIE.findElement(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"));
        for(int i=0; i<5; i++){
            if(currentStatus.getText().contains("Select")){
                Thread.sleep(5000);
            } else break;
        }
        Assert.assertFalse(currentStatus.getText().contains("Select"), "Test failed because of status.");

        return agentIE;

    }

    @Test
    public WebDriver loopChrome (WebDriver driver) throws InterruptedException {
        Thread.sleep(20000);
        WebElement button_SSO_IE = agentChrome.findElement(By.cssSelector("#ssoButton > span"));
        button_SSO_IE.click();
        button_SSO_IE.click();
        Thread.sleep(3000);
        WebDriverWait waitForButtonContinue = new WebDriverWait(agentChrome, 5);
        waitForButtonContinue.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#btn_continue > span.ui-button-text.ui-c")));

        WebDriverWait waitForGroupList = new WebDriverWait(agentChrome, 10);
        waitForGroupList.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#group_input_label")));

        WebElement groupList = agentChrome.findElement(By.cssSelector("#group_input_label"));
        groupList.click();
        WebElement chatGroup = agentChrome.findElement(By.cssSelector("[data-label=test_alex]"));
        chatGroup.click();
        WebElement btnContinue = agentChrome.findElement(By.cssSelector("#btn_continue > span.ui-button-text.ui-c"));
        btnContinue.click();

        //verification that page has been loaded
        WebDriverWait waitForSupervisorTab = new WebDriverWait(agentChrome, 10);
        waitForSupervisorTab.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#tabView > ul > li:nth-child(1)")));
        WebElement tabSupervisor = agentChrome.findElement(By.cssSelector("#tabView > ul > li:nth-child(1)"));
        tabSupervisor.click();

      /*  WebElement currentStatus = agentChrome.findElement(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"));
        for(int i=0; i<5; i++){
            if(currentStatus.getText().contains("Select status")){
                Thread.sleep(5000);
            } else break;
        }
        Assert.assertFalse(currentStatus.getText().contains("Select status"), "Test failed because of status.");
        */

        return agentChrome;
    }

    @Test
    public void ssoLoop() throws InterruptedException {
        agentIE = ssoLoginIE(agentIE);
        agentChrome = ssoLoginChrome(agentChrome);
        for(int i = 0; i<10; ++i){
            agentIE = loopIE(agentIE);
            agentChrome = loopChrome(agentChrome);
        }
    }

}
