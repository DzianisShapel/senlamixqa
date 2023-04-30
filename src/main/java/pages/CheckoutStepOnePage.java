package pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.selector.ByDeepShadow;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;

public class CheckoutStepOnePage {

    private SelenideElement firstName = $(By.id("first-name"));

    private SelenideElement lastName = $(By.id("last-name"));

    private SelenideElement postalCode = $(By.id("postal-code"));

    private SelenideElement continueButton = $(By.id("continue"));


    public CheckoutStepTwoPage fillInUserForm() {
        firstName.sendKeys(new Faker().name().firstName());
        lastName.sendKeys(new Faker().name().lastName());
        postalCode.sendKeys(new Faker().address().zipCode());
        continueButton.click();
        return new CheckoutStepTwoPage();
    }


}
