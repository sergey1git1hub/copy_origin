package webphone;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.regex.Pattern;

import static data.Data.*;
import static org.testng.Assert.assertEquals;
import static helpMethods.HelpMethods.handleLogoutWindow;


public class CheckStatusAfterLoginIE1_4_3 {

    /*    @FindBy(id = "name") WebElement name;
        @FindBy(id = "phoneNumber") WebElement phoneNumber;
        @FindBy(id = "email") WebElement email;
        @FindBy(id = "question") WebElement question;*/

    String webphoneUrl = "http://172.21.24.109/gbwebphone/";


    /*   @Test*/
    public void loginAgent() throws InterruptedException, AWTException {
      /*  System.setProperty("webdriver.firefox.bin", "C:/Program Files (x86)/Mozilla Firefox/firefox.exe");
        System.setProperty("webdriver.gecko.agentDriver", "C:/geckodriver/geckodriver.exe");
        WebDriver agentDriver = new FirefoxDriver();*/
        System.setProperty("webdriver.ie.driver", "C:/iedriver/IEDriverServer.exe");
        //System.setProperty("webdriver.ie.driver", "C:/iedriver32/IEDriverServer.exe");
        DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
        ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
                true);
       /*
        solution 1
        ieCapabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING,false);

        ieCapabilities.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, true);
        ieCapabilities.setJavascriptEnabled(true);*/
       // solution 3 works fine
        ieCapabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
        ieCapabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
        agentDriver = new InternetExplorerDriver(ieCapabilities);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_SPACE);
        robot.keyRelease(KeyEvent.VK_SPACE);
        robot.keyRelease(KeyEvent.VK_ALT);
/*       solution 2 getting error
        new browser(agentDriver);*/
        agentDriver.get(webphoneUrl);
       /* Does not work
       String openDevTools = Keys.chord(Keys.ENTER);
        agentDriver.findElement(new By.ByTagName("body")).sendKeys(openDevTools);*/
        WebDriverWait waitForTitle = new WebDriverWait(agentDriver, 10);
        waitForTitle.until(ExpectedConditions.titleIs("gbwebphone"));
        assertEquals(agentDriver.getTitle(), "gbwebphone");


        //agentDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
/*        WebElement language = agentDriver.findElement(By.cssSelector("#lang_input"));
        language.click();
        WebElement lang_ru = agentDriver.findElement(By.cssSelector("[data-label=Русский]"));
        lang_ru.click();
        Thread.sleep(5000);
        */


        WebElement username = agentDriver.findElement(By.cssSelector("#username_input"));
        WebElement password = agentDriver.findElement(By.cssSelector("#password_input"));
        WebElement button_Connect = agentDriver.findElement(
                By.cssSelector("#btn_connect > span.ui-button-text.ui-c"));

        username.sendKeys("81016");
/*        username.sendKeys("1");
        username.sendKeys("0");

        username.sendKeys("1");
        username.sendKeys("6");*/
        password.sendKeys("1");
        button_Connect.click();
        agentDriver = handleLogoutWindow(agentDriver);
        /*****************/
        WebDriverWait waitForGroupList = new WebDriverWait(agentDriver, 5);
        waitForGroupList.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#group_input_label")));
        /******************/
        WebElement groupList = agentDriver.findElement(By.cssSelector("#group_input_label"));
        groupList.click();
        WebDriverWait waitForChatGroup = new WebDriverWait(agentDriver, 2);
        waitForChatGroup.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-label=test_alex]")));
        WebElement chatGroup = agentDriver.findElement(By.cssSelector("[data-label=test_alex]"));
        chatGroup.click();
        WebElement btnContinue = agentDriver.findElement(By.cssSelector("#btn_continue > span.ui-button-text.ui-c"));
        btnContinue.click();
      /*  WebElement tabSupervisor = agentDriver.findElement(By.cssSelector("#tabView > ul > li:nth-child(1)"));
        tabSupervisor.click();*/
        //agentDriver.quit();
    }

    /*@Test(dependsOnMethods = "loginAgent")*/
    public void checkStatusAvailable() throws InterruptedException {

        /*WebElement currentStatus = agentDriver.findElement(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"));
        for (int i = 0; i < 5; i++) {
            if (currentStatus.getText().contains("Select")) {
                Thread.sleep(5000);
            } else break;
        }*/

        WebElement currentStatus = agentDriver.findElement(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"));
        WebDriverWait waitForAvailableStatus = new WebDriverWait(agentDriver, 15);
/*        waitForAvailableStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bAvailable\\b.*|.*\\bIncoming\\b.*")));*/
        waitForAvailableStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bДоступен\\b.*|.*\\bIncoming\\b.*")));
        //System.out.println(currentStatus.getText());
    }

    /* @Test*/
    public void teardown() {

    /* does not work
     ((JavascriptExecutor) agentDriver).executeScript("window.confirm = function(msg) " +
                "{ return true; }");*/
        agentDriver.quit();

    }

    @Test
    public void checkStatusLoop() throws InterruptedException, AWTException {
        for (int i = 0; i < 2; i++) {
            loginAgent();
            checkStatusAvailable();
            teardown();
        }
    }
}

//Wait for status available