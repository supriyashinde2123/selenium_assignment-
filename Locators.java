package PlayWright_Day1;

import com.microsoft.playwright.*;

public class Locators {
    static String username = "standard_user";
    static String password = "secret_sauce";

    // Fixed the attribute name from 'values' to 'value'
    static String css_LoginButton = "input[type=\"submit\"][value=\"Login\"]";
    static String name_userName = "[name=\"user-name\"]";
    static String id_password = "#password";

    public static void main(String[] args) {
        try (Playwright obj_Playwright = Playwright.create()) {
            Browser obj_browser = obj_Playwright.chromium()
                    .launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page obj_page = obj_browser.newPage();


            obj_page.navigate("https://www.saucedemo.com/");


            try {
                Thread.sleep(5000);

                Locator obj_userNameLocator = obj_page.locator(name_userName);
                obj_userNameLocator.fill(username);

                obj_page.locator(id_password).fill(password);
                obj_page.locator(css_LoginButton).click();


                String pageTitle = obj_page.title();
                if (pageTitle.equals("Swag Labs")) {
                    System.out.println("Test passed: Title is 'Swag Labs'");
                } else {
                    System.out.println("Test Failed: Title is '" + pageTitle + "', but expected 'Swag Labs'");
                }
            } catch (Exception e) {
                System.out.println("Test failed with an exception:");
                e.printStackTrace();
            }


            obj_browser.close();
        }
    }
}
