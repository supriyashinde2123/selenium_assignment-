package PlayWright_Day2;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class LocatoreFiltering {
    public static void main(String[] args) throws Exception{
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );
            Page page = browser.newPage();


            page.navigate("file:///C:/Users/CCST/Downloads/LocatorFiltering.html");

            Locator sourceLocator = page.locator("sourceContainer");
            Locator targetLocator = page.locator("targetContainer");

            Locator sitem1= page.locator("#item1");

            Locator sitem2= page.locator("#item2");

            Locator sitem3= page.locator("#item3");

Locator targetCon= page.locator("#targetContainer");


            Thread.sleep(2000);
            sitem1.dragTo(targetCon);
            Thread.sleep(2000);

            sitem2.dragTo(targetCon);
            Thread.sleep(2000);
            sitem3.dragTo(targetCon);
            Thread.sleep(2000);


            System.out.println("successfully all element");


            page.waitForTimeout(2000);

            browser.close();
        }
    }
}
