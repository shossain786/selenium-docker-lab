import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class BaseTest {
    WebDriver driver;
    String appUrl = "https://panjatan.netlify.app/";

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        String remoteUrl = System.getProperty("remoteUrl", "http://localhost:4444");
        boolean isRemote = Boolean.getBoolean("remote");
        System.out.println("OS: " + System.getProperty("os.name") + ", isRemote: " + isRemote + ", remoteUrl: " + remoteUrl);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-gpu");

        System.out.println("Remote Mode: " + isRemote);

        if (isRemote) {
            driver = new RemoteWebDriver(new URL(remoteUrl + "/wd/hub"), options);
        } else {
            driver = new ChromeDriver(options);
        }
        driver.get(appUrl);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}