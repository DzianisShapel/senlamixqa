import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.CheckoutStepTwoPage;
import pages.HomePage;
import pages.InventoryPage;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

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
