package PlayWright_Day2;

import com.microsoft.playwright.*;

public class GeolocationCustomization {
    public static void main(String[] args) {

        try (Playwright playwright = Playwright.create();
             Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
             BrowserContext obj_context = browser.newContext(new Browser.NewContextOptions().setLocale("ja-JP"))) {

            Page obj_Page = obj_context.newPage();
            obj_Page.navigate("https://wikipedia.org");


            obj_Page.waitForTimeout(5000);

            System.out.println("Page title: " + obj_Page.title());


            Locator obj_langLabel = obj_Page.locator("#jsLangLabel");
            if (obj_langLabel.isVisible()) {
                String langValue = obj_langLabel.textContent();
                System.out.println("Language: " + langValue);
            } else {
                System.out.println("Language label element not found on this page state.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
