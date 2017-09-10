package webphone;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static data.Data.agentChrome;
import static data.Data.webphoneUrl;
import static helpMethods.HelpMethods.handleLogoutWindow;

public class LoginSlow {

    @Test(priority = 1)
    public void ssoLoginChrome() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        capabilities.setCapability("pageLoadStrategy", "normal");
        agentChrome = new ChromeDriver(capabilities);
        agentChrome.get(webphoneUrl);
        Assert.assertEquals(agentChrome.getTitle(), "gbwebphone");
        WebDriverWait waitForTitle = new WebDriverWait(agentChrome, 10);
        waitForTitle.until(ExpectedConditions.titleIs("gbwebphone"));
        Assert.assertEquals(agentChrome.getTitle(), "gbwebphone");
        //Thread.sleep(2000);
        WebElement button_SSO_IE = agentChrome.findElement(By.cssSelector("#ssoButton > span"));
        String winHandleBefore = agentChrome.getWindowHandle();
        button_SSO_IE.click();
        for (String winHandle : agentChrome.getWindowHandles()) {
            agentChrome.switchTo().window(winHandle);
        }
       // Thread.sleep(2500);
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
        agentChrome.manage().timeouts().pageLoadTimeout(1, TimeUnit.SECONDS);
        agentChrome = handleLogoutWindow(agentChrome);
 /*       WebDriverWait waitForButtonContinue = new WebDriverWait(agentChrome, 5);
        waitForButtonContinue.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#btn_continue > span.ui-button-text.ui-c")));*/

/*        WebDriverWait waitForGroupList = new WebDriverWait(agentChrome, 1);
        waitForGroupList.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#group_input_label")));*/
        /*String jsScript = "";
        JavascriptExecutor js = (JavascriptExecutor) agentChrome;
        js.executeScript(jsScript);*/

/*        WebElement myButton = (new WebDriverWait(agentChrome, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("myButtonId")));*/

 /*  maybe before we will access anything related with #group_input_label
        WebDriverWait waitForGroupList = new WebDriverWait(agentChrome, 1);
        waitForGroupList.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#group_input_label")));*/
        ((JavascriptExecutor)agentChrome).executeScript("window.stop();");

        /*WebElement groupList = agentChrome.findElement(By.cssSelector("#group_input_label"));
        agentChrome.manage().timeouts().pageLoadTimeout(1, TimeUnit.SECONDS);
        groupList.click();*/

        WebElement groupList = agentChrome.findElement(By.cssSelector("#group_input > div.ui-selectonemenu-trigger.ui-state-default.ui-corner-right > span"));
        agentChrome.manage().timeouts().pageLoadTimeout(2, TimeUnit.SECONDS);
        groupList.click();
      /*  Thread.sleep(2000);
        for(int i = 0; i<9; i++){
        groupList.click();
        Thread.sleep(500);
        }*/
        agentChrome.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
      /*  Thread.sleep(100);
        WebElement chatGroup = agentChrome.findElement(By.cssSelector("[data-label=\\!test_group5_5220]"));
        chatGroup.click();
        Thread.sleep(500);*/

       /* Thread.sleep(100);
        WebElement chatGroup = agentChrome.findElement(By.cssSelector("[data-label=\\!test_group5_5220]"));
        chatGroup.click();
        Thread.sleep(1000);
        WebElement btnContinue = agentChrome.findElement(By.cssSelector("#btn_continue > span.ui-button-text.ui-c"));
        btnContinue.click();
        WebElement currentStatus = agentChrome.findElement(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"));
        WebDriverWait waitForAvailableStatus = new WebDriverWait(agentChrome, 15);
        waitForAvailableStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bДоступен\\b.*|.*\\bIncoming\\b.*|.*\\bAvailable\\b.*")));
        System.out.println("Chrome login: 1.");*/

    }
}