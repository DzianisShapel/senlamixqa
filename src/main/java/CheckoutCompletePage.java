import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage{

    private By completeHeader = By.cssSelector(".complete-header");
    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public String getCheckoutStatus() {
        return driver.findElement(completeHeader).getText();
    }


}
