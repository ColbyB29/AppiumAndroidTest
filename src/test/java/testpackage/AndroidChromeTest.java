package testpackage;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import pageFactory.Pagefactory;
import appiumFactory.appiumFactory;

public class AndroidChromeTest {

    WebDriver driver;

    ChromeOptions options=new ChromeOptions();

    URL url;

    Pagefactory pagefactory;

    appiumFactory appiumFactory1;

    @BeforeMethod
    @Given("Landing on homepage")
    public void setUp() throws MalformedURLException {

        options.setPlatformName("Android");

        options.setAndroidDeviceSerialNumber("N000TA1183962301141");

        options.setBrowserVersion("110");

        url = new URL("http://localhost:4723/wd/hub");

        driver = new AndroidDriver(url, options);

        pagefactory = new Pagefactory((AndroidDriver) driver);

        appiumFactory1 = new appiumFactory((AndroidDriver) driver);

        verifyTitle();


    }


    public void verifyTitle() throws MalformedURLException {
        driver.get("https://pgs-clients.pgs.casino/dev/lobby/89-ad6e648/index.html");
        Assert.assertEquals(driver.getTitle(), "PGS Test Suite");

    }

    @When("Selecting an Environment {string} and toggle cheat {string}")
    public void selectingAnEnvironmentAndToggleCheat(String envt, String cheatStatus) throws InterruptedException {
        Thread.sleep(2000);
        pagefactory.setEnvt(envt);
        pagefactory.setCheatStatus(cheatStatus);

    }

    @And("Selecting a game {string}")
    public void selectingAGame(String game) throws InterruptedException {
        //Legendary Larry

        Thread.sleep(2000);
        //  driver.close();
        pagefactory.selectGame(game);
        pagefactory.clickLaunch();

        Thread.sleep(30000); // hard coding is inconsistant

        //appium
        appiumFactory1.TapStartButton(350,1325);
    }

    @Then("I verify that the game is loading")
    public void i_verify_that_the_game_is_loading() throws InterruptedException {
        //some steps
        Thread.sleep(5000);
        WebElement element = driver.findElement(By.id("game-container"));
        System.out.println(element.getTagName());
        // Assert.assertEquals();
        driver.quit();
    }

}
