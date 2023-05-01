package steps;

import io.cucumber.java.en.And;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class OrderStepDefinition {

    @And("^is able to complete ordering (.*) with (.*) and (.*) and (.*)$")
    public void orderProduct(String item, String first_name, String last_name, String postal_code){
        $(By.id("checkout")).click();
        $(By.id("first-name")).sendKeys(first_name);
        $(By.id("last-name")).sendKeys(last_name);
        $(By.id("postal-code")).sendKeys(postal_code);
        $(By.id("continue")).click();
        $x("//div[@class='inventory_item_name']").shouldHave(text(item));
        $(By.id("finish")).click();
        $(".complete-header").shouldHave(text("Thank you for your order!"));
    }
}
