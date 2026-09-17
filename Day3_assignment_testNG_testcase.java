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
public class Day3_assignment_testNG_testcase {


        WebDriver driver;
        WebDriverWait wait;


        By usernameField = By.id("user-name");
        By passwordField = By.id("password");
        By loginButton = By.id("login-button");
        By errorMessage = By.xpath("//h3[@data-test='error']");


        @BeforeMethod
        public void setUp() {
            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            driver.manage().window().maximize();
        }

        @Test(priority = 1, description = "Verify successful login with valid credentials")
        public void testPositiveLoginSauceDemo() {
            driver.get("https://www.saucedemo.com");

            WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
            WebElement password = driver.findElement(passwordField);
            WebElement loginBtn = driver.findElement(loginButton);

            username.sendKeys("standard_user");
            password.sendKeys("secret_sauce");
            loginBtn.click();

            wait.until(ExpectedConditions.urlContains("inventory.html"));
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("inventory.html"), "Login failed. Actual URL: " + currentUrl);
        }


        @Test(priority = 2, description = "Verify error message appears with invalid credentials")
        public void testNegativeLoginSauceDemo() {
            driver.get("https://www.saucedemo.com");

            WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
            WebElement password = driver.findElement(passwordField);
            WebElement loginBtn = driver.findElement(loginButton);

            username.sendKeys("admin");
            password.sendKeys("123");
            loginBtn.click();


            WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            String actualErrorText = errorElement.getText();

            Assert.assertTrue(errorElement.isDisplayed(), "Error message is not displayed!");
            Assert.assertTrue(actualErrorText.contains("Epic sadface"), "Unexpected error message: " + actualErrorText);
        }


        @AfterMethod
        public void tearDown() {
            if (driver != null) {
                driver.quit();
            }
        }
    }


