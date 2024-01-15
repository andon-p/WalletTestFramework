package test.stepdefs.android;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import test.utils.AndroidTestSetup;

public class CreateWalletSteps {

    private AndroidTestSetup setup;
    public CreateWalletSteps() throws Exception {
        setup = new AndroidTestSetup();
        setup.setUp();
    }

    @Given("I am on the main page")
    public void iAmOnTheMainPage() {
        setup.mainPage.closeSecurityPopup();
    }

    @When("I create a new wallet")
    public void iCreateANewWallet() throws InterruptedException {
        setup.createNewWalletPage.createNewWallet();
    }

    @When("I create a new wallet without backup")
    public void iCreateANewWalletWithoutBackup() throws InterruptedException {
        setup.createNewWalletPage.createWalletWithoutPhrase();
    }

    @Then("the wallet should be created successfully")
    public void theWalletShouldBeCreatedSuccessfully() {
        setup.createNewWalletPage.validateWalletCreated();
    }
}