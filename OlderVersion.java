package PlayWright_Day1;

import com.microsoft.playwright.*;

import java.nio.file.Paths;

public class OlderVersion {

        public static void main(String[] args) {

            try(Playwright obj_Playwright=Playwright.create()){

                Browser obj_browser = obj_Playwright.chromium().launch(
                        new BrowserType.LaunchOptions()
                                .setHeadless(false)
                                .setExecutablePath(Paths.get("C:\\Program Files\\Google\\Chrome Beta\\Application\\chrome.exe")));
                System.out.println("Browser version:"+obj_browser.version());

                BrowserContext obj_context=obj_browser.newContext();

                Page obj_page=obj_context.newPage();

                obj_page.navigate("https://example.com");

                System.out.println("Browser version:"+obj_browser.version());

                System.out.println("page title"+obj_page.title());
                System.out.println("playwright installation works correctly!");

                obj_page.waitForTimeout(2000);

                obj_page.close();

            }
            catch (Exception e){
                System.out.println("Test failed with an exception:");

                e.printStackTrace();
            }
        }
    }




