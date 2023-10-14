import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pom.DeletePlaylistPage;
import pom.LoginPage;

import java.time.Duration;

import static java.sql.DriverManager.getDriver;

public class Homework19 extends BaseTest{

    LoginPage loginPage;
    DeletePlaylistPage deletePlaylistPage;

    @Test(dataProvider ="loginParameters", dataProviderClass = DataProviderClass.class)
    @Parameters({"qaUrl"})
    public void deletePlayList(String email, String password) throws InterruptedException{
        WebElement loginButton = getDriver().findElement(By.cssSelector("button[type='submit']"));

        loginPage = new LoginPage(getDriver());
        deletePlaylistPage = new DeletePlaylistPage(getDriver());

        loginPage.provideEmail(email);
        loginPage.providePassword(password);
        loginPage.clickLoginButton();
        Thread.sleep(5000);

        deletePlaylistPage.openPlaylist();
        deletePlaylistPage.clickDeletePlaylistButton();

        WebElement notificationMsg = getDriver().findElement(By.cssSelector("div.success.show"));
        Assert.assertTrue(notificationMsg.isDisplayed());
    }
}

