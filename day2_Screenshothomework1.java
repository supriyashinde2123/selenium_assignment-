import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

public class day2_Screenshothomework1 {

     static WebDriver driver;
        static String screenshotDir;
        public static void main(String[] args) {
            screenshotDir="screenshot/";
            new File(screenshotDir).mkdirs();
            try{
                driver=new ChromeDriver();
                driver.get("file:///C:/Users/CCST/Downloads/SeleniumMaterial-20260915T091244Z-1-001/SeleniumMaterial/TestcasesClassAssignment-drivingLicenseUI.html");
                Thread.sleep(1000);
                String actualTitle=driver.getTitle();
                String expectedTitle="Driving license Application....";
                if(actualTitle.equals(expectedTitle)){
                    System.out.println("pass:Title verified.actual title:"+actualTitle);
                }else{
                    System.out.println("fail:Title mismatch.Excepted:"+expectedTitle+",Actual:"+actualTitle);
                    captureScreenshot(driver,"frame4_mismatch");

                }

            }
            catch (Exception e){
                e.printStackTrace();
            }
            finally {
                driver.quit();

            }


        }

        public static void captureScreenshot(WebDriver driver,String file_name){
            try{
                File screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(screenshot,new File(screenshotDir+"/"+file_name+".png"));

            }
            catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("screenshot saved!");
        }
    }



