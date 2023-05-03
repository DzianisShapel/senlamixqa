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
      openHomePage();
      logIn();
      String itemToAdd = getItem();
      addToCart(itemToAdd);
      goToCart();
      checkItemInCart(itemToAdd);
      checkout();
      fillInUserForm();
      validateCheckoutForm(itemToAdd);
      finishOrder();
   }

   private void openHomePage(){
      driver.get(System.getProperty("baseUrl"));
   }

   private void logIn(){
      driver.findElement(By.id("user-name")).sendKeys(System.getProperty("user"));
      driver.findElement(By.id("password")).sendKeys(System.getProperty("password"));
      driver.findElement(By.id("login-button")).click();
   }

   private void addToCart(String item) {
      driver.findElement(By.xpath("//div[text()='"+ item +"']/ancestor::div[@class='inventory_item_label']/following-sibling::div/child::button")).click();
   }

   private void goToCart(){
      driver.findElement(By.cssSelector(".shopping_cart_link")).click();
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

   private String getItem(){
      List<WebElement> items = driver.findElements(By.xpath("//div[@class='inventory_item_name']"));
      List<String> itemsName = items.stream().map(WebElement::getText).collect(Collectors.toList());
      Random rand = new Random();
      return itemsName.get(rand.nextInt(itemsName.size()));
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

   //ddad
}
