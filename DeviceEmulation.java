package PlayWright_Day1;

import com.microsoft.playwright.*;

import java.awt.*;

public class DeviceEmulation {
    static  final String LOGIN_URL="file:///C:/Users/CCST/Downloads/login.html";

    public static void main(String[] args) {
        try (Playwright obj_playwright=Playwright.create()){
            Browser obj_browser=obj_playwright.chromium().launch
                    (new BrowserType.LaunchOptions().setHeadless(false));
//            BrowserContext obj_context=obj_browser.newContext(new Browser.NewContextOptions()
//                            .setUserAgent("Mozilla/5.0 (iPhone; CPU iPhone OS 17_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.0 Mobile/15E148 Safari/604.1")
//                    .setViewportSize(360,640)
//                   .setDeviceScaleFactor(2)
//                   .setIsMobile(true)
//                   .setHasTouch(true));

            BrowserContext obj_desktopSafariContext = obj_browser.newContext(new Browser.NewContextOptions()
                    .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.0 Safari/605.1.15")
                    .setViewportSize(1280, 720)
                    .setDeviceScaleFactor(2)
                    .setIsMobile(false)
                    .setHasTouch(false));


//            BrowserContext obj_context=obj_browser.newContext(new Browser.NewContextOptions()
//                    .setUserAgent("Mozilla/5.0(BB10;Touch) AppleWebKit/537.10+(KHTML,like Gecko) versio/10.0.9.2372 Mobile Safari/537.10+")
//                    .setViewportSize(360,640)
//                    .setDeviceScaleFactor(2)
//                    .setIsMobile(true)
//                    .setHasTouch(true));



            Page obj_page=obj_desktopSafariContext.newPage();
            Thread.sleep(2000);
            obj_page.navigate(LOGIN_URL);
            obj_page.locator("[data-testid='username-input']").fill("validuser");
            obj_page.locator("[data-testid='password-input']").fill("validpassword");
            Thread.sleep(2000);
            Page obj_controlspage=obj_desktopSafariContext.waitForPage(()->{
                obj_page.locator("[data-testid='submit-btn']").click();
            });
            //login
            Thread.sleep(2000);
            obj_controlspage.waitForLoadState();
            //validate navigation by url
            String url=obj_controlspage.url();
            System.out.println("Navigated to:"+url);
            if(!url.contains("ControlsPractice.html")){
                throw new AssertionError("Navigation failed.Expected Controlspractice.html but got:"+url);
            }
            //validate page title
            String title=obj_controlspage.title();
            System.out.println("page title:"+title);


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
