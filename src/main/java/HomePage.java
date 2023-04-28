import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{

    private By username = By.id("user-name");
    private By password = By.id("password");
    private By loginButton = By.id("login-button");

    public HomePage(WebDriver driver){
        super(driver);
    }

    public HomePage load(){
        driver.get(System.getProperty("baseUrl"));
        return this;
    }

    public InventoryPage logIn(){
        driver.findElement(username).sendKeys(System.getProperty("user"));
        driver.findElement(password).sendKeys(System.getProperty("password"));
        driver.findElement(loginButton).click();
        return new InventoryPage(driver);
    }

}
