package PlayWright_Day2;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;


public class LocatorFiltering {

    static final String LOGIN_URL = "file:///C:/Users/CCST/Downloads/LocatorFiltering.html";

    public static void main(String[] args) {

        try (Playwright obj_playwright = Playwright.create()) {
            Browser obj_browser = obj_playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );
            BrowserContext obj_context = obj_browser.newContext();
            Page obj_page = obj_context.newPage();

            obj_page.navigate(LOGIN_URL);
            obj_page.waitForLoadState();

            Locator sourceContainer = obj_page.locator(".container")
                    .filter(new Locator.FilterOptions()
                            .setHas(obj_page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("TO DO"))));

            Locator targetContainer = obj_page.locator(".container")
                    .filter(new Locator.FilterOptions()
                            .setHas(obj_page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Done"))));

            System.out.println("Source container visible: " + sourceContainer.isVisible());
            System.out.println("Target container visible: " + targetContainer.isVisible());

            Locator itemToDrag = obj_page.locator("#item1");
            System.out.println("item to drag: " + itemToDrag.innerText());

            itemToDrag.dragTo(targetContainer);

            obj_page.waitForTimeout(500);

            String resultText = obj_page.locator("#result").innerText();
            System.out.println("Result text: " + resultText);

            if (!resultText.equals("Write Selenium Tests moved to Done")) {
                throw new AssertionError("Unexpected result text: " + resultText);
            }


        }}}