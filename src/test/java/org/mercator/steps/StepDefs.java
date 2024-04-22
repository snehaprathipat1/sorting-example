package org.mercator.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class StepDefs extends Methods {
    @Given("I am on the home page")
    public void iAmOnTheHomePage() {
        getSauceDemoLoginPage();
    }

    @When("I select highest price item")
    public void iSelectHighestPriceItem() {
        matchHighestProductWithItem(selectHighestPriceItem());
    }

    @When("^I enter (.+) and (.+)$")
    public void enterLoginDetails(String username, String password) {
        enterValidCredentials(username, password);
    }

    @When("^I click on login button$")
    public void clickButton(){
        clickByCssSelector("*[data-test=\"login-button\"]");
    }
    @And("^I should see (.+) as (.+)$")
    public void ShouldSee(String element, String text){
        isElementPresent(element,text);
    }

    @And("I add it to cart")
    public void iAddItToCart() {
        clickByCssSelector("*[data-test=\"add-to-cart\"]");
    }

    @And("I click on the basket")
    public void iClickOnTheBasket() {
        clickByCssSelector("*[data-test=\"shopping-cart-link\"]");
    }

    @Then("I should see the item in basket")
    public void iShouldSeeTheItemInBasket() {
        assertText("*[data-test=\"inventory-item-name\"]");
    }
}
