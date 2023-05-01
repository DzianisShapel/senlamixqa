package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class LoginStepDefinition {

    @Given("user navigates to login page {string} by opening Chrome")
    public static void openApp(String baseUrl){
        open(baseUrl);
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
