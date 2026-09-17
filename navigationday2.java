import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class navigationday2 {

        public static void main(String[] args) {
            WebDriver obj_driver = new ChromeDriver();

            try {

                Thread.sleep(1000);
                obj_driver.manage().window().maximize();
              obj_driver.get("https://www.google.com");
                System.out.println("step 1-loaded via get():" +obj_driver.getTitle());
                System.out.println("handle after get():" +obj_driver.getWindowHandle());
                Thread.sleep(5000);

                obj_driver.get("https://www.selenium.com");
                System.out.println("step 2-navigated via nivagate().to():" +obj_driver.getTitle());
                System.out.println("handle after navigate().to():" +obj_driver.getWindowHandle());
                Thread.sleep(5000);

                obj_driver.get("https://www.wikipedia.com");
                System.out.println("step 3-navigate via navigate().to():" +obj_driver.getTitle());
                System.out.println("handle after navigate().to():" +obj_driver.getWindowHandle());
                Thread.sleep(5000);

                obj_driver.navigate().back();
                System.out.println("step 5 after back() again:" +obj_driver.getTitle());
                Thread.sleep(5000);

                obj_driver.navigate().forward();
                System.out.println("step 6 after forward() again:" +obj_driver.getTitle());
                Thread.sleep(5000);

                obj_driver.navigate().refresh();
                System.out.println("step 7 after refresh() again:" +obj_driver.getTitle());
                System.out.println("handle after navigate().to():"+obj_driver.getWindowHandle());

                Thread.sleep(5000);



            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                obj_driver.quit();
            }
        }
    }



