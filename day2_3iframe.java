import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions; // Corrected import (added 's')
import org.openqa.selenium.support.ui.Select;             // Added missing Select import
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class day2_3iframe {
    // Added 'throws InterruptedException' because Thread.sleep is used
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        // Corrected typo: implicitlyWait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("file:///C:/Users/CCST/Downloads/SeleniumMaterial-20260915T091244Z-1-001/SeleniumMaterial/iFrameDemo.html");

        // Corrected case sensitivity: WebDriverWait and driver
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // --- Frame 1 (By Index) ---
        driver.switchTo().frame(0);

        // Corrected case sensitivity (WebElement) and class name (ExpectedConditions)
        WebElement frame1Btn = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("frame1Btn"))
        );
        frame1Btn.click();
        Thread.sleep(2000);

        String frame1Result = driver.findElement(By.id("frame1Result")).getText();
        if (frame1Result.equals("frame1 button clicked!")) {
            System.out.println("pass:frame 1(by index) handled correctly.");
        } else {
            System.out.println("fail:frame 1 result mismatch");
        }

        System.out.println("handle inside iframe:" + driver.getWindowHandle());
        driver.switchTo().defaultContent();
        System.out.println("handle default content:" + driver.getWindowHandle());
        Thread.sleep(2000);

        // --- Frame 2 (By Name) ---
        driver.switchTo().frame("framebyname");

        // Corrected: ExpectedConditions, visibilityOfElementLocated, and fixed the missing closing parenthesis
        WebElement frame2Input = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("frame2Input"))
        );

        frame2Input.sendKeys("seleniumStudent");

        // Corrected case sensitivity: String
        String enteredValue = frame2Input.getAttribute("value");

        // Note: Adjusted the comparison value to match what was sent ("seleniumStudent")
        if (enteredValue.equals("seleniumStudent")) {
            System.out.println("pass:frame 2 (by name) handle correctly");
        } else {
            System.out.println("fail:frame 2 input mismatch");
        }
        Thread.sleep(2000);

        // --- Frame 3 (By WebElement) ---
        driver.switchTo().parentFrame();

        WebElement frame3Element = driver.findElement(By.id("frame 3"));
        driver.switchTo().frame(frame3Element); // Corrected variable case sensitivity

        // Corrected case sensitivity (WebElement) and class name (ExpectedConditions)
        WebElement dropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("frame3dropdown"))
        );

        // Corrected case sensitivity for Select class and its methods
        Select select = new Select(dropdown);
        select.selectByVisibleText("two");

        String selectedValue = select.getFirstSelectedOption().getText(); // Corrected getText() case
        if (selectedValue.equals("two")) {
            System.out.println("pass:frame3 (by webelement)handle correctly");
        } else {
            System.out.println("fail:frame3 dropdown mismatch");
        }
        Thread.sleep(2000);


        driver.switchTo().defaultContent();
        WebElement mainBtn = driver.findElement(By.id("mainBtn"));
        if (mainBtn.isDisplayed()) {
            System.out.println("pass:successfully returned to main page context");
        }

        Thread.sleep(2000);
        driver.quit();
    }
}

