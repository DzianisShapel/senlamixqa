package pages;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class HomePage {

    private SelenideElement username = $(By.id("user-name"));

    private SelenideElement password = $(By.id("password"));

    private SelenideElement loginButton = $(By.id("login-button"));

    private SelenideElement title = $x("//span[@class='title']");


    public HomePage openHomePage(){
        open(System.getProperty("baseUrl"));
        return this;
    }

    public InventoryPage logIn(){
        username.sendKeys(System.getProperty("user"));
        password.sendKeys(System.getProperty("password"));
        loginButton.click();
        return new InventoryPage();
    }

    public HomePage checkTitle(){
        title.shouldHave(text("Products"));
        return this;
    }

}
