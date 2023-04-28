import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Random;

public class InventoryPage extends BasePage{

    private By item = By.xpath("//div[@class='inventory_item_name']");

    private By cartLink = By.cssSelector(".shopping_cart_link");

    public InventoryPage(WebDriver driver){
        super(driver);
    }

    public String getItem(){
        List<WebElement> items = driver.findElements(item);
        List<String> itemsName = items.stream().map(WebElement::getText).toList();
        Random rand = new Random();
        return itemsName.get(rand.nextInt(itemsName.size()));
    }

    public InventoryPage addToCart(String item) {
        driver.findElement(By.xpath("//div[text()='"+ item +"']/ancestor::div[@class='inventory_item_label']/following-sibling::div/child::button")).click();
        return this;
    }

    public CartPage goToCart(){
        driver.findElement(cartLink).click();
        return new CartPage(driver);
    }
}
