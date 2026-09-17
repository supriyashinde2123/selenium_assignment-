import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class Day3_javascriptExecutor_assignment5 {

        public static void main(String[] args) throws InterruptedException {
            WebDriver driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.manage().window().maximize();

            JavascriptExecutor js = (JavascriptExecutor) driver;

            try {

                driver.get("file:///C:/Users/CCST/Downloads/SeleniumMaterial-20260915T091244Z-1-001/SeleniumMaterial/hiddenElementJavascriptExecutor.html");
                Thread.sleep(1000);


                WebElement scrollTargetBtn = driver.findElement(By.id("scrollTargetBtn"));


                js.executeScript("arguments[0].scrollIntoView(true);", scrollTargetBtn);
                System.out.println("Action: Executed scrollIntoView on the target button.");
                Thread.sleep(2000);


                boolean isButtonVisible = scrollTargetBtn.isDisplayed();
                if (isButtonVisible) {
                    System.out.println("Validation Passed: scrollTargetBtn is visible on the screen.");
                } else {
                    System.out.println("Validation Failed: scrollTargetBtn is still hidden from the viewport.");
                }

                scrollTargetBtn.click();
                System.out.println("Action: Clicked scrollTargetBtn successfully.");
                Thread.sleep(2000);


                String confirmationMessage = driver.findElement(By.id("result")).getText();
                System.out.println("Extracted UI Message: " + confirmationMessage);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Thread.sleep(2000);
                driver.quit();
            }
        }
    }


