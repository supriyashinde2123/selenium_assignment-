package PalyWright_Day4;

import com.microsoft.playwright.*;
import java.nio.file.Paths;

public class TraceViewer {
    public static void main(String[] args) {
        try (Playwright obj_Playwright = Playwright.create()) {
            Browser obj_Browser = obj_Playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext obj_Context = obj_Browser.newContext();


            obj_Context.tracing().start(new Tracing.StartOptions()
                    .setSnapshots(true)
                    .setScreenshots(true)
                    .setSources(true));

            Page obj_page = obj_Context.newPage();
            try {
                String filepath = Paths.get("C:/Users/CCST/Downloads/SeleniumMaterial-20260915T091244Z-1-001/SeleniumMaterial/actionClass_Menu.html")
                        .toUri().toString();

                obj_page.navigate(filepath);
                obj_page.locator("#productsMenu").hover();
                obj_page.locator("#productsSubmenu").waitFor();
                obj_page.locator("#laptopsLink").click();

                String resultText = obj_page.locator("#result").textContent();
                System.out.println("Result: " + resultText);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {

                if (obj_page != null) {
                    obj_page.close();
                }


                obj_Context.tracing().stop(new Tracing.StopOptions()
                        .setPath(Paths.get("D:\\ccstjul2026\\Playwright\\trace.zip")));


                obj_Context.close();
                obj_Browser.close();
            }
        }
    }
}
