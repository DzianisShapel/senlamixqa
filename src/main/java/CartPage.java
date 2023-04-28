import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.Map;

public class CartPage extends BasePage{


    private By itemInCart = By.className("inventory_item_name");
    private By itemQuantity = By.className("cart_quantity");

    private By checkout = By.id("checkout");

    public CartPage(WebDriver driver){
        super(driver);
    }

    public Map<String, String> getItemFromCart(String itemToAdd){
        Map<String, String> map = new HashMap<>();
        map.put("name", wait.until(ExpectedConditions.visibilityOfElementLocated(itemInCart)).getText());
        map.put("quantity", driver.findElement(itemQuantity).getText());
        return map;
    }

    public CheckoutStepOnePage checkout(){
        driver.findElement(checkout).click();
        return new CheckoutStepOnePage(driver);
    }

}
