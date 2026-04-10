import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public abstract class BaseTest {
    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();
    String appUrl = "https://panjatan.netlify.app/";

    protected WebDriver getDriver() {
        return driverThread.get();
    }

    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browser) throws MalformedURLException {
        String remoteUrl = System.getProperty("remoteUrl", "http://localhost:4444");
        boolean isRemote = Boolean.getBoolean("remote");
        System.out.println("OS: " + System.getProperty("os.name") + ", isRemote: " + isRemote + ", remoteUrl: " + remoteUrl);

        System.out.println("Remote Mode: " + isRemote);
        WebDriver driver;

        if (browser.equalsIgnoreCase("firefox")) {

            System.out.println("Firefox Browser");
            FirefoxOptions options = new FirefoxOptions();

            if (isRemote) {
                driver = new RemoteWebDriver(new URL(remoteUrl + "/wd/hub"), options);
            } else {
                driver = new FirefoxDriver(options);
            }

        } else {
            System.out.println("Chrome Browser");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            options.addArguments("--start-maximized");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");

            if (isRemote) {
                driver = new RemoteWebDriver(new URL(remoteUrl + "/wd/hub"), options);
            } else {
                driver = new ChromeDriver(options);
            }
        }

        driverThread.set(driver);
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().get(appUrl);
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