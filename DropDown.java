package PlayWright_Day2;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
public class DropDown {

        public static void main(String[] args) {
            try (Playwright playwright = Playwright.create()) {
                Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                Page page = browser.newPage();


                page.navigate("file:///C:/Users/CCST/Downloads/ControlsPractice.html");


                Locator moduleDropdown = page.locator("#module");
                Thread.sleep(2000);


                moduleDropdown.selectOption("CCST");
                page.waitForTimeout(1000);


                moduleDropdown.selectOption(new SelectOption().setValue("DAI"));
                page.waitForTimeout(1000);


                moduleDropdown.selectOption(new SelectOption().setLabel("HPCSA"));
                page.waitForTimeout(1000);


                moduleDropdown.selectOption(new SelectOption().setIndex(2));
                page.waitForTimeout(1000);

                browser.close();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

