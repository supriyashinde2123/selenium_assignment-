package PalyWright_Day4;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
public class Navtigatetonewpagelogin {

        public static void main(String[] args) throws Exception {
            try (Playwright obj_Playwright = Playwright.create()) {
                Browser obj_Browser = obj_Playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
                BrowserContext obj_Context = obj_Browser.newContext();
                Page obj_page = obj_Context.newPage();

                obj_page.navigate("file:///C:/Users/CCST/Downloads/login.html");

                Locator usernameField = obj_page.locator("#username");
                Locator passwordField = obj_page.locator("#password");
                Locator obj_submit = obj_page.locator("text=Sign In");

                usernameField.fill("admin");
                passwordField.fill("admin123");


                Page popupPage = obj_page.waitForPopup(() -> {
                    obj_submit.click();
                });


                popupPage.waitForLoadState();

                String expectedUrl = "file:///C:/Users/CCST/Downloads/ControlsPractice.html?";
                String actualUrl = popupPage.url();

                System.out.println("Actual Page URL: " + actualUrl);

                assertThat(popupPage).hasURL(expectedUrl);

                if (actualUrl.equals(expectedUrl)) {
                    System.out.println("Assertion Passed: URL matches.");
                } else {
                    System.out.println("Assertion Failed: URL mismatch.");
                }

                obj_Browser.close();
            }
        }
    }

