package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage{

    @FindBy(id = "user-name")
    WebElement username;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(id = "login-button")
    WebElement loginButton;


    public HomePage(WebDriver driver){
        super(driver);
    }

    public HomePage openHomePage(){
        driver.get(System.getProperty("baseUrl"));
        return this;
    }

    public InventoryPage logIn(){
        username.sendKeys(System.getProperty("user"));
        password.sendKeys(System.getProperty("password"));
        loginButton.click();
        return new InventoryPage(driver);
    }
}
