import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.InventoryPage;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Check correct removing item from cart")
public class RemoveProductFromCartTest {

    @Test
    public  void removeProductFromCart(){
        InventoryPage inventoryPage = new HomePage().openHomePage().logIn();
        String itemToAdd = inventoryPage.getItem();
        inventoryPage
                .addToCart(itemToAdd)
                .goToCart()
                .checkItemInCart(itemToAdd)
                .removeItemFromCart()
                .continueShopping()
                .checkTitle();
    }
}
