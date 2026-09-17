import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class javaScriptAlert {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("file:///C:/Users/CCST/Downloads/SeleniumMaterial-20260915T091244Z-1-001/SeleniumMaterial/javascriptAlerts.html");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("alertBtn")).click();
        Alert obj_simpleAlert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = obj_simpleAlert.getText();
        System.out.println("Alert text:" + alertText);
        obj_simpleAlert.accept();
        String alertResult = driver.findElement(By.id("alertResult")).getText();
        if (alertResult.equals("Alert was shown and accepted.")) {
            System.out.println("Pass:simple alert handled correctly");
        } else {
            System.out.println("Fail:alert result mismatch");
        }
        driver.quit();
    }

    }



