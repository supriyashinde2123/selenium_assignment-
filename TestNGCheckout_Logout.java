package segregation_Details;
import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
public class TestNGCheckout_Logout {

        private Playwright obj_Playwright;
        private Browser obj_Browser;
        private BrowserContext obj_Context;
        private Page obj_page;

        @BeforeMethod
        public void setUp() {

            obj_Playwright = Playwright.create();
            obj_Browser = obj_Playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000)
            );
            obj_Context = obj_Browser.newContext();
            obj_page = obj_Context.newPage();
        }

        @Test
        public void testEndToEndCheckoutAndLogout() {
            obj_page.navigate("https://www.saucedemo.com/");
            assertThat(obj_page).hasTitle("Swag Labs");

            // 2. Authentication Flow
            Locator obj_username = obj_page.locator("#user-name");
            assertThat(obj_username).isVisible();
            obj_username.fill("standard_user");
            assertThat(obj_username).hasValue("standard_user");

            Locator obj_password = obj_page.locator("#password");
            assertThat(obj_password).isVisible();
            obj_password.fill("secret_sauce");
            assertThat(obj_password).hasValue("secret_sauce");

            Locator obj_loginBtn = obj_page.locator("#login-button");
            assertThat(obj_loginBtn).isEnabled();
            obj_loginBtn.click();

            assertThat(obj_page).hasURL(java.util.regex.Pattern.compile("inventory.html"));
            Locator firstProduct = obj_page.locator(".inventory_item").first();
            assertThat(firstProduct).isVisible();
            System.out.println("Login Status: Test Pass. Dashboard loaded successfully.");

            String expectedProductName = obj_page.locator(".inventory_item_name").first().textContent();

            Locator obj_addToCartBtn = obj_page.locator("//button[text()='Add to cart']").first();
            assertThat(obj_addToCartBtn).isEnabled();
            obj_addToCartBtn.click();

            Locator obj_removeBtn = obj_page.locator("//button[text()='Remove']").first();
            assertThat(obj_removeBtn).isVisible();

            Locator obj_cartBadge = obj_page.locator(".shopping_cart_badge");
            assertThat(obj_cartBadge).hasText("1");
            System.out.println("Product added to cart. Badge updated successfully.");


            Locator obj_cartLink = obj_page.locator(".shopping_cart_link");
            obj_cartLink.click();
            assertThat(obj_page).hasURL(java.util.regex.Pattern.compile("cart.html"));
            System.out.println("Navigated to cart.");

            Locator cartItemName = obj_page.locator(".inventory_item_name").first();
            assertThat(cartItemName).hasText(expectedProductName);
            System.out.println("Verified product name in cart matches selection: " + expectedProductName);


            Locator obj_checkoutBtn = obj_page.locator("#checkout");
            assertThat(obj_checkoutBtn).isEnabled();
            obj_checkoutBtn.click();
            assertThat(obj_page).hasURL(java.util.regex.Pattern.compile("checkout-step-one.html"));

            Locator obj_firstName = obj_page.locator("#first-name");
            obj_firstName.fill("John");
            assertThat(obj_firstName).hasValue("John");

            Locator obj_lastName = obj_page.locator("#last-name");
            obj_lastName.fill("Doe");
            assertThat(obj_lastName).hasValue("Doe");

            Locator obj_postalCode = obj_page.locator("#postal-code");
            obj_postalCode.fill("411001");
            assertThat(obj_postalCode).hasValue("411001");
            System.out.println("Checkout information filled successfully.");

            Locator obj_continueBtn = obj_page.locator("#continue");
            assertThat(obj_continueBtn).isEnabled();
            obj_continueBtn.click();
            assertThat(obj_page).hasURL(java.util.regex.Pattern.compile("checkout-step-two.html"));


            Locator summarySubtotal = obj_page.locator(".summary_subtotal_label");
            Locator summaryTax = obj_page.locator(".summary_tax_label");
            Locator summaryTotal = obj_page.locator(".summary_total_label");
            assertThat(summarySubtotal).isVisible();
            assertThat(summaryTax).isVisible();
            assertThat(summaryTotal).isVisible();
            System.out.println("Summary breakdown verified: " + summaryTotal.textContent());


            Locator obj_finishBtn = obj_page.locator("#finish");
            assertThat(obj_finishBtn).isEnabled();
            obj_finishBtn.click();
            assertThat(obj_page).hasURL(java.util.regex.Pattern.compile("checkout-complete.html"));

            Locator confirmationHeader = obj_page.locator(".complete-header");
            assertThat(confirmationHeader).hasText("Thank you for your order!");
            System.out.println("Checkout Status: Order Placed Successfully.");


            Locator obj_burgerMenu = obj_page.locator("#react-burger-menu-btn");
            assertThat(obj_burgerMenu).isEnabled();
            obj_burgerMenu.click();

            Locator obj_logoutLink = obj_page.locator("#logout_sidebar_link");
            obj_logoutLink.waitFor(new Locator.WaitForOptions().setState(com.microsoft.playwright.options.WaitForSelectorState.VISIBLE));
            assertThat(obj_logoutLink).isVisible();
            obj_logoutLink.click();


            assertThat(obj_page).hasURL("https://www.saucedemo.com/");
            assertThat(obj_loginBtn).isVisible();
            System.out.println("Logout Status: Test Pass (Successfully logged out)");
        }

        @AfterMethod
        public void tearDown() {
            if (obj_Browser != null) {
                obj_Browser.close();
                System.out.println("Browser closed down cleanly via TestNG lifecycle.");
            }
            if (obj_Playwright != null) {
                obj_Playwright.close();
            }
        }
    }




