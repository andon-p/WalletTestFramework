package test.utils;

import org.openqa.selenium.remote.DesiredCapabilities;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.appium.java_client.android.AndroidDriver;
import pages.android.AndroidCreateNewWalletPage;
import pages.android.AndroidMainPage;

import java.net.URL;

public class AndroidTestSetup {

    public AndroidDriver driver;
    public AndroidMainPage mainPage;
    public AndroidCreateNewWalletPage createNewWalletPage;

    @Before
    public void setUp() throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:deviceName", "Pixel 6 API 27");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:app", "/Users/andonpopchev/Desktop/v8.0.3_release.apk");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:platformVersion", "8.1");

        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);


        mainPage = new AndroidMainPage(driver);
        createNewWalletPage = new AndroidCreateNewWalletPage(driver);

    }

    @After
    public void tearDown() {
        try {
            if (driver != null) {
                driver.quit();
                driver = null;
            }
        } catch (Exception e) {
            System.out.println("Exception while trying to quit the driver: " + e.getMessage());
        }
    }
}