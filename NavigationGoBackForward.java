package playwright_day7;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class NavigationGoBackForward {
    private static final String BASE="https://the-internet.herokuapp.com";
    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;

    @BeforeMethod
    public void setUp(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
        context = browser.newContext();
        page = context.newPage();
    }

    @Test
    public void demo2_backAndForward(){
        page.navigate(BASE + "/");

        page.navigate(BASE + "/checkboxes");
        assertThat(page).hasURL(Pattern.compile(".*/checkboxes"));

        page.goBack();
        assertThat(page).hasURL(BASE + "/");

        page.goForward();
        assertThat(page).hasURL(Pattern.compile(".*/checkboxes"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        if(context != null) context.close();
        if(browser != null) browser.close();
        if(playwright != null) playwright.close();
    }
}
