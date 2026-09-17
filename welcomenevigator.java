import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.Set;
public class welcomenevigator {

        public static void main (String[]args){
            WebDriver Loc_driver = new FirefoxDriver();

            try {
                Loc_driver.get("file:///C:/Users/CCST/Downloads/SeleniumMaterial-20260915T091244Z-1-001/SeleniumMaterial/TestcasesClassAssignment-drivingLicenseUI.html");

                // Parent handel

                String parenthandel = Loc_driver.getWindowHandle();


                Thread.sleep(1000);

                WebElement obj_Fullname = Loc_driver.findElement(By.cssSelector("input[id = 'fullName']"));
                WebElement obj_Address = Loc_driver.findElement(By.cssSelector("input[id = 'address']"));
                WebElement obj_Age = Loc_driver.findElement(By.cssSelector("input[id = 'age']"));
                WebElement obj_Birthplace = Loc_driver.findElement(By.cssSelector("input[id = 'place of birth']"));
                WebElement obj_MaleGender = Loc_driver.findElement(By.cssSelector("input[id = 'Male']"));
//          WebElement obj_FemalGender = Loc_driver.findElement(By.xpath("Female"));
                WebElement obj_IsColourBlind = Loc_driver.findElement(By.cssSelector("input[name = 'color_yes']"));
                WebElement obj_clickSubmit = Loc_driver.findElement(By.cssSelector("button[type = 'submit']"));

                Thread.sleep(1000);

                obj_Fullname.sendKeys("amit");
                Thread.sleep(1000);
                obj_Address.sendKeys("pune");
                Thread.sleep(1000);
                obj_Age.sendKeys("23");
                Thread.sleep(1000);
                obj_Birthplace.sendKeys("aaa");
                Thread.sleep(1000);
                obj_MaleGender.click();
                Thread.sleep(1000);
                obj_IsColourBlind.click();
                Thread.sleep(1000);
                obj_clickSubmit.click();
                Thread.sleep(3000);

                // get windi=ows handles
                Set<String> allWindows = Loc_driver.getWindowHandles();


                for (String windowhandel : allWindows) {
                    System.out.println("windows handle dec " + windowhandel);

                    if (!windowhandel.equals(parenthandel)) {
                        Loc_driver.switchTo().window(windowhandel);
                        String NewWindow = Loc_driver.getWindowHandle();
                        System.out.println("windows handle dec " + windowhandel);
                        break;
                    }
                }


                String actulUrl = Loc_driver.getCurrentUrl();

                if (actulUrl.contains("welcome.html")) {
                    System.out.println("PASS : URL Verified" + actulUrl);
                } else {
                    System.out.println("FAILED : URL Mismatch" + actulUrl);
                }



                System.out.println(Loc_driver.getTitle());

                Thread.sleep(7000);

                WebElement vi = Loc_driver.findElement(By.xpath("//input[@id='nameField' and @disabled]"));
                if(!vi.isEnabled()){
                    System.out.println("Test case pass For Disable Text Box");
                }else {
                    System.out.println("TestCase Failed (If Enable)For Disable Text Box");
                }

                Thread.sleep(2000);
                WebElement Entername = Loc_driver.findElement(By.id("enterNameBtn"));
                Entername.click();

                WebElement vi2 = Loc_driver.findElement(By.id("nameField"));
                if(vi2.isEnabled()){
                    vi2.sendKeys("sam");
                    System.out.println("Test case pass For Enable Text Box");
                }else {
                    System.out.println("TestCase Failed (If Disable)");
                }


            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //Loc_driver.quit();
            }
        }
    }

