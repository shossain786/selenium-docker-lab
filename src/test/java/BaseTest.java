import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public abstract class BaseTest {
    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();
    String appUrl = "https://panjatan.netlify.app/";

    protected WebDriver getDriver() {
        return driverThread.get();
    }

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        String remoteUrl = System.getProperty("remoteUrl", "http://localhost:4444");
        boolean isRemote = Boolean.getBoolean("remote");
        System.out.println("OS: " + System.getProperty("os.name") + ", isRemote: " + isRemote + ", remoteUrl: " + remoteUrl);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        System.out.println("Remote Mode: " + isRemote);

        WebDriver driver;
        if (isRemote) {
            driver = new RemoteWebDriver(new URL(remoteUrl + "/wd/hub"), options);
        } else {
            driver = new ChromeDriver(options);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driverThread.set(driver);
        driver.get(appUrl);
    }

    @AfterMethod
    public void tearDown() {
        WebDriver driver = driverThread.get();
        if (driver != null) {
            driver.quit();
            driverThread.remove();
        }
    }
}