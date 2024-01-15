package pages.android;


import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;


public class AndroidCreateNewWalletPage {

    private AndroidDriver driver;
    private Wait<AndroidDriver> wait;


    // Define the elements on the page
    By createWalletButton = By.xpath("//android.widget.Button[@package='com.wallet.crypto.trustapp']");
    By createNewWalletButton = By.xpath("//android.widget.TextView[@text='Create new wallet']");
    By firstCheckmark = By.xpath("//android.widget.TextView[@text='Trust Wallet does not keep a copy of your secret phrase.']");
    By backupSecretPhraseButton = By.xpath("//android.widget.TextView[@text='Back up secret phrase']");
    By backupManuallyButton = By.xpath("//android.widget.TextView[@text='Back up manually']");
    By skipButton = By.xpath("//android.widget.TextView[@text='SKIP']");
    By secondCheckmark = By.xpath("//android.widget.TextView[@text='Saving this digitally in plain text is NOT recommended. Examples include screenshots, text files, or emailing yourself']");
    By thirdCheckmark = By.xpath("//android.widget.TextView[@text='Write down your secret phrase, and store it in a secure offline location!']");
    By continueButton = By.xpath("//android.widget.TextView[@text='Continue']");
    By confirmButton = By.xpath("//android.widget.TextView[@text='Confirm']");
    By numberOneButton = By.xpath("//android.widget.TextView[@text='1']");
    By starUsingTrustWalletButton = By.xpath("//android.widget.TextView[@text=\"Start using Trust Wallet\"]");
    By welcomePopup = By.xpath("//android.widget.TextView[@text=\"Welcome aboard\"]");
    By[] secretWords = new By[12];
    By copyToClipboardButton = By.xpath("//android.widget.TextView[@text='Copy to Clipboard']");

    By[] wordSections = new By[12];
    By[][] wordOptions = new By[12][3];

    // Constructor
    public AndroidCreateNewWalletPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);

        // Initialize the secretWords and wordOptions arrays
        for (int i = 0; i < 12; i++) {
            secretWords[i] = By.xpath("(//android.widget.TextView)[position()=" + (i + 1) + "]");
        }

        for (int i = 0; i < 12; i++) {
            wordSections[i] = By.xpath("(//android.widget.TextView[starts-with(@text, 'Word #')])[" + (i + 1) + "]");
            for (int j = 0; j < 3; j++) {
                wordOptions[i][j] = By.xpath("(//android.widget.TextView[starts-with(@text, 'Word #')])[" + (i + 1) + "]/following-sibling::android.widget.Button[" + (j + 1) + "]");
            }
        }
    }

    // Method to create a new wallet
    public void createNewWallet() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(createWalletButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(createNewWalletButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(backupManuallyButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(firstCheckmark)).click();
        wait.until(ExpectedConditions.elementToBeClickable(secondCheckmark)).click();
        wait.until(ExpectedConditions.elementToBeClickable(thirdCheckmark)).click();
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(copyToClipboardButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
        selectSecretWords();
        wait.until(ExpectedConditions.elementToBeClickable(confirmButton)).click();
        enterSecurityCode();
        enterSecurityCode();
    }

    // Method to create a new wallet without secret phrase backup
    public void createWalletWithoutPhrase() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(createWalletButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(createNewWalletButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(skipButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(numberOneButton));
        enterSecurityCode();
        enterSecurityCode();
    }

    // Method to validate that the wallet was created
    public void validateWalletCreated() {
        isWelcomeScreenDisplayed();
        wait.until(ExpectedConditions.elementToBeClickable(starUsingTrustWalletButton)).click();
    }

    // Method to select the secret words
    public void selectSecretWords() {
        String clipboardText = (String) driver.getClipboardText();
        List<String> secretWords = Arrays.asList(clipboardText.split(" "));

        for (int i = 0; i < 4; i++) {
            try {
                // Get the word number
                String wordSectionText = driver.findElement(wordSections[i]).getText();
                int wordNumber = Integer.parseInt(wordSectionText.split("#")[1].trim()) - 1;

                // Get the correct word
                String correctWord = secretWords.get(wordNumber);

                // Find the button that matches the correct word and click on it
                WebElement correctAnswer = driver.findElement(By.xpath("//android.widget.TextView[contains(@text, '" + correctWord + "')]"));
                correctAnswer.click();
                Thread.sleep(2000);

            } catch (NoSuchElementException e) {
                break; // If no matching elements are found, break the loop
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Method for checking the welcome popup
    public boolean isWelcomeScreenDisplayed() {
        WebElement element = driver.findElement(welcomePopup);
        return element.isDisplayed();
    }

    // Method for entering the security code
    public void enterSecurityCode() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(numberOneButton));
        WebElement element = driver.findElement(numberOneButton);
        for (int i = 0; i < 6; i++) {
            element.click();
            Thread.sleep(2000);
        }
    }

}
