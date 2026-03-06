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
    String remoteUrl = "http://localhost:4444";

    @BeforeEach
    public void setUp() throws MalformedURLException {
        remoteUrl = System.getProperty("remoteUrl", "http://localhost:4444");
        isRemote  = System.getProperty("os.name").toLowerCase().contains("linux");
        System.out.println("OS: " + System.getProperty("os.name") + ", isRemote: " + isRemote + ", remoteUrl: " + remoteUrl);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-gpu");

        if (isRemote) {
            try {
                driver = new RemoteWebDriver(new URL(remoteUrl + "/wd/hub"), options);
            } catch (Exception e) {
                System.out.println("Remote WebDriver failed, falling back to local ChromeDriver: " + e.getMessage());
                driver = new ChromeDriver(options);
            }
        } else {
            driver = new ChromeDriver(options);
        }
        driver.get(appUrl);
    }
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
