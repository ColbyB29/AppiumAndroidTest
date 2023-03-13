package tesseractFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.awt.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.io.File;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.openqa.selenium.Point;
import appiumFactory.appiumFactory;
import javax.imageio.ImageIO;


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

        BufferedImage img = ImageIO.read(screen);

        BufferedImage BetImage = img.getSubimage(60, 1085, 120, 45);

        //this line saves the screenshot to the imgs file
        //ImageIO.write(BetImage, "png", f);

        String BetText = tesseract.doOCR(BetImage).trim();

        System.out.println("Bet Amount Text:" + BetText);

        return BetText;
    }

    public String GetMinorJackpot(WebDriver driver) throws TesseractException, IOException {
        File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        //this line is the filepath object if we need to save a screenshot to the imgs folder
        //File f = new File(System.getProperty("user.dir") + "\\imgs\\"+"testName1"+strDate+".png");

        BufferedImage img = ImageIO.read(screen);

        BufferedImage MinorImage = img.getSubimage(65, 185, 150, 50);

        //this line saves the screenshot to the imgs file
        // ImageIO.write(dest, "png", f);

        String MinorText = tesseract.doOCR(MinorImage).trim();

        System.out.println("Minor Text: " + MinorText);

        return MinorText;
    }

    public String GetMajorJackpot(WebDriver driver) throws TesseractException, IOException {
        File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        //this line is the filepath object if we need to save a screenshot to the imgs folder
        //File f = new File(System.getProperty("user.dir") + "\\imgs\\"+"testName1.png");

        BufferedImage img = ImageIO.read(screen);

        BufferedImage MajorImage = img.getSubimage(505, 185, 150, 50);

        //this line saves the screenshot to the imgs file
        //ImageIO.write(MajorImage, "png", f);

        String MajorText = tesseract.doOCR(MajorImage).trim();

        System.out.println("Major Text: " + MajorText);

        return MajorText;
    }



    public void OCRJackpots(WebDriver driver, WebElement element, appiumFactory factory) throws IOException, TesseractException {

        int counter = 0;
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_hh-mm");
        String strDate = dateFormat.format(date);
        Point p = element.getLocation();
        results = new ArrayList<OCRResult>();


        //Loop over every minor and major jackpot amounts starting by incrementing the bet upwards
        for(counter = 0; counter <= 5;counter++){

            results.add(new OCRResult(GetMinorJackpot(driver),GetMajorJackpot(driver),GetBetAmount(driver)));

            //Verify minor
            //System.out.print("minor text: "+GetMinorJackpot(driver));

            //Next, we verify major
            //System.out.print("major text: "+GetMajorJackpot(driver));

            //Next, we verify bet amount
            //System.out.print("bet text: "+GetBetAmount(driver));


            factory.TapOnScreen(150,1200);
       }

        counter = 0;
        for(counter = 0; counter <= 7;counter++){

            results.add(new OCRResult(GetMinorJackpot(driver),GetMajorJackpot(driver),GetBetAmount(driver)));

            factory.TapOnScreen(150,1275);

        }
        counter = 0;
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
