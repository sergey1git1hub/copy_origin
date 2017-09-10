package bigTest;

import org.sikuli.script.FindFailed;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static data.Data.agentChrome;


public class BigTestCFWrapper {

    //TwoLinesAgentHangupv2
    @Test(priority = 1, groups = "pageLoads")
    public void ssoLoginChrome() throws InterruptedException {
        webphone.TwoLinesAgentHangupv2.ssoLoginChrome();
    }

    @Test(dependsOnMethods = "ssoLoginChrome")
    public void callOnFirstLine() throws FindFailed, InterruptedException {
        webphone.TwoLinesAgentHangupv2.callOnFirstLine();
    }

    @Test(dependsOnMethods = "callOnFirstLine")
    public void callOnSecondLine() throws FindFailed, InterruptedException {
        webphone.TwoLinesAgentHangupv2.callOnSecondLine();
    }

    @Test(dependsOnMethods = "callOnSecondLine")
    public void agentHangupLine1() throws FindFailed, InterruptedException {
        webphone.TwoLinesAgentHangupv2.agentHangupLine1();
    }

    @Test(dependsOnMethods = "agentHangupLine1")
    public void agentHangupLine2() throws FindFailed, InterruptedException {
        webphone.TwoLinesAgentHangupv2.agentHangupLine2();
    }

    @Test(dependsOnMethods = "agentHangupLine2")
    public void setResultCodeAndCheckAvailableStatus() throws FindFailed, InterruptedException {
        webphone.TwoLinesAgentHangupv2.setResultCodeAndCheckAvailableStatus();
    }

    //TwoLinesClientHangupv2
    @Test()
    public void callOnFirstLine_() throws FindFailed, InterruptedException {
        webphone.TwoLinesAgentHangupv2.callOnFirstLine();
    }

    @Test(dependsOnMethods = "callOnFirstLine_")
    public void callOnSecondLine_() throws FindFailed, InterruptedException {
        webphone.TwoLinesAgentHangupv2.callOnSecondLine();
    }

    @Test(dependsOnMethods = "callOnSecondLine_")
    public void clientHangupLine1() throws FindFailed, InterruptedException {
        webphone.TwoLinesClientHangupv2.clientHangupLine1();
    }

    @Test(dependsOnMethods = "clientHangupLine1")
    public void clientHangupLine2() throws FindFailed, InterruptedException {
        webphone.TwoLinesClientHangupv2.clientHangupLine2();
    }
    @Test(dependsOnMethods = "clientHangupLine2")
    public void setResultCodeAndCheckAvailableStatus_() throws FindFailed, InterruptedException {
        webphone.TwoLinesClientHangupv2.setResultCodeAndCheckAvailableStatus();
    }

