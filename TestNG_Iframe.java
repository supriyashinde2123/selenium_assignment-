package PalyWright_Day4;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNG_Iframe {


    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;

    @BeforeMethod
    public void setUp() {

        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();


        page.navigate("file:///C:/Users/CCST/Downloads/SeleniumMaterial-20260915T091244Z-1-001/SeleniumMaterial/iFrameDemo.html");

        System.out.println("Page open in context: " + context.pages().size());
        System.out.println("URL after navigate: " + page.url());
    }

    @Test
    public void testIframeHandling() throws InterruptedException {

        FrameLocator obj_frame1 = page.frameLocator("iframe").nth(0);
        Locator frame1Btn = obj_frame1.locator("#frame1Btn");

        frame1Btn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        frame1Btn.click();
        Thread.sleep(2000);

        String frame1Result = obj_frame1.locator("#frame1Result").textContent();
        Assert.assertEquals(frame1Result, "Frame 1 button clicked!", "Frame 1 result mismatch");
        System.out.println("pass: Frame 1 By index handled correctly");


        FrameLocator obj_frame2 = page.frameLocator("iframe[name='frameByName']");
        Locator frame2Input = obj_frame2.locator("#frame2Input");

        frame2Input.fill("PlayWright is amazing");
        Thread.sleep(2000);

        String enteredValue = frame2Input.inputValue();
        Assert.assertEquals(enteredValue, "PlayWright is amazing", "Frame 2 input mismatch.");
        System.out.println("pass: Frame 2 (by name) handled correctly.");


        FrameLocator obj_frame3 = page.frameLocator("#frame3");
        Locator dropdown = obj_frame3.locator("#frame3Dropdown");

        dropdown.selectOption(new SelectOption().setLabel("Two"));
        Thread.sleep(2000);

        String selectedValue = dropdown.locator("option:checked").textContent();
        Assert.assertEquals(selectedValue.trim(), "Two", "Frame 3 dropdown mismatch.");
        System.out.println("pass: Frame 3 (by element) handled correctly.");


        Locator mainBtn = page.locator("#mainBtn");
        Assert.assertTrue(mainBtn.isVisible(), "Main page button is not visible.");
        System.out.println("pass: Successfully interacted within main page context.");
        Thread.sleep(2000);
    }

    @AfterMethod
    public void tearDown() {

        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
        System.out.println("Browser and Playwright instances closed successfully.");
    }
}
