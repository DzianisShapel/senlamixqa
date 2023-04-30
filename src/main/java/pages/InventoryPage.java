package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;

public class InventoryPage {

    private ElementsCollection items = $$x("//div[@class='inventory_item_name']");
    private SelenideElement cartLink = $(".shopping_cart_link");



    public InventoryPage(){

    }

    public String getItem(){
        List<String> itemsName = items.texts();
        Random rand = new Random();
        return itemsName.get(rand.nextInt(itemsName.size()));
    }

    public InventoryPage addToCart(String item) {
        $x("//div[text()='" + item + "']/ancestor::div[@class='inventory_item_label']/following-sibling::div/child::button").click();
        return this;
    }

    public CartPage goToCart(){
        cartLink.click();
        return new CartPage();
    }
}
