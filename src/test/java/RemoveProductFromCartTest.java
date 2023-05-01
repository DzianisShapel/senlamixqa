import org.junit.jupiter.api.*;
import pages.HomePage;
import pages.InventoryPage;


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
