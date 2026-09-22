package PlayWrightRevisionDay3;

import com.microsoft.playwright.*;

public class LoginPage {
    public static void main(String[] args) throws Exception {
        try (Playwright obj_Playwright = Playwright.create()) {
            Browser obj_Browser = obj_Playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext obj_Context = obj_Browser.newContext();
            Page obj_page = obj_Context.newPage();

            obj_page.navigate("file:///C:/Users/CCST/Downloads/login.html");
             Thread.sleep(1000);
            Locator usernameField = obj_page.locator("#username");
            Thread.sleep(1000);
            Locator passwordField = obj_page.locator("#password");
            Thread.sleep(1000);

            Locator obj_submit = obj_page.locator("text=Sign In");
            Thread.sleep(1000);
            usernameField.fill("admin");
            Thread.sleep(1000);
            passwordField.fill("admin123");
            Thread.sleep(1000);

            obj_submit.click();


            obj_page.waitForLoadState();

            String expectedUrl = "file:///C:/Users/CCST/Downloads/ControlsPractice.html?";
            Thread.sleep(1000);
            String actualUrl = obj_page.url();
            Thread.sleep(1000);
            System.out.println("Actual Page URL: " + actualUrl);
            Thread.sleep(1000);

            if (actualUrl.equals(expectedUrl)) {
                System.out.println("Assertion Passed: URL matches.");
                Thread.sleep(1000);
            } else {
                System.out.println("Assertion Failed: URL mismatch.");
                Thread.sleep(1000);
            }


            Thread.sleep(3000);
        }
    }
}



