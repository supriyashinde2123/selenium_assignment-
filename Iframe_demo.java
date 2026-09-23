package PalyWright_Day4;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;

public class Iframe_demo {
    public static void main(String[] args) throws InterruptedException {
        try (Playwright playwright = Playwright.create()) {
            Browser obj_browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );
            BrowserContext obj_context = obj_browser.newContext();
            Page obj_page = obj_context.newPage();

            obj_page.navigate("file:///C:/Users/CCST/Downloads/SeleniumMaterial-20260915T091244Z-1-001/SeleniumMaterial/iFrameDemo.html");
            System.out.println("page open in context:" + obj_context.pages().size());
            System.out.println("URL after navigate:" + obj_page.url());


            FrameLocator obj_frame1 = obj_page.frameLocator("iframe").nth(0);
            Locator frame1Btn = obj_frame1.locator("#frame1Btn");
            frame1Btn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            frame1Btn.click();
            Thread.sleep(2000);

            String frame1Result = obj_frame1.locator("#frame1Result").textContent();
            if (frame1Result.equals("Frame 1 button clicked!")) {
                System.out.println("pass:Frame 1 By index handled correctly");
            } else {
                System.out.println("fail:Frame 1 result mismatch");
            }
            Thread.sleep(2000);


            FrameLocator obj_frame2 = obj_page.frameLocator("iframe[name='frameByName']");
            Locator frame2Input = obj_frame2.locator("#frame2Input");
            frame2Input.fill("PlayWright is amazing");
            String enteredValue = frame2Input.inputValue();
            if (enteredValue.equals("PlayWright is amazing")) {
                System.out.println("pass:Frame 2(by name)handled correctly.");
            } else {
                System.out.println("fail:frame 2 input mismatch.");
            }
            Thread.sleep(2000);


            FrameLocator obj_frame3 = obj_page.frameLocator("#frame3");
            Locator dropdown = obj_frame3.locator("#frame3Dropdown");
            dropdown.selectOption(new SelectOption().setLabel("Two"));

            String selectedValue = dropdown.locator("option:checked").textContent();
            if (selectedValue.trim().equals("Two")) {
                System.out.println("pass:Frame3(by element)handled correctly.");
            } else {
                System.out.println("fail: frame 3 dropdown mismatch.");
            }
            Thread.sleep(2000);


            Locator mainBtn = obj_page.locator("#mainBtn");
            if (mainBtn.isVisible()) {
                System.out.println("pass: successfully return to main obj_page context.");
            }
            Thread.sleep(2000);

            obj_browser.close();
        }
    }
}
