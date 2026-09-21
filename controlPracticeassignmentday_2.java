package PlayWright_Day2;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.SelectOption;

public class controlPracticeassignmentday_2 {

    public static void main(String[] args) throws Exception {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );
            Page page = browser.newPage();
            page.navigate("file:///C:/Users/CCST/Downloads/ControlsPractice.html");


            var dropdown = page.getByTestId("#module");

            dropdown.selectOption("CCST");
            Thread.sleep(2000);

            dropdown.selectOption(new SelectOption().setLabel("DAI"));
            Thread.sleep(2000);

            dropdown.selectOption(new SelectOption().setLabel("HPCSA"));
            page.waitForTimeout(1000);

            dropdown.selectOption(new SelectOption().setIndex(3));
            Thread.sleep(2000);

            browser.close();
        }
    }
}

