package test;

import static com.ti.framework.config.Constants.BASE_URL;
import static com.ti.framework.config.Constants.VIDEO_FOLDER;
import static com.ti.framework.utils.data.SQLUtils.closeConnection;

import com.ti.framework.base.BasePage;
import com.ti.framework.base.BrowserType;
import com.ti.framework.base.DriverFactory;
import com.ti.framework.config.PropertyManager;
import com.ti.framework.utils.logs.Log;
import com.ti.framework.utils.video.SpecializedScreenRecorder;
import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class Base extends BasePage {
    protected static SpecializedScreenRecorder videoRec;

    @BeforeTest
    @Parameters("browser")
    void setup(String browser) throws IOException, AWTException {
        DriverFactory.getInstance().setDriver(BrowserType.valueOf(browser));
        if(Boolean.parseBoolean(PropertyManager.getInstance().getProperty("Video"))){
            videoRec.startRecording(VIDEO_FOLDER);
        }
        DriverFactory.getInstance().getDriver().navigate().to(BASE_URL);
        Log.info("Tests is starting!");
    }

    @AfterTest
    void turnDown() throws Exception {
        Log.info("Tests are ending!");
        DriverFactory.getInstance().removeDriver();
        if(videoRec != null){
            videoRec.stopRecording();
        }

        closeConnection();
    }
}
