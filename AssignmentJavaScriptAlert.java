package PalyWright_Day4;

import com.microsoft.playwright.*;

public class AssignmentJavaScriptAlert {

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser obj_browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );
            BrowserContext obj_context = obj_browser.newContext();
            Page obj_page = obj_context.newPage();
            obj_page.navigate("file:///C:/Users/CCST/Downloads/SeleniumMaterial-20260915T091244Z-1-001/SeleniumMaterial/javascriptAlerts.html");


            obj_page.onceDialog(dialog -> {
                System.out.println("Alert text: " + dialog.message());
                dialog.accept();
            });

            System.out.println("Before Alert");
            obj_page.click("#alertBtn");
            System.out.println("After Alert");


            String alertResult = obj_page.locator("#alertResult").textContent();
            if (alertResult.equals("Alert was shown and accepted.")) {
                System.out.println("PASS: simple alert handled correctly.");
            } else {
                System.out.println("FAIL: simple alert mismatch. Actual: " + alertResult);
            }


            obj_page.onceDialog(dialog -> {
                System.out.println("Confirm text (Accept): " + dialog.message());
                dialog.accept();
            });
            obj_page.click("#confirmBtn");

            String confirmResult = obj_page.locator("#confirmResult").textContent();
            if (confirmResult.equals("You clicked OK.")) {
                System.out.println("PASS: confirm dialogue accepted correctly.");
            } else {
                System.out.println("FAIL: confirm accept mismatch. Actual: " + confirmResult);
            }


            obj_page.onceDialog(dialog -> {
                System.out.println("Confirm text (Dismiss): " + dialog.message());
                dialog.dismiss();
            });
            obj_page.click("#confirmBtn");

            String confirmDismissResult = obj_page.locator("#confirmResult").textContent();
            if (confirmDismissResult.equals("You clicked Cancel.")) {
                System.out.println("PASS: confirm dialogue dismissed correctly.");
            } else {
                System.out.println("FAIL: confirm dismiss mismatch. Actual: " + confirmDismissResult);
            }


            obj_page.onceDialog(dialog -> {
                System.out.println("Prompt text (Accept): " + dialog.message());
                dialog.accept("CCST Student");
            });
            obj_page.click("#promptBtn");

            String promptResult = obj_page.locator("#promptResult").textContent();
            if (promptResult.equals("You entered: CCST Student")) {
                System.out.println("PASS: prompt dialogue handled correctly.");
            } else {
                System.out.println("FAIL: prompt accept mismatch. Actual: " + promptResult);
            }

            obj_page.onceDialog(Dialog::dismiss);

            obj_page.click("#promptBtn");
            obj_page.waitForTimeout(500);

            promptResult = obj_page.locator("#promptResult").textContent();

            if (promptResult.equals("Prompt was dismissed.")) {
                System.out.println("PASS: prompt dialogue handled correctly.");
            } else {
                System.out.println("FAIL: prompt accept mismatch. Actual: " + promptResult);
            }

            obj_browser.close();
        }
    }
}




