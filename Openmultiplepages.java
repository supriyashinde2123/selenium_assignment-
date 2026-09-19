package PlayWright_Day1;
import com.microsoft.playwright.*;
public class Openmultiplepages {
        public static void main(String[] args) {

            try (Playwright obj_Playwright = Playwright.create()) {

                Browser obj_browser = obj_Playwright.chromium().launch(
                        new BrowserType.LaunchOptions().setHeadless(false)
                );

                BrowserContext obj_context = obj_browser.newContext();

                System.out.println("Browser version: " + obj_browser.version());


                Page obj_page1 = obj_context.newPage();
                obj_page1.navigate("https://example.com");
                System.out.println("Page 1 Title: " + obj_page1.title());
                obj_page1.waitForTimeout(1000);
                obj_page1.close();


                Page obj_page2 = obj_context.newPage();
                obj_page2.navigate("https://saucedemo.com");
                System.out.println("Page 2 Title: " + obj_page2.title());
                obj_page2.waitForTimeout(1000);
                obj_page2.close();

                System.out.println("Playwright multiple page test completed successfully!");


                obj_context.close();
                obj_browser.close();

            } catch (Exception e) {
                System.out.println("Test failed with an exception:");
                e.printStackTrace();
            }
        }
    }


