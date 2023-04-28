import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("User flow for ordering product")
public class OrderProductTest {

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
   public void orderProduct(){
      InventoryPage inventoryPage = new HomePage(driver).load().logIn();
      String itemToAdd = inventoryPage.getItem();
      Map<String, String> map = inventoryPage.addToCart(itemToAdd).goToCart().getItemFromCart(itemToAdd);
      checkItemInCart(map, itemToAdd);
      CheckoutStepTwoPage checkoutStepTwoPage = inventoryPage.goToCart().checkout().fillInUserForm();
      String itemBeingOrdered = checkoutStepTwoPage.getItemBeingOrdered();
      validateCheckoutForm(itemBeingOrdered, itemToAdd);
      checkOrderCompletion(checkoutStepTwoPage.finishOrder().getCheckoutStatus());
   }

   private void checkItemInCart(Map<String, String> map, String itemToAdd){
          assertAll(
            () -> assertEquals(itemToAdd, map.get("name")),
            () -> assertEquals("1", map.get("quantity"))
    );
   }
   private void validateCheckoutForm(String itemBeingOrdered, String itemToAdd) {
      assertEquals(itemToAdd, itemBeingOrdered);
   }
   private void checkOrderCompletion(String checkoutStatus) {
      assertEquals( "Thank you for your order!", checkoutStatus);
   }
}
