
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TestNavigation {

    public static void main(String[] args) {

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        try {

            driver.get("file:///C:/Users/CCST/Downloads/SeleniumMaterial-20260915T091244Z-1-001/SeleniumMaterial/TestcasesClassAssignment-drivingLicenseUI.html");
            Thread.sleep(1000);

            // Store the original (parent) window handle
            String parentWindow = driver.getWindowHandle();

            //Create web elements
            WebElement obj_fullname = driver.findElement(By.id("fullname"));
            WebElement obj_address = driver.findElement(By.id("address"));
            WebElement obj_age = driver.findElement(By.id("age"));
            WebElement obj_birthplace = driver.findElement(By.id("placeofbirth"));
            WebElement obj_gender = driver.findElement(By.id("Male"));
            WebElement obj_colorBlind = driver.findElement(By.name("color_yes"));
            WebElement obj_licenseType = driver.findElement(By.id("licenseType"));
            WebElement obj_language = driver.findElement(By.id("languages"));
            WebElement obj_submit = driver.findElement(By.xpath("//button[text()='Submit']"));

            Select languageSelect = new Select(obj_language);
            Select licenseTypeSelect = new Select(obj_licenseType);

            // Input data
            obj_fullname.sendKeys("Virat Kohli");
            obj_address.sendKeys("A/301, Kumar Padmalaya");
            obj_age.sendKeys("21");
            obj_birthplace.sendKeys("PUNE");
            Thread.sleep(1000);

            obj_gender.click();
            obj_colorBlind.click();
            Thread.sleep(1000);
            // Handling Drop downs

            // Option 1: select by visible text
            licenseTypeSelect.selectByVisibleText("Permanent");

            // Option 2: select by value attribute
            licenseTypeSelect.selectByValue("permanent");

            // Option 3: select by index (0 = placeholder, 1 = Permanent, 2 = Learning)
            licenseTypeSelect.selectByIndex(1);

            Thread.sleep(1000);

            // Handling Multi Select controls

            // Option 1: select by visible text
            languageSelect.selectByVisibleText("Marathi");

            // Option 2: select by value attribute
            languageSelect.selectByValue("japanese");

            // Option 3: select by index
            languageSelect.selectByIndex(1);

            Thread.sleep(1000);
            // Perform action - submit form
            obj_submit.click();

            Thread.sleep(1000);

            // Get all window handles
            Set<String> allWindows = driver.getWindowHandles();


            for (String windowHandle : allWindows) {
                System.out.println("window handle desc " + windowHandle);

                if (!windowHandle.equals(parentWindow)) {
                    driver.switchTo().window(windowHandle);
                    String newWindow = driver.getWindowHandle();
                    System.out.println("window handle desc " + windowHandle);
                    break;
                }
            }

            // Verify URL
            String actualUrl = driver.getCurrentUrl();

            if (actualUrl.contains("welcome.html")) {
                System.out.println("PASS: URL verified. Actual URL: " + actualUrl);
            } else {
                System.out.println("FAIL: URL mismatch. Actual URL: " + actualUrl);
            }


            // Verify title
            String actualTitle = driver.getTitle();
            String expectedTitle = "Welcome";
            if (actualTitle.equals(expectedTitle)) {
                System.out.println("PASS: Title verified. Actual Title: " + actualTitle);
            } else {
                System.out.println("FAIL: Title mismatch. Expected: " + expectedTitle + ", Actual: " + actualTitle);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }

    }

}