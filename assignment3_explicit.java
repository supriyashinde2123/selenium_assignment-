import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
public class assignment3_explicit {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        try {

            driver.get("file:///C:/Users/CCST/Downloads/SeleniumMaterial-20260915T091244Z-1-001/SeleniumMaterial/welcome.html");
            WebDriverWait obj_wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement message = obj_wait.until(
                    ExpectedConditions.elementToBeClickable(By.id("message"))
            );
            WebElement enterButton = obj_wait.until(
                    ExpectedConditions.elementToBeClickable(By.id("enterName"))
            );

            enterButton.click();
            System.out.println("pass: 'Enter name' button clicked successfully.");

            if (message.getText().equals("welcome!!")) {
                System.out.println("pass:welcome message displayed correctly.");
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

