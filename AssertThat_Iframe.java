package PalyWright_Day4;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class AssertThat_Iframe {

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser obj_browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );
            BrowserContext obj_context = obj_browser.newContext();
            Page obj_page = obj_context.newPage();

            obj_page.navigate("file:///C:/Users/CCST/Downloads/SeleniumMaterial-20260915T091244Z-1-001/SeleniumMaterial/iFrameDemo.html");
            System.out.println("page open in context: " + obj_context.pages().size());
            System.out.println("URL after navigate: " + obj_page.url());


            FrameLocator obj_frame1 = obj_page.frameLocator("iframe").nth(0);
            Locator frame1Btn = obj_frame1.locator("#frame1Btn");
            frame1Btn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            frame1Btn.click();


            Locator frame1Result = obj_frame1.locator("#frame1Result");
            assertThat(frame1Result).hasText("Frame 1 button clicked!");
            System.out.println("pass: Frame 1 By index handled correctly");


            FrameLocator obj_frame2 = obj_page.frameLocator("iframe[name='frameByName']");
            Locator frame2Input = obj_frame2.locator("#frame2Input");
            frame2Input.fill("PlayWright is amazing");


            assertThat(frame2Input).hasValue("PlayWright is amazing");
            System.out.println("pass: Frame 2(by name) handled correctly.");


            FrameLocator obj_frame3 = obj_page.frameLocator("#frame3");
            Locator dropdown = obj_frame3.locator("#frame3dropdown");
            dropdown.selectOption(new SelectOption().setLabel("Two"));


            assertThat(dropdown).hasValue("Two");

            System.out.println("pass: Frame 3(by element) handled correctly.");


            Locator mainBtn = obj_page.locator("#mainBtn");


            assertThat(mainBtn).isVisible();
            System.out.println("pass: successfully returned to main obj_page context.");

            obj_browser.close();
        }
    }
}
