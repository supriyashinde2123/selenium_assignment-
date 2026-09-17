import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Day3_RightClickContextMenu {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("file:///C:/Users/CCST/Downloads/SeleniumMaterial-20260915T091244Z-1-001/SeleniumMaterial/rightClickContextMenuInteraction.html");


            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            Actions actions = new Actions(driver);
        WebElement targetBox=wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("targetBox"))
        );
        actions.contextClick(targetBox).perform();
        WebElement contextmenu=wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("contextMenu"))
        );
        if(contextmenu.isDisplayed()){
            System.out.println("pass:context menu appeared after right-click.");

        }else{
            System.out.println("Fail:Context menu did not appear.");
        }
        WebElement deleteOption=wait.until(
                ExpectedConditions.elementToBeClickable(By.id("deleteOption"))
        );
        deleteOption.click();
        String result=driver.findElement(By.id("result")).getText();
        if(result.equals("You selected: Delete")){
            System.out.println("Pass:'delete' option selected correctly.Result:"+result);
        }else{
            System.out.println("Fail:Result mismatch.Actual:"+result);
        }
    }
}
