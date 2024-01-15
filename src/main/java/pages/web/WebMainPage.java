package pages.web;


import helpers.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WebMainPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private SeleniumHelper helper;
    By searchButton = By.xpath("//button[@data-testid='wallet-header-manage-crypto-button']");
    By searchInput = By.xpath("//*[@placeholder='Token name or contract address']");
    By backButton = By.xpath("//div[@data-tooltip-place='top-end']/button");
    By sendButton = By.xpath("//button[@data-testid='wallet-board-send-button']");
    By receiveButton = By.xpath("//button[@data-testid='wallet-board-receive-button']");
    By swapButton = By.xpath("//button[@data-testid='wallet-board-swap-button']");

    public WebMainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        this.helper = new SeleniumHelper(driver);
    }

    // Method to search for a token
    public void searchToken(String tokenName) {
        helper.retryClick(wait.until(ExpectedConditions.elementToBeClickable(searchButton)));
        WebElement searchInputElement = wait.until(ExpectedConditions.elementToBeClickable(searchInput));
        helper.retryClick(searchInputElement);
        searchInputElement.sendKeys(tokenName);
    }

    // Method to verify the main page
    public void verifyMainPage() {
        WebElement searchButtonElement = wait.until(ExpectedConditions.visibilityOfElementLocated(searchButton));
        assert searchButtonElement.isDisplayed() : "Search button is not displayed";
        WebElement sendButtonElement = wait.until(ExpectedConditions.visibilityOfElementLocated(sendButton));
        assert sendButtonElement.isDisplayed() : "Send button is not displayed";
        WebElement receiveButtonElement = wait.until(ExpectedConditions.visibilityOfElementLocated(receiveButton));
        assert receiveButtonElement.isDisplayed() : "Receive button is not displayed";
        WebElement swapButtonElement = wait.until(ExpectedConditions.visibilityOfElementLocated(swapButton));
        assert swapButtonElement.isDisplayed() : "Swap button is not displayed";

    }


    // Method to add token
    public void addToken(String tokenName) {
        By tokenSwitch = By.xpath("//button[@role='switch' and .//span[text()='" + tokenName + "']]");
        WebElement switchElement = driver.findElement(tokenSwitch);
        String state = switchElement.getAttribute("aria-checked");
        if (state.equals("false")) {
            helper.retryClick(switchElement);
            assert switchElement.getAttribute("aria-checked").equals("true") : "Switch is not turned on";
        } else {
            System.out.println("Switch is already on");
        }
    }

    // Method to check if token is in the token list
    public boolean isTokenInList(String tokenName) {
        By tokenLocator = By.xpath("//div[@data-tooltip-content]");
        List<WebElement> tokens = driver.findElements(tokenLocator);
        for (WebElement token : tokens) {
            String tooltipContent = token.getAttribute("data-tooltip-content");
            if (tooltipContent.contains(tokenName)) {
                return true;
            }
        }
        return false;
    }

    // Method to remove token
    public void removeToken(String tokenName) {
        By tokenSwitch = By.xpath("//button[@role='switch' and .//span[text()='" + tokenName + "']]");
        WebElement switchElement = driver.findElement(tokenSwitch);
        String state = switchElement.getAttribute("aria-checked");
        if (state.equals("true")) {
            helper.retryClick(switchElement);
            assert switchElement.getAttribute("aria-checked").equals("false") : "Switch is not turned off";
        } else {
            System.out.println("Switch is already off");
        }
    }

    // Method to check if the token is displayed
    public boolean isTokenDisplayed(String tokenName, String network) {
        By tokenLocator = By.xpath("//*[contains(@data-tooltip-content, '" + tokenName + "') and contains(@data-tooltip-content, '" + network + "')]");
        wait.until(ExpectedConditions.presenceOfElementLocated(tokenLocator));
        List<WebElement> tokens = driver.findElements(tokenLocator);
        return !tokens.isEmpty();
    }

    // Go back to main screen
    public void goBackToMainScreen() {
        driver.findElement(backButton).click();
    }
}