package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class CartStepDefinition {

    @Given("^logged in with (.*) and (.*) user$")
    public void navigateToStore(String username, String password){
        open("https://www.saucedemo.com/");
        $(By.id("user-name")).sendKeys(username);
        $(By.id("password")).sendKeys(password);
        $(By.id("login-button")).click();
        webdriver().shouldHave(url("https://www.saucedemo.com/inventory.html"));
    }

    @When("^add (.*) to cart$")
    public void addItemToCart(String item){
        $x("//div[text()='"+ item +"']/ancestor::div[@class='inventory_item_label']/following-sibling::div/child::button").click();
    }

    @Then("^can view (.*) in the cart$")
    public void goToCartAndViewProduct(String item){
        $(".shopping_cart_link").click();
        $(By.className("inventory_item_name")).shouldHave(text(item));
    }

    @And("^delete (.*) from cart$")
    public void deleteItemFromCart(String item){
      $x("//div[text()='"+ item + "']/parent::a/following-sibling::div[@class='item_pricebar']/child::button").click();
      $x("//span[@class='shopping_cart_badge']").should(disappear);
    }
}
