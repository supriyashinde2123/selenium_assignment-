import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class day3_HighlightHeading {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {

            driver.get("file:///C:/Users/CCST/Downloads/SeleniumMaterial-20260915T091244Z-1-001/SeleniumMaterial/TestcasesClassAssignment-drivingLicenseUI.html");
            WebElement obj_handling = driver.findElement(
                    By.xpath("//h2[text()='Driving License Application']")
            );
            js.executeScript("arguments[0].style.border='3px solid red';", obj_handling);
            js.executeScript("arguments[0].style.backgroundcolor='yellow';", obj_handling);
            Thread.sleep(2000);

        } finally {
            driver.quit();
        }
    }
}
