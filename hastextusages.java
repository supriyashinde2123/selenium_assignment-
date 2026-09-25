package PlayWright_Day5;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;
import static PlayWright_Day5.CustomeAsseration.assertThat;
import com.microsoft.playwright.*;
import org.testng.annotations.Test;

import static PlayWright_Day5.CustomeAsseration.assertThat;

public class hastextusages {

        @Test
        void testhastextusages() {
            Playwright obj_playwright = Playwright.create();


            Browser obj_Browser = obj_playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );


            BrowserContext obj_context = obj_Browser.newContext();
            Page obj_page = obj_context.newPage();

            obj_page.navigate("https://www.saucedemo.com/");


            assertThat(obj_page.locator("#login-button")).hasCssClass("submit-button");
        }
    }


