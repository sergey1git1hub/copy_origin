package sikuli;

import org.junit.Test;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;



public class sikuli3cx {



    @Test

    public void use3cxphone() throws FindFailed {

//Open cxphone application with google home page
        App cxphone = App.open("C:\\Program Files (x86)\\3CXPhone\\3CXPhone.exe");
//Create and initialize an instance of Screen object
        Screen screen = new Screen();
//Add image path
        Pattern image = new Pattern("C:\\SikuliImages\\button_AcceptCall.png");
        screen.wait(image, 10);
//Click on the image
        screen.click(image);
        //for second line
        screen.wait(image, 10);
//Click on the image
        screen.click(image);
        screen.wait(image, 10);
//Click on the image
        screen.click(image);
//Close cxphone
        //cxphone.close();
    }

}