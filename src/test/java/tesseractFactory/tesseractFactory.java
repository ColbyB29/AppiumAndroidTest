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

    public void shootWebElement(WebDriver driver, WebElement element) throws IOException, TesseractException {

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_hh-mm");
        String strDate = dateFormat.format(date);
        File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        Point p = element.getLocation();

        int width = 150;
        int height = 50;

        BufferedImage img = ImageIO.read(screen);

        BufferedImage dest = img.getSubimage(p.getX() + 65, p.getY() + 185, width, height);

        ImageIO.write(dest, "png", screen);

        File f = new File(System.getProperty("user.dir") + "\\imgs\\"+"testName1"+strDate+".png");

        FileUtils.copyFile(screen, f);

        String text = tesseract.doOCR(dest);
        System.out.print("text: "+text);
    }

}
