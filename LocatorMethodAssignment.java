package PlayWright_Day1;

import com.microsoft.playwright.*;

public class LocatorMethodAssignment {

    public static void main(String[] args) {

        try (Playwright obj_Playwright = Playwright.create()) {
            Browser obj_browser = obj_Playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );
            BrowserContext obj_context = obj_browser.newContext();
            Page obj_page = obj_context.newPage();

            try {
                obj_page.navigate("file:///C:/Users/CCST/Downloads/TestcasesClassAssignment-drivingLicenseUI.html");
                obj_page.waitForTimeout(1000);

                obj_page.locator("#licenseType").selectOption("permanent");
                obj_page.waitForTimeout(1000);

                obj_page.locator("#fullname").fill("amit");
                obj_page.locator("#address").fill("Pune");
                obj_page.locator("#age").fill("25");
                obj_page.locator("#placeofbirth").fill("Maharashtra");
                obj_page.waitForTimeout(1000);

                obj_page.locator("#Male").check();
                obj_page.waitForTimeout(1000);

                obj_page.locator("[name='color_yes']").check();
                obj_page.waitForTimeout(1000);

                obj_page.locator("#languages").selectOption(new String[]{"english", "marathi"});
                obj_page.waitForTimeout(1000);

                obj_page.locator("#identity").setInputFiles(java.nio.file.Paths.get("C:\\Users\\CCST\\Pictures\\butterfly.jpg"));
                obj_page.waitForTimeout(1000);


                Locator obj_submit = obj_page.locator("button:has-text('Submit'), button:has-text('submit'), input[type='submit']");

                System.out.println("Attempting to click submit button...");


                Page obj_newpage = obj_context.waitForPage(() -> {
                    obj_submit.click();
                });

                Thread.sleep(1000);
                System.out.println("Current Page Title: " + obj_page.title());
                System.out.println("Current Page URL: " + obj_page.url());
                obj_newpage.waitForLoadState();
                System.out.println("Success! New Page Title: " + obj_newpage.title());
                System.out.println("Success! New Page URL: " + obj_newpage.url());

                obj_newpage.waitForTimeout(2000);
                obj_newpage.close();

            } catch (Exception e) {
                System.out.println("An error occurred during UI automation execution:");
                e.printStackTrace();
            } finally {
                obj_page.close();
                obj_browser.close();
            }
        }
    }
}
