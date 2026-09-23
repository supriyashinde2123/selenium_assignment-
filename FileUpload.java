package PalyWright_Day4;

import com.microsoft.playwright.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import java.nio.file.Paths;

public class FileUpload {

    public static void main(String[] args) {

        try (Playwright playwright = Playwright.create()) {

            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );
            BrowserContext context = browser.newContext();
            Page page = context.newPage();


            page.navigate("file:///C:/Users/CCST/Downloads/SeleniumMaterial-20260915T091244Z-1-001/SeleniumMaterial/fileUpload.html");


            Locator fileInput = page.locator("#fileInput");
            Locator fileNameLabel = page.locator("#fileName");
            Locator uploadButton = page.locator("#uploadBtn");
            Locator resultText = page.locator("#result");


            String filePath = "C:/Users/CCST/Downloads/SeleniumMaterial-20260915T091244Z-1-001/SeleniumMaterial/actionClass_Menu.html";
            fileInput.setInputFiles(Paths.get(filePath));


            assertThat(fileNameLabel).containsText("actionClass_Menu.html");
            System.out.println("Pass: File selected. Display text: " + fileNameLabel.innerText());


            uploadButton.click();

            String expectedMessage = "File 'actionClass_Menu.html' uploaded successfully!";
            assertThat(resultText).hasText(expectedMessage);
            System.out.println("Pass: Upload confirmed. Message: " + resultText.innerText());


            browser.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
