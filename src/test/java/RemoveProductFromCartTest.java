import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Check correct removing item from cart")
public class RemoveProductFromCartTest {
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
    public  void removeProductFromCart(){
        openHomePage();
        logIn();
        List<String> itemsToAdd = getItems();
        addToCart(itemsToAdd);
        goToCart();
        checkCart(itemsToAdd);
        removeOneItem(itemsToAdd);
    }

    private void openHomePage(){
        driver.get(System.getProperty("baseUrl"));
    }

    private void logIn(){
        driver.findElement(By.id("user-name")).sendKeys(System.getProperty("user"));
        driver.findElement(By.id("password")).sendKeys(System.getProperty("password"));
        driver.findElement(By.id("login-button")).click();
    }

    private List<String> getItems(){
        List<WebElement> items = driver.findElements(By.xpath("//div[@class='inventory_item_name']"));
        List<String> itemsName = items.stream().map(WebElement::getText).collect(Collectors.toList());
        Collections.shuffle(itemsName);
        return itemsName.subList(0,2);
    }

    private void addToCart(List<String> items) {
        items.forEach(item -> driver.findElement(By.xpath("//div[text()='"+ item +"']/ancestor::div[@class='inventory_item_label']/following-sibling::div/child::button")).click());
        /*for (String item: items) {
            driver.findElement(By.xpath("//div[text()='"+ item +"']/ancestor::div[@class='inventory_item_label']/following-sibling::div/child::button")).click();
        }*/
    }

    private void goToCart(){
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
    }

    private void checkCart(List<String> itemsToAdd) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_item_name")));
        List<String> itemsInCart = driver.findElements(By.className("inventory_item_name")).stream().map(WebElement::getText).collect(Collectors.toList());
        assertAll(
                () -> assertEquals(itemsToAdd, itemsInCart),
                () -> assertEquals(String.valueOf(itemsToAdd.size()), driver.findElement(By.cssSelector(".shopping_cart_badge")).getText())
        );
    }

    private void removeOneItem(List<String> items) {
        driver.findElement(By.xpath("//div[text()='"+ items.get(0) + "']/parent::a/following-sibling::div[@class='item_pricebar']/child::button")).click();
        assertEquals(String.valueOf(items.size() - 1), driver.findElement(By.className("shopping_cart_badge")).getText());
    }

}
