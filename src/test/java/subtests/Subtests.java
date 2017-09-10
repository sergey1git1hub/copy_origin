package subtests;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import org.testng.annotations.Test;

/**
 * Created by SChubuk on 01.09.2017.
 */
public class Subtests {

    //WebDriver driver;

    App cxphone;
    Screen screen;
    org.sikuli.script.Pattern button_3CXAcceptCall;
    org.sikuli.script.Pattern closePhoneWindow;



    @Test
    public void test1() throws FindFailed {
        cxphone = App.open("C:\\Program Files (x86)\\3CXPhone\\3CXPhone.exe");
        screen = new Screen();
        button_3CXAcceptCall = new org.sikuli.script.Pattern("C:\\SikuliImages\\button_3CXAcceptCall.png");
        screen.wait(button_3CXAcceptCall, 10);
        screen.click(button_3CXAcceptCall);
        closePhoneWindow = new org.sikuli.script.Pattern("C:\\SikuliImages\\closePhoneWindow.png");
        screen.wait(closePhoneWindow, 10);
        screen.click(closePhoneWindow);
    }


    @Test
    public void test2() throws FindFailed {
        screen.wait(button_3CXAcceptCall, 10);
        screen.click(button_3CXAcceptCall);

    }

}
