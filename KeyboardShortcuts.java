package PalyWright_Day4;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class KeyboardShortcuts {

    public static void main(String[] args) throws Exception{

        try (Playwright obj_playwright = Playwright.create()) {

            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
            BrowserContext obj_context = obj_browser.newContext();

            Page page = obj_context.newPage();
            page.navigate("file:///C:/Users/CCST/Downloads/SeleniumMaterial-20260915T091244Z-1-001/SeleniumMaterial/keyboardShortcuts.html");
           Thread.sleep(1000);
            System.out.println("Page Title: " + page.title());

            Locator obj_sourceText = page.locator("#sourceText");
            Thread.sleep(1000);
            Locator obj_targetText = page.locator("#targetText");
            Thread.sleep(1000);
            Locator obj_result = page.locator("#result");
            Thread.sleep(1000);


            obj_sourceText.click();
            page.keyboard().press("Control+A");
            Thread.sleep(1000);
            System.out.println("Select all performed on source container.");
            Thread.sleep(1000);

            page.keyboard().press("Control+C");
            System.out.println("Copy performed on source container.");


            obj_targetText.click();

            page.keyboard().press("Control+V");
            Thread.sleep(1000);
            System.out.println("Paste performed into target container.");
            Thread.sleep(1000);

            String expectedMessage = "Text copied successfully to Target !";
            Thread.sleep(1000);
            assertThat(obj_result).hasText(expectedMessage);


            String actualText = obj_result.innerText();
            Thread.sleep(1000);
            if (actualText.equalsIgnoreCase(expectedMessage)) {
                System.out.println("Pass : text was copied and pasted correctly.");
                Thread.sleep(1000);
            } else {
                System.out.println("Fail: result mismatch, actual : " + actualText);
                Thread.sleep(1000);
            }

            obj_browser.close();
        }
    }
}