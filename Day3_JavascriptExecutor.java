import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Day3_JavascriptExecutor {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js=(JavascriptExecutor) driver;
        try{
            double zoomLevelBefore = ((Number) js.executeScript("return window.devicePixelRatio;")).doubleValue();
            System.out.println("Current Zoom Ratio Before: " + zoomLevelBefore);
            Actions actions=new Actions(driver);
            actions.keyDown(Keys.CONTROL).sendKeys(Keys.ADD).keyUp(Keys.CONTROL).perform();
            Thread.sleep(1000);
            double zoomLevelAfter = ((Number) js.executeScript("return window.devicePixelRatio;")).doubleValue();
            System.out.println("Current zoom ratio:"+ zoomLevelAfter);

        }catch (InterruptedException e){
            e.printStackTrace();
        }
        driver.quit();
    }
}