    //PDProgressiveReleasedAUXagentHangup
    @Test()
    public void changeStatusToAUX() throws FindFailed, InterruptedException {
        webphoneANDpowerdialer.PDProgressiveReleasedAUXagentHangup.changeStatusToAUX();
    }
    @Test(dependsOnMethods = "changeStatusToAUX")
    public void runSQLQuery() throws FindFailed, InterruptedException, SQLException, ClassNotFoundException {
        webphoneANDpowerdialer.PDProgressiveReleasedAUXagentHangup.runSQLQuery();
    }
    @Test(dependsOnMethods = "runSQLQuery")
    public void waitForCallOnClientSide() throws FindFailed, InterruptedException, SQLException, ClassNotFoundException {
        webphoneANDpowerdialer.PDProgressiveReleasedAUXagentHangup.waitForCallOnClientSide();
    }
    @Test(dependsOnMethods = "waitForCallOnClientSide")
    public void noIncomingCallToAgent() throws FindFailed, InterruptedException, SQLException, ClassNotFoundException {
        webphoneANDpowerdialer.PDProgressiveReleasedAUXagentHangup.noIncomingCallToAgent();
    }
    @Test(dependsOnMethods = "noIncomingCallToAgent")
    public void changeStatusToAvailable() throws FindFailed, InterruptedException, SQLException, ClassNotFoundException {
        webphoneANDpowerdialer.PDProgressiveReleasedAUXagentHangup.changeStatusToAvailable();
    }
    @Test(dependsOnMethods = "changeStatusToAvailable")
    public void waitForCallOnClientSide2() throws FindFailed, InterruptedException, SQLException, ClassNotFoundException {
        webphoneANDpowerdialer.PDProgressiveReleasedAUXagentHangup.waitForCallOnClientSide2();
    }
    @Test(dependsOnMethods = "waitForCallOnClientSide2")
    public void receiveIncomingCallToAgent() throws FindFailed, InterruptedException, SQLException, ClassNotFoundException {
        webphoneANDpowerdialer.PDProgressiveReleasedAUXagentHangup.receiveIncomingCallToAgent();
    }
    @Test(dependsOnMethods = "receiveIncomingCallToAgent")
    public void agentHangup() throws FindFailed, InterruptedException, SQLException, ClassNotFoundException {
        webphoneANDpowerdialer.PDProgressiveReleasedAUXagentHangup.agentHangup();
    }
    @Test(dependsOnMethods = "agentHangup")
    public void setResultCodeAndCheckAvailableStatus__() throws FindFailed, InterruptedException, SQLException, ClassNotFoundException {
        webphoneANDpowerdialer.PDProgressiveReleasedAUXagentHangup.setResultCodeAndCheckAvailableStatus();
    }

    //PDProgressiveReleasedAUXafterCall
    @Test()
    public void runSQLQuery_() throws FindFailed, InterruptedException, SQLException, ClassNotFoundException {
        webphoneANDpowerdialer.PDProgressiveReleasedAUXafterCall.runSQLQuery();
    }
    @Test(dependsOnMethods = "runSQLQuery_")
    public void waitForCallOnClientSide2_() throws FindFailed, InterruptedException, SQLException, ClassNotFoundException {
        webphoneANDpowerdialer.PDProgressiveReleasedAUXafterCall.waitForCallOnClientSide2();
    }
    @Test(dependsOnMethods = "waitForCallOnClientSide2_")
    public void receiveIncomingCallToAgent_() throws FindFailed, InterruptedException, SQLException, ClassNotFoundException {
        webphoneANDpowerdialer.PDProgressiveReleasedAUXafterCall.receiveIncomingCallToAgent();
    }
    @Test(dependsOnMethods = "receiveIncomingCallToAgent_")
    public void changeStatusToAUX_() throws FindFailed, InterruptedException, SQLException, ClassNotFoundException {
        webphoneANDpowerdialer.PDProgressiveReleasedAUXafterCall.changeStatusToAUX();
    }
    @Test(dependsOnMethods = "changeStatusToAUX_")
    public void agentHangup_() throws FindFailed, InterruptedException, SQLException, ClassNotFoundException {
        webphoneANDpowerdialer.PDProgressiveReleasedAUXafterCall.agentHangup();
    }
    @Test(dependsOnMethods = "agentHangup_")
    public void setResultCodeAndCheckAUXStatus() throws FindFailed, InterruptedException, SQLException, ClassNotFoundException {
        webphoneANDpowerdialer.PDProgressiveReleasedAUXafterCall.setResultCodeAndCheckAUXStatus();
    }
    @Test(dependsOnMethods = "setResultCodeAndCheckAUXStatus")
    public void waitForCallOnClientSide_() throws FindFailed, InterruptedException, SQLException, ClassNotFoundException {
        webphoneANDpowerdialer.PDProgressiveReleasedAUXafterCall.waitForCallOnClientSide();
    }
    @Test(dependsOnMethods = "waitForCallOnClientSide_")
    public void noIncomingCallToAgent_() throws FindFailed, InterruptedException, SQLException, ClassNotFoundException {
        webphoneANDpowerdialer.PDProgressiveReleasedAUXafterCall.noIncomingCallToAgent();
    }

