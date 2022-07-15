package com.ti.framework.utils.ui;

import static com.ti.framework.config.Constants.SCREENSHOT_FOLDER;
import static com.ti.framework.config.CreateFolder.createFolder;

import com.ti.framework.base.DriverFactory;
import com.ti.framework.base.FrameworkException;
import com.ti.framework.utils.logs.Log;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumUtil {

  private static WebDriver driver = DriverFactory.getInstance().getDriver();

  public static WebElement highLight(WebElement element) {
    for (int i = 0; i < 3; i++) {
      //Creating JavaScriptExecuter Interface
      JavascriptExecutor js = (JavascriptExecutor) driver;
      for (int iCnt = 0; iCnt < 3; iCnt++) {
        js.executeScript("arguments[0].setAttribute('style','background: yellow')",
            element);
        try {
          Thread.sleep(20);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
        js.executeScript("arguments[0].setAttribute('style','background:')", element);
      }
    }
    return element;
  }

  public static String getScreenShot(WebElement element) {
    String ssDateTime = new SimpleDateFormat("yyMMddHHmmss")
        .format(Calendar.getInstance().getTime());
    String file = null;
    try {
      file = createFolder(SCREENSHOT_FOLDER) + "/" + ssDateTime + ".png";
    } catch (FrameworkException e) {
      e.printStackTrace();
    }

    try {
      File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      FileUtils.copyFile(
          (element == null ? srcFile : element.getScreenshotAs(OutputType.FILE)),
          new File(file));
    } catch (Exception e) {
      Log.error(
          "Class SeleniumUtils | Method takeSnapShot | Exception desc: " + e.getMessage());
    }
    return file;
  }

  public static void scrollToElement(WebElement element) throws FrameworkException {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    try{
      js.executeScript("arguments[0].scrollIntoView(true)", element);
    }catch (JavascriptException jse){
      throw new FrameworkException(jse.getMessage());
    }
  }

  public static void refresh(){
    driver.navigate().refresh();
  }
}
