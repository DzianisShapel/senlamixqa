import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepOnePage extends BasePage{
    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
    }

    private By firstName = By.id("first-name");

    private By lastName = By.id("last-name");

    private By postalCode = By.id("postal-code");

    private By continueButton = By.id("continue");


    public CheckoutStepTwoPage fillInUserForm() {
        driver.findElement(firstName).sendKeys(new Faker().name().firstName());
        driver.findElement(lastName).sendKeys(new Faker().name().lastName());
        driver.findElement(postalCode).sendKeys(new Faker().address().zipCode());
        driver.findElement(continueButton).click();
        return new CheckoutStepTwoPage(driver);
    }


}
