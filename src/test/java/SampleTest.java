import org.testng.Assert;
import org.testng.annotations.Test;

public class SampleTest extends BaseTest {

    @Test
    public void testPageTitle() {
        System.out.println(getDriver().getTitle());
        Assert.assertEquals(getDriver().getTitle(), "Complete Selenium Practice Hub");
    }

    @Test
    public void testPageSource() {
        System.out.println(getDriver().getCurrentUrl());
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://panjatan.netlify.app/");
    }
}