package PlayWright_Day2;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;

public class AutoWait {
    static  String strPage="file:///C:/Users/CCST/Downloads/welcome.html";

    public static void main(String[] args) {
        try(Playwright obj_Playwright=Playwright.create()) {
            Browser obj_browser = obj_Playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext obj_context = obj_browser.newContext();
            Page obj_Page = obj_context.newPage();
            Locator message = obj_Page.locator("#message");
            obj_Page.navigate(strPage);
            long start = System.currentTimeMillis();
            message.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            String labelText = message.innerText();
            long elapsed = System.currentTimeMillis() - start;
            System.out.println("Label text:" + labelText);
            System.out.println("Time waited:" + elapsed + "ms");

        }
        }
    }


