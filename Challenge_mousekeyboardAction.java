package PalyWright_Day4;
import com.microsoft.playwright.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.microsoft.playwright.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Challenge_mousekeyboardAction {

    public static void main(String[] args) {

        try (Playwright obj_playwright = Playwright.create()) {

            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
            BrowserContext obj_context = obj_browser.newContext(new Browser.NewContextOptions().setAcceptDownloads(true));


            Page page = obj_browser.newPage();
            page.navigate("file:///C:/Users/CCST/Downloads/SeleniumMaterial-20260915T091244Z-1-001/SeleniumMaterial/challenge_MouseKeyboardActions.html");
            System.out.println(page.title());

            Locator obj_hover = page.locator("#documentsMenu");
            obj_hover.hover();

            Locator obj_submenu = page.locator("#uploadDocLink");
            obj_submenu.click();

            Locator obj_input = page.locator("#fileInput");
            String file = "C:\\Users\\CCST\\Pictures/butterfly.jpg";
            page.setInputFiles("#fileInput", Paths.get(file));

            Locator obj_upload = page.locator("#uploadBtn");
            obj_upload.click();

            Locator obj_result = page.locator("#result");

            assertThat(obj_result).hasText("You greedy fellow !!");

        }
    }
}