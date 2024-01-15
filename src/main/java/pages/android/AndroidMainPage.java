package pages.android;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AndroidMainPage {

    private AppiumDriver driver;

    // Define elements on the page
    By securityMessage = By.xpath("//*[@text='DEVICE SECURITY COMPROMISED']");
    By okButton = By.xpath("//*[@text='OK']");

    public AndroidMainPage(AppiumDriver driver) {
        this.driver = driver;
    }

    // Method for closing the security popup
    public void closeSecurityPopup(){
        if(isSecurityMessageDisplayed()){
            clickOkButton();
        }
    }

    public void clickOkButton() {
        WebElement element = driver.findElement(okButton);
        element.click();
    }

    public boolean isSecurityMessageDisplayed() {
        WebElement element = driver.findElement(securityMessage);
        return element.isDisplayed();
    }
}