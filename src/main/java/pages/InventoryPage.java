package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage extends BasePage {

    @FindBy(xpath = "//div[@class='inventory_item_name']")
    List<WebElement> items;

    @FindBy(css = ".shopping_cart_link")
    WebElement cartLink;

    public InventoryPage(WebDriver driver){
        super(driver);
    }

    public String getItem(){
       List<String> itemsName = items.stream().map(WebElement::getText).collect(Collectors.toList());
        Collections.shuffle(itemsName);
        return itemsName.get(0);
    }

    public InventoryPage addToCart(String item) {
        driver.findElement(By.xpath("//div[text()='"+ item +"']/ancestor::div[@class='inventory_item_label']/following-sibling::div/child::button")).click();
        return this;
    }

    public CartPage goToCart(){
        cartLink.click();
        return new CartPage(driver);
    }
}
