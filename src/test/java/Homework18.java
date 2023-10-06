import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Homework18 extends BaseTest {
    @Test
    public void playSong() throws InterruptedException {
        String url = "https://qa.koel.app/";

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(url);
        WebElement emailField = driver.findElement(By.cssSelector("input[type='email']"));
        WebElement passwordField = driver.findElement(By.cssSelector("input[type='password']"));
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

        emailField.click();
        emailField.sendKeys("anna.suvorova@testpro.io");

        passwordField.click();
        passwordField.sendKeys("te$t$tudent");

        loginButton.click();
        Thread.sleep(5000);

        WebElement playNextButton = driver.findElement(By.xpath("//i[@data-testid='play-next-btn']"));
        WebElement playSongButton = driver.findElement(By.xpath("//span[@data-testid='play-btn']"));
        playNextButton.click();
        playSongButton.click();
        Thread.sleep(5000);

        WebElement soundbarsButton = driver.findElement(By.cssSelector("div[class='bars']"));
        Assert.assertTrue(soundbarsButton.isDisplayed());
        driver.quit();
    }
}
