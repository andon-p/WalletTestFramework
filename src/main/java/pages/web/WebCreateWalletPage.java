package pages.web;

import helpers.ConfigLoader;
import helpers.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;


public class WebCreateWalletPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private SeleniumHelper helper;

    // Define elements on page
    By welcomeTitle = By.xpath("//*[@data-testid='onboarding-step-title']");
    By createNewWalletButton = By.xpath("//*[contains(text(), 'Create a new wallet')]");
    By passwordInput = By.xpath("//input[@type='password'][1]");
    By passwordConfirm = By.xpath("//p[text()='Confirm new password']/following-sibling::div//input[@type='password']");
    By acceptTerms = By.xpath("//*[contains(text(), 'I have read and agree to the ')]");
    By nextButton = By.xpath("//button[@type='submit' and text()='Next']");
    By shareDataBtn = By.xpath("//*[contains(text(), 'Share data')]");
    By openWalletButton = By.xpath("//button[@type='button' and text()='Open wallet']");

    By gotItButton = By.xpath("//*[text()='Got it']");
    By readyButton = By.xpath("//*[text()='I’m ready to use Trust Wallet']");

    public WebCreateWalletPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));  // wait for up to 30 seconds
        this.helper = new SeleniumHelper(driver);
    }

    // Method for creating wallet
    public void createWallet() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeTitle)).click();
        wait.until(ExpectedConditions.elementToBeClickable(createNewWalletButton)).click();

        // Get password from config file
        String password = ConfigLoader.getInstance().getProperty("wallet.password");

        // Enter password
        driver.findElement(passwordInput).sendKeys(password);
        WebElement passwordConfirmElement = wait.until(ExpectedConditions.elementToBeClickable(passwordConfirm));
        passwordConfirmElement.click();
        passwordConfirmElement.sendKeys(password);

        // Accept terms
        driver.findElement(acceptTerms).click();

        // Click 'Next' button
        driver.findElement(nextButton).click();

        // Click Share Data and Open Wallet
        wait.until(ExpectedConditions.elementToBeClickable(shareDataBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(openWalletButton)).click();

        // Go through the tips
        wait.until(ExpectedConditions.visibilityOfElementLocated(gotItButton));
        helper.retryClick(wait.until(ExpectedConditions.elementToBeClickable(gotItButton)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(readyButton));
        helper.retryClick(wait.until(ExpectedConditions.elementToBeClickable(readyButton)));


    }
}