package test;

import Base.BaseTest;
import config.TestConfig;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class DashboardTests extends BaseTest {
    @Test
    public void userIsLoggedInWithoutUiLogin() {
        page.navigate(TestConfig.BASE_URL + "/inventory.html");
        assertThat(page).hasURL("https://www.saucedemo.com/");
    }

    @Test
    public void verifyDashboardProductsLoaded() {

        page.navigate(TestConfig.BASE_URL + "/inventory.html");


        //  assertThat(page.locator(".app_logo")).isVisible();


        assertThat(page.locator(".inventory_item")).hasCount(0);
    }


}
