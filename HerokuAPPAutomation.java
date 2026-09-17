import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class HerokuAPPAutomation {


        public static void main(String[] args) {




                WebDriver driver = new ChromeDriver();
                driver.manage().window().maximize();


                driver.get("https://the-internet.herokuapp.com/add_remove_elements/");


                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

                try {

                    WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Add Element')]")));


                    addButton.click();
                    int buttonsToAdd = 3;
                    for (int i = 0; i < buttonsToAdd; i++) {
                        addButton.click();
                    }
                    int totalExpectedButtons = buttonsToAdd + 1;
                    System.out.println("Clicked 'Add Element' successfully.");


                    wait.until(ExpectedConditions.presenceOfElementLocated(By.className("added-manually")));
                    List<WebElement> deleteButtons = driver.findElements(By.className("deleteElement"));

                    if (deleteButtons.size() == totalExpectedButtons) {
                        System.out.println("Validation Passed: " + deleteButtons.size() + " Delete buttons found.");
                    } else {
                        System.out.println("Validation Failed: Expected " + totalExpectedButtons + " buttons but found " + deleteButtons.size());
                    }



                    for (int i = deleteButtons.size() - 1; i >= 0; i--) {
                        deleteButtons.get(i).click();
                    }


                    boolean areButtonsGone = wait.until(ExpectedConditions.invisibilityOfAllElements(deleteButtons));
                    if (areButtonsGone) {

                    }

                } catch (Exception e) {

                    e.printStackTrace();
                } finally {

                    driver.quit();
                }
            }
        }
