package Day1;

import com.microsoft.playwright.*;
import com.microsoft.playwright.*;
import java.nio.file.Paths;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SessionStorage {


        static String userName = "standard_user";
        static String password = "secret_sauce";
        static String css_LoginButton = "input[type=\"submit\"][value=\"Login\"]";
        static String id_userName = "#user-name";
        static String id_password = "#password";

        public static void main(String[] args) throws Exception {
            // Step 1: Create playwright object
            try (Playwright obj_Playwright = Playwright.create()) {

                // Step 2: Launch the browser
                Browser obj_Browser = obj_Playwright.chromium().launch(
                        new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));

                // Step 3: Create the first context, log in, and save session
                BrowserContext obj_Context = obj_Browser.newContext();
                Page obj_page = obj_Context.newPage();

                obj_page.navigate("https://www.saucedemo.com/");
                obj_page.locator(id_userName).fill(userName);
                obj_page.locator(id_password).fill(password);
                obj_page.locator(css_LoginButton).click();

                // Verify login success
                assertThat(obj_page).hasURL("https://www.saucedemo.com/inventory.html");

                // FIX: Saving with lowercase path
                obj_Context.storageState(new BrowserContext.StorageStateOptions()
                        .setPath(Paths.get("auth/storagestate.json")));

                obj_page.close();
                obj_Context.close();

                // Step 4: Create a new context loading the saved session
                // FIX: Loading with matching lowercase path
                BrowserContext obj_context1 = obj_Browser.newContext(
                        new Browser.NewContextOptions()
                                .setStorageStatePath(Paths.get("auth/storagestate.json")));

                Page obj_page1 = obj_context1.newPage();

                // This will now successfully load directly into the inventory page bypass login
                obj_page1.navigate("https://www.saucedemo.com/inventory.html");

                // Verify the new page successfully loaded the authenticated session
                assertThat(obj_page1).hasURL("https://www.saucedemo.com/inventory.html");

                obj_page1.close();
                obj_context1.close();
            }
        }
    }

