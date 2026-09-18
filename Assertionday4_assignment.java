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
import org.testng.annotations.Test;

import java.time.Duration;

public class Assertionday4_assignment {

    WebDriver driver;
    WebDriverWait wait;



    By messageHeading = By.id("message");
    By enterNameButton = By.id("enterNameBtn");
    By nameInputField = By.id("nameField");

    @BeforeMethod
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        Thread.sleep(1000);
    }

    @Test
    public void testWelcomePage() throws InterruptedException {
        driver.get("file:///C:/Users/CCST/Downloads/SeleniumMaterial-20260915T091244Z-1-001/SeleniumMaterial/welcome.html");




        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(messageHeading));
        String actualMessage = message.getText();
        Assert.assertEquals(actualMessage, "Welcome!!", "Message text does not match!");




        WebElement nameField = driver.findElement(nameInputField);
        Assert.assertFalse(nameField.isEnabled(), "Text field IS DISABLED initially.");


        WebElement actionButton = driver.findElement(enterNameButton);
        actionButton.click();
        Thread.sleep(1000);




        Assert.assertTrue(nameField.isEnabled(), "Text field IS ENABLED after button click.");


    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
