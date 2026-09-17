import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Day3_FileUploadDemo {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("file:///C:/Users/CCST/Downloads/SeleniumMaterial-20260915T091244Z-1-001/SeleniumMaterial/fileUpload.html");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement fileInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("fileInput"))
        );


        String filepath = "C:/Users/CCST/Downloads/SeleniumMaterial-20260915T091244Z-1-001/SeleniumMaterial/actionClass_Menu.html";
        fileInput.sendKeys(filepath);
        Thread.sleep(5000);
        System.out.println("File path sent to input field.");


        WebElement fileNameLabel = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("fileName"))
        );


        if (fileNameLabel.getText().contains("actionClass_Menu.html")) {
            System.out.println("pass: file selected and displayed correctly. Text: " + fileNameLabel.getText());
        } else {
            System.out.println("fail: file name not displayed as expected. Actual: " + fileNameLabel.getText());
        }


        WebElement upload = driver.findElement(By.id("uploadBtn"));
        upload.click();
        Thread.sleep(5000);

        WebElement result = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("result"))
        );


        String expectedMessage = "File 'actionClass_Menu.html' uploaded successfully!";
        if (result.getText().equalsIgnoreCase(expectedMessage)) {
            System.out.println("pass: upload confirmed. Message: " + result.getText());
        } else {
            System.out.println("fail: upload message mismatch. Actual: " + result.getText());
        }

        driver.quit();
    }
}
