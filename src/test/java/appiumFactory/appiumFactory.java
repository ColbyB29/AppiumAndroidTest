package appiumFactory;


import io.appium.java_client.AppiumDriver;
//import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;

import static io.appium.java_client.touch.offset.PointOption.point;


public class appiumFactory {

    private static AndroidDriver driver;

  //  private static AppiumDriver<MobileElement> testDriver;

    public appiumFactory(AndroidDriver driver){
        this.driver = driver;
    }

    //Hard coded tapping the start button by the x and y cords
    public static void TapStartButton(int x, int y){
        new TouchAction(driver).tap(point(325,1300)).perform().release();
    }


}
