package tesseractFactory;
import org.openqa.selenium.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import appiumFactory.appiumFactory;
import javax.imageio.ImageIO;

//Tesseract Factory is responsible for doing the Optical character recognition related fuctions
public class tesseractFactory {

    ArrayList<OCRResult> results = null;
    Tesseract tesseract = null;
    public tesseractFactory(){
        tesseract = new Tesseract();
        tesseract.setDatapath("C:\\Users\\colby.bradley\\Desktop\\Tesseract\\Tess4J\\tessdata");
    }

    public String GetBetAmount(WebDriver driver) throws IOException, TesseractException {
        File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        //this line is the filepath object if we need to save a screenshot to the imgs folder
        //File f = new File(System.getProperty("user.dir") + "\\imgs\\"+"testName2.png");

        //Image of whole screen
        BufferedImage img = ImageIO.read(screen);

        //Area on screen where the bet amount is located
        BufferedImage BetImage = img.getSubimage(60, 1085, 120, 45);

        //this line saves the screenshot to the imgs file
        //ImageIO.write(BetImage, "png", f);

        //Setting BetText to a trimmed string. trimming removes new lines(/n) that are put at the end of strings
        String BetText = tesseract.doOCR(BetImage).trim();

        System.out.println("Bet Amount Text:" + BetText);

        return BetText;
    }

    public String GetMinorJackpot(WebDriver driver) throws TesseractException, IOException {
        File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        //this line is the filepath object if we need to save a screenshot to the imgs folder
        //File f = new File(System.getProperty("user.dir") + "\\imgs\\"+"testName1"+strDate+".png");

        //Image of the whole screen
        BufferedImage img = ImageIO.read(screen);

        //Area on screen where the Minor Jackpot is located
        BufferedImage MinorImage = img.getSubimage(65, 185, 150, 50);

        //this line saves the screenshot to the imgs file
        // ImageIO.write(dest, "png", f);

        //Setting MinorText to a trimmed string. trimming removes new lines(/n) that are put at the end of strings
        String MinorText = tesseract.doOCR(MinorImage).trim();

        System.out.println("Minor Text: " + MinorText);

        return MinorText;
    }

    public String GetMajorJackpot(WebDriver driver) throws TesseractException, IOException {
        File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        //this line is the filepath object if we need to save a screenshot to the imgs folder
        //File f = new File(System.getProperty("user.dir") + "\\imgs\\"+"testName1.png");

        //Image of the whole screen
        BufferedImage img = ImageIO.read(screen);

        //Area on screen where the Major Jackpot is located
        BufferedImage MajorImage = img.getSubimage(505, 185, 150, 50);

        //this line saves the screenshot to the imgs file
        //ImageIO.write(MajorImage, "png", f);

        //Setting MajorText to a trimmed string. trimming removes new lines(/n) that are put at the end of strings
        String MajorText = tesseract.doOCR(MajorImage).trim();

        System.out.println("Major Text: " + MajorText);

        return MajorText;
    }



    public void OCRJackpots(WebDriver driver, appiumFactory factory) throws IOException, TesseractException {

        int counter = 0; // Used in the for loops
        results = new ArrayList<OCRResult>(); // ArrayList that contains OCRResult objects to verify after all results are added


        // Loop over every minor and major jackpot amounts starting by incrementing the bet upwards
        for(counter = 0; counter <= 5;counter++){

            // Creating a new OCRResult and adding it to our ArrayList
            results.add(new OCRResult(GetMinorJackpot(driver),GetMajorJackpot(driver),GetBetAmount(driver)));

            // Tapping on the bet up button
            factory.TapOnScreen(150,1200);
       }

        // At this point we have reached the highest value bet, now we need to loop over the rest of the bets by going down
        // Reset the counter
        counter = 0;


        // Loop over every minor and major jackpot amounts by decrementing the bet downwards from the highest bet
        for(counter = 0; counter <= 7;counter++){

            // Creating a new OCRResult and adding it to our ArrayList
            results.add(new OCRResult(GetMinorJackpot(driver),GetMajorJackpot(driver),GetBetAmount(driver)));

            // Tapping on the bet down button
            factory.TapOnScreen(150,1275);

        }

        // Using the same counter to iterate over the list of results.
        // Resetting the counter
        counter = 0;

        // For loop for incrementing over the results ArrayList.
        for(counter = 0; counter <= results.size()-1;counter++){
            if(results.get(counter).VerifyResults()){
                System.out.println("test passed. minor jackpot = " + results.get(counter).GetMinorResult() + " major jackpot = " + results.get(counter).GetMajorResult() + " bet amount = " + results.get(counter).GetBetAmountResult());
            }
            else{
                System.out.println("test failed. minor jackpot = " + results.get(counter).GetMinorResult() + " major jackpot = " + results.get(counter).GetMajorResult() + " bet amount = " + results.get(counter).GetBetAmountResult());
            }
        }



    }

}
