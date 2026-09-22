package PlayWrightRevisionDay3;

import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

// Import native TestNG Assertions
import org.testng.Assert;

public class TestCaseBasicNG {

    private Playwright obj_Playwright;
    private Browser obj_Browser;
    private BrowserContext obj_Context;
    private Page obj_page;

    @BeforeMethod
    public void setUp() {
        obj_Playwright = Playwright.create();
        obj_Browser = obj_Playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        obj_Context = obj_Browser.newContext();
        obj_page = obj_Context.newPage();
        obj_page.navigate("https://saucedemo.com");
    }

    @Test(priority = 1)
    public void testPositiveLogin() throws InterruptedException {
        // Step 1: Fill valid credentials
        obj_page.locator("#user-name").fill("standard_user");
        obj_page.locator("#password").fill("secret_sauce");

        Thread.sleep(1000);
        obj_page.locator("#login-button").click();

        // Step 2: TestNG Assertion on URL
        String currentUrl = obj_page.url();
        Assert.assertTrue(currentUrl.contains("inventory.html"),
                "Login failed! URL does not contain inventory.html");

        // Step 3: TestNG Assertion to verify product header exists
        boolean isHeaderDisplayed = obj_page.locator(".title").isVisible();
        Assert.assertTrue(isHeaderDisplayed, "Product catalog header is not visible!");
    }

    @Test(priority = 2)
    public void testNegativeLogin() throws InterruptedException {
        // Step 1: Fill invalid credentials
        obj_page.locator("#user-name").fill("standard_user");
        obj_page.locator("#password").fill("wrong_password");

        Thread.sleep(1000);
        obj_page.locator("#login-button").click();

        // Step 2: TestNG Assertion on URL (Should NOT change to inventory page)
        String currentUrl = obj_page.url();
        Assert.assertFalse(currentUrl.contains("inventory.html"),
                "URL erroneously updated to inventory page on failed login");

        // Step 3: TestNG Assertion checking the error message text
        String errorMessage = obj_page.locator("[data-test='error']").innerText();
        Assert.assertEquals(errorMessage,
                "Epic sadface: Username and password do not match any user in this service",
                "Error message text did not match expected string!");
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(1000);
        if (obj_Browser != null) obj_Browser.close();
        if (obj_Playwright != null) obj_Playwright.close();
    }
}
