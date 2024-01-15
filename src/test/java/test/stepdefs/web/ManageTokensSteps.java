package test.stepdefs.web;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import test.utils.WebTestSetup;

public class ManageTokensSteps {

    private WebTestSetup setup;

    public ManageTokensSteps() throws InterruptedException {
        setup = new WebTestSetup();
        setup.setUp();
    }

    @Given("I am on the main page")
    public void iAmOnTheMainPage() throws InterruptedException {
        setup.createPage.createWallet();
        setup.mainPage.verifyMainPage();
    }

    @When("I search for the {string} token")
    public void iSearchForTheToken(String tokenName) {
        setup.mainPage.searchToken(tokenName);
    }

    @When("The {string} token on {string} is displayed")
    public void theTokenOnNetworkIsDisplayed(String tokenName, String network) {
        Assert.assertTrue(setup.mainPage.isTokenDisplayed(tokenName, network));
    }

    @When("I {string} the {string} token")
    public void iActionTheToken(String action, String tokenName) {
        if ("add".equals(action)) {
            setup.mainPage.addToken(tokenName);
        } else if ("remove".equals(action)) {
            setup.mainPage.removeToken(tokenName);
        }
    }

    @When("I go back to the main screen")
    public void iGoBackToTheMainScreen() {
        setup.mainPage.goBackToMainScreen();
    }

    @Then("the {string} token should {string} in the list")
    public void theTokenShouldBeInList(String tokenName, String beInList) {
        if ("be".equals(beInList)) {
            Assert.assertTrue(setup.mainPage.isTokenInList(tokenName));
        } else if ("not be".equals(beInList)) {
            Assert.assertFalse(setup.mainPage.isTokenInList(tokenName));
        }
    }
}