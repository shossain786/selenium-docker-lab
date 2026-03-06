import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SampleTest extends BaseTest{
    @Test
    public void testPageTitle() {
        System.out.println(driver.getTitle());
        Assertions.assertEquals("Complete Selenium Practice Hub", driver.getTitle());
    }

    @Test
    public void testGoogleSource() {
        System.out.println(driver.getCurrentUrl());
        Assertions.assertTrue(driver.getCurrentUrl().equals("https://panjatan.netlify.app/"));
    }
}
