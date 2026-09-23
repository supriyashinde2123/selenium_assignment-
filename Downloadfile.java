package PalyWright_Day4;

import com.microsoft.playwright.*;
import java.net.URI; // Added for correct URI parsing
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Downloadfile {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser obj_browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500)
            );
            BrowserContext obj_context = obj_browser.newContext(
                    new Browser.NewContextOptions().setAcceptDownloads(true)
            );
            Page obj_page = obj_context.newPage();

            String filePath = Paths.get(URI.create("file:///C:/Users/CCST/Downloads/fileDownload.html")).toString();
            obj_page.navigate(filePath);


            try {
                Download obj_download = obj_page.waitForDownload(() -> {
                    obj_page.click("#downloadBtn");
                });

                System.out.println("Download started. Suggested filename: " + obj_download.suggestedFilename());


                Path saveDir = Paths.get("C:/Users/CCST/Downloads/DownloadedFiles");
                Files.createDirectories(saveDir);

                Path savepath = saveDir.resolve(obj_download.suggestedFilename());
                obj_download.saveAs(savepath);

                System.out.println("File saved to: " + savepath.toAbsolutePath());

                if (Files.exists(savepath)) {
                    System.out.println("PASS: Downloaded file exists at expected location.");
                } else {
                    System.out.println("FAIL: Downloaded file not found at expected location.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                obj_browser.close();
            }
        }
    }
}
