package PlayWright_Day2;

import com.microsoft.playwright.*;
import java.util.List;

public class LocatorChaining {
    static final String LOGIN_URL = "file:///C:/Users/CCST/Downloads/ControlsPractice.html";

    public static void main(String[] args) throws Exception {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );


            Page obj_page = browser.newPage();
            obj_page.navigate(LOGIN_URL);


            Locator modulesDropdown = obj_page.locator("#module");
            modulesDropdown.click();

            List<String> optionTexts = modulesDropdown.locator("option").allInnerTexts();
            System.out.println("Dropdown options found: " + optionTexts);

            modulesDropdown.selectOption("CCST");


            Locator rows = obj_page.locator("table").locator("tbody").locator("tr");
            int rowcount = rows.count();
            System.out.println("Row count: " + rowcount);

            Thread.sleep(2000);


            for (int i = 0; i < rowcount; i++) {
                Locator row = rows.nth(i);
                Locator marksInput = row.locator("input[type='number']");
                marksInput.fill("99");
            }


            Locator saveButton = obj_page.locator("#saveButton");
            System.out.println("Save button enabled: " + saveButton.isEnabled());
            saveButton.click();


            obj_page.waitForTimeout(2000);


            browser.close();
        }
    }
}
