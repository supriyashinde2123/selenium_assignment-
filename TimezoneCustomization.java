package PlayWright_Day2;

import com.microsoft.playwright.*;

public class TimezoneCustomization {
    public static void main(String[] args) {

        try (Playwright playwright = Playwright.create();
             Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false))) {



            System.out.println("Configuring Browser Context to: Asia/Kolkata");

            try (BrowserContext kolkataContext = browser.newContext(new Browser.NewContextOptions()
                    .setLocale("ja-JP")
                    .setTimezoneId("Asia/Kolkata"))) {

                Page page1 = kolkataContext.newPage();
                page1.navigate("https://wikipedia.org");

                String tzKolkata = (String) page1.evaluate("() => Intl.DateTimeFormat().resolvedOptions().timeZone");
                String timeKolkata = (String) page1.evaluate("() => new Date().toLocaleString('en-US', { timeZoneName: 'long' })");

                System.out.println("Verified Browser Timezone ID : " + tzKolkata);
                System.out.println("Verified Browser Wall Clock Time: " + timeKolkata);
            }




            System.out.println("Configuring Browser Context to: Europe/Paris");

            try (BrowserContext parisContext = browser.newContext(new Browser.NewContextOptions()
                    .setLocale("ja-JP")
                    .setTimezoneId("Europe/Paris"))) {

                Page page2 = parisContext.newPage();
                page2.navigate("https://wikipedia.org");

                String tzParis = (String) page2.evaluate("() => Intl.DateTimeFormat().resolvedOptions().timeZone");
                String timeParis = (String) page2.evaluate("() => new Date().toLocaleString('en-US', { timeZoneName: 'long' })");

                System.out.println("Verified Browser Timezone ID : " + tzParis);
                System.out.println("Verified Browser Wall Clock Time: " + timeParis);
            }



            System.out.println("Configuring Browser Context to: Australia/Sydney");

            try (BrowserContext sydneyContext = browser.newContext(new Browser.NewContextOptions()
                    .setLocale("ja-JP")
                    .setTimezoneId("Australia/Sydney"))) {

                Page page3 = sydneyContext.newPage();
                page3.navigate("https://wikipedia.org");

                String tzSydney = (String) page3.evaluate("() => Intl.DateTimeFormat().resolvedOptions().timeZone");
                String timeSydney = (String) page3.evaluate("() => new Date().toLocaleString('en-US', { timeZoneName: 'long' })");

                System.out.println("Verified Browser Timezone ID : " + tzSydney);
                System.out.println("Verified Browser Wall Clock Time: " + timeSydney);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
