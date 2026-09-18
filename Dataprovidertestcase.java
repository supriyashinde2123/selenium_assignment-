package Day4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Dataprovidertestcase {

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


    @DataProvider(name = "loginDataSet")
    public Object[][] getLoginData() throws IOException {
        String csvFilePath = "C:\\Users\\CCST\\Downloads\\SeleniumMaterial-20260915T091244Z-1-001\\SeleniumMaterial/loginData.csv"; // Added missing semicolon
        List<Object[]> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] values = line.split(",");
                String username = values[0].trim();
                String password = values[1].trim();


                records.add(new Object[]{username, password});
            }
        }


        return records.toArray(new Object[0][0]);
    }

    @Test(dataProvider = "loginDataSet")
    public void testpositiveSaucedemo(String username, String password) {
        driver.get("https://www.saucedemo.com");

        WebElement obj_username = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        WebElement obj_password = driver.findElement(passwordField);
        WebElement loginBtn = driver.findElement(loginButton);

        obj_username.sendKeys(username);
        obj_password.sendKeys(password);
        loginBtn.click();

        wait.until(ExpectedConditions.urlContains("inventory.html"));
        String currenturl = driver.getCurrentUrl();


        Assert.assertTrue(currenturl.contains("inventory.html"),
                "Login failed for user: " + username + ". URL: " + currenturl);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}


