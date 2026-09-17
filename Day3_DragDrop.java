import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Day3_DragDrop {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("file:///C:/Users/CCST/Downloads/SeleniumMaterial-20260915T091244Z-1-001/SeleniumMaterial/dragDrop.html");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions actions = new Actions(driver);


        WebElement item1 = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("item1"))
        );
        WebElement targetContainer = driver.findElement(By.id("targetContainer"));

        actions.dragAndDrop(item1, targetContainer).perform();

        String result1 = driver.findElement(By.id("result")).getText();
        System.out.println("After draganddrop(): " + result1);

        if (result1.contains("Write Manual Testcases moved to Done")) {
            System.out.println("Pass: drag and drop worked with draganddrop().");
        } else {
            System.out.println("Fail: drag and drop() did not register the move as expected");
        }

        Thread.sleep(1000);

        WebElement item2 = driver.findElement(By.id("item2"));


        actions.clickAndHold(item2)
                .moveToElement(targetContainer)
                .pause(Duration.ofMillis(300))
                .release()
                .build()
                .perform();

        String result2 = driver.findElement(By.id("result")).getText();
        System.out.println("After manual click-hold-move-release: " + result2);

        if (result2.contains("Define Entry and Exit Criteria moved to Done")) {
            System.out.println("Pass: drag and drop worked with manual actions sequence.");
        } else {
            System.out.println("Fail: Manual action sequence did not register the move as expected");
        }

        Thread.sleep(1000);
        driver.quit();
    }
}