    //PDPreviewFreeCallChrome
    @Test
    public void ssoLoginChrome_() throws FindFailed, InterruptedException, SQLException, ClassNotFoundException {
        webphoneANDagentdesktopANDpowerdialer.PDPreviewFreeAUXsubtests.ssoLoginChrome();
    }
    @Test(dependsOnMethods = "ssoLoginChrome_")
    public void changeStatusToAUX__() throws FindFailed, InterruptedException, SQLException, ClassNotFoundException {
        webphoneANDagentdesktopANDpowerdialer.PDPreviewFreeAUXsubtests.changeStatusToAUX();
    }
    @Test(dependsOnMethods = "changeStatusToAUX__")
    public void switchToADTab() throws FindFailed, InterruptedException, SQLException, ClassNotFoundException {
        webphoneANDagentdesktopANDpowerdialer.PDPreviewFreeAUXsubtests.switchToADTab();
    }
    @Test(dependsOnMethods = "switchToADTab")
    public void runSQLQuery__() throws FindFailed, InterruptedException, SQLException, ClassNotFoundException {
        webphoneANDagentdesktopANDpowerdialer.PDPreviewFreeAUXsubtests.runSQLQuery();
    }
    @Test(dependsOnMethods = "runSQLQuery__")
    public void noIncomingCall() throws FindFailed, InterruptedException, SQLException, ClassNotFoundException {
        webphoneANDagentdesktopANDpowerdialer.PDPreviewFreeAUXsubtests.noIncomingCall();
    }
    @Test(dependsOnMethods = "noIncomingCall")
    public void changeStatusToAvailable_() throws FindFailed, InterruptedException, SQLException, ClassNotFoundException {
        webphoneANDagentdesktopANDpowerdialer.PDPreviewFreeAUXsubtests.changeStatusToAvailable();
    }
    @Test(dependsOnMethods = "changeStatusToAvailable_")
    public void switchToADTab__() throws FindFailed, InterruptedException, SQLException, ClassNotFoundException {
        webphoneANDagentdesktopANDpowerdialer.PDPreviewFreeCallChrome.switchToADTab();
    }
    @Test(dependsOnMethods = "switchToADTab__")
    public void agentAcceptCall() throws FindFailed, InterruptedException, SQLException, ClassNotFoundException {
        webphoneANDagentdesktopANDpowerdialer.PDPreviewFreeCallChrome.agentAcceptCall();
    }
    @Test(dependsOnMethods = "agentAcceptCall")
    public void saveCRMCard() throws FindFailed, InterruptedException, SQLException, ClassNotFoundException {
        webphoneANDagentdesktopANDpowerdialer.PDPreviewFreeCallChrome.saveCRMCard();
    }
    @Test(dependsOnMethods = "saveCRMCard")
    public void checkAvailableStatus() throws FindFailed, InterruptedException, SQLException, ClassNotFoundException {
        webphoneANDagentdesktopANDpowerdialer.PDPreviewFreeCallChrome.checkAvailableStatus();
    }



    @AfterSuite
    public void teardown() {
        agentChrome.quit();
        try {
            webphone.TwoLinesClientHangupv2.clientHangupLine1();
            webphone.TwoLinesClientHangupv2.clientHangupLine2();
        } catch (Exception e) {
        }
    }

    /*
    * 1.Login to webphone 2.0.0
    * 2.Call 94636 on the first line
    * 3.Answer call on client side
    * 4.Switch to the second line
    * 5.Call 94626 on the second line
    * 6.Answer call on cilent side
    * 7.Hangup the second line on client side
    * 8.Hangup the first line on client side
    * 9.Choose result code "Удачно"
    * 10.Click save button
    * 11.Verify available status
    * */

}
