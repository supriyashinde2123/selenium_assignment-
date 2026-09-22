package PlayWrightRevisionDay3;

import com.microsoft.playwright.*;

public class Dragdrop {
    public static void main(String[] args) throws Exception {

        try (Playwright obj_Playwright = Playwright.create()) {
            Browser obj_Browser = obj_Playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

            BrowserContext obj_Context = obj_Browser.newContext();
            Page obj_page = obj_Context.newPage();
            obj_page.navigate("https://the-internet.herokuapp.com/");
            Thread.sleep(2000);



            Locator obj_DragDropLink = obj_page.locator("//a[text()='Drag and Drop']");
            obj_DragDropLink.click();
            Thread.sleep(2000);
            Locator sourceElement = obj_page.locator("#column-a");
            Thread.sleep(2000);
            Locator targetElement = obj_page.locator("#column-b");

            sourceElement.dragTo(targetElement);
            Thread.sleep(2000);
            System.out.println("Drag and Drop action performed successfully.");
            Thread.sleep(2000);
            String sourceText = obj_page.locator("#column-a header").textContent();
            String targetText = obj_page.locator("#column-b header").textContent();

            if (sourceText.equals("B") && targetText.equals("A")) {
                System.out.println("Validation Pass: " + sourceText);
            } else {
                System.out.println("Validation Failed: " + sourceText);
            }

            obj_page.close();

        }
    }
}

