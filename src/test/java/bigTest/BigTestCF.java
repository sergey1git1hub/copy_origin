package bigTest;

import org.sikuli.script.FindFailed;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static data.Data.agentChrome;


public class BigTestCF {

    @Test
    public void controlFlow() throws InterruptedException, FindFailed, SQLException, ClassNotFoundException {
        /*//TwoLinesAgentHangupv2
        webphone.TwoLinesAgentHangupv2.ssoLoginChrome();
        webphone.TwoLinesAgentHangupv2.callOnFirstLine();
        webphone.TwoLinesAgentHangupv2.callOnSecondLine();
        webphone.TwoLinesAgentHangupv2.agentHangupLine1();
        webphone.TwoLinesAgentHangupv2.agentHangupLine2();
        webphone.TwoLinesAgentHangupv2.setResultCodeAndCheckAvailableStatus();

        //TwoLinesClientHangupv2
        webphone.TwoLinesClientHangupv2.callOnFirstLine();
        webphone.TwoLinesClientHangupv2.callOnSecondLine();
        webphone.TwoLinesClientHangupv2.clientHangupLine1();
        webphone.TwoLinesClientHangupv2.clientHangupLine2();
        webphone.TwoLinesClientHangupv2.setResultCodeAndCheckAvailableStatus();

        //PDProgressiveReleasedAUXagentHangup
        webphoneANDpowerdialer.PDProgressiveReleasedAUXagentHangup.changeStatusToAUX();
        webphoneANDpowerdialer.PDProgressiveReleasedAUXagentHangup.runSQLQuery();
        webphoneANDpowerdialer.PDProgressiveReleasedAUXagentHangup.waitForCallOnClientSide();
        webphoneANDpowerdialer.PDProgressiveReleasedAUXagentHangup.noIncomingCallToAgent();
        webphoneANDpowerdialer.PDProgressiveReleasedAUXagentHangup.changeStatusToAvailable();
        webphoneANDpowerdialer.PDProgressiveReleasedAUXagentHangup.waitForCallOnClientSide2();
        webphoneANDpowerdialer.PDProgressiveReleasedAUXagentHangup.receiveIncomingCallToAgent();
        webphoneANDpowerdialer.PDProgressiveReleasedAUXagentHangup.agentHangup();
        webphoneANDpowerdialer.PDProgressiveReleasedAUXagentHangup.setResultCodeAndCheckAvailableStatus();

        //PDProgressiveReleasedAUXafterCall
        webphoneANDpowerdialer.PDProgressiveReleasedAUXafterCall.runSQLQuery();
        webphoneANDpowerdialer.PDProgressiveReleasedAUXafterCall.waitForCallOnClientSide2();
        webphoneANDpowerdialer.PDProgressiveReleasedAUXafterCall.receiveIncomingCallToAgent();
        webphoneANDpowerdialer.PDProgressiveReleasedAUXafterCall.changeStatusToAUX();
        webphoneANDpowerdialer.PDProgressiveReleasedAUXafterCall.agentHangup();
        webphoneANDpowerdialer.PDProgressiveReleasedAUXafterCall.setResultCodeAndCheckAUXStatus();
        webphoneANDpowerdialer.PDProgressiveReleasedAUXafterCall.waitForCallOnClientSide();
        webphoneANDpowerdialer.PDProgressiveReleasedAUXafterCall.noIncomingCallToAgent();*/

        //PDPreviewFreeCallChrome
        webphoneANDagentdesktopANDpowerdialer.PDPreviewFreeAUXsubtests.ssoLoginChrome();
        webphoneANDagentdesktopANDpowerdialer.PDPreviewFreeAUXsubtests.changeStatusToAUX();
        webphoneANDagentdesktopANDpowerdialer.PDPreviewFreeAUXsubtests.switchToADTab();
        webphoneANDagentdesktopANDpowerdialer.PDPreviewFreeAUXsubtests.runSQLQuery();
        webphoneANDagentdesktopANDpowerdialer.PDPreviewFreeAUXsubtests.noIncomingCall();
        webphoneANDagentdesktopANDpowerdialer.PDPreviewFreeAUXsubtests.changeStatusToAvailable();
        webphoneANDagentdesktopANDpowerdialer.PDPreviewFreeCallChrome.switchToADTab();
        webphoneANDagentdesktopANDpowerdialer.PDPreviewFreeCallChrome.agentAcceptCall();
        webphoneANDagentdesktopANDpowerdialer.PDPreviewFreeCallChrome.saveCRMCard();
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
