package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class LoginStepDefinition {

    @Given("^user navigates to login page by opening Chrome$")
    public static void openApp(){
        open("https://www.saucedemo.com/");
    }

    @When("^user enters correct (.*) AND (.*) values$")
    public static void login(String username,String password){
        $(By.id("user-name")).sendKeys(username);
        $(By.id("password")).sendKeys(password);
        $(By.id("login-button")).click();
    }

    @Then("^user is directed to inventory page$")
    public static void directToHomePage(){
        webdriver().shouldHave(url("https://www.saucedemo.com/inventory.html"));
    }
}
