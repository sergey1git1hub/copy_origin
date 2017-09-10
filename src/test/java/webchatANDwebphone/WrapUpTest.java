package webchatANDwebphone;

import helpMethods.HelpMethods;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static data.Data.adminDriver;
import static data.Data.agentDriver;
import static data.Data.clientDriver;
import static org.testng.Assert.assertEquals;
import static helpMethods.HelpMethods.handleLogoutWindow;


public class WrapUpTest {

    /*    @FindBy(id = "name") WebElement name;
        @FindBy(id = "phoneNumber") WebElement phoneNumber;
        @FindBy(id = "email") WebElement email;
        @FindBy(id = "question") WebElement question;*/

 /*   String webphoneUrl = "http://172.21.1.192:8081";
    String webchatClientUrl = "http://172.21.1.192:8080/api/external/workgroupProvider/direct/100";*/

    String webchatClientUrl = "http://172.21.7.239:8080/api/external/workgroupProvider/direct/100";
    String webphoneUrl = "http://172.21.7.239/gbwebphone/";

    @Test
    public void makeGroupOnline() throws InterruptedException {
        //adminDriver = helpMethods.HelpMethods.login(adminDriver);
        adminDriver = HelpMethods.login(adminDriver);
        adminDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebElement button_Filters = adminDriver.findElement(By.cssSelector("body > app-root > " +
                "md-sidenav-container > div.mat-sidenav-content >" +
                " md-card > app-workgroup-list > div > div:nth-child(1) > div:nth-child(2) > div > " +
                "div:nth-child(2) > button:nth-child(4)"));
        button_Filters.click();
        Thread.sleep(1000L);

        WebElement button_OpenFilters = adminDriver.findElement(By.cssSelector("#filterButton > div.mat-button-ripple.mat-ripple"));
        button_OpenFilters.click();
        WebElement nameFilter = adminDriver.findElement(By.xpath("/html/body/app-root/md-sidenav-container/div[2]/md-card/app-workgroup-list/" +
                "div/div[2]/ngx-datatable/div/datatable-header/div/div[2]/datatable-header-cell[1]/div/" +
                "md-input-container/div/div/div/input"));
        nameFilter.sendKeys("Selenium");
        //check status
        WebElement groupState = adminDriver.findElement(By.xpath("/html/body/app-root/md-sidenav-container/div[2]/md-card/app-workgroup-list/div/div[2]/ngx-datatable/div/datatable-body/datatable-selection/datatable-scroller/datatable-row-wrapper/datatable-body-row/div[2]/datatable-body-cell[2]/" +
                "div/span"));
        if (groupState.getText().equals("Online")) {
            System.out.println("Group is already Online.");
        } else {
            System.out.println(groupState.getText());
            WebElement selectWorkgroup = adminDriver.findElement(By.xpath("/html/body/app-root/md-sidenav-container/div[2]/md-card/app-workgroup-list/div/div[2]/ngx-datatable/div/datatable-body/datatable-selection/datatable-scroller/datatable-row-wrapper/" +
                    "datatable-body-row/div[2]/datatable-body-cell[3]"));
            selectWorkgroup.click();
            WebElement button_Online = adminDriver.findElement(By.xpath("/html/body/app-root/md-sidenav-container/div[2]/md-card/app-workgroup-list/div/div[1]/div[2]/div/div[1]/button[1]/div[1]"));
            button_Online.click();
            Thread.sleep(1000L);
            groupState = adminDriver.findElement(By.xpath("/html/body/app-root/md-sidenav-container/div[2]/md-card/app-workgroup-list/div/div[2]/ngx-datatable/div/datatable-body/datatable-selection/datatable-scroller/datatable-row-wrapper/datatable-body-row/div[2]/datatable-body-cell[2]/div/span"));
            Assert.assertEquals(groupState.getText(), "Online");
        }
        adminDriver.quit();
    }

    @Test(/*dependsOnMethods = "makeGroupOnline"*/)
    public void loadForm() throws InterruptedException {
        //System.setProperty("webdriver.firefox.bin", "C:/Program Files (x86)/Mozilla Firefox/firefox.exe");
/*        System.setProperty("webdriver.gecko.driver", "C:/geckodriver/geckodriver.exe");
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("network.proxy.type", 0);
        clientDriver = new FirefoxDriver(profile);
        clientDriver.get("http://172.21.7.239:8080/api/external/workgroupProvider/direct/49");*/
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");
        clientDriver = new ChromeDriver();
        //clientDriver.get("http://172.21.7.239:8080/api/external/workgroupProvider/direct/100");
        clientDriver.get(webchatClientUrl);
        try {
            WebElement supportUnavailableMessage = clientDriver.findElement(By.cssSelector("body > div > div > button"));
            if (supportUnavailableMessage.getText().equals("Support Unavailable")) {
                makeGroupOnline();
                loadForm();
            }
        } catch (ElementNotVisibleException e) {

        }catch(NoSuchElementException e)
        {
        }
        assertEquals(clientDriver.getTitle(), "Online Support");
        /*clientDriver.quit();*/

    }

