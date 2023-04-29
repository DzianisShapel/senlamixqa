package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepTwoPage extends BasePage{

    private By itemBeingOrdered = By.className("inventory_item_name");

    private By finish = By.id("finish");
    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
    }

    public String getItemBeingOrdered(){
        return driver.findElement(itemBeingOrdered).getText();
    }

    public CheckoutCompletePage finishOrder(){
        driver.findElement(finish).click();
        return new CheckoutCompletePage(driver);
    }

}
