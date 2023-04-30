package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class CheckoutStepTwoPage {

    private SelenideElement itemBeingOrdered = $(By.className("inventory_item_name"));

    private SelenideElement finish = $(By.id("finish"));

    public CheckoutStepTwoPage checkOrderedItem(String itemName){
        itemBeingOrdered.shouldHave(text(itemName));
        return this;
    }

    public CheckoutCompletePage finishOrder(){
        finish.click();
        return new CheckoutCompletePage();
    }

}
