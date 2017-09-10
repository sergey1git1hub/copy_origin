package webchatANDwebphone;

import helpMethods.HelpMethods;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static data.Data.*;
import static org.testng.Assert.assertEquals;
import static helpMethods.HelpMethods.handleLogoutWindow;


public class Webchat {

/*    @FindBy(id = "name") WebElement name;
    @FindBy(id = "phoneNumber") WebElement phoneNumber;
    @FindBy(id = "email") WebElement email;
    @FindBy(id = "question") WebElement question;*/


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
    if(groupState.getText().equals("Online"))
    {System.out.println("Group is already Online.");} else{
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
        clientDriver.get(webchatClientUrl);
        if(clientDriver.getTitle().equals("Online Support")) {}
        else {makeGroupOnline();}
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
        WebElement tabSupervisor = agentDriver.findElement(By.cssSelector("#tabView > ul > li:nth-child(1)"));
        tabSupervisor.click();
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

    @Test(dependsOnMethods ="loginAgent")
    public void checkStatusDump() throws InterruptedException {
        WebElement currentStatus = agentDriver.findElement(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"));
        for(int i=0; i<5; i++){
            if(currentStatus.getText().contains("Select")){
                Thread.sleep(5000);
            } else break;
        }
        Assert.assertFalse(currentStatus.getText().contains("Select"), "Test failed because of status.");
    }

    @Test(dependsOnMethods = "loadForm")
    public void filloutForm(){
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
    public void waitScreen(){
        WebElement button_CancelRequest = clientDriver.findElement(By.cssSelector(
                "#waitScreenFragment > div > div.container > div > div > div.cta > button"));
        assertEquals(true, button_CancelRequest.isDisplayed());
    }

    @Test(dependsOnMethods = "waitScreen")
    public void makeChat() throws InterruptedException {
        Thread.sleep(2000);
/*        private boolean existsElement(String id) {
            try {
                driver.findElement(By.id(id));
            } catch (NoSuchElementException e) {
                return false;
            }
            return true;
        }*/
        try{
        WebElement button_Accept = agentDriver.findElement(By.cssSelector(
                "button[id^=\"accept\"]"));

        button_Accept.click();
        } catch(NoSuchElementException e){}
        catch(ElementNotVisibleException e){}
        finally{ //confirm that autoaccept  has happened
            WebElement check_present_button_Send = agentDriver.findElement(By.cssSelector(
                    "button[id^=\"send\"] > span"));
            System.out.println("Autoaccept");
        }
        Thread.sleep(2000);
        WebElement textArea = agentDriver.findElement(By.cssSelector(
                "textarea[id^=\"message\"]"));
        textArea.sendKeys("I am fine!");
        String send = Keys.chord(Keys.CONTROL, Keys.ENTER);
        textArea.sendKeys(send);

       /* WebElement button_Send = agentDriver.findElement(By.cssSelector(
                "button[id^=\"send\"] > span"));
        WebDriverWait wait = new WebDriverWait(agentDriver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[id^=\"send\"]")));
        button_Send.click();
*/

    }

    @Test(dependsOnMethods = "makeChat")
    public void messageReceivedByClient(){
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement msgReceivedByClient = clientDriver.findElement(By.cssSelector(
                "#message-container > li.clearfix > div.message.message__reply"));

        assertEquals(msgReceivedByClient.getText(), "I am fine!");
        WebElement textarea = clientDriver.findElement(By.cssSelector("#message"));
        textarea.sendKeys("Great! Test passed!");

        WebElement button_Send = clientDriver.findElement(By.cssSelector("#btn-message-submit"));
        button_Send.click();
    }

    @Test(dependsOnMethods  = "messageReceivedByClient")
    public void messageReceivedByAgent() throws InterruptedException {
        Thread.sleep(1000);
        WebElement msgReceivedByAgent = agentDriver.findElement(By.cssSelector(
                "div[id^=\"chatMessages\"] > div:nth-child(3) > span:nth-child(2)"));
        assertEquals(msgReceivedByAgent.getText(), "Great! Test passed!");

}


    //+ agent side - status changed to wrapup
    //+client side -
    //+совершить звонок после чата проблема была в agentdesktop
    //свернуть окно чата
    //still logs with no data to display
    //vfrc знакомый
    //hhhh

    @Test(dependsOnMethods  = "messageReceivedByAgent")
    public void endChatByAgent() throws InterruptedException, ParseException {
        List<WebElement> closeList = agentDriver.findElements(By.cssSelector("div.ui-dialog-titlebar.ui-widget-header.ui-helper-clearfix.ui-corner-top >" +
                " a.ui-dialog-titlebar-icon.ui-dialog-titlebar-close.ui-corner-all"));
        WebElement close = closeList.get(1);
        close.click();
    }

    @Test(dependsOnMethods  = "messageReceivedByAgent")
    public void checkStatusTiming() {
        try{
        List<WebElement> closeList = agentDriver.findElements(By.cssSelector("div.ui-dialog-titlebar.ui-widget-header.ui-helper-clearfix.ui-corner-top >" +
                " a.ui-dialog-titlebar-icon.ui-dialog-titlebar-close.ui-corner-all"));
        WebElement close = closeList.get(1);
        close.click();
        Thread.sleep(2000);
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
        for(int i=0; i<10; i++){
            String time = currentStatusDuration.getText();
            String timeToCompare = "00:00:05";
            DateFormat sdf = new SimpleDateFormat("hh:mm:ss");
            Date timeParsed = sdf.parse(time);
            Date timeToCompareParsed = sdf.parse(timeToCompare);
/*            if(date1.compareTo(date2)>0){
                System.out.println("Date1 is after Date2");*/
            Assert.assertTrue(timeToCompare.compareTo(time)>0);
        }}catch (InterruptedException e){}
        catch (ParseException e){}

    }



    @Test(dependsOnMethods = "endChatByAgent")
            public void sendEmailToClient(){
        //assertEquals(status.getText(), "Wrapup");
        WebElement email = clientDriver.findElement(By.cssSelector("#client-email"));
        email.sendKeys("nameserhiy@emotion.com.ua");
        WebElement button_Send = clientDriver.findElement(By.cssSelector("#chat-history-container > form > div.cta > button"));
        //button_Send.click();
        //assertEquals(email.getText(), "nameserhiy@emotion.com.ua");

}

    @Test(dependsOnMethods = "endChatByAgent")
    public void closeClientBrowser(){
        clientDriver.quit();
    }

@AfterClass
public void teardown(){
        try{
    agentDriver.quit();
    clientDriver.quit();
        } catch(Exception e){
        }
}
}