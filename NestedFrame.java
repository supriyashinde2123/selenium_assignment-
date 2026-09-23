package PalyWright_Day4;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.util.Arrays;

public class NestedFrame {
    public static void main(String[] args) throws InterruptedException {
        try (Playwright playwright = Playwright.create()) {
            Browser obj_browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
                            .setArgs(Arrays.asList("--start-maximized"))
            );
            BrowserContext obj_context = obj_browser.newContext();
            Page obj_page = obj_context.newPage();
            obj_page.setDefaultTimeout(10000);

            obj_page.navigate("file:///C:/Users/CCST/Downloads/SeleniumMaterial-20260915T091244Z-1-001/SeleniumMaterial/iFrameDemo.html");


            FrameLocator outerFrame = obj_page.frameLocator("iframe[id='outerFrame']");
            System.out.println("Located outer frame.");

            Locator outerHeading = outerFrame.locator("h4");
            outerHeading.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            System.out.println("Outer frame heading text: " + outerHeading.textContent());


            FrameLocator innerFrame = outerFrame.frameLocator("iframe#innerFrame");
            System.out.println("Located inner (nested) frame.");


            Locator innerFrameBtn = innerFrame.locator("#innerFrameBtn");
            innerFrameBtn.click();
            Thread.sleep(2000);

            String innerResult = innerFrame.locator("#innerFrameResult").textContent();
            if (innerResult.equals("Inner frame button clicked!")) {
                System.out.println("PASS: nested inner frame button handled correctly.");
            } else {
                System.out.println("FAIL: Inner frame result mismatch.");
            }
            Thread.sleep(2000);


            boolean backInOuter = outerFrame.locator("iframe#innerFrame").count() > 0;
            if (backInOuter) {
                System.out.println("PASS: outer frame context.");
            } else {
                System.out.println("FAIL: Inner frame Result mismatch.");
            }
            Thread.sleep(2000);


            backInOuter = outerFrame.locator("iframe#innerFrame").count() > 0;
            if (backInOuter) {
                System.out.println("PASS: outer frame context verified again.");
            } else {
                System.out.println("FAIL: could not find innerFrame from outer frame context.");
            }


            Locator mainBtn = obj_page.locator("#mainBtn");
            if (mainBtn.isVisible()) {
                System.out.println("PASS: successfully returned to main page context.");
            } else {
                System.out.println("FAIL: did not return to main page correctly.");
            }

            obj_browser.close();
        }
    }
}
