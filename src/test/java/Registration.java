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
        driver.findElement(By.cssSelector("#firstName")).sendKeys("Saddam");
        driver.findElement(By.cssSelector("#email")).sendKeys("saddam@pramana.com");


        WebElement country = driver.findElement(By.xpath("//select[@id='country']"));
        Select selectCountry = new Select(country);
        selectCountry.selectByVisibleText("India");

        driver.findElement(By.xpath("//input[@id='male']")).click();

        driver.findElement(By.cssSelector("#male")).click();

        driver.findElement(By.cssSelector("#coding")).click();
        driver.findElement(By.cssSelector("#testing")).click();
        driver.findElement(By.cssSelector("#design")).click();

        driver.findElement(By.cssSelector("#bio")).sendKeys("Software Developer");

        driver.findElement(By.xpath("//button[.='Register']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert =  wait.until(ExpectedConditions.alertIsPresent());
        String actualMsg = alert.getText();
        alert.accept();

        Assert.assertEquals(actualMsg, "Registration form submitted successfully!");
    }
}
