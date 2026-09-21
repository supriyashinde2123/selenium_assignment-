package PlayWright_Day2;

import com.microsoft.playwright.*;
import java.util.List;

public class DragDropLoop {
    public static void main(String[] args) throws Exception {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );

            Page obj_page = browser.newPage();
            obj_page.navigate("file:///C:/Users/CCST/Downloads/LocatorFiltering.html");


            Locator sourcecontainer = obj_page.locator("#sourceContainer");
            Locator targetcontainer = obj_page.locator("#targetContainer");


            List<Locator> listItems = sourcecontainer.locator(".draggable-item").all();


            for (int i = 0; i < listItems.size(); i++) {


                Locator item = sourcecontainer.locator(".draggable-item").first();

                System.out.println("Moving item: " + item.innerText());


                item.dragTo(targetcontainer);

                obj_page.waitForTimeout(1000);
            }

            obj_page.waitForTimeout(2000);
            browser.close();
        }
    }
}
