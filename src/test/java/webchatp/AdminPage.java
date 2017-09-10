package webchatp;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static data.Data.*;
import static org.testng.Assert.assertEquals;

public class AdminPage {

    @Test
    public void login() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(webchatUrl);
        assertEquals(driver.getTitle(), "gbwebchat");

        WebElement name = driver.findElement(By.cssSelector("[name=username]"));
        WebElement password = driver.findElement(By.cssSelector("[name=password]"));

        WebElement button_login = driver.findElement(By.cssSelector("body > app-root > " +
                "md-sidenav-container > div.mat-sidenav-content > md-card > app-login-detail > " +
                "div > form > div:nth-child(3) > button"));


        name.sendKeys("81016");
        password.sendKeys("1");
        button_login.click();
            Thread.sleep(1000);
    }

        @Test(dependsOnMethods = "login")
        public void openCreateNewWorkgroupForm () throws InterruptedException {
            Thread.sleep(1000);
            WebElement menu = driver.findElement(By.cssSelector("body > app-root > md-sidenav-container > div.mat-sidenav-content > app-navigation >" +
                    " md-toolbar > div > md-toolbar-row > div > div:nth-child(1) > div > div:nth-child(1) >" +
                    " button > div.mat-button-ripple.mat-ripple.mat-button-ripple-round"));
            menu.click();
            Thread.sleep(1000);
            WebElement workgroups = driver.findElement(By.xpath("/html/body/app-root/md-sidenav-container/" +
                    "div[2]/app-navigation/md-sidenav/button[1]/div[1]"));
            workgroups.click();

            WebElement button_add = driver.findElement(By.xpath("/html/body/app-root/md-sidenav-container/div[2]" +
                    "/md-card/app-workgroup-list/div/div[1]/div[2]/div/div[1]/button[4]/div[1]"));
            button_add.click();

        }

        @Test(dependsOnMethods = "openCreateNewWorkgroupForm")
    public void filloutGeneralTab() throws InterruptedException {
            Thread.sleep(1000);
            WebElement name = driver.findElement(By.cssSelector("#md-input-5"));
            WebElement onlineFrom = driver.findElement(By.cssSelector("[name = onlineFrom"));
            WebElement onlineTill = driver.findElement(By.cssSelector("[name = onlineTill"));
            WebElement selectTemplate = driver.findElement(By.xpath("//*[@id=\"md-tab-content-0-0\"]/div/div/div[2]/md-select/div/span[2]"));



            name.sendKeys("lifecell2");
            onlineFrom.sendKeys("07202017");
            onlineFrom.sendKeys(Keys.TAB);
            onlineFrom.sendKeys("0100AM");

            onlineTill.sendKeys("09202017");
            onlineTill.sendKeys(Keys.TAB);
            onlineTill.sendKeys("0100AM");

            Thread.sleep(1000);
            WebElement templateList = driver.findElement(By.xpath("//*[@id=\"md-tab-content-0-0\"]/div/div/div[2]/md-select/div"));
            templateList.click();
            Thread.sleep(500);
           /* WebElement templateLifecell = driver.findElement(By.xpath("/*//*[@id=\"md-option-10\"]"));
            templateLifecell.click();*/

            Thread.sleep(500);
            WebElement tenant = driver.findElement(By.cssSelector("#md-tab-content-0-0 > div > div > div:nth-child(5) > md-select > div > span.mat-select-arrow"));

            tenant.click();
            Thread.sleep(500);
            WebElement masterTenant = driver.findElement(By.cssSelector("#md-option-0"));
            masterTenant.click();
            WebElement paginationShevron = driver.findElement(By.cssSelector("#cdk-overlay-1 > md-dialog-container > app-workgroup-detail >" +
                    " div > form > md-tab-group > md-tab-header >" +
                    " div.mat-tab-header-pagination.mat-tab-header-pagination-after.mat-elevation-z4" +
                    ".mat-ripple > div"));
            paginationShevron.click();
            paginationShevron.click();
            paginationShevron.click();
            Thread.sleep(500);
            WebElement emailConfigTab = driver.findElement(By.cssSelector("#md-tab-label-0-7"));
            emailConfigTab.click();
        }

        @Test(dependsOnMethods = "filloutGeneralTab")
    public void filloutEmailConfigTab() throws InterruptedException {
            Thread.sleep(500);
            WebElement smtpHost = driver.findElement(By.cssSelector("[name=smtpHost]"));
            WebElement smtpPort = driver.findElement(By.cssSelector("[name=smtpPort]"));
            WebElement email = driver.findElement(By.cssSelector("[name=email]"));
            WebElement emailSubject = driver.findElement(By.cssSelector("[name=emailSubject]"));
            WebElement emailMessageFormat = driver.findElement(By.cssSelector("[name=emailMessageFormat]"));
            WebElement emailText = driver.findElement(By.cssSelector("[name=emailText]"));
            Thread.sleep(500);
            WebElement button_Save = driver.findElement(By.xpath("//*[@id=\"cdk-overlay-1\"]/md-dialog-container/app-workgroup-detail/div/form/div/button[1]/div[1]"));


            smtpHost.sendKeys("172.21.7.88");
            smtpPort.sendKeys("25");
            email.sendKeys("gbwebchat@globalbilgi.com.ua");
            emailSubject.sendKeys("[emailSubject] lifecell.com.ua");
            emailMessageFormat.sendKeys("{dateFormat message.sendDate \"HH:mm\"}, <b>{message.sender.identifier}:</b> {message.message}");
            emailText.sendKeys("{history}");
            button_Save.click();

        }


    }

