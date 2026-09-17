import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class Day3_AlertHandlingChromeOption {
    public static void main(String[] args) {
        ChromeOptions options=new ChromeOptions();
        options.setCapability("unhandledPromptBehavior","accept");
        WebDriver driver=new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        try{
            driver.get("file:///C:/Users/CCST/Downloads/SeleniumMaterial-20260915T091244Z-1-001/SeleniumMaterial/javascriptAlerts.html");
            Thread.sleep(1000);
            driver.findElement(By.id("alertBtn")).click();
            Thread.sleep(1000);
            System.out.println("Page Title:"+driver.getTitle());
            Thread.sleep(5000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            driver.quit();
        }

    }
}
