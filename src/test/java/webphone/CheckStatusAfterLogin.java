package webphone;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static data.Data.agentDriver;
import static data.Data.webphoneUrl;
import static org.testng.Assert.assertEquals;
import static helpMethods.HelpMethods.handleLogoutWindow;


public class CheckStatusAfterLogin {

    /*    @FindBy(id = "name") WebElement name;
        @FindBy(id = "phoneNumber") WebElement phoneNumber;
        @FindBy(id = "email") WebElement email;
        @FindBy(id = "question") WebElement question;*/



    @Test
    public void loginAgent() throws InterruptedException {
      /*  System.setProperty("webdriver.firefox.bin", "C:/Program Files (x86)/Mozilla Firefox/firefox.exe");
        System.setProperty("webdriver.gecko.agentDriver", "C:/geckodriver/geckodriver.exe");
        WebDriver agentDriver = new FirefoxDriver();*/
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");
        agentDriver = new ChromeDriver();
        agentDriver.get(webphoneUrl);
        assertEquals(agentDriver.getTitle(), "gbwebphone");
        agentDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement username = agentDriver.findElement(By.cssSelector("#username_input"));
        WebElement password = agentDriver.findElement(By.cssSelector("#password_input"));
        WebElement button_Connect = agentDriver.findElement(
                By.cssSelector("#btn_connect > span.ui-button-text.ui-c"));

        username.sendKeys("81016");
        password.sendKeys("1");
        button_Connect.click();
        agentDriver = handleLogoutWindow(agentDriver);
        /*****************/
        WebDriverWait waitForGroupList = new WebDriverWait(agentDriver, 10);
        waitForGroupList.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#group_input_label")));
        /******************/
        WebElement groupList = agentDriver.findElement(By.cssSelector("#group_input_label"));
        groupList.click();
        WebElement chatGroup = agentDriver.findElement(By.cssSelector("[data-label=test_alex]"));
        chatGroup.click();
        WebElement btnContinue = agentDriver.findElement(By.cssSelector("#btn_continue > span.ui-button-text.ui-c"));
        btnContinue.click();
      /*  WebElement tabSupervisor = agentDriver.findElement(By.cssSelector("#tabView > ul > li:nth-child(1)"));
        tabSupervisor.click();*/
        //agentDriver.quit();
    }

    @Test(dependsOnMethods = "loginAgent")
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
        waitForAvailableStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bAvailable\\b.*|.*\\bIncoming\\b.*")));
        //System.out.println(currentStatus.getText());
    }

    @AfterClass
    public void teardown(){
        agentDriver.quit();
    }

}

//Wait for status available