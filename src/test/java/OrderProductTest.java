
import org.junit.jupiter.api.*;
import pages.HomePage;
import pages.InventoryPage;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("User flow for ordering product")
public class OrderProductTest {

   @Test
   public void orderProduct(){
      InventoryPage inventoryPage = new HomePage().openHomePage().logIn();
      String itemToAdd = inventoryPage.getItem();
      inventoryPage
              .addToCart(itemToAdd)
              .goToCart()
              .checkItemInCart(itemToAdd)
              .checkout()
              .fillInUserForm()
              .checkOrderedItem(itemToAdd)
              .finishOrder()
              .checkOrderStatus();
   }
}
