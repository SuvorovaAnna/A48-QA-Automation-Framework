import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pom.LoginPage;

import java.time.Duration;

public class Homework21 extends BaseTest{

    LoginPage loginPage;
    public static final String NEW_PLAYLIST_NAME = "New Name";
    @Test(dataProvider ="loginParameters", dataProviderClass = DataProviderClass.class)
    public void renamePlaylist(String email, String password){
        WebElement loginButton = getDriver().findElement(By.cssSelector("button[type='submit']"));

        loginPage = new LoginPage(getDriver());

        loginPage.provideEmail(email);
        loginPage.providePassword(password);
        loginPage.clickLoginButton();

        wait.until(ExpectedConditions.visibilityOf(loginButton));

        WebElement playlist = getDriver().findElement(By.cssSelector(".playlist:nth-child(3)"));
        actions.doubleClick(playlist).perform();

        WebElement playlistInputField = getDriver().findElement(By.cssSelector("[name='name']"));
        playlistInputField.sendKeys(Keys.chord(Keys.CONTROL, "New Name", Keys.BACK_SPACE));

        playlistInputField.sendKeys(NEW_PLAYLIST_NAME);
        playlistInputField.sendKeys(Keys.ENTER);

        WebElement notificationRenamePlaylist = getDriver().findElement(By.cssSelector("div.success.show"));
        Assert.assertTrue(notificationRenamePlaylist.isDisplayed());


    }
}
