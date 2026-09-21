package PlayWright_Day2;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class BuiltInLocatord2 {

     public static void main(String[] args) throws Exception {

            try (Playwright playwright = Playwright.create()) {
                Browser browser = playwright.chromium().launch(
                        new BrowserType.LaunchOptions().setHeadless(false)
                );
                Page page = browser.newPage();
                page.navigate("file:///C:/Users/CCST/Downloads/login.html");


                page.getByPlaceholder("Username").fill("admin");
                Thread.sleep(2000);


                page.getByTestId("Password").fill("admin");
                Thread.sleep(2000);


                page.getByText("Sign In").click();
                Thread.sleep(2000);

                String paragraphText = page.getByText("Forgot your password?").textContent();
                Thread.sleep(2000);
                System.out.println("Pass:Found paragraph using getByText().Text:" + paragraphText);
                Thread.sleep(2000);

                browser.close();
            }
        }
    }


