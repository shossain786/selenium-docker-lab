import org.testng.Assert;
import org.testng.annotations.Test;

public class SampleTest extends BaseTest {

    @Test
    public void testPageTitle() {
        System.out.println(driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "Complete Selenium Practice Hub");
    }

    @Test
    public void testPageSource() {
        System.out.println(driver.getCurrentUrl());
        Assert.assertEquals(driver.getCurrentUrl(), "https://panjatan.netlify.app/");
    }
}