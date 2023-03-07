package org.example;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.URI;
import java.net.URISyntaxException;

public class Main extends BaseTest{

    private AndroidDriver driver;
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

}