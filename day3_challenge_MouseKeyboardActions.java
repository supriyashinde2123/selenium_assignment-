import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import java.io.File;
import java.io.FileWriter;
import java.time.Duration;

public class day3_challenge_MouseKeyboardActions {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();


        File tempFile = new File("C:\\html\\dom");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("Sample test content for Selenium upload automation.");
        } catch (Exception e) {
            System.out.println("Could not create temporary file: " + e.getMessage());
        }

        try {
            driver.get("file:///C:/Users/CCST/Downloads/challenge_MouseKeyboardActions.html");
            Actions actions = new Actions(driver);

            Thread.sleep(2000);


            WebElement documentsMenu = driver.findElement(By.xpath("//*[contains(text(), 'Documents')]"));
            actions.moveToElement(documentsMenu).perform();
            System.out.println("Hovered over Documents.");

            Thread.sleep(2000);

            WebElement uploadOption = driver.findElement(By.xpath("//*[contains(text(), 'Upload Document')]"));
            System.out.println("Is Upload visible? " + uploadOption.isDisplayed());


            uploadOption.click();
            System.out.println("Clicked Upload Document.");

            Thread.sleep(2000);


            WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']"));
            System.out.println("Is Upload section visible? " + fileInput.isDisplayed());


            fileInput.sendKeys(tempFile.getAbsolutePath());
            System.out.println("File path sent to input field.");

            Thread.sleep(3000);


            WebElement statusMessage = driver.findElement(By.xpath("//input[@type='file']/following-sibling::*"));
            System.out.println("Result Text: " + statusMessage.getText());

            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (tempFile.exists()) {
                tempFile.delete();
            }
            driver.quit();
        }
    }
}
