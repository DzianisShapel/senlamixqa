import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SingletonBaseClass {
    private static WebDriver driver = null;
    private static String browserName= "chrome";

    static WebDriverWait wait;

    public static void init() {
        if (driver == null) {
            if (browserName.equalsIgnoreCase("chrome")) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(options);
                wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            } else if (browserName.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            }
        }
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }
    public static WebDriver getDriver() {
        return driver;
    }
    public static void quit() {
        driver.quit();
        driver=null;
    }
}
