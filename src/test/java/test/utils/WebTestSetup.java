package test.utils;

import helpers.ConfigLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import pages.web.WebCreateWalletPage;
import pages.web.WebMainPage;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;

public class WebTestSetup {

    public WebDriver driver;
    public WebCreateWalletPage createPage;
    public WebMainPage mainPage;

    @Before
    public void setUp() throws InterruptedException {
        // Load properties from config file
        String chromeDriverPath = ConfigLoader.getInstance().getProperty("webdriver.chrome.driver");
        String extensionPath = ConfigLoader.getInstance().getProperty("extension.path");

        // Set up driver
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        // Set up ChromeOptions to add the extension
        ChromeOptions options = new ChromeOptions();
        options.addExtensions(new File(extensionPath));
        // Initialize WebDriver with the options
        driver = new ChromeDriver(options);

        // Wait for the new tab to open
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(driver -> driver.getWindowHandles().size() > 1);

        // Get a list of all tab handles
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

        // Switch to the new tab (the last one in the list)
        driver.switchTo().window(tabs.get(tabs.size() - 1));
        // Initialize WebCreateWalletPage and WebMainPage
        createPage = new WebCreateWalletPage(driver);
        mainPage = new WebMainPage(driver);

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