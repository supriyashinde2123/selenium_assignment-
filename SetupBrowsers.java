package PlayWright_Day1;

import com.microsoft.playwright.CLI;

import java.io.IOException;
import java.net.URISyntaxException;

public class SetupBrowsers {
    public static void main(String[] args) throws IOException,InterruptedException, URISyntaxException {
        CLI.main(new String[]{"install"});

    }

}