    @Test
    public void loginAgent() throws InterruptedException {
      /*  System.setProperty("webdriver.firefox.bin", "C:/Program Files (x86)/Mozilla Firefox/firefox.exe");
        System.setProperty("webdriver.gecko.agentDriver", "C:/geckodriver/geckodriver.exe");
        WebDriver agentDriver = new FirefoxDriver();*/
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");
        agentDriver = new ChromeDriver();
        //agentDriver.get("http://172.21.7.239/gbwebphone/");
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
        Thread.sleep(3000);
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

 /*   @Test(dependsOnMethods ="loginAgent")
    public void checkStatus() throws InterruptedException {
        WebElement currentStatus = agentDriver.findElement(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"));
        for(int i=0; i<5; i++){
        if(currentStatus.getText().contains("Select")){
            Thread.sleep(5000);
        } else break;
    }
        Assert.assertFalse(currentStatus.getText().contains("Select"), "Test failed because of status.");
    }*/

    @Test(dependsOnMethods = "loginAgent")
    public void checkStatusAvailable() throws InterruptedException {

        WebElement currentStatus = agentDriver.findElement(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"));
        WebDriverWait waitForAvailableStatus = new WebDriverWait(agentDriver, 10);
        waitForAvailableStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bAvailable\\b.*|.*\\bIncoming\\b.*")));
        //System.out.println(currentStatus.getText());
    }

    @Test(dependsOnMethods = "loadForm")
    public void filloutForm() {
        WebElement name = clientDriver.findElement(By.cssSelector("#name"));
        WebElement phoneNumber = clientDriver.findElement(By.cssSelector("#phoneNumber"));
        WebElement email = clientDriver.findElement(By.cssSelector("#email"));
        WebElement question = clientDriver.findElement(By.cssSelector("#question"));
        WebElement button = clientDriver.findElement(By.cssSelector("#form-78 > div.cta > button"));
//#form-78 > div.cta > button

        name.sendKeys("Sergey");
        phoneNumber.sendKeys("+380971662799");
        email.sendKeys("nameserhiy@emotion.com.ua");
        question.sendKeys("How are you?");
        button.click();

        assertEquals(name.getAttribute("value"), "Sergey");
        assertEquals(phoneNumber.getAttribute("value"), "+380971662799");
        assertEquals(email.getAttribute("value"), "nameserhiy@emotion.com.ua");
        assertEquals(question.getAttribute("value"), "How are you?");
        //* clientDriver.quit();*//*

    }

    @Test(dependsOnMethods = "filloutForm")
    public void waitScreen() {
        WebElement button_CancelRequest = clientDriver.findElement(By.cssSelector(
                "#waitScreenFragment > div > div.container > div > div > div.cta > button"));
        assertEquals(true, button_CancelRequest.isDisplayed());
    }

    @Test(dependsOnMethods = "waitScreen")
    public void incomingChatRequest(){

        WebDriverWait waitForIncomingChat = new WebDriverWait(agentDriver, 10);
        waitForIncomingChat.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[id^=\"accept\"]")));

    }

    @Test(dependsOnMethods = "waitScreen")
    public void checkStatusTiming() {
        try {
            WebDriverWait waitForWrapUpStatus = new WebDriverWait(agentDriver, 15);
            waitForWrapUpStatus.until(ExpectedConditions.textMatches(By.cssSelector("#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\bAvailable\\b.*|.*\\bIncoming\\b.*")));

            WebElement status = agentDriver.findElement(By.cssSelector(
                    "#statusButton > span.ui-button-text.ui-c"));
            Assert.assertTrue(status.getText().contains("Wrapup"));
            Thread.sleep(12000);
            Assert.assertTrue(status.getText().contains("Wrapup"));
            Thread.sleep(3000);
            status = agentDriver.findElement(By.cssSelector(
                    "#statusButton > span.ui-button-text.ui-c"));
            Assert.assertFalse(status.getText().contains("Wrapup"));
            Assert.assertTrue(status.getText().contains("Техпроблемы"));

            status.click();
            WebElement status_Available = agentDriver.findElement(By.cssSelector(
                    "#statusListPanel>div>button:first-child"));
            status_Available.click();
            WebElement timer = null;
            //check for 100 seconds
            WebElement currentStatusDuration = agentDriver.findElement(By.cssSelector("#currentStatusDuration"));
            for (int i = 0; i < 10; i++) {
                String time = currentStatusDuration.getText();
                String timeToCompare = "00:00:05";
                DateFormat sdf = new SimpleDateFormat("hh:mm:ss");
                Date timeParsed = sdf.parse(time);
                Date timeToCompareParsed = sdf.parse(timeToCompare);
/*            if(date1.compareTo(date2)>0){
                System.out.println("Date1 is after Date2");*/
                Assert.assertTrue(timeToCompare.compareTo(time) > 0);
            }
        } catch (InterruptedException e) {
        } catch (ParseException e) {
        }

    }
}