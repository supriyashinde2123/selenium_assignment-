package PlayWrightRevisionDay3;

import com.microsoft.playwright.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import java.nio.file.Paths;

public class Screenshot {
    public static void main(String[] args) throws Exception {
        // Declare variables outside try block so they are accessible in the catch block
        Playwright obj_Playwright = null;
        Browser obj_Browser = null;
        Page obj_page = null;

        try {
            // Step 1: create playwright object
            obj_Playwright = Playwright.create();

            // Step 2: launch the browser
            obj_Browser = obj_Playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

            // Step 3: create a browser context object
            BrowserContext obj_Context = obj_Browser.newContext();

            // Step 4: create a page object
            obj_page = obj_Context.newPage();

            obj_page.navigate("https://www.saucedemo.com/inventory1.html");
            System.out.println("Page Title: " + obj_page.title());

            Locator obj_username = obj_page.locator("#user-name");
            obj_username.fill("standard_user");

            Locator obj_password = obj_page.locator("#password");
            obj_password.fill("secret_sauce");

            Thread.sleep(2000);

            Locator obj_submit = obj_page.locator("#login-button");
            obj_submit.click();

            assertThat(obj_page).hasURL("https://saucedemo.com");

            String currentUrl = obj_page.url();
            if (currentUrl.contains("inventory.html")) {
                System.out.println("Test Pass");
            } else {
                System.out.println("Test Failed");
            }
            System.out.println("Current URL: " + currentUrl);
            Thread.sleep(2000);

        } catch (AssertionError e) {
            if (obj_page != null) {
                String screenshotpath = "Screenshot/testLogin_" + System.currentTimeMillis() + ".png";
                obj_page.screenshot(new Page.ScreenshotOptions()
                        .setPath(Paths.get(screenshotpath))
                        .setFullPage(true));
                System.out.println("Screenshot saved: " + screenshotpath);
            }
            throw e;
        }

        }
    }






