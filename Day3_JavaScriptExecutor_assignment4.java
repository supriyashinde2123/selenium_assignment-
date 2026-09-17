import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Day3_JavaScriptExecutor_assignment4 {


        public static void main(String[] args) throws InterruptedException {
            WebDriver driver = new ChromeDriver();
            JavascriptExecutor js = (JavascriptExecutor) driver;

            try {
                driver.get("file:///C:/Users/CCST/Downloads/SeleniumMaterial-20260915T091244Z-1-001/SeleniumMaterial/hiddenElementJavascriptExecutor.html");
                driver.manage().window().maximize();

                String pageTitle = (String) js.executeScript("return document.title;");
                System.out.println("Page Title via JS: " + pageTitle);

                WebElement hiddenBtn = driver.findElement(By.id("hiddenBtn"));
                Thread.sleep(1000);

                try {
                    hiddenBtn.click();
                    System.out.println("click() succeeded on hidden element.");
                } catch (Exception e) {
                    System.out.println(".click() failed on hidden element: " + e.getClass().getSimpleName());
                }

                Thread.sleep(1000);

                js.executeScript("arguments[0].click();", hiddenBtn);

                String hiddenText = (String) js.executeScript("return arguments[0].textContent;", hiddenBtn);
                System.out.println("Hidden button text: " + hiddenText);

                Thread.sleep(5000);

                String result2 = driver.findElement(By.id("result")).getText();

                if (result2.equals("Hey Your Trasure will be at your doorstep, wait until TOMORROW !")) {
                    System.out.println("pass:Hidden button clicked correctly. Result: " + result2);
                } else {
                    System.out.println("fail: result mismatch. Actual: " + result2);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Thread.sleep(1000);
                driver.quit();
            }
        }
    }


