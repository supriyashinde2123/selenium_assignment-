package PlayWright_Day1;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.impl.junit.BrowserContextExtension;

public class TestPlayWrightSetup {

    public static void main(String[] args) {

        try(Playwright obj_Playwright=Playwright.create()){

            Browser obj_browser=obj_Playwright.chromium().launch();

            BrowserContext obj_context=obj_browser.newContext();

            Page obj_page=obj_context.newPage();

            obj_page.navigate("https://example.com");

            System.out.println("Browser version:"+obj_browser.version());

            System.out.println("page title"+obj_page.title());
            System.out.println("playwright installation works correctly!");

            obj_page.waitForTimeout(2000);

            obj_page.close();

        }
        catch (Exception e){
            System.out.println("Test failed with an exception:");

            e.printStackTrace();
        }
    }
}
