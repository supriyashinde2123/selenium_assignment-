package PlayWrightRevisionDay3;


import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class DynamicControl {
    public static void main(String[] args) {
        try (Playwright obj_playwright = Playwright.create()) {

            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

            BrowserContext obj_context = obj_browser.newContext();

            Page obj_page = obj_context.newPage();

            obj_page.navigate("https://the-internet.herokuapp.com/");
            obj_page.waitForTimeout(1000);
            System.out.println("HerokuApp Launched: " + obj_page.title());

            Locator dynamicControls = obj_page.locator("//a[@href='/dynamic_controls']");
            dynamicControls.click();

            obj_page.waitForTimeout(2000);

            if (obj_page.url().contains("/dynamic_controls")) {
                System.out.println("PASSED:Dynamic controls got clicked ");
            } else {
                System.out.println("FAILED");
            }


            assertThat(obj_page).hasURL("https://the-internet.herokuapp.com/dynamic_controls");
            System.out.println("Welcome to Dynamic Controls Page");

            Locator checkBox = obj_page.locator("//input[@type='checkbox']");
            checkBox.check();
            obj_page.waitForTimeout(2000);
            System.out.println("Checkbox Checked");

            Locator removeBtn = obj_page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Remove"));
            removeBtn.click();
            obj_page.waitForTimeout(1000);
            obj_browser.close();
        }
    }
}


