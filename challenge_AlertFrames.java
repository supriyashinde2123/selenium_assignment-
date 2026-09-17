
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.Instant;

public class challenge_AlertFrames {
    public static void main(String[] args) {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("file:///C:/Users/CCST/Downloads/SeleniumMaterial-20260915T091244Z-1-001/SeleniumMaterial/challenge_AlertsFrames.html");
        WebDriverWait obj_wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        webDriver.switchTo().frame(0);

        // Error here: 'wait' cannot be resolved
        WebElement frameConfirmBtn = obj_wait.until(ExpectedConditions.elementToBeClickable(By.id("frameConfirmBtn")));
        frameConfirmBtn.click();
        Alert frameAlert = obj_wait.until(ExpectedConditions.alertIsPresent());
        String frameAlertText = frameAlert.getText();
        System.out.println("Alert text : " + frameAlertText);
        frameAlert.dismiss();

        String frameConfirmResult = webDriver.findElement(By.id("frameConfirmResult")).getText();
        if (frameConfirmResult.equals("Cancelled inside frame!")) {
            System.out.println("PASS : Frame alert message is displayed. Actual Message " + frameConfirmResult);
        } else
            System.out.println("FAIL : Frame alert message is not displayed. Actual Message " + frameConfirmResult);
        webDriver.switchTo().defaultContent();

        webDriver.quit();

    }
}

