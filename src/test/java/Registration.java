import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Registration  extends BaseTest{
    @Test(description = "Positive Test to validate User Registration Works.")
    public void registrationTest() {
        getDriver().findElement(By.cssSelector("#firstName")).sendKeys("Saddam");
        getDriver().findElement(By.cssSelector("#email")).sendKeys("saddam@pramana.com");


        WebElement country = getDriver().findElement(By.xpath("//select[@id='country']"));
        Select selectCountry = new Select(country);
        selectCountry.selectByVisibleText("India");

        getDriver().findElement(By.xpath("//input[@id='male']")).click();

        getDriver().findElement(By.cssSelector("#male")).click();

        getDriver().findElement(By.cssSelector("#coding")).click();
        getDriver().findElement(By.cssSelector("#testing")).click();
        getDriver().findElement(By.cssSelector("#design")).click();

        getDriver().findElement(By.cssSelector("#bio")).sendKeys("Software Developer");

        getDriver().findElement(By.xpath("//button[.='Register']")).click();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        Alert alert =  wait.until(ExpectedConditions.alertIsPresent());
        String actualMsg = alert.getText();
        alert.accept();

        Assert.assertEquals(actualMsg, "Registration form submitted successfully!");
    }
}
