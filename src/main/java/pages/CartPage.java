package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {

    @FindBy(xpath = "//div[@class='inventory_item_name']")
    WebElement item;

    @FindBy(xpath = "//button[@class='btn btn_secondary btn_small cart_button']")
    WebElement removeButton;

    @FindBy(xpath = "//span[@class='shopping_cart_badge']")
    WebElement cartBadge;

    @FindBy(xpath = "//div[@class='removed_cart_item']")
    WebElement removedItem;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getItemInCart() {
        wait.until(ExpectedConditions.visibilityOf(item));
        return item.getText();
    }

    public CartPage removeItemFromCart() {
        removeButton.click();
        return this;
    }

    public Boolean isItemRemoved(){
       return removedItem.isDisplayed();
    }
}
