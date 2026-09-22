package PlayWrightRevisionDay3;

import com.microsoft.playwright.*;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

// Import java.nio.file.Paths for video directory path mapping
import java.nio.file.Paths;

public class RecordVideo {
    private Playwright obj_Playwright;
    private Browser obj_Browser;
    private BrowserContext obj_Context;
    private Page obj_page;

    @BeforeMethod
    public void setUp() {
        obj_Playwright = Playwright.create();
        obj_Browser = obj_Playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));


        obj_Context = obj_Browser.newContext(
                new Browser.NewContextOptions()
                        .setRecordVideoDir(Paths.get("videos/"))
                        .setRecordVideoSize(1280, 720));


        obj_page = obj_Context.newPage();
        obj_page.navigate("https://saucedemo.com");
    }

    @Test(priority = 1)
    public void testPositiveLogin() throws InterruptedException {
        obj_page.locator("#user-name").fill("standard_user");
        obj_page.locator("#password").fill("secret_sauce");
        Thread.sleep(1000);
        obj_page.locator("#login-button").click();

        String currentUrl = obj_page.url();
        Assert.assertTrue(currentUrl.contains("inventory.html"), "Login failed! URL does not contain inventory.html");

        boolean isHeaderDisplayed = obj_page.locator(".title").isVisible();
        Assert.assertTrue(isHeaderDisplayed, "Product catalog header is not visible!");
    }

    @Test(priority = 2)
    public void testNegativeLogin() throws InterruptedException {
        obj_page.locator("#user-name").fill("standard_user");
        obj_page.locator("#password").fill("wrong_password");
        Thread.sleep(1000);
        obj_page.locator("#login-button").click();

        String currentUrl = obj_page.url();
        Assert.assertFalse(currentUrl.contains("inventory.html"), "URL erroneously updated to inventory page on failed login");

        String errorMessage = obj_page.locator("[data-test='error']").innerText();
        Assert.assertEquals(errorMessage, "Epic sadface: Username and password do not match any user in this service", "Error message text did not match expected string!");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        // FIX 4: Capture the video object BEFORE closing the context
        Video video = obj_page.video();


        java.nio.file.Path videoPath = (video != null) ? video.path() : null;


        if (obj_Context != null) obj_Context.close();
        if (obj_Browser != null) obj_Browser.close();
        if (obj_Playwright != null) obj_Playwright.close();


        if (result.getStatus() == ITestResult.FAILURE) {
            if (videoPath != null) {
                System.out.println("Test failed - keeping video: " + videoPath.toAbsolutePath());
            }
        } else {
            if (videoPath != null) {
                try {
                    java.nio.file.Files.deleteIfExists(videoPath);
                    System.out.println("Test passed - video deleted successfully.");
                } catch (Exception e) {
                    System.out.println("Could not delete video: " + e.getMessage());
                }
            }
        }
    }
}
