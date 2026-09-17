import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
public class assignment5 {

    public static void main(String[] args) {

        WebDriver obj_Driver = new ChromeDriver();

        try {

            obj_Driver.get("file:///C:/Users/CCST/Downloads/SeleniumMaterial-20260915T091244Z-1-001/SeleniumMaterial/TestcasesClassAssignment-drivingLicenseUI.html");

            Thread.sleep(1000);


            WebElement Obj_licenseType = obj_Driver.findElement(By.id("licenseType"));
            Select licenseTypeSelect = new Select(Obj_licenseType);

            Thread.sleep(1000);
            licenseTypeSelect.selectByVisibleText("Permanent");
            licenseTypeSelect.selectByValue("permanent");
            licenseTypeSelect.selectByIndex(1);

            WebElement txtFullName = obj_Driver.findElement(By.id("fullname"));
            txtFullName.sendKeys("amit");

            WebElement txtAddress = obj_Driver.findElement(By.id("address"));
            txtAddress.sendKeys("Pune");

            WebElement txtAge = obj_Driver.findElement(By.id("age"));
            txtAge.sendKeys("25");

            WebElement txtPlaceOfBirth = obj_Driver.findElement(By.id("placeofbirth"));
            txtPlaceOfBirth.sendKeys("Maharashtra");
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
             licenseTypeSelect=new Select(Obj_licenseType);

            Thread.sleep(1000);

            licenseTypeSelect.selectByVisibleText("permanent");

            licenseTypeSelect.selectByValue("permanent");

            licenseTypeSelect.selectByIndex(1);
            Thread.sleep(1000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            obj_Driver.quit();
        }
    }

}
