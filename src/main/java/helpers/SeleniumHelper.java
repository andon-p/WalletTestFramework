package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumHelper {

    private WebDriver driver;

    public SeleniumHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void retryClick(WebElement element) {
        final int MAX_ATTEMPTS = 10;
        for (int i = 0; i < MAX_ATTEMPTS; i++) {
            try {
                element.click();
                return; // if click successful, exit the method
            } catch (ElementClickInterceptedException e) {
                System.out.println("Click intercepted, retrying (" + (i + 1) + "/" + MAX_ATTEMPTS + ")");
            }
        }
        throw new RuntimeException("Failed to click element after " + MAX_ATTEMPTS + " attempts");
    }
}