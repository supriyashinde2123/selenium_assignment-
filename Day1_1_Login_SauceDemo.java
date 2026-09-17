import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Day1_1_Login_SauceDemo {
    public static void main(String[] args) {
        WebDriver obj_driver = new ChromeDriver();
        try{
            obj_driver.get("https:www.saucedemo.com");
            Thread.sleep(1000);
            WebElement obj_username= obj_driver.findElement(By.id("user-name"));
            WebElement obj_password= obj_driver.findElement(By.id("password"));
            WebElement obj_loginButton= obj_driver.findElement(By.id("login-button"));
            obj_username.sendKeys("standard_user");
            obj_password.sendKeys("secret_sauce");
            Thread.sleep(1000);
            obj_loginButton.click();
            Thread.sleep(1000);
            if(obj_driver.getCurrentUrl().contains("inventory.html")){
                System.out.println("login successful");
            }else{
                System.out.println("login failed");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            obj_driver.quit();
        }
    }
}
