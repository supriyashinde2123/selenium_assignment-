package segregation_Details;

import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.BrowserType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestLoginFunctionality {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;

    @DataProvider(name = "sauceCredentials")
    public Object[][] sauceCredentialsSupplier() {
        return new Object[][] {
                {"standard_user", "secret_sauce", true},
                {"locked_out_user", "secret_sauce", false},
                {"invalid_user", "invalid_pass", false}
        };
    }

    @BeforeMethod
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500)
        );
        context = browser.newContext();
        page = context.newPage();
    }

    @Test(dataProvider = "sauceCredentials")
    public void testLoginScenarios(String username, String password, boolean isSuccessExpected) {
        page.navigate("https://www.saucedemo.com/");


        String pageTitle = page.title();
        Assert.assertEquals(pageTitle, "Swag Labs", "Page title does not match!");


        LoginFunctionality loginPage = new LoginFunctionality(page);


        loginPage.performLogin(username, password);

        if (isSuccessExpected) {

            page.waitForURL("**/inventory.html");
            Assert.assertTrue(page.url().contains("inventory.html"), "Login failed for valid user: " + username);
            System.out.println("Successful Login Verified for: " + username);
        } else {

            String errorMsg = loginPage.getErrorMessageText();
            Assert.assertFalse(errorMsg.isEmpty(), "Error notification element was missing for invalid state!");
            System.out.println("Negative Login Interception Passed for: " + username + " -> Reason: " + errorMsg);
        }
    }

    @AfterMethod
    public void tearDown() {
        if (playwright != null) {
            playwright.close();
            System.out.println("Playwright close.");
        }
    }
}
