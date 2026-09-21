package PlayWright_Day2;
import com.microsoft.playwright.*;
import com.microsoft.playwright.*;
import java.util.List;

public class Locatorfiltering_2 {



        static final String LOGIN_URL = "file:///C:/Users/CCST/Downloads/ControlsPractice.html";

        public static void main(String[] args) throws Exception {
            try (Playwright playwright = Playwright.create()) {
                Browser browser = playwright.chromium().launch(
                        new BrowserType.LaunchOptions().setHeadless(false)
                );

                Page obj_page = browser.newPage();
                obj_page.navigate(LOGIN_URL);
                obj_page.waitForLoadState();


                Locator modulesDropdown = obj_page.locator("#module");
                modulesDropdown.click();

                Locator options = modulesDropdown.locator("option");


                Locator targetValue = options.filter(new Locator.FilterOptions().setHasText("CCST"));


                String targetAttributeValue = targetValue.getAttribute("value");
                System.out.println("Extracted filtered value: " + targetAttributeValue);


                modulesDropdown.selectOption(targetAttributeValue);


                Locator rows = obj_page.locator("table tbody tr");
                int rowcount = rows.count();
                System.out.println("Row count: " + rowcount);


                Locator targetedRowValue = rows.filter(new Locator.FilterOptions().setHasText("YourRowIdentifier"));
                if (targetedRowValue.count() > 0) {
                    targetedRowValue.locator("input[type='number']").fill("99");
                }


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


