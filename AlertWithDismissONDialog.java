package PalyWright_Day4;

import com.microsoft.playwright.*;
import java.util.concurrent.atomic.AtomicInteger;

public class AlertWithDismissONDialog {

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser obj_browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );
            BrowserContext obj_context = obj_browser.newContext();
            Page obj_page = obj_context.newPage();
            obj_page.navigate("file:///C:/Users/CCST/Downloads/SeleniumMaterial-20260915T091244Z-1-001/SeleniumMaterial/javascriptAlerts.html");

            AtomicInteger confirmCount = new AtomicInteger(0);
            AtomicInteger promptCount = new AtomicInteger(0);


            obj_page.onDialog(dialog -> {
                System.out.println("Dialog type:" + dialog.type() + ",message:" + dialog.message());
                switch (dialog.type()) {
                    case "alert":
                        dialog.accept();
                        break;
                    case "confirm":
                        if (confirmCount.getAndIncrement() == 0) {
                            dialog.accept();
                        } else {
                            dialog.dismiss();
                        }
                        break;
                    case "prompt":
                        if (promptCount.getAndIncrement() == 0) {
                            dialog.accept("CCST Student");
                        } else {
                            dialog.dismiss();
                        }
                        break;
                }
            });

            obj_page.click("#confirmBtn");
            obj_page.waitForTimeout(500);
            String confirmResultAccept = obj_page.textContent("#confirmResult");
            if (confirmResultAccept.equals("You clicked OK.")) {
                System.out.println("PASS: confirm dialogue accepted correctly.");
            } else {
                System.out.println("FAIL: confirm accept result mismatch. Actual: " + confirmResultAccept);
            }

            obj_page.click("#confirmBtn");
            obj_page.waitForTimeout(500);
            String confirmResultDismiss = obj_page.textContent("#confirmResult");
            if (confirmResultDismiss.equals("You clicked Cancel.")) {
                System.out.println("PASS: confirm dialogue dismissed correctly.");
            } else {
                System.out.println("FAIL: confirm dismiss result mismatch. Actual: " + confirmResultDismiss);
            }


            obj_page.click("#promptBtn");
            obj_page.waitForTimeout(1000);
            String promptResultAccept = obj_page.textContent("#promptResult");
            if (promptResultAccept.equals("You entered: CCST Student")) {
                System.out.println("PASS: prompt dialogue accepted correctly.");
            } else {
                System.out.println("FAIL: prompt accept result mismatch. Actual: " + promptResultAccept);
            }


            obj_page.click("#promptBtn");
            obj_page.waitForTimeout(1000);

            String promptResult = obj_page.locator("#promptResult").textContent();
            if (promptResult.equals("Prompt was dismissed.")) {
                System.out.println("PASS: prompt dialogue handled correctly.");
            } else {
                System.out.println("FAIL: prompt dismiss mismatch. Actual: " + promptResult);
            }

            obj_browser.close();
        }
    }
}
