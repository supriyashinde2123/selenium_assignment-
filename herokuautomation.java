package PlayWrightRevisionDay3;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;

public class herokuautomation {
    public static void main(String[] args) throws Exception {

        try (Playwright obj_Playwright = Playwright.create()) {
            Browser obj_Browser = obj_Playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

            BrowserContext obj_Context = obj_Browser.newContext();
            Page obj_page = obj_Context.newPage();
            obj_page.navigate("https://the-internet.herokuapp.com/");
            Thread.sleep(2000);
            Locator addRemoveElement = obj_page.locator("//a[text()='Add/Remove Elements']");
            Thread.sleep(2000);
            addRemoveElement.click();
            Thread.sleep(2000);

            if (obj_page.url().contains("add_remove_elements")) {
                System.out.println("Test Pass");

                Locator addElementBtn = obj_page.locator("//button[text()='Add Element']");
                Thread.sleep(2000);
                addElementBtn.click();
                System.out.println("Clicked Add Element button successfully.");
                Thread.sleep(2000);
                Locator deleteBtn = obj_page.locator("//button[text()='Delete']");

                if (deleteBtn.isVisible()) {
                    System.out.println("Validation Pass: Delete button is visible.");
                } else {
                    System.out.println("Validation Failed: Delete button is NOT visible.");
                }

                deleteBtn.click();
                if(!deleteBtn.isVisible()){
                    System.out.println("delete button is disappears");
                    Thread.sleep(2000);

                }else{
                    System.out.println("delete button is appears");
                    Thread.sleep(2000);
                }
                Thread.sleep(2000);
                System.out.println("Test Failed");

                obj_page.close();

            }
        }
    }
}
