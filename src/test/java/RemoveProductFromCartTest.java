import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.CartPage;
import pages.HomePage;
import pages.InventoryPage;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Check correct removing item from cart")
public class RemoveProductFromCartTest {
    private WebDriver driver;


    @BeforeAll
    public void startDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @AfterAll
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void removeProductFromCart() {

        InventoryPage inventoryPage = new HomePage(driver).openHomePage().logIn();
        String itemToAdd = inventoryPage.getItem();
        String itemInCart = inventoryPage.addToCart(itemToAdd).goToCart().getItemInCart();
        checkCart(itemToAdd, itemInCart);
        checkCartAfterRemoving(inventoryPage.goToCart());
    }

    private void checkCart(String itemToAdd, String itemInCart) {
        assertEquals(itemToAdd, itemInCart);
    }

    private void checkCartAfterRemoving(CartPage cartPage) {
       assertFalse(cartPage.removeItemFromCart().isItemRemoved());
    }

}
