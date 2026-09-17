import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
public class testcasesClassAssignment_drivingLicenseUI {

        public static void main(String[] args) {

            WebDriver obj_Driver = new ChromeDriver();

            try {

                obj_Driver.get("file:///C:/Users/CCST/Downloads/SeleniumMaterial-20260915T091244Z-1-001/SeleniumMaterial/TestcasesClassAssignment-drivingLicenseUI.html");

                Thread.sleep(1000);


                WebElement licenseDropdown = obj_Driver.findElement(By.id("licenseType"));
                Select selectLicense = new Select(licenseDropdown);
                selectLicense.selectByValue("permanent");
                Thread.sleep(1000);


                WebElement FullName = obj_Driver.findElement(By.id("fullname"));
                FullName.sendKeys("amit");

                WebElement Address = obj_Driver.findElement(By.id("address"));
                Address.sendKeys("Pune");

                WebElement Age = obj_Driver.findElement(By.id("age"));
                Age.sendKeys("25");

                WebElement PlaceOfBirth = obj_Driver.findElement(By.id("placeofbirth"));
                PlaceOfBirth.sendKeys("Maharashtra");
                Thread.sleep(1000);


                WebElement radioMale = obj_Driver.findElement(By.cssSelector("#Male"));
                if (!radioMale.isSelected()) {
                    radioMale.click();
                }
                Thread.sleep(1000);


                WebElement chkColorNo = obj_Driver.findElement(By.name("color_yes"));
                if (!chkColorNo.isSelected()) {
                    chkColorNo.click();
                }
                Thread.sleep(1000);


                WebElement languagesDropdown = obj_Driver.findElement(By.id("languages"));
                Select selectLanguages = new Select(languagesDropdown);


                if (selectLanguages.isMultiple()) {
                    selectLanguages.selectByValue("english");
                    selectLanguages.selectByValue("marathi");
                }
                Thread.sleep(1000);


                WebElement fileUpload = obj_Driver.findElement(By.id("identity"));

                fileUpload.sendKeys("C:\\Users\\Public\\Documents\\sample_id.png");
                Thread.sleep(1000);


                WebElement btnSubmit = obj_Driver.findElement(By.xpath("//button[text()='submit']"));
                btnSubmit.click();

                Thread.sleep(1000);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {

                obj_Driver.quit();
            }
        }

}
