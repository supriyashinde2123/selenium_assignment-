package PlayWrightRevisionDay3;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
public class Revision_BasicPlaywright {

    public static void main(String[] args) throws  Exception {
        //Step 1: create playwright object
        try(Playwright obj_Playwright= Playwright.create()){
            //Step 2: launch the browser
            Browser obj_Browser=obj_Playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            //Step 3: create a browser context object
            BrowserContext obj_Context= obj_Browser.newContext();
            //Step 4: create a page object
            Page obj_page=obj_Context.newPage();
            obj_page.navigate("https://www.saucedemo.com/");
            System.out.println(obj_page.title());
            Locator obj_username = obj_page.locator("#user-name");
            obj_username.fill("standard_user");
            Locator obj_password = obj_page.locator("#password");
            obj_password.fill("secret_sauce");
            Thread.sleep(2000);
            Locator obj_submit = obj_page.locator("#login-button");

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

