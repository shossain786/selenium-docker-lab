import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {
    WebDriver driver;
    String appUrl = "https://panjatan.netlify.app/";
    boolean isRemote = false;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        isRemote  = System.getProperty("os.name").toLowerCase().startsWith("linux");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-gpu");

        driver = isRemote? new RemoteWebDriver(
                new URL("http://localhost:4444"),
                options
        ) : new ChromeDriver(options);
        driver.get(appUrl);
    }
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
