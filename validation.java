import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class validation {

    public static void main(String[] args) {
        WebDriver obj_driver = new ChromeDriver();

        try {

            obj_driver.get("https://saucedemo.com");
            obj_driver.getTitle();
            Thread.sleep(1000);


            System.out.println("Site 1 Title: " + obj_driver.getTitle());


            WebElement obj_username = obj_driver.findElement(By.id("user-name"));
            WebElement obj_password = obj_driver.findElement(By.id("password"));
            WebElement obj_loginButton = obj_driver.findElement(By.id("login-button"));

            obj_username.sendKeys("");
            obj_password.sendKeys("");
            Thread.sleep(1000);

            obj_loginButton.click();
            Thread.sleep(1000);


            if (obj_driver.getCurrentUrl().contains("inventory.html")) {
                System.out.println("Site 1 login successful");

                System.out.println("Site 1 Inventory Title: " + obj_driver.getTitle());
            } else {


                System.out.println("Site 1 login failed");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

