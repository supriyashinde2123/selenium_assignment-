package PlayWrightRevisionDay3;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
public class Login {
    public static void main(String[] args)throws  Exception {
        try(Playwright obj_Playwright=Playwright.create()){
            Browser obj_Browser=obj_Playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext obj_Context= obj_Browser.newContext();
            Page obj_page=obj_Context.newPage();
            obj_page.navigate("file:///C:/Users/CCST/Downloads/login.html");
            System.out.println(obj_page.title());
            Locator obj_username = obj_page.locator("#username");
            obj_username.fill("admin");
            Locator obj_password = obj_page.locator("#password");
            obj_password.fill("123");
            Thread.sleep(2000);
            Locator obj_submit = obj_page.getByTestId("submit-btn");
            Thread.sleep(2000);
            obj_submit.click();
            String currentUrl = obj_page.url();
            if (currentUrl.contains("inventory.html")) {
                System.out.println("Test Pass");
            } else {
                System.out.println("Test Failed");
            }

            System.out.println("current url:"+ obj_page.url());
            Thread.sleep(2000);
            obj_Browser.close();
        }
    }

}
