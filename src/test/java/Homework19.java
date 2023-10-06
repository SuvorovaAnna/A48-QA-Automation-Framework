import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

import static java.sql.DriverManager.getDriver;

public class Homework19 extends BaseTest{
    @Test(dataProvider ="loginParameters", dataProviderClass = DataProviderClass.class)
    @Parameters({"qaUrl"})
    public void deletePlayList(String email, String password) throws InterruptedException{
        WebElement loginButton = getDriver().findElement(By.cssSelector("button[type='submit']"));

        provideEmail(email);
        providePassword(password);

        clickToElement(loginButton);
        Thread.sleep(5000);

        openPlaylist();
        clickDeletePlaylistButton();

        WebElement notificationMsg = getDriver().findElement(By.cssSelector("div.success.show"));
        Assert.assertTrue(notificationMsg.isDisplayed());
    }
}

