package PalyWright_Day4;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;
import java.util.Arrays;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
public class AssertThatNestedFrame {
    public static void main(String[] args) {
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


            Locator innerFrameResult = innerFrame.locator("#innerFrameResult");
            assertThat(innerFrameResult).hasText("Inner frame button clicked!");
            System.out.println("PASS: nested inner frame button handled correctly.");


            Locator innerFrameInOuter = outerFrame.locator("iframe#innerFrame");
            assertThat(innerFrameInOuter).isVisible();
            System.out.println("PASS: outer frame context verified.");


            Locator mainBtn = obj_page.locator("#mainBtn");
            assertThat(mainBtn).isVisible();
            System.out.println("PASS: successfully verified main page context.");

            obj_browser.close();
        }
    }
}

