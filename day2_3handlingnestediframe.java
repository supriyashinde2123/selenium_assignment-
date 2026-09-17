import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class day2_3handlingnestediframe {
    public static void main(String[] args) {
        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Navigate to your HTML file
            driver.get("file:///C:/Users/CCST/Downloads/SeleniumMaterial-20260915T091244Z-1-001/SeleniumMaterial/iFrameDemo.html");

            // --- STEP 1: Switch to the Innermost Frame ---
            // Switch to Outer Frame first (Replace locator with actual ID/Name/Index if different)
            driver.switchTo().frame("outerFrame4");

            // Switch to Innermost Frame from the context of the outer frame
            driver.switchTo().frame("innerFrame4");

            // --- STEP 2: Interact with elements in the innermost iframe ---
            // Click on the button inside the innermost iframe
            WebElement innerBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(By.id("innerFrameBtn4"))
            );
            innerBtn.click();

            // Validate the text displayed after the click
            WebElement textElement = driver.findElement(By.id("innerFrame4Result"));
            String actualText = textElement.getText();
            String expectedText = "Button Clicked!"; // Change to match your actual expected validation text

            if (actualText.equals(expectedText)) {
                System.out.println("PASS: Innermost iframe text validated successfully.");
            } else {
                System.out.println("FAIL: Text mismatch. Found: " + actualText);
            }

            // --- STEP 3: Return to the main window context ---
            driver.switchTo().defaultContent();

            // Check for the main window button to verify we are back
            WebElement mainBtn = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("mainBtn"))
            );

            if (mainBtn.isDisplayed()) {
                System.out.println("PASS: Successfully returned to the main page context.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            driver.quit();
        }
    }
}
