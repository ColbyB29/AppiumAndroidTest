package testpackage;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.sourceforge.tess4j.TesseractException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import pageFactory.Pagefactory;
import appiumFactory.appiumFactory;
import tesseractFactory.tesseractFactory;

public class AndroidChromeTest {

    WebDriver driver;

    ChromeOptions options=new ChromeOptions();

    URL url;

    Pagefactory pagefactory;

    appiumFactory appiumFactory1;

    tesseractFactory tesseractFactory1;

    @BeforeMethod
    @Given("Landing on homepage")
    public void setUp() throws MalformedURLException {

        //Setting up our Android environment for testing, Currently it is using my Samsung Galaxy A03s
        options.setPlatformName("Android");

        options.setAndroidDeviceSerialNumber("N000TA1183962301141");

        options.setBrowserVersion("110");

        url = new URL("http://localhost:4723/wd/hub");

        driver = new AndroidDriver(url, options);

        pagefactory = new Pagefactory((AndroidDriver) driver);

        appiumFactory1 = new appiumFactory((AndroidDriver) driver);

        tesseractFactory1 = new tesseractFactory();
        //==============================================================================================

        // Verify Title is the expected value
        verifyTitle("PGS Test Suite");


    }

    // Launches the website in test and accepts a string parameter as the expected game title and throws an assert if the title doesnt match
    public void verifyTitle(String expectedGameTitle) throws MalformedURLException {
        driver.get("https://pgs-clients.pgs.casino/dev/lobby/89-ad6e648/index.html");
        Assert.assertEquals(driver.getTitle(), expectedGameTitle);

    }

    // Set the environment and cheat status. The parameters are supplied in the main.feature file
    @When("Selecting an Environment {string} and toggle cheat {string}")
    public void selectingAnEnvironmentAndToggleCheat(String envt, String cheatStatus) throws InterruptedException {
        Thread.sleep(2000);
        pagefactory.setEnvt(envt);
        pagefactory.setCheatStatus(cheatStatus);

    }

    // We pick the game parameter in the main.feature file, we can click launch with selenium finding the button by id
    @And("Selecting a game {string}")
    public void selectingAGame(String game) throws InterruptedException {
        //Legendary Larry seems to be our main test game

        Thread.sleep(2000);
        pagefactory.selectGame(game);
        pagefactory.clickLaunch();

        Thread.sleep(40000); // hard coding is inconsistant

        //appium
        appiumFactory1.TapOnScreen(325,1300);
    }

    // Using selenium to find the game-container object, Asserting if we do find it means that the game has been successfully loaded
    @Then("I verify that the game is loading")
    public void i_verify_that_the_game_is_loading() throws InterruptedException {
        Thread.sleep(5000);
        WebElement element = driver.findElement(By.id("game-container"));
        System.out.println(element.getTagName());
        Assert.assertEquals(element.getTagName(),"iframe");

    }

    @Given("Verify Minor and Major Jackpots")
    public void tesseractOCRJackpots() throws Exception {

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

        // New tab is open. we need to switch focus to that new window
        System.out.println(driver.getTitle());
        driver.switchTo().window(tabs.get(1));
        System.out.println(driver.getTitle());


        tesseractFactory1.OCRJackpots(driver,appiumFactory1);
    }

    @Then("testing")
    public void TestMethod() throws TesseractException, IOException, InterruptedException {
       // tesseractFactory1 = new tesseractFactory();

        tesseractFactory1.GetBetAmount(driver);
    }

    @After
    public void tearDown() throws Exception {
        driver.close();
        driver.quit();

    }
}
