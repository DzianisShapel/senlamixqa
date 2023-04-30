package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class CheckoutCompletePage {

    private SelenideElement completeHeader = $(".complete-header");


    public void checkOrderStatus(){
        completeHeader.shouldHave(text("Thank you for your order!"));
    }



}
