import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class Day3_TestNG_Login_SauceDemo {
    WebDriver driver;
    WebDriverWait wait;

    By usernameField = By.id("user-name");
    By passwordField = By.id("password");
    By loginButton = By.id("login-button");
    By errorMessage = By.xpath("//h3[@data-test='error']");

    @BeforeMethod
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        Thread.sleep(1000);
    }

    @Test(priority = 1)
    public void testPositiveLoginSauceDemo() throws InterruptedException {
        driver.get("https://saucedemo.com");
        Thread.sleep(1000);

        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        WebElement password = driver.findElement(passwordField);
        WebElement loginBtn = driver.findElement(loginButton);

        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        Thread.sleep(1000);

        loginBtn.click();

        wait.until(ExpectedConditions.urlContains("inventory.html"));
        String currenturl = driver.getCurrentUrl();
        Assert.assertTrue(currenturl.contains("inventory.html"), "login failed.URL:" + currenturl);
        Thread.sleep(1000);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
