package PlayWright_Day2;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;

public class ExplicitWait {
    static String strPage = "file:///C:/Users/CCST/Downloads/ControlsPractice.html";

    public static void main(String[] args) {
        try (Playwright obj_playwright = Playwright.create()) {
            Browser obj_browser = obj_playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500)
            );
            BrowserContext obj_context = obj_browser.newContext();
            Page obj_page = obj_context.newPage();

            obj_page.navigate(strPage);


            Locator moduleDropdown = obj_page.locator("#module");

            long start = System.currentTimeMillis();
            moduleDropdown.selectOption(new SelectOption().setLabel("CCST"));


            Locator rows = obj_page.locator("#studentTableBody tr");
            rows.first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

            long elapsed = System.currentTimeMillis() - start;
            System.out.println("Time taken to load rows: " + elapsed + " ms");
            System.out.println("Updated row count after selection: " + rows.count());


            Locator rowsWithMarkInput = rows.filter(
                    new Locator.FilterOptions().setHas(obj_page.locator("input[type='number']"))
            );


            for (Locator row : rowsWithMarkInput.all()) {
                Locator marksInput = row.locator("input[type='number']");
                marksInput.fill("99");
            }


            Locator saveBtn = obj_page.locator("#saveButton");
            if (saveBtn.isEnabled()) {
                saveBtn.click();
                System.out.println("Saved successfully!");
            }

            obj_page.waitForTimeout(2000);


            obj_context.close();
            obj_browser.close();
        }
    }
}