package tesseractFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.awt.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.io.File;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.openqa.selenium.Point;
import appiumFactory.appiumFactory;
import javax.imageio.ImageIO;

public class tesseractFactory {

    File fileToWorkWith = null;
    Tesseract tesseract = null;
    public tesseractFactory(){
        tesseract = new Tesseract();
        tesseract.setDatapath("C:\\Users\\colby.bradley\\Desktop\\Tesseract\\Tess4J\\tessdata");
    }
    public void captureScreenshot(String testName, WebDriver driver) throws AWTException, TesseractException, IOException {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_hh-mm");
        String strDate = dateFormat.format(date);

        // Take screenshot of mobile devices screen (whole screen)
        /*
        try {
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            fileToWorkWith = scrFile;
            FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "\\imgs\\"+testName+strDate+".png"), true);
        } catch (Exception e) {
            System.out.println(e);
        }

         */


        // Takes screenshot of desktop screen inside the rect

        Rectangle rect = new Rectangle(65, 330, 120, 50);
        Robot robot = new Robot();
        BufferedImage img = robot.createScreenCapture(rect);
        String text = tesseract.doOCR(img);
        System.out.print("text: "+text);

        File outputfile = new File(System.getProperty("user.dir") + "\\imgs\\"+testName+strDate+".png");
        ImageIO.write(img, "png", outputfile);

        //FileUtils.copyFile(imgFile, new File(System.getProperty("user.dir") + "\\imgs\\"+testName+strDate+".png"), true);
    }

    public void VerifyMinor() throws TesseractException {
        Tesseract tesseract = new Tesseract();


        Rectangle rect = new Rectangle(65, 630, 120, 50);


        try {



           // File img = new File("somefile.png");
            BufferedImage image = ImageIO.read(fileToWorkWith);


            // the path of your tess data folder
            // inside the extracted file
            String text = tesseract.doOCR(fileToWorkWith,rect);

            // path of your image file
            System.out.print("text: "+text);
        } catch (TesseractException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String GetMinorJackpot(WebDriver driver) throws TesseractException, IOException {
        File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        //this line is the filepath object if we need to save a screenshot to the imgs folder
        //File f = new File(System.getProperty("user.dir") + "\\imgs\\"+"testName1"+strDate+".png");

        BufferedImage img = ImageIO.read(screen);

        BufferedImage MinorImage = img.getSubimage(65, 185, 150, 50);

        //this line saves the screenshot to the imgs file
        // ImageIO.write(dest, "png", f);

        String MinorText = tesseract.doOCR(MinorImage);

        return MinorText;
    }

    public String GetMajorJackpot(WebDriver driver) throws TesseractException, IOException {
        File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        //this line is the filepath object if we need to save a screenshot to the imgs folder
        File f = new File(System.getProperty("user.dir") + "\\imgs\\"+"testName1.png");

        BufferedImage img = ImageIO.read(screen);

        BufferedImage MajorImage = img.getSubimage(505, 185, 150, 50);

        //this line saves the screenshot to the imgs file
         ImageIO.write(MajorImage, "png", f);

        String MajorText = tesseract.doOCR(MajorImage);

        return MajorText;
    }

    // This method still needs more logic added to it. It is missing a way to verify that the result from OCR is currently valid at the current bet level
    public void OCRJackpots(WebDriver driver, WebElement element, appiumFactory factory, int xPos, int yPos, int width, int height) throws IOException, TesseractException {

        int counter = 0;
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_hh-mm");
        String strDate = dateFormat.format(date);
        Point p = element.getLocation();


        //Loop over every minor and major jackpot amounts starting by incrementing the bet upwards
        for(counter = 0; counter <= 5;counter++){
            //Verify minor
            System.out.print("minor text: "+GetMinorJackpot(driver));

            //Next, we verify major
            System.out.print("major text: "+GetMajorJackpot(driver));

            factory.TapOnScreen(150,1200);
       }

        counter = 0;
        for(counter = 0; counter <= 7;counter++){

            //Minor
            System.out.print("minor text: "+GetMinorJackpot(driver));

            //Major
            System.out.print("major text: "+GetMajorJackpot(driver));

            factory.TapOnScreen(150,1275);

        }








    }

}
