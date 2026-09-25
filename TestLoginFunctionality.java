package segregation_Details;

import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestLoginFunctionality {
    public static void main(String[] args) {

    }

    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;

    @BeforeMethod
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000)
        );
        context = browser.newContext();
        page = context.newPage();
    }

    @Test
    public void testSuccessfulLogin() {
        page.navigate("https://www.saucedemo.com/");

        String pageTitle = page.title();
        System.out.println("Page Title: " + pageTitle);
        Assert.assertEquals(pageTitle, "Swag Labs", "Page title does not match!");

        Locator obj_username = page.getByPlaceholder("Username");
        obj_username.fill("standard_user");

        Locator obj_password = page.getByPlaceholder("Password");
        obj_password.fill("secret_sauce");

        Locator obj_submit = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login"));
        obj_submit.click();

        page.waitForURL("**/inventory.html");
        Assert.assertTrue(page.url().contains("inventory.html"), "Login failed! Did not redirect to inventory page.");
        System.out.println("Login Status: Test Pass");
    }

    @AfterMethod
    public void tearDown() {
        if (page != null) {
            page.close();
        }
        if (context != null) {
            context.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
            System.out.println("Playwright closed completely.");
        }
    }
}
