package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CartPage {

    private SelenideElement itemInCart = $(By.className("inventory_item_name"));
    private SelenideElement itemQuantity = $(By.className("cart_quantity"));
    private SelenideElement checkout = $(By.id("checkout"));

    private SelenideElement removeButton = $x("//button[text()='Remove']");

    private SelenideElement cartBadge = $x("//span[@class='shopping_cart_badge']");

    private SelenideElement removedElement = $x("//div[@class='removed_cart_item']");

    private SelenideElement continueShoppingButton = $(By.id("continue-shopping"));


    public CartPage checkItemInCart(String itemName){
        itemInCart.shouldHave(text(itemName));
        itemQuantity.shouldHave(text("1"));
        return this;
    }

    public CartPage removeItemFromCart(){
        removeButton.click();
        cartBadge.should(disappear);
        removedElement.should(exist);
        return this;
    }

    public HomePage continueShopping(){
        continueShoppingButton.click();
        return new HomePage();
    }

    public CheckoutStepOnePage checkout(){
        checkout.click();
        return new CheckoutStepOnePage();
    }

}
