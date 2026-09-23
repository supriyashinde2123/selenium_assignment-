package PalyWright_Day4;

import com.microsoft.playwright.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ActionClass_menu {

    public static void main(String[] args) throws Exception {

        try (Playwright playwright = Playwright.create()) {

            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );
            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            page.navigate("file:///C:/Users/CCST/Downloads/SeleniumMaterial-20260915T091244Z-1-001/SeleniumMaterial/actionClass_Menu.html");
            Thread.sleep(1000);

            Locator menu = page.locator("#productsMenu");
            Locator submenu = page.locator("#productsSubmenu");
            Locator resultText = page.locator("#result");
            Locator serviceMenu=page.locator("#servicesMenu");


            menu.hover();
            Thread.sleep(1000);


            Locator laptopsLink = page.locator("text=Laptops");
            laptopsLink.click();
            Thread.sleep(1000);

            assertThat(resultText).hasText("You clicked: Laptops");
            System.out.println("Pass: Clicked Laptops and text matched successfully.");
            Thread.sleep(1000);



            menu.hover();
            Thread.sleep(1000);


            Locator phonesLink = page.locator("text=Phones");
            phonesLink.click();
            Thread.sleep(1000);

            assertThat(resultText).hasText("You clicked: Phones");
            System.out.println("Pass: Clicked Phones and text matched successfully.");
            Thread.sleep(1000);


            menu.hover();
            Thread.sleep(1000);


            Locator tabletsLink = page.locator("text=Tablets");
            tabletsLink.click();
            Thread.sleep(1000);

            assertThat(resultText).hasText("You clicked: Tablets");
            System.out.println("Pass: Clicked Tablets and text matched successfully.");
            Thread.sleep(1000);

            serviceMenu.hover();
            Thread.sleep(1000);


            Locator support1 = page.locator("#supportLink");
            support1.click();
            Thread.sleep(1000);

            assertThat(page.locator("#supportLink")).hasText("Support");
            System.out.println("Pass: Clicked service and text matched successfully.");
            Thread.sleep(1000);



            browser.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
