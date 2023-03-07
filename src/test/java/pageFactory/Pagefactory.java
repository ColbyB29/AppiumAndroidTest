package pageFactory;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class Pagefactory {

    public Pagefactory(AndroidDriver driver){
        PageFactory.initElements(driver, this);
    }
    @FindBy(how = How.TAG_NAME, using ="button")
    List<WebElement> btns;

    @FindBy(id = "environment-select")
    WebElement environmentDropdown;

    @FindBy(id = "jurisdiction-select")
    WebElement jurisdictionDropdown;

    @FindBy(id = "game-select")
    WebElement gameDropdown;

    @FindBy(id = "currency-select")
    WebElement currencyDropdown;

    @FindBy(id = "locale-select")
    WebElement localeDropdown;

    @FindBy(id = "launch-in-tab")
    WebElement launchBtn;

    @FindBy(id = "showcheat-checkbox")
    WebElement cheatBtn;

    public void selectGame(String game){
        Select drp = new Select(gameDropdown);
        drp.selectByVisibleText(game);
    }

    public void clickLaunch(){
        launchBtn.click();
    }

    public void setEnvt(String envt) {

        Select drp = new Select(environmentDropdown);
        drp.selectByVisibleText(envt);
    }

    public void setCheatStatus(String cheatStatus) {
        if(cheatStatus=="on"){
            cheatBtn.click();
        }
    }

    @FindBy(how=How.ID, using = "game-wrapper")
    WebElement gameWrapper;

    public int selectgameWrapper(){
        WebElement canvas = gameWrapper.findElement(By.tagName("canvas"));
        int height = Integer.parseInt(canvas.getAttribute("height"));
        return height;
    }

}
