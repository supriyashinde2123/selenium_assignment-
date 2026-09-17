import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Challenge_AjaxPage {
    public static void main(String[] args) {
        WebDriver webDriver = new ChromeDriver();

        webDriver.get("file:///C:/Users/CCST/Downloads/SeleniumMaterial-20260915T091244Z-1-001/SeleniumMaterial/challenge_AjaxPage.html");

        WebDriverWait obj_wait= new WebDriverWait(webDriver, Duration.ofSeconds(10));
        Boolean spinner=obj_wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));

        if(spinner){
            System.out.println("PASS : page has loadded successfully");
        }
        else{
            System.out.println("FAIL : page loading is unsuccessful");
        }

        WebElement message = obj_wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        if (message.isDisplayed())
        {
            System.out.println("PASS : Welcome message is displayed : Actual "+message.getText());
        }
        else
            System.out.println("FAIL : Welcome message is not displayed : Actual "+message.getText());

        WebElement loadQuoteBtn=obj_wait.until(ExpectedConditions.elementToBeClickable(By.id("loadQuoteBtn")));
        if(loadQuoteBtn.isDisplayed()){
            System.out.println("PASS : Load Quote Button  is displayed");
            loadQuoteBtn.click();
        }
        else
            System.out.println("FAIL : Load Quote Button  is not displayed");

        Boolean quoteLoading = obj_wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("quoteLoading")));
        if (quoteLoading)
        {
            System.out.println("PASS : Quote loading element has disappeared ");
        }
        else
            System.out.println("FAIL : Quote loading element has not disappeared ");

        WebElement quoteBox= webDriver.findElement(By.id("quoteBox"));
        if (!quoteBox.getText().isEmpty())
            System.out.println("PASS : Quote Box is populated . Actual : "+quoteBox.getText());
        else
            System.out.println("FAIL : Quote Box is empty");



        webDriver.quit();
    }
}