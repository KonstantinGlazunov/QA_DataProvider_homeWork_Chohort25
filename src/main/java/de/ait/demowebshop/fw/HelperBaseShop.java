package de.ait.demowebshop.fw;

import ch.qos.logback.core.util.FileSize;
import com.google.common.io.Files;
import de.ait.demowebshop.utils.Recorder;
import org.monte.media.Format;
import org.monte.media.FormatKeys;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class HelperBaseShop {
    WebDriver driver;

    public HelperBaseShop(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();



    }

    public void type(String hashtag, String text) {
        if(text!=null){
        driver.findElement(By.cssSelector(hashtag)).click();
        driver.findElement(By.cssSelector(hashtag)).clear();
        driver.findElement(By.cssSelector(hashtag)).sendKeys(text);
        }
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }


    public String takeScreenShot(){
        File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screenShot = new File("screenshots/screen" + System.currentTimeMillis() / 1000 + ".png");

        try {
            Files.copy(tmp, screenShot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return screenShot.getAbsolutePath();
    }
    private ScreenRecorder screenRecorder;

    public  void startRecording() throws IOException, AWTException {
        File file = new File("record");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;

        Rectangle captureSize = new Rectangle(0, 0, width, height);

        GraphicsConfiguration gc = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();

        screenRecorder = new Recorder(gc, captureSize,
                new Format(MediaTypeKey, FormatKeys.MediaType.FILE, MimeTypeKey, MIME_AVI),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_MJPG,
                        CompressorNameKey, ENCODING_AVI_MJPG, DepthKey, 24, FrameRateKey,
                        Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
                null, file, "MyVideo");
    screenRecorder.start();
    }
    public  void stopRecording() throws IOException {
screenRecorder.stop();
    }
    public void deleteScreenCast(){
        File directory = new File("record");
        File[] files = directory.listFiles();
        for(File file : files){
            file.delete();
        }
    }

    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
