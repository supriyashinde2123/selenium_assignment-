import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import java.time.Duration;

public class day2_fluentWaitDemo {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {
            // Fixed: Added missing semicolon
            driver.get("file:///C:/Users/CCST/Downloads/SeleniumMaterial-20260915T091244Z-1-001/SeleniumMaterial/welcome.html");

            // Fixed: Added missing semicolon
            Wait<WebDriver> obj_fluentWait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(10))
                    .pollingEvery(Duration.ofSeconds(2))
                    .ignoring(NoSuchElementException.class);

            WebElement message = obj_fluentWait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("message"))
            );

            // Fixed: Corrected unbalanced parenthesis in the if condition
            if (message.getText().equals("Welcome!!")) {
                System.out.println("pass:welcome message display correctly");
            } else {
                System.out.println("fail:message text mismatch.actual:" + message.getText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
