package segregation_Details;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TestNGViewProductAddToCart {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;

    @BeforeMethod
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500)
        );
        context = browser.newContext();
        page = context.newPage();
    }

    @Test
    public void testAddProductAndViewCart() {
        System.out.println("--- Starting Add to Cart and View Cart Test ---");


        page.navigate("https://www.saucedemo.com");
        page.getByPlaceholder("Username").fill("standard_user");
        page.getByPlaceholder("Password").fill("secret_sauce");
        page.locator("#login-button").click();


        assertThat(page).hasURL(java.util.regex.Pattern.compile(".*/inventory.html"));
        System.out.println("Login Status: Pass (Dashboard loaded successfully)");


        String expectedProductName = page.locator(".inventory_item_name").first().textContent();


        Locator addToCartBtn = page.locator("button:has-text('Add to cart')").first();
        assertThat(addToCartBtn).isEnabled();
        addToCartBtn.click();
        System.out.println("Product clicked to add to cart.");


        Locator cartBadge = page.locator(".shopping_cart_badge");
        assertThat(cartBadge).hasText("1");
        System.out.println("Cart container badge verified with text value: 1");


        page.locator(".shopping_cart_link").click();
        assertThat(page).hasURL(java.util.regex.Pattern.compile(".*/cart.html"));
        System.out.println("Navigation Status: Navigated to Cart page successfully.");


        Locator cartItemName = page.locator(".inventory_item_name").first();
        assertThat(cartItemName).hasText(expectedProductName);
        System.out.println("Cart Validation Pass: Selected item matches target product -> " + expectedProductName);
    }

    @AfterMethod
    public void tearDown() {
        if (playwright != null) {
            playwright.close();
            System.out.println("Playwright structure closed down clean.");
        }
    }
}
