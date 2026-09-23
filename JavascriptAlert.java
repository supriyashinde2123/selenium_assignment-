package PalyWright_Day4;

import com.microsoft.playwright.*;

public class JavascriptAlert {
    public static void main(String[] args) throws InterruptedException {
        try (Playwright playwright = Playwright.create()) {
            Browser obj_browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );
            BrowserContext obj_context = obj_browser.newContext();
            Page obj_page = obj_context.newPage();
            obj_page.navigate("file:///C:/Users/CCST/Downloads/SeleniumMaterial-20260915T091244Z-1-001/SeleniumMaterial/javascriptAlerts.html");
            obj_page.onceDialog(dialog -> {
                System.out.println("Alert text: "+ dialog.message());
                try{
                    Thread.sleep(1000);

                }catch (InterruptedException e ){
                    e.printStackTrace();
                }
                dialog.accept();

            });
            System.out.println("Before Alert ");
            obj_page.click("#alertBtn");
            System.out.println("After Alert");
            obj_page.waitForTimeout(1000);
            String alertResult=obj_page.textContent("#alertResult");
            if(alertResult.equals("Alert was shown and accepted.")){
                System.out.println("PASS: simple alert handled correctly.");
            }else {
                System.out.println("FAIL: alert result mismatch.Actual:"+ alertResult);
            }
            obj_page.onceDialog(dialog -> {
                System.out.println("confirm text:"+ dialog.message());
                dialog.accept();
            });
            obj_page.click("#confirmBtn");
            obj_page.waitForTimeout(500);
            String confirmResult=obj_page.textContent("#confirmResult");
            if(confirmResult.equals("You clicked OK.")){
                System.out.println("PASS:confirm dialogue accept correctly.");

            }else {
                System.out.println("FAIL:confirm result mismatch. Actual:"+confirmResult);
            }
            obj_page.onceDialog(dialog -> {
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                dialog.accept("CCST Student");
            });
            obj_page.click("#promptBtn");
            obj_page.waitForTimeout(1000);
            String promptResult=obj_page.textContent("#promptResult");
            if(promptResult.equals("You entered: CCST Student")){
                System.out.println("PASS:prompt dialogue handled correctly");
            }else {
                System.out.println("FAIL:prompt result mismatch. Actual:"+promptResult);
            }
            obj_browser.close();

        }
    }
}
