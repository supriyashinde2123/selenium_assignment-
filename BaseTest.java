package Base;

import com.microsoft.playwright.*;
import config.TestConfig;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeMethod
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Browser.NewContextOptions options = new Browser.NewContextOptions();
        Path statePath = Paths.get(TestConfig.USER_STATE);

        try {

            if (Files.exists(statePath) && Files.size(statePath) > 0) {
                options.setStorageStatePath(statePath);
            }
        } catch (Exception e) {
            System.err.println("Failed to read storage state file: " + e.getMessage());
        }

        context = browser.newContext(options);
        page = context.newPage();
    }

    @AfterMethod
    public void tearDown() {
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}
