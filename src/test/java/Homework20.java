import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class Homework20 extends BaseTest{
    @Test(dataProvider ="loginParameters", dataProviderClass = DataProviderClass.class)
    @Parameters({"qaUrl"})
    public void deletePlayList(String email, String password) throws InterruptedException{
        WebElement loginButton = getDriver().findElement(By.cssSelector("button[type='submit']"));

        provideEmail(email);
        providePassword(password);

        clickToElement(loginButton);

        Wait<WebDriver> wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".avatar")));

        loginButton = getDriver().findElement(By.cssSelector("button[type='submit']"));

       // Assert.assertEquals(getDriver().getCurrentUrl(), QA_URL);
        Assert.assertTrue(getDriver().findElement(By.cssSelector(".avatar")).isDisplayed());
        Assert.assertFalse(loginButton.isDisplayed());

        openPlaylist();
        clickDeletePlaylistButton();

        WebElement notificationMsg = getDriver().findElement(By.cssSelector("div.success.show"));
        Assert.assertTrue(notificationMsg.isDisplayed());
    }
}
