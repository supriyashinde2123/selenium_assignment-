package PalyWright_Day4;

import com.microsoft.playwright.*;

public class SauseDemoSolmo {
    public static void main(String[] args) throws Exception {
        // Step 1: create playwright object
        try (Playwright obj_Playwright = Playwright.create()) {

            // Step 2: launch the browser with properly chained LaunchOptions
            Browser obj_Browser = obj_Playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000) // Changed to 1000ms so slow-mo is actually visible
            );

            // Step 3: create a browser context object
            BrowserContext obj_Context = obj_Browser.newContext();

            // Step 4: create a page object
            Page obj_page = obj_Context.newPage();
            obj_page.navigate("https://www.saucedemo.com/");
            System.out.println("Page Title: " + obj_page.title());

            // Step 5: Fill login credentials
            Locator obj_username = obj_page.locator("#user-name");
            obj_username.fill("standard_user");

            Locator obj_password = obj_page.locator("#password");
            obj_password.fill("secret_sauce");

            Thread.sleep(2000);

            // Step 6: Click login button
            Locator obj_submit = obj_page.locator("#login-button");
            obj_submit.click();

            // Step 7: Verify login success
            String currentUrl = obj_page.url();
            if (currentUrl.contains("inventory.html")) {
                System.out.println("Test Pass");
            } else {
                System.out.println("Test Failed");
            }
            System.out.println("Current URL: " + obj_page.url());

            Thread.sleep(2000);
            obj_Browser.close();
        }
    }
}
