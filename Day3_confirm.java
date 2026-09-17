import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class Day3_confirm {

        public static void main(String[] args) {

            ChromeOptions options = new ChromeOptions();
            options.setCapability("unhandledPromptBehavior", "ignore");

            WebDriver driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

            try {
                driver.get("file:///C:/Users/CCST/Downloads/SeleniumMaterial-20260915T091244Z-1-001/SeleniumMaterial/javascriptAlerts.html");
                Thread.sleep(1000);

                driver.findElement(By.id("confirmBtn")).click();
                Thread.sleep(1000);


                Alert confirmDialog = driver.switchTo().alert();
                System.out.println("Confirm Text: " + confirmDialog.getText());


                confirmDialog.accept();

                Thread.sleep(1000);


                driver.findElement(By.id("promptBtn")).click();
                Thread.sleep(1000);


                Alert promptBox = driver.switchTo().alert();
                System.out.println("Prompt Text: " + promptBox.getText());


                promptBox.sendKeys("this is a prompt box");


                promptBox.accept();
                Thread.sleep(1000);

                System.out.println("Page Title: " + driver.getTitle());

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                driver.quit();
            }
        }
    }



