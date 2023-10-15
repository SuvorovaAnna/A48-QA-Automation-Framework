import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pom.HomePage;
import pom.LoginPage;

public class Homework22 extends BaseTest{
    LoginPage loginPage;
    HomePage homePage;

    @Test(dataProvider ="loginParameters", dataProviderClass = DataProviderClass.class)
    public void renamePlaylist(String email, String password){

        loginPage = new LoginPage(getDriver());
        homePage = new HomePage(getDriver());

        loginPage.provideEmail(email)
                .providePassword(password)
                .clickLoginButton();

        loginPage.waitUntilLoginButtonDisplayed();

        homePage.playlist();
        homePage.setPlaylistInputField();

        Assert.assertTrue(homePage.isNotificationRenamePlaylistDisplayed());
    }
}