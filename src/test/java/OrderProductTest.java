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
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("User flow for ordering product")
public class OrderProductTest {

   private WebDriver driver;

   private WebDriverWait wait;

   @BeforeAll
   public void startDriver() {
      ChromeOptions options = new ChromeOptions();
      options.addArguments("--remote-allow-origins=*");
      driver = new ChromeDriver(options);
      wait = new WebDriverWait(driver, Duration.ofSeconds(10));
   }

   @AfterAll
   public void tearDown(){
      driver.quit();
   }

   @Test
   public void orderProduct(){

      InventoryPage inventoryPage = new HomePage(driver).load().logIn();
      String itemToAdd = inventoryPage.getItem();
      inventoryPage.addToCart(itemToAdd).goToCart();

      checkItemInCart(itemToAdd);
      checkout();
      fillInUserForm();
      validateCheckoutForm(itemToAdd);
      finishOrder();
   }


   private void checkItemInCart(String itemToAdd){
    WebElement item = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_item_name")));
    String itemInCart = item.getText();
    String itemQuantity = driver.findElement(By.className("cart_quantity")).getText();
    assertAll(
            () -> assertEquals(itemInCart, itemToAdd),
            () -> assertEquals("1", itemQuantity)
    );
   }

   private void checkout() {
      driver.findElement(By.id("checkout")).click();
   }
   private void fillInUserForm() {
      driver.findElement(By.id("first-name")).sendKeys(new Faker().name().firstName());
      driver.findElement(By.id("last-name")).sendKeys(new Faker().name().lastName());
      driver.findElement(By.id("postal-code")).sendKeys(new Faker().address().zipCode());
      driver.findElement(By.id("continue")).click();
   }

   private void validateCheckoutForm(String item) {
     assertAll(
             () -> assertEquals(item, driver.findElement(By.className("inventory_item_name")).getText()),
             () -> assertTrue(driver.findElement(By.xpath("//div[text()='Payment Information']")).isDisplayed()),
             () -> assertTrue(driver.findElement(By.xpath("//div[text()='Shipping Information']")).isDisplayed()),
             () -> assertTrue(driver.findElement(By.xpath("//div[text()='Price Total']")).isDisplayed())
     );
   }
   private void finishOrder() {
      driver.findElement(By.id("finish")).click();
      assertEquals( "Thank you for your order!", driver.findElement(By.cssSelector(".complete-header")).getText());
   }
}
