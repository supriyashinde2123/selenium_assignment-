package PlayWright_Day1;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;

public class BuiltInLocators  {
    public static void main(String[] args) throws Exception {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );
            Page page = browser.newPage();

            page.navigate("file:///C:/Users/CCST/Downloads/login.html");


            page.getByLabel("Username").fill("admin");
            Thread.sleep(2000);


            page.getByLabel("Password").fill("admin");

            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign In")).click();
            Thread.sleep(2000);

            String paragraphText = page.getByText("Forgot your password?").textContent();
            Thread.sleep(2000);
            System.out.println("Pass:Found paragraph using getByText().Text:" + paragraphText);
            Thread.sleep(2000);

            browser.close();
        }
    }
}
